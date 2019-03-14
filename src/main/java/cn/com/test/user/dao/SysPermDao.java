package cn.com.test.user.dao;

import cn.com.test.user.entity.SysPerm;
import cn.com.test.user.entity.SysPermExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @Author mazhiqiang
 * @Description //TODO
 * @Date 9:40 2019\3\14 0014
 * @Param 
 * @return 
 **/
public interface SysPermDao {
    int countByExample(SysPermExample example);

    int deleteByExample(SysPermExample example);

    int deleteByPrimaryKey(Integer perm_id);

    int insert(SysPerm record);

    int insertSelective(SysPerm record);

    List<SysPerm> selectByExample(SysPermExample example);

    SysPerm selectByPrimaryKey(Integer perm_id);

    int updateByExampleSelective(@Param("record") SysPerm record, @Param("example") SysPermExample example);

    int updateByExample(@Param("record") SysPerm record, @Param("example") SysPermExample example);

    int updateByPrimaryKeySelective(SysPerm record);

    int updateByPrimaryKey(SysPerm record);

    SysPerm selectParentId(Integer[] perm_id);

    void deleteAll(SysPerm perm_id);

    List<SysPerm> selectPermName();
}