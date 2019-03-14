package cn.com.test.user.controller;

import cn.com.test.user.entity.*;
import cn.com.test.user.service.*;
import cn.com.test.user.utils.BaseResponse;
import cn.com.test.user.utils.MD5Util;
import cn.com.test.user.vcode.Captcha;
import cn.com.test.user.vcode.GifCaptcha;
import cn.com.test.user.vcode.VerifyCodeUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author mazhiqiang
 * @Description //TODO
 * @Date 9:43 2019\3\14 0014
 * @Param
 * @return
 **/
@RestController
@RequestMapping
public class LoginController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Resource
    SysUserService sysUserService;
    // @Autowired
    // private JedisClient jedisClient;
    @Autowired
    SysUserPermService sysUserPermService;
    @Resource
    SysUserRoleService sysUserRoleService;
    @Resource
    SysRolePermService sysRolePermService;
    @Resource
    SysPermService sysPermService;

//    @Resource
//    private SysAreaCodeService sysAreaCodeService;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping(value = "/login", produces = "application/json;charset=utf-8")
    public @ResponseBody
    BaseResponse login(@RequestParam String username, @RequestParam String password, @RequestParam("vcode") String vcode, HttpServletRequest request) {
        try {
            // 密码加密
            password = MD5Util.getPwd(password);
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                return ajaxFail("用户名或密码不能为空", FAIL_CODE);
            }
            SysUser user1 = sysUserService.getUserName(username);
            //List<SysUser> user1 = sysUserService.getByUserName();
            //for (SysUser item: user1) {
            if (user1 != null && !user1.equals("")) {
                //return ajaxFail("用户名不存在请重新输入", FAIL_CODE);
                if (!username.equals(user1.getUser_name())) {
                    return ajaxFail("用户名不一致，请重新输入", FAIL_CODE);
                }
                if (!password.equals(user1.getPassword())) {
                    return ajaxFail("密码错误，请重新输入", FAIL_CODE);
                }
                if (user1.getStatus() == 0) {
                    return ajaxFail("账户被锁定、请联系管理员", FAIL_CODE);
                }
            } else {
                return ajaxFail("用户名不存在请重新输入", FAIL_CODE);
            }

            //}

            if (!VerifyCodeUtils.verifyCode(request, vcode)) {
                return ajaxFail("验证码错误", FAIL_CODE);
            }
            SysUserExample example = new SysUserExample();
            SysUserExample.Criteria criteria = example.createCriteria();
            criteria.andUser_nameEqualTo(username);
            List<SysUser> sysUsers = sysUserService.selectAll(example);
            /*正则验证用户名，不能出现汉字*/
            if (!username.matches(USER_LOGIN) && sysUsers.size() <= 0) {
                return ajaxFail("用户名输入有误，请重新输入", FAIL_CODE);
            }

            /*if (!password.equals(sysUsers.get(0).getPassword())) {
                return ajaxFail("密码错误，请重新输入", FAIL_CODE);
            }*/
            /*if (sysUsers.get(0).getStatus() == 0) {
                return ajaxFail("账户被锁定、请联系管理员", FAIL_CODE);
            }*/

            /*把 区域  级别 放入session中  区域名称 区域编号 */
//             List<SysUserAreaCode> areaCodeuser = sysAreaCodeService.getUserArea();
//                    for (SysUserAreaCode itt :areaCodeuser) {
//                        if(username.equals(itt.getUser_name())){
//                            /*根据code 把 区域信息 放入session*/
//                            List<SysAreaCode> areaCode = sysAreaCodeService.getAreaCode();
//                            for (SysAreaCode item :areaCode) {
//                                if (item.getAreacode().equals(itt.getAreacode())) {
//                                    /*编号*/
//                                    request.getSession().setAttribute("areaCode",item.getAreacode());
//                                    /*名称*/
//                                    request.getSession().setAttribute("areaName",item.getAreaname());
//                                    /*级别*/
//                                    request.getSession().setAttribute("areaLevel",item.getLevel());
//                                }
//                            }
//                        }
//                    }


            /*用户新增  signInfo  companyName SPID 登陆 放入session 中*/
            for (SysUser item : sysUsers) {
                if (item.getUser_name().equals(username)) {
                    request.getSession().setAttribute("signInfo", item.getSignInfo());
                    request.getSession().setAttribute("companyName", item.getCompanyName());
                    request.getSession().setAttribute("SPID", item.getSPID());
                }
            }
            // jedisClient.setObject(username, sysUsers.get(0), 60 * 60 * 3);
            request.getSession().setAttribute(SESSION_USER, sysUsers.get(0));
            String ip = getIP(request);
            SysUser user = sysUsers.get(0);
            user.setLast_login(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            user.setIp(ip);
            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andUser_idEqualTo(user.getUser_id());
            System.out.println(sysUserExample);
            //sysUserService.update(user, sysUserExample);
            return ajaxSucc("", LOGIN_SUCCESS, SUCCESS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            return ajaxFail(LOGIN_FAIL, FAIL_CODE);
        }
    }

    /**
     * 获取验证码（Gif版本）
     *
     * @param response
     */
    /*@RequestMapping(value = "/getGifCode")*/
    @RequestMapping(value = "/getGifCode")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            // session获取
            HttpSession session = request.getSession();
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /* *
             * gif格式动画验证码
             * 宽，高，位数。
             */
            Captcha captcha = new GifCaptcha(146, 42, 4);
            //输出
            ServletOutputStream out = response.getOutputStream();
            captcha.out(out);
            out.flush();
            //存入会话session
            session.setAttribute(VerifyCodeUtils.V_CODE, captcha.text().toLowerCase());
        } catch (Exception e) {
            log.error("获取验证码异常：%s", e.getMessage());
        }
        /*try {
            // 生成验证码实例
            ChecoCodeUtil util = ChecoCodeUtil.Instance();
            request.getSession().setAttribute("sessionCode", util.getStr());
            ServletOutputStream stream = response.getOutputStream();
            ImageIO.write(util.getImage(), "JPEG", stream);
            stream.flush();
            stream.close();
        } catch (IOException e) {
            System.out.println("验证码生成失败");
        }*/
    }

    /**
     * 获取用户权限列表
     *
     * @param username
     * @return
     */
    @PostMapping(value = "/getUserPerm", produces = "application/json;charset=utf-8")
    public @ResponseBody
    BaseResponse getUserPerm(@RequestParam String username, HttpServletRequest request) {
        try {
            //根据用户名查询用户
            SysUser sysUser = getUser(request);

            Map<Integer, Integer> perm_idMap = new LinkedHashMap<>();

            //根据用户id查询权限id
            SysUserPermExample userPermExample = new SysUserPermExample();
            SysUserPermExample.Criteria userPermCriteria = userPermExample.createCriteria();
            userPermCriteria.andUser_idEqualTo(sysUser.getUser_id());
            List<SysUserPerm> userPerms = sysUserPermService.selectAll(userPermExample);
            if (userPerms.size() > 0 && userPerms != null) {
                userPerms.stream().forEach(userPerm -> {
                    perm_idMap.put(userPerm.getPerm_id(), userPerm.getPerm_id());
                });
            }
            //根据用户id查询角色id
            SysUserRoleExample userRoleExample = new SysUserRoleExample();
            SysUserRoleExample.Criteria userRoleCriteria = userRoleExample.createCriteria();
            userRoleCriteria.andUser_idEqualTo(sysUser.getUser_id());
            List<SysUserRole> userRoles = sysUserRoleService.selectAll(userRoleExample);
            if (userRoles.size() > 0 && userRoles != null) {
                //根据角色id查询权限id
                List<Integer> role_ids = new ArrayList<>();
                userRoles.stream().forEach(userRole -> {
                    System.out.println("角色id：" + userRole.getRole_id());
                    role_ids.add(userRole.getRole_id());
                });
                SysRolePermExample rolePermExample = new SysRolePermExample();
                SysRolePermExample.Criteria rolePermCriteria = rolePermExample.createCriteria();
                rolePermCriteria.andRole_idIn(role_ids);
                List<SysRolePerm> rolePerms = sysRolePermService.selectAll(rolePermExample);
                rolePerms.stream().forEach(rolePerm -> {
                    System.out.println("权限id：" + rolePerm.getPerm_id());
                    perm_idMap.put(rolePerm.getPerm_id(), rolePerm.getPerm_id());
                });
            }
            List<Integer> perm_ids = new ArrayList<>();
            //遍历perm_idMap集合
            perm_idMap.forEach((k, v) -> {
                perm_ids.add(k);
            });

            JSONArray array = new JSONArray();
            //根据perm_id查询所有相关权限
            if (perm_ids.size() > 0 && perm_ids != null) {
                SysPermExample permExample = new SysPermExample();
                SysPermExample.Criteria permCriteria = permExample.createCriteria();
                permCriteria.andPerm_idIn(perm_ids);
                List<SysPerm> perms = sysPermService.selectAll(permExample);
                perms.forEach(perm -> {
                    JSONObject permJson = new JSONObject();
                    permJson.put("id", perm.getPerm_id());
                    permJson.put("parentId", perm.getParent_id());
                    permJson.put("title", perm.getPerm_name());
                    permJson.put("icon", perm.getPerm_icon());
                    permJson.put("href", perm.getPerm_url());
                    permJson.put("sort", perm.getSort());
                    permJson.put("create_time", perm.getCreate_time().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                    permJson.put("perm_state", perm.getPerm_state());
                    if (!permJson.put("perm_state", perm.getPerm_state()).equals(2)) {
                        array.add(permJson);
                    }
                });
            }
            return ajaxSucc(array, FIND_SUCCESS, SUCCESS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            return ajaxFail(FIND_FAIL, FAIL_CODE);
        }

    }

    private String getIP(HttpServletRequest request) {
        String ip = null;
        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
