package cn.com.test.user.dao;

import cn.com.test.user.entity.SysUserDept;
import cn.com.test.user.entity.SysUserDeptExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserDeptDao {
    int countByExample(SysUserDeptExample example);

    int deleteByExample(SysUserDeptExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUserDept record);

    int insertSelective(SysUserDept record);

    List<SysUserDept> selectByExample(SysUserDeptExample example);

    SysUserDept selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysUserDept record, @Param("example") SysUserDeptExample example);

    int updateByExample(@Param("record") SysUserDept record, @Param("example") SysUserDeptExample example);

    int updateByPrimaryKeySelective(SysUserDept record);

    int updateByPrimaryKey(SysUserDept record);

    /**
     * 分页查询部门下的用户id
     *
     * @param deptId   部门id
     * @param start    起始行
     * @param pageSize 每页大小
     * @return 结果
     */
    List<SysUserDept> pageList(@Param("deptId") Integer deptId, @Param("start") Integer start, @Param("pageSize") Integer pageSize);
}