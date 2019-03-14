package cn.com.test.user.controller;

import cn.com.test.user.aop.Log;
import cn.com.test.user.entity.SysPerm;
import cn.com.test.user.entity.SysPermExample;
import cn.com.test.user.service.SysPermService;
import cn.com.test.user.utils.BaseResponse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * @Author mazhiqiang
 * @Description //TODO 菜单管理/权限管理
 * @Date 9:47 2019\3\14 0014
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/perm")
public class SysPermController extends BaseController {
    @Resource
    SysPermService sysPermService;

    private static final Logger log = Logger.getLogger(SysPermController.class);

    /**
     * 查询
     *
     * @return
     */
    @GetMapping(value = "/select", produces = "application/json;charset=utf-8")
    public @ResponseBody
    BaseResponse select() {
        SysPermExample example = new SysPermExample();
        SysPermExample.Criteria criteria = example.createCriteria();
        List<SysPerm> perms = sysPermService.selectAll(example);
        JSONArray array = new JSONArray();

        perms.stream().forEach(perm -> {
            JSONObject permJson = new JSONObject();
            permJson.put("id", perm.getPerm_id());
            permJson.put("parentId", perm.getParent_id());
            permJson.put("title", perm.getPerm_name());
            String icon = perm.getPerm_icon();
            if (icon.contains("&")) {
                icon = icon.substring(1, icon.length());
            }
            permJson.put("icon", icon);
            permJson.put("url", perm.getPerm_url());
            permJson.put("type", perm.getPerm_type());
            permJson.put("state", perm.getPerm_state());
            permJson.put("code", perm.getPerm_code());
            permJson.put("sort", perm.getSort());

            permJson.put("create_time", perm.getCreate_time().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            array.add(permJson);
        });
        return this.ajaxSucc(array, this.FIND_SUCCESS, "200");
    }

    /**
     * 按id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/select/{permId}", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String selectById(@PathVariable("permId") Integer id) {
        SysPerm sysPerm = (SysPerm) this.sysPermService.selectById(id);
        return "按id查询，id为：" + id;
    }

    /**
     * 添加
     *
     * @param sysPerm
     * @return
     */
    @PostMapping(value = "/insert", produces = "application/json;charset=utf-8")
    @Log(function_id = "4", operate_type = "1", operate_content = "添加菜单")
    public BaseResponse insert(@RequestBody SysPerm sysPerm) {
        try {
            /*新增验证*/
            /*名称*/
            if (sysPerm.getPerm_name() == null && sysPerm.getPerm_name().equals("")) {
                return this.ajaxFail(this.SAVE_FAIL, sysPerm.getPerm_name() + "null");
            }

            List<SysPerm> sysPerms = sysPermService.selectPermName();
            for (SysPerm item : sysPerms) {
                String perm_name = item.getPerm_name();
                if (sysPerm.getPerm_name().equals(perm_name)) {
                    return this.ajaxFail("菜单名称不能重复", FAIL_CODE);
                }
            }

            /*序号*/
            if (sysPerm.getSort() == null && sysPerm.getSort().equals("")) {
                return this.ajaxFail(this.SAVE_FAIL, sysPerm.getSort() + "null");
            }
            sysPerm.setCreate_time(new Date());
            int count = sysPermService.save(sysPerm);
            if (count > 0) {
                return this.ajaxSucc("", this.SAVE_SUCCESS, "200");
            } else {
                return this.ajaxFail(this.SAVE_FAIL, "300");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return this.ajaxFail(this.SAVE_FAIL, "300");
        }
    }

    /**
     * 删除
     *
     * @param permIds
     * @return
     */
    @DeleteMapping(value = "/delete/{permId}", produces = "application/json;charset=utf-8")
    @Log(function_id = "4", operate_type = "2", operate_content = "删除菜单")
    public BaseResponse delete(@PathVariable("permId") Integer[] permIds) {
        try {
            if (permIds == null) {
                return this.ajaxFail("请选择要删除的记录", FAIL_CODE);
            }

            List<Integer> ids = Arrays.asList(permIds);

            //判定当前菜单下是否含有子菜单，有子菜单不能删除，需先删除子菜单
            SysPermExample example = new SysPermExample();
            example.createCriteria().andParent_idIn(ids);
            List<SysPerm> sysPerms = sysPermService.selectAll(example);
            log.info("判定当前菜单下是否含有子菜单，有子菜单不能删除，需先删除子菜单" + "-------------------------");
            log.info(sysPerms);
            if (sysPerms.size() > 0 && sysPerms != null) {
                return ajaxFail("当前菜单下是否含有子菜单，有子菜单不能删除，需先删除子菜单！", FAIL_CODE);
            }
            SysPermExample permExample = new SysPermExample();
            SysPermExample.Criteria deptCriteria = permExample.createCriteria();
            deptCriteria.andPerm_idIn(ids);
            sysPermService.delete(permExample);
            this.sysPermService.delete(example);
            return this.ajaxSucc((Object) null, this.DELETE_SUCCESS, "200");
        } catch (Exception var4) {
            var4.printStackTrace();
            return this.ajaxFail(this.DELETE_FAIL, "300");
        }
    }


    /**
     * 修改
     *
     * @param sysPerm
     * @return
     */
    @PutMapping(value = "/update", produces = "application/json;charset=utf-8")
    @Log(function_id = "4", operate_type = "3", operate_content = "修改菜单信息")
    public @ResponseBody
    BaseResponse update(@RequestBody SysPerm sysPerm) {
        if (sysPerm.getSort() != null) {
            if (sysPerm.getSort() < 0) {
                return ajaxFail("序号不能为负数，请填写正确的格式", FAIL_CODE);
            }
        }

        /*修改*/
       /* if(StringUtils.isEmpty(sysPerm.getPerm_id()+"")) {
            return this.ajaxFail("权限id不能为空", FAIL_CODE);
        }*/

        if (sysPerm.getPerm_id() != null) {
             /*  List<SysPerm> sysPerms = sysPermService.selectPermName();
               for (SysPerm item:
                    sysPerms) {
                   if(item.getPerm_name().equals("系统管理")){
                       return this.ajaxFail("禁止禁用系统管理",FAIL_CODE);
                   }
               }*/
            this.sysPermService.updateById(sysPerm);
            return this.ajaxSucc((Object) null, this.UPDATE_SUCCESS, SUCCESS_CODE);
        }
        return this.ajaxFail("操作失败，请联系管理员", FAIL_CODE);
    }
}