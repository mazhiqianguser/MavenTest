package cn.com.test.user.dao;

import cn.com.test.user.entity.SysPerm;
import cn.com.test.user.entity.SysRolePerm;
import cn.com.test.user.entity.SysRolePermExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRolePermDao {
    int countByExample(SysRolePermExample example);

    int deleteByExample(SysRolePermExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysRolePerm record);

    int insertSelective(SysRolePerm record);

    List<SysRolePerm> selectByExample(SysRolePermExample example);

    SysRolePerm selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysRolePerm record, @Param("example") SysRolePermExample example);

    int updateByExample(@Param("record") SysRolePerm record, @Param("example") SysRolePermExample example);

    int updateByPrimaryKeySelective(SysRolePerm record);

    int updateByPrimaryKey(SysRolePerm record);

    /**
     * 根据角色查询角色树
     *
     * @param roleId 角色id
     * @return 权限树
     */
    List<SysPerm> selectByRoleId(Integer roleId);

    List<SysRolePerm> selectAllId();
}