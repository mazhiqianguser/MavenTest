package cn.com.test.user.dao;

import cn.com.test.user.entity.SysRole;
import cn.com.test.user.entity.SysRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SysRoleDao {
    int countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    int deleteByPrimaryKey(Integer role_id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(Integer role_id);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    /**
     * 分页查询
     *
     * @param start    起始页
     * @param pageSize 每页大小
     * @return 分页结果
     */
    List<SysRole> pageList(@Param("start") Integer start, @Param("pageSize") Integer pageSize);

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