package cn.com.test.user.controller;

import cn.com.test.user.aop.Log;
import cn.com.test.user.entity.*;
import cn.com.test.user.service.*;
import cn.com.test.user.utils.BaseResponse;
import cn.com.test.user.utils.MD5Util;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangziqiang
 * <p>
 * 描述 : 用户处理
 * 时间 ：2018/6/12 15:07
 */
@RestController
@RequestMapping(value = "/user")
public class SysUserController extends BaseController {
/*
    @Resource
    private SysAreaCodeService sysAreaCodeService;*/

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysUserDeptService sysUserDeptService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Resource
    private SysUserPermService sysUserPermService;

    @Resource
    private OperateLogService operateLogService;
    /*log*/
    private static final Logger log = Logger.getLogger(SysUserController.class);

    /**
     * 查询用户列表
     * 1、部门编号为空 查询全部
     * 2、部门编号不为空 查询该部门下的用户
     *
     * @return 结果
     */
    @GetMapping(value = "/select", produces = "application/json;charset=utf-8")
    public String select(@RequestParam(required = false) Integer deptId, @RequestParam Integer page, @RequestParam Integer limit) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        SysUserExample userExample = new SysUserExample();
        List<Integer> userIds = new ArrayList<>();
        if (deptId == null) {
            List<SysUser> userList = sysUserService.pageList(page, limit);
            log.debug(userList);
            if (!userList.isEmpty()) {
                jsonObject.put("count", sysUserService.count(userExample));
                jsonObject.put("data", userList);
            } else {
                jsonObject.put("count", 0);
                jsonObject.put("data", null);
            }
        } else {//部门编号不为空 查询改部门下用户id
            SysUserDeptExample example = new SysUserDeptExample();
            SysUserDeptExample.Criteria criteria = example.createCriteria();
            criteria.andDept_idEqualTo(deptId);
            List<SysUserDept> userDepts = sysUserDeptService.pageList(deptId, page, limit);
            if (userDepts == null) {
                jsonObject.put("count", 0);
                jsonObject.put("data", null);
            } else {
                userDepts.forEach(sysUserDept ->
                        userIds.add(sysUserDept.getUser_id()));
                SysUserExample.Criteria userExampleCriteria = userExample.createCriteria();
                if (!userIds.isEmpty()) {
                    userExampleCriteria.andUser_idIn(userIds);
                    // 查询总记录数
                    jsonObject.put("count", sysUserDeptService.count(example));
                    jsonObject.put("data", sysUserService.selectAll(userExample));
                } else {
                    jsonObject.put("count", 0);
                    jsonObject.put("data", new ArrayList<>());
                }
            }
        }
        return JSON.toJSONString(jsonObject);
    }

    /**
     * 添加用户或者修改用户时候需要查询所有的角色信息
     *
     * @return 角色列表
     */
    @GetMapping(value = "/roles", produces = "application/json;charset=utf-8")
    public BaseResponse getRoles() {
        SysRoleExample example = new SysRoleExample();
        SysRoleExample.Criteria criteria = example.createCriteria();
        //排除角色为0（不可用）的记录
        criteria.andRole_stateNotEqualTo(0);
        List<SysRole> roles = sysRoleService.selectAll(example);

        if (!roles.isEmpty()) {
            return ajaxSucc(roles, FIND_SUCCESS, SUCCESS_CODE);
        } else if (roles.isEmpty()) {
            /*如果角色为空，默认角色名称为《管理员》*/
            SysRole sysRole = new SysRole();
            sysRole.setRole_id(1);
            sysRole.setRole_name(DEFAULT_ROLE_NAME);
            sysRole.setCreate_time(new Date());
            sysRole.setUpdate_time(new Date());
            sysRoleService.save(sysRole);
            roles.add(sysRole);
            return ajaxSucc(roles, "请先添加角色", SUCCESS_CODE);
        }
        return ajaxFail(FIND_FAIL, FAIL_CODE);
    }

    /**
     * 添加用户
     *
     * @param sysUser 用户信息
     * @return 添加结果
     */
    @PostMapping(value = "/insert/{roleId}/{deptId}", produces = "application/json;charset=utf-8")
    @Log(function_id = "2", operate_type = "1", operate_content = "添加用户信息")
    public BaseResponse insert(HttpServletRequest request, @PathVariable("roleId") Integer roleId,/*@PathVariable("selectName") String selectName,*/ @PathVariable("deptId") Integer deptId, @RequestBody SysUser sysUser) {
        SysUser system_user = (SysUser) request.getSession().getAttribute("system_user");
        String userName = sysUser.getUser_name();
        // 校验用户名是否唯一
        SysUserExample userExample = new SysUserExample();
        userExample.createCriteria().andUser_nameEqualTo(userName);
        List<SysUser> users = sysUserService.selectAll(userExample);
        if (users.size() > 0) {
            return ajaxFail("用户名称已经存在，请尝试其它用户名称", FAIL_CODE);
        }
        // 密码加密
        if (sysUser.getPassword() != null) {
            sysUser.setPassword(MD5Util.getPwd(sysUser.getPassword()));
        }
        /*手机号验证*/
        if (!sysUser.getPhone().matches(REGEX_MOBILE)) {
            return ajaxFail("手机号格式不对，请填写正确的格式", FAIL_CODE);
        }
        if (!sysUser.getEmail().matches(REGEX_EMAIL)) {
            return ajaxFail("邮箱格式不正确，请填写正确格式", FAIL_CODE);
        }
        int count = sysUserService.save(sysUser);
        // 添加成功后 用户角色关联增加数据
        if (count > 0) {
            /*count > 0 新增 区域 areaCode 表数据*/
//            if(selectName!=null && !selectName.equals("")){
//                /*查 code 编号 */
//                SysUserAreaCode userCode = new SysUserAreaCode();
//                List<SysAreaCode> areaCode = sysAreaCodeService.getAreaCode();
//                for (SysAreaCode item : areaCode) {
//                    if(item.getAreaname().equals(selectName)){
//                        userCode.setAreacode(item.getAreacode());
//                    }
//                }
//                userCode.setUser_name(sysUser.getUser_name());
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                userCode.setCreate_time(sdf.format(new Date()));
//                userCode.setCreate_by(system_user.getUser_name());
//                sysAreaCodeService.insertUserArea(userCode);
//            }
            Integer userId = sysUser.getUser_id();
            SysUserRole userRole = new SysUserRole();
            userRole.setUser_id(userId);
            userRole.setRole_id(roleId);
            int i = sysUserRoleService.save(userRole);
            SysUserDept userDept = new SysUserDept();
            userDept.setDept_id(deptId);
            userDept.setUser_id(userId);
            int j = sysUserDeptService.save(userDept);
            if (i > 0 && j > 0) {
                return ajaxSucc(null, SAVE_SUCCESS, SUCCESS_CODE);
            } else {
                return ajaxFail(SAVE_FAIL, FAIL_CODE);
            }
        } else {
            return ajaxFail(SAVE_FAIL, FAIL_CODE);
        }
    }

    /**
     * 删除用户  删除用户关联的角色 删除角色关联的权限
     *
     * @param userId 用户id
     * @return 结果
     */
    @DeleteMapping(value = "/delete/{userId}", produces = "application/json;charset=utf-8")
    @Log(function_id = "2", operate_type = "2", operate_content = "删除用户信息")
    public BaseResponse delete(@PathVariable("userId") Integer userId) {
        try {

            int count = 0;
            //         int   countAreacode = 0;
            if (userId != null && !userId.equals("")) {
//                SysUser sysUser = sysUserService.selectById(userId);
//                if (sysUser.getUser_name()!=null && !sysUser.getUser_name().equals("")){
//                    List<SysUserAreaCode> userArea = sysAreaCodeService.getUserArea();
//                    for (SysUserAreaCode item:userArea) {
//                        if(item.getUser_name().equals(sysUser.getUser_name())){
//                            countAreacode = sysAreaCodeService.deleteAreaCode(item.getUser_name());
                count = sysUserService.deleteById(userId);
//                        }
//                    }
//                }

            }
            /*&&countAreacode >0*/
            if (count > 0) {
                return ajaxSucc(null, DELETE_SUCCESS, SUCCESS_CODE);
            } else {
                return ajaxFail(DELETE_FAIL, FAIL_CODE);
            }
        } catch (Exception e) {
            log.debug(e);
            System.out.println(e.getMessage());
            return ajaxFail(DELETE_FAIL, FAIL_CODE);
        }
    }

    /**
     * 用于修改
     *
     * @param userId 用户id
     * @return 查询结果
     */
    @GetMapping(value = "/select/{userId}", produces = "application/json;charset=utf-8")

    public BaseResponse getById(@PathVariable("userId") Integer userId) {
        SysUser sysUser = sysUserService.selectById(userId);
        //MD5Util.convertMD5(MD5Util.convertMD5(sysUser.getPassword()));
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        sysUserRoleExample.createCriteria().andUser_idEqualTo(userId);
        List<SysUserRole> userRoles = sysUserRoleService.selectAll(sysUserRoleExample);
        SysDept sysDept = sysUserDeptService.selectDeptByUserId(userId);
        if (sysUser != null) {
            JSONObject object = new JSONObject();
            if (!userRoles.isEmpty()) {
                Integer roleId = userRoles.get(0).getRole_id();
                object.put("roleId", roleId);
                object.put("user", sysUser);
                if (sysDept != null) {
                    object.put("deptId", sysDept.getDept_id());
                    object.put("deptName", sysDept.getDept_name());
                } else {
                    object.put("deptId", "");
                    object.put("deptName", "");
                }
            } else {
                object.put("roleId", "");
                object.put("user", sysUser);
                if (sysDept == null) {
                    object.put("deptId", "");
                    object.put("deptName", "");
                } else {
                    object.put("deptId", sysDept.getDept_id());
                    object.put("deptName", sysDept.getDept_name());

                }
            }
            return ajaxSucc(object, FIND_SUCCESS, SUCCESS_CODE);
        } else {
            return ajaxFail(FIND_FAIL, FAIL_CODE);
        }
    }

    /**
     * 修改用户信息
     *
     * @param userId  用户id
     * @param sysUser 用户信息
     * @return 修改结果
     */
    @PutMapping(value = "/update/{userId}/{roleId}/{deptId}", produces = "application/json;charset=utf-8")
    @Log(function_id = "2", operate_type = "3", operate_content = "修改用户信息")
    public BaseResponse update(HttpServletRequest request, @PathVariable("userId") Integer userId, @PathVariable("roleId") Integer roleId, @PathVariable("deptId") Integer deptId, /*@PathVariable("areacode") String areacodename,*/ @RequestBody SysUser sysUser) {
        sysUser.setUser_id(userId);
        /*手机号验证*/
        if (!sysUser.getPhone().matches(REGEX_MOBILE)) {
            return ajaxFail("手机号格式不对，请填写正确的格式", FAIL_CODE);
        }
        /*邮箱验证*/
        if (!sysUser.getEmail().matches(REGEX_EMAIL)) {
            return ajaxFail("邮箱格式不对，请填写正确的格式", FAIL_CODE);
        }
        int count = sysUserService.updateById(sysUser);
        /*用户修改手机号同时修改日志表中的手机号作为记录*/
        /*TOperateLog tOperateLog = new TOperateLog();
        if(sysUser.getUser_id()!=null && !sysUser.getUser_id().equals("") && sysUser.getPhone()!=null && !sysUser.getPhone().equals("")){
            tOperateLog.setUser_id(sysUser.getUser_id());
            tOperateLog.setPhone(sysUser.getPhone());
            operateLogService.updateOperate(tOperateLog);
        }*/
        if (count > 0) {
            /*查询出来的 username */
//            List<SysUserAreaCode> userArea = sysAreaCodeService.getUserArea();
//            for (SysUserAreaCode item :userArea) {
//                if(sysUser.getUser_name().equals(item.getUser_name())){
//                    //*修改区域信息*//*
//                    SysUserAreaCode sysUserAreaCode = new SysUserAreaCode();
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                    sysUserAreaCode.setUser_name(sysUser.getUser_name());
//                    List<SysAreaCode> areaCodec = sysAreaCodeService.getAreaCode();
//                    for (SysAreaCode it :areaCodec) {
//                        if(it.getAreaname().equals(areacodename)){
//                            //*根据参数 areacodename  改变 areacode 省编号*//*
//                            sysUserAreaCode.setAreacode(it.getAreacode());
//                        }
//                    }
//                    //*修改人*//*
//                    SysUser system_user = (SysUser) request.getSession().getAttribute("system_user");
//                    sysUserAreaCode.setUpdate_by(system_user.getUser_name());
//                    //*修改时间*//*
//                    sysUserAreaCode.setUpdate_time(sdf.format(new Date()));
//                    sysAreaCodeService.updataAreaCode(sysUserAreaCode);
//                }
//            }

            // 修改用户的角色
            SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRole_id(roleId);
            sysUserRole.setUser_id(userId);
            sysUserRoleExample.createCriteria().andUser_idEqualTo(userId);
            sysUserRoleService.update(sysUserRole, sysUserRoleExample);
            SysUserDept sysUserDept = new SysUserDept();
            sysUserDept.setUser_id(userId);
            sysUserDept.setDept_id(deptId);
            // 修改用户的部门
            SysUserDeptExample sysUserDeptExample = new SysUserDeptExample();
            sysUserDeptExample.createCriteria().andUser_idEqualTo(userId).andDept_idEqualTo(deptId);
            List<SysUserDept> userDepts = sysUserDeptService.selectAll(sysUserDeptExample);
            // 先判断是否存在依赖关系
            if (userDepts.isEmpty()) {
                sysUserDeptService.save(sysUserDept);
                List<SysUserDept> userDepts1 = sysUserDeptService.selectAll(sysUserDeptExample);
                if (userDepts1.size() > 0) {
                    sysUserDeptExample = new SysUserDeptExample();
                    sysUserDeptExample.createCriteria().andUser_idEqualTo(userId);
                    sysUserDeptService.update(sysUserDept, sysUserDeptExample);
                }
            } else {
                sysUserDeptExample = new SysUserDeptExample();
                sysUserDeptExample.createCriteria().andUser_idEqualTo(userId);
                sysUserDeptService.update(sysUserDept, sysUserDeptExample);
            }
            return ajaxSucc(null, UPDATE_SUCCESS, SUCCESS_CODE);
        } else {
            return ajaxFail(UPDATE_FAIL, FAIL_CODE);
        }
    }

    /*禁用显示按钮 处理*/
    @PostMapping(value = "/disabled/{userId}", produces = "application/json;charset=utf-8")
    @Log(function_id = "1", operate_type = "5", operate_content = "隐藏/显示（用户信息）")
    public BaseResponse accountDisabled(@PathVariable("userId") Integer userId, @RequestBody SysUser sysUser) {
        sysUser.setUser_id(userId);
        int count = sysUserService.updateById(sysUser);
        if (count > 0) {
            return ajaxSucc(null, UPDATE_SUCCESS, SUCCESS_CODE);
        } else {
            return ajaxFail(UPDATE_FAIL, FAIL_CODE);
        }
    }

    /**
     * 查询用户权限（用户-权限）disabled
     *
     * @param userId 用户编号
     * @return 结果
     */
    @GetMapping(value = "/perm/{userId}", produces = "application/json;charset=utf-8")

    public BaseResponse perm(@PathVariable("userId") Integer userId) {
        SysUserPermExample userPermExample = new SysUserPermExample();
        userPermExample.createCriteria().andUser_idEqualTo(userId);
        List<SysPerm> userPerms = sysUserPermService.selectByUserId(userId);
        if (userPerms.isEmpty()) {
            return ajaxFail(FIND_FAIL, FAIL_CODE);
        }
        List<Object> list = new ArrayList<>();
        userPerms.forEach(sysPerm -> {
            JSONObject object = new JSONObject();
            object.put("id", sysPerm.getPerm_id());
            object.put("parentId", sysPerm.getParent_id());
            object.put("title", sysPerm.getPerm_name());
            if ("1".equals(sysPerm.getPerm_code())) {
                object.put("checked", true);
            } else {
                object.put("checked", false);
            }
            object.put("icon", "");
            object.put("href", "");
            list.add(object);
        });
        return ajaxSucc(list, FIND_SUCCESS, SUCCESS_CODE);
    }

    /**
     * 保存用户权限
     *
     * @param userId 用户id
     * @param perms  权限id 1,2,3
     * @return 保存结果
     */

    @PostMapping(value = "/perm/{userId}", produces = "application/json;charset=utf-8")
    @Log(function_id = "2", operate_type = "4", operate_content = "添加用户权限")
    public BaseResponse assignPerm(@PathVariable("userId") Integer userId, String perms) {
        // 首先清除以前的老权限
        SysUserPermExample userPermExample = new SysUserPermExample();
        userPermExample.createCriteria().andUser_idEqualTo(userId);
        sysUserPermService.delete(userPermExample);
        // 进行新的权限的保存
        String[] split = perms.split(",");
        AtomicInteger integer = new AtomicInteger(0);
        Arrays.asList(split).forEach(permId -> {
            SysUserPerm userPerm = new SysUserPerm();
            userPerm.setUser_id(userId);
            userPerm.setPerm_id(Integer.valueOf(permId));
            //保存
            int count = sysUserPermService.save(userPerm);
            if (count > 0) {
                integer.incrementAndGet();
            }
        });
        // 判断是否成功
        if (integer.get() > 0 || integer.get() == split.length) {
            return ajaxSucc(null, UPDATE_SUCCESS, SUCCESS_CODE);
        } else {
            return ajaxFail(UPDATE_FAIL, FAIL_CODE);
        }
    }

    /**
     * 用于个人中心展示
     *
     * @param username 用户id
     * @return 查询结果
     */
    @GetMapping(value = "/selectInfo/", produces = "application/json;charset=utf-8")
    public BaseResponse getByName(@RequestParam("username") String username) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUser_nameEqualTo(username);
        List<SysUser> sysUsers = sysUserService.selectAll(sysUserExample);
        SysUser sysUser = sysUsers.get(0);
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        sysUserRoleExample.createCriteria().andUser_idEqualTo(sysUser.getUser_id());
        List<SysUserRole> userRoles = sysUserRoleService.selectAll(sysUserRoleExample);
        if (sysUser != null) {
            JSONObject object = new JSONObject();
            if (!userRoles.isEmpty()) {
                Integer roleId = userRoles.get(0).getRole_id();
                object.put("roleId", roleId);
                object.put("user", sysUser);
            } else {
                object.put("roleId", "");
                object.put("user", sysUser);
            }
            return ajaxSucc(object, FIND_SUCCESS, SUCCESS_CODE);
        } else {
            return ajaxFail(FIND_FAIL, FAIL_CODE);
        }
    }


    /**
     * 个人中心修改用户信息
     *
     * @param roleId
     * @param sysUser
     * @return
     */
    @PutMapping(value = "/updateInfo/{roleId}", produces = "application/json;charset=utf-8")
    @Log(function_id = "2", operate_type = "6", operate_content = "个人信息修改")
    public BaseResponse updateInfo(@PathVariable("roleId") Integer roleId, @RequestBody SysUser sysUser) {
        /*验证手机号*/
        if (!sysUser.getPhone().matches(REGEX_MOBILE)) {
            return ajaxFail("手机号格式不正确，请填写正确的格式", FAIL_CODE);
        }
        /*验证邮箱*/

        if (!sysUser.getEmail().matches(REGEX_EMAIL)) {
            return ajaxFail("邮箱格式不正确，请填写正确的格式", FAIL_CODE);
        }
        int count = sysUserService.updateById(sysUser);
        /*用户修改手机号同时修改日志表中的手机号作为记录*/
        TOperateLog tOperateLog = new TOperateLog();
        tOperateLog.setUser_id(sysUser.getUser_id());
        tOperateLog.setPhone(sysUser.getPhone());
        operateLogService.updateOperate(tOperateLog);
        if (count > 0) {
            // 修改用户的角色
            SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRole_id(roleId);
            sysUserRole.setUser_id(sysUser.getUser_id());
            sysUserRoleExample.createCriteria().andUser_idEqualTo(sysUser.getUser_id());

            sysUserRoleService.update(sysUserRole, sysUserRoleExample);
            return ajaxSucc(null, UPDATE_SUCCESS, SUCCESS_CODE);
        } else {
            return ajaxFail(UPDATE_FAIL, FAIL_CODE);
        }
    }


    /**
     * 修改密码
     *
     * @param userName   登录用户名
     * @param oldPwd     旧密码
     * @param password   新密码
     * @param confirmPwd 确认密码
     * @param request
     * @return
     */
    @PostMapping(value = "/updatePwd", produces = "application/json;charset=utf-8")
    @Log(function_id = "2", operate_type = "7", operate_content = "修改密码")
    public BaseResponse updatePwd(@RequestParam("username") String userName, @RequestParam String oldPwd, @RequestParam String password, @RequestParam String confirmPwd, HttpServletRequest request) {
        try {
            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andUser_nameEqualTo(userName);
            List<SysUser> sysUsers = sysUserService.selectAll(sysUserExample);
            SysUser user = sysUsers.get(0);
            if (user.equals("") && user == null) {
                return ajaxFail("用户名不存在", FAIL_CODE);
            }
            if (oldPwd != null && !oldPwd.equals("")) {
                // 密码加密
                oldPwd = MD5Util.getPwd(oldPwd);
                if (!user.getPassword().equals(oldPwd)) {
                    return ajaxFail("旧密码不正确", FAIL_CODE);
                }
            } else {
                return ajaxFail("请填写旧密码", FAIL_CODE);
            }


            if (password != null && !password.equals("")) {
                if (password.length() < 6) {
                    return ajaxFail("密码不能少于6位", FAIL_CODE);
                }
            } else {
                return ajaxFail("请填写新密码", FAIL_CODE);
            }
            if (confirmPwd != null && !confirmPwd.equals("")) {
                if (!password.equals(confirmPwd)) {
                    return ajaxFail("两次输入的密码不一致，请重新输入", FAIL_CODE);
                }
            } else {
                return ajaxFail("请再次输入密码 ，请重新输入", FAIL_CODE);
            }
            // 密码加密
            user.setPassword(MD5Util.getPwd(password));
            sysUserService.updateById(user);
            return ajaxFail("密码修改成功,请重新登录", SUCCESS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            return ajaxFail(UPDATE_FAIL, FAIL_CODE);
        }
    }

    /**
     * @return
     * @Author mazhiqiang
     * @Description //TODO 根据id 重置 用户密码
     * @Date 15:52 2018\9\27 0027
     * @Param userId
     **/

    @GetMapping(
            value = {"/updatapassword/{userId}"},
            produces = {"application/json;charset=utf-8"}
    )
    @Log(function_id = "2", operate_type = "8", operate_content = "重置密码")
    public BaseResponse updatapassword(
            @PathVariable("userId") Integer userId,
            HttpServletRequest request) {
        try {
            if (userId != null) {
                SysUser sysUser = sysUserService.selectById(userId);
                sysUser.setPassword(MD5Util.getPwd(RESET_PASSWORD));
                sysUserService.updatapassword(sysUser);
                log.info("密码重置为初始密码,123456    重置成功");
                return ajaxFail("密码重置为初始密码", SUCCESS_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajaxFail(UPDATE_FAIL, FAIL_CODE);
    }


    /**
     * @Author mazhiqiang
     * @Description //TODO 查询code
     * @Date 10:50 2019\3\5 0005
     * @Param
     * @return
     **/
    /*@GetMapping(
            value = "/getAreaCode",
            produces = "application/json;charset=utf-8"
    )
    public String getAreaCode(){
        List<Object> list = null;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        try {
            list = new ArrayList<>();
            List<SysAreaCode> areaCodeList = sysAreaCodeService.getAreaCode();
            for (SysAreaCode item : areaCodeList) {
                String citycode = item.getAreacode();

                list.add(citycode);
            }
            jsonObject.put("code", "200");
            jsonObject.put("data", areaCodeList);
        } catch (Exception er) {
            er.printStackTrace();
        }
        return jsonObject.toString();
    }*/
}
