package cn.com.test.user.controller;

import cn.com.test.user.aop.Log;
import cn.com.test.user.entity.*;
import cn.com.test.user.service.SysRolePermService;
import cn.com.test.user.service.SysRoleService;
import cn.com.test.user.service.SysUserRoleService;
import cn.com.test.user.utils.BaseResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author mazhiqiang
 * @Description //TODO 角色管理
 * @Date 9:48 2019\3\14 0014
 * @Param
 * @return
 **/
@RestController
@RequestMapping(value = "/role")
public class SysRoleController extends BaseController {
    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysRolePermService sysRolePermService;

    @Resource
    private SysUserRoleService sysUserRole;

    /**
     * 查询列表
     *
     * @return 分页列表
     */
    @GetMapping(value = "/select", produces = "application/json;charset=utf-8")
    public String select(@RequestParam Integer page, @RequestParam Integer limit) {
        SysRoleExample example = new SysRoleExample();
        SysRoleExample.Criteria criteria = example.createCriteria();
        criteria.andRole_stateEqualTo(1);
        //查询角色列表
        List<SysRole> roles = sysRoleService.pageList(page, limit);
        /*if(roles.size() < 0){
                for (SysRole item:roles) {
                    item.setRole_id(1);
                    item.setRole_name("管理员");
                    item.setRole_state(0);
                    item.setCreate_time(new Date());
                    item.setUpdate_time(new Date());
            }
        }
*/
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", sysRoleService.count(example));
        jsonObject.put("data", roles);
        return JSON.toJSONString(jsonObject);
    }

    /**
     * 新增角色
     *
     * @param sysRole 角色信息
     * @return 结果
     */
    @PostMapping(value = "/insert", produces = "application/json;charset=utf-8")
    @Log(function_id = "3", operate_type = "1", operate_content = "添加角色")
    public BaseResponse insert(@RequestBody SysRole sysRole) {
        String role_name = sysRole.getRole_name();
        if (role_name != null && !"".equals(role_name.trim())) {
            sysRole.setCreate_time(new Date());
            sysRole.setUpdate_time(new Date());
            List<SysRole> sysRoles = sysRoleService.selectRoleName();
            for (SysRole item : sysRoles) {
                if (item.getRole_name().equals(sysRole.getRole_name())) {
                    return ajaxFail("角色名重复", FAIL_CODE);
                }
            }
            if (sysRole.getRole_name().length() > 6) {
                return ajaxFail("角色名过长,请重新输入", FAIL_CODE);
            }
            int count = sysRoleService.save(sysRole);
            if (count > 0) {
                return ajaxSucc(null, SAVE_SUCCESS, SUCCESS_CODE);
            } else {
                return ajaxFail(SAVE_FAIL, FAIL_CODE);
            }
        }

        return ajaxFail("角色名不能为空", FAIL_CODE);
    }


    /**
     * 删除角色
     *
     * @param id 角色id
     * @return 结果
     */
    @DeleteMapping(value = "/delete/{id}", produces = "application/json;charset=utf-8")
    @Log(function_id = "3", operate_type = "2", operate_content = "删除角色")
    public BaseResponse delete(@PathVariable("id") Integer id) {

        /**
         * 用户分配角色，角色不能删除
         * */
        List<SysUserRole> role = sysUserRole.getRoleById(id);
        for (SysUserRole item :
                role) {
            if (item.getRole_id().equals(id)) {
                return ajaxFail("此角色赋予用户，不能删除！", FAIL_CODE);
            }
        }




        /*修改删除*/

        List<SysRolePerm> sysRolePerms = sysRolePermService.selectAllId();
        for (SysRolePerm item : sysRolePerms) {
            if (item.getRole_id().equals(id)) {
                return ajaxFail("不能删除拥有权限的角色！", FAIL_CODE);
            }
        }

        int count = sysRoleService.deleteById(id);
        if (count > 0) {
            return ajaxSucc(null, DELETE_SUCCESS, SUCCESS_CODE);
        } else {
            return ajaxFail(DELETE_FAIL, FAIL_CODE);
        }
    }

    /**
     * 修改角色信息
     *
     * @param roleId 角色id
     * @return 结果
     */
    @PutMapping(value = "/update/{roleId}", produces = "application/json;charset=utf-8")
    @Log(function_id = "3", operate_type = "3", operate_content = "修改角色信息")
    public BaseResponse update(@PathVariable("roleId") Integer roleId, @RequestBody SysRole sysRole) {
        sysRole.setRole_id(roleId);
        sysRole.setUpdate_time(new Date());
        int count = sysRoleService.updateById(sysRole);
        if (count > 0) {
            return ajaxSucc(null, UPDATE_SUCCESS, SUCCESS_CODE);
        } else {
            return ajaxFail(UPDATE_FAIL, FAIL_CODE);
        }
    }

    /**
     * 根据角色id查询该角色拥有的权限树
     *
     * @param roleId 角色id
     * @return 返回权限树json
     */
    @GetMapping(value = "/select/{roleId}", produces = "application/json;charset=utf-8")
    public BaseResponse selectByRoleId(@PathVariable("roleId") Integer roleId) {
        List<SysPerm> sysPerms = sysRolePermService.selectByRoleId(roleId);
        if (sysPerms.isEmpty()) {
            return ajaxFail(FIND_FAIL, FAIL_CODE);
        }
        List<Object> list = new ArrayList<>();
        sysPerms.forEach(sysPerm -> {
            JSONObject object = new JSONObject();
            object.put("parentId", sysPerm.getParent_id());
            object.put("id", sysPerm.getPerm_id());
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
     * 给角色分配权限
     *
     * @param roleId 角色id
     * @param perms  权限id字符串
     * @return 结果
     */


    @PostMapping(value = "/perm", produces = "application/json;charset=utf-8")
    @Log(function_id = "3", operate_type = "4", operate_content = "添加角色权限")
    public BaseResponse assignPerm(@RequestParam Integer roleId, @RequestParam String perms) {
        String[] split = perms.split(",");
        AtomicInteger integer = new AtomicInteger(0);
        // 分配权限之前先删除以前的老权限
        SysRolePermExample sysRolePermExample = new SysRolePermExample();
        SysRolePermExample.Criteria criteria = sysRolePermExample.createCriteria();
        criteria.andRole_idEqualTo(roleId);
        sysRolePermService.delete(sysRolePermExample);
        //将用户的权限存入数据库中
        Arrays.asList(split).forEach(str -> {
            SysRolePerm sysRolePerm = new SysRolePerm();
            sysRolePerm.setRole_id(roleId);
            sysRolePerm.setPerm_id(Integer.valueOf(str));
            int count = sysRolePermService.save(sysRolePerm);
            if (count > 0) {
                integer.incrementAndGet();
            }
        });
        if (integer.get() > 0) {
            return ajaxSucc(null, UPDATE_SUCCESS, SUCCESS_CODE);
        } else {
            return ajaxFail(UPDATE_FAIL, FAIL_CODE);
        }
    }
}
