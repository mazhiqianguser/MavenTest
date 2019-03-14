package cn.com.test.user.dao;

import cn.com.test.user.entity.SysUser;
import cn.com.test.user.entity.SysUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserDao {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(Integer user_id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Integer user_id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 分页查询用户信息
     *
     * @param start    起始行
     * @param pageSize 页面大小
     * @return 结果
     */
    List<SysUser> pageList(@Param("start") Integer start, @Param("pageSize") Integer pageSize);

    List<SysUser> getByUserName();

    SysUser getUserName(String username);
}