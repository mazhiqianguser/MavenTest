package cn.com.test.user.service;

import cn.com.test.user.entity.SysPerm;
import cn.com.test.user.entity.SysRolePerm;
import cn.com.test.user.entity.SysRolePermExample;
import cn.com.test.user.service.base.BaseService;

import java.util.List;

/**
 * @Author mazhiqiang
 * @Description //TODO
 * @Date 9:51 2019\3\14 0014
 * @Param
 * @return
 **/
public interface SysRolePermService extends BaseService<SysRolePerm, SysRolePermExample> {
    /**
     * 根据角色查询角色拥有的权限
     *
     * @param roleId 角色id
     * @return 返回所有权限树
     */
    List<SysPerm> selectByRoleId(Integer roleId);

    List<SysRolePerm> selectAllId();

}
