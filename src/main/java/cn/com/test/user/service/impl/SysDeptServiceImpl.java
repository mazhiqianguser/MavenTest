package cn.com.test.user.service.impl;

import cn.com.test.user.dao.SysDeptDao;
import cn.com.test.user.entity.SysDept;
import cn.com.test.user.entity.SysDeptExample;
import cn.com.test.user.service.SysDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author mazhiqiang
 * @Description //TODO
 * @Date 9:54 2019\3\14 0014
 * @Param
 * @return
 **/
@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Resource
    SysDeptDao sysDeptDao;

    /**
     * <p>Title: selectByExample</p>
     * <p>Description:动态查询(selectByExample) </p>
     *
     * @param example
     * @return
     */
    @Override
    public List<SysDept> selectAll(SysDeptExample example) {
        return sysDeptDao.selectByExample(example);
    }

    /**
     * <p>Title: selectByExample</p>
     * <p>Description:按主键查询 (selectByPrimaryKey)</p>
     *
     * @param id
     * @return
     */
    @Override
    public SysDept selectById(Integer id) {
        return null;
    }

    /**
     * <p>Title: save</p>
     * <p>Description:添加(insertSelective) </p>
     *
     * @param entity
     * @return
     */
    @Override
    public int save(SysDept entity) {
        return sysDeptDao.insertSelective(entity);
    }

    /**
     * <p>Title: update</p>
     * <p>Description: 动态修改(updateByExampleSelective)</p>
     *
     * @param entity
     * @param example
     * @return
     */
    @Override
    public int update(SysDept entity, SysDeptExample example) {
        return 0;
    }

    /**
     * <p>Title: updateById</p>
     * <p>Description:按主键修改(updateByPrimaryKeySelective) </p>
     *
     * @param entity
     * @return
     */
    @Override
    public int updateById(SysDept entity) {
        return sysDeptDao.updateByPrimaryKeySelective(entity);
    }

    /**
     * <p>Title: count</p>
     * <p>Description: 动态查询总条数(countByExample)</p>
     *
     * @param example
     * @return
     */
    @Override
    public int count(SysDeptExample example) {
        return 0;
    }

    /**
     * <p>Title: deleteByExample</p>
     * <p>Description:动态删除(deleteByExample) </p>
     *
     * @param example
     * @return
     */
    @Override
    public int delete(SysDeptExample example) {
        return sysDeptDao.deleteByExample(example);
    }

    /**
     * <p>Title: deleteByPrimaryKey</p>
     * <p>Description:按主键删除(deleteByPrimaryKey) </p>
     *
     * @param id@return
     */
    @Override
    public int deleteById(Integer id) {
        return sysDeptDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<SysDept> selectDeptsByParentId(int deptId) {
        return sysDeptDao.selectDeptsByParentId(deptId);
    }
}
