package cn.com.test.user.service;/**
 * Created by cuidianlong on 2018-06-07.
 */

import cn.com.test.user.entity.SysUser;
import cn.com.test.user.entity.SysUserExample;
import cn.com.test.user.service.base.BaseService;

import java.util.List;

/**
 * @Author mazhiqiang
 * @Description //TODO 
 * @Date 9:40 2019\3\14 0014
 * @Param 
 * @return 
 **/
public interface SysUserService extends BaseService<SysUser, SysUserExample> {
    /**
     * 分页查询
     * @param page  第几页
     * @param limit 页面大小
     * @return 分页结果
     */
    List<SysUser> pageList(Integer page, Integer limit);

    int updatapassword(SysUser sysUser);

    List<SysUser> getByUserName();

    SysUser getUserName(String username);
}
