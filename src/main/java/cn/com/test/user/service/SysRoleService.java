package cn.com.test.user.service;

import cn.com.test.user.entity.SysRole;
import cn.com.test.user.entity.SysRoleExample;
import cn.com.test.user.service.base.BaseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author mazhiqiang
 * @Description //TODO
 * @Date 9:52 2019\3\14 0014
 * @Param
 * @return
 **/
public interface SysRoleService extends BaseService<SysRole, SysRoleExample> {
    /**
     * 分页查询
     *
     * @param page  第几页
     * @param limit 每页大小
     * @return 分页结果
     */
    List<SysRole> pageList(Integer page, Integer limit);

    /**
     * 根据用户id查询用户所有的角色信息
     *
     * @param userId 用户ID
     * @return 结果集合
     */
    List<SysRole> selectRoleByUserId(Integer userId);

    Map<String, String> selectRoleAndPerm(HashMap<Object, Object> map);

    List<SysRole> selectRoleName();

}
