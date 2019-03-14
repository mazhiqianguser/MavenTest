package cn.com.test.user.service;

import cn.com.test.user.entity.SysPerm;
import cn.com.test.user.entity.SysUserPerm;
import cn.com.test.user.entity.SysUserPermExample;
import cn.com.test.user.service.base.BaseService;

import java.util.List;

/**
 * @Author mazhiqiang
 * @Description //TODO
 * @Date 9:40 2019\3\14 0014
 * @Param
 * @return
 **/
public interface SysUserPermService extends BaseService<SysUserPerm, SysUserPermExample> {
    /**
     * 根据用户id 查询用户拥有的权限
     *
     * @param userId 用户id
     * @return 用户权限
     */
    List<SysPerm> selectByUserId(Integer userId);
}
