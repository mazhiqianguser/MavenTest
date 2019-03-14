package cn.com.test.user.dao;

import cn.com.test.user.entity.SysPerm;
import cn.com.test.user.entity.SysUserPerm;
import cn.com.test.user.entity.SysUserPermExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserPermDao {
    int countByExample(SysUserPermExample example);

    int deleteByExample(SysUserPermExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUserPerm record);

    int insertSelective(SysUserPerm record);

    List<SysUserPerm> selectByExample(SysUserPermExample example);

    SysUserPerm selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysUserPerm record, @Param("example") SysUserPermExample example);

    int updateByExample(@Param("record") SysUserPerm record, @Param("example") SysUserPermExample example);

    int updateByPrimaryKeySelective(SysUserPerm record);

    int updateByPrimaryKey(SysUserPerm record);

    /**
     * 根据用户id 查询用户的权限
     *
     * @param userId 用户id
     * @return 结果
     */
    List<SysPerm> selectByUserId(Integer userId);
}