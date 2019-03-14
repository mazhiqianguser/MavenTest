package cn.com.test.user.service;

import cn.com.test.user.entity.SysUserRole;
import cn.com.test.user.entity.SysUserRoleExample;
import cn.com.test.user.service.base.BaseService;

import java.util.List;

/**
 * @Author mazhiqiang
 * @Description //TODO
 * @Date 9:53 2019\3\14 0014
 * @Param
 * @return
 **/
public interface SysUserRoleService extends BaseService<SysUserRole, SysUserRoleExample> {
    List<SysUserRole> getRoleById(Integer id);
}
