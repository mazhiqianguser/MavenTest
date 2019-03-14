package cn.com.test.user.service.impl;

import cn.com.test.user.dao.SysPermDao;
import cn.com.test.user.entity.SysPerm;
import cn.com.test.user.entity.SysPermExample;
import cn.com.test.user.service.SysPermService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author mazhiqiang
 * @Description //TODO
 * @Date 9:55 2019\3\14 0014
 * @Param
 * @return
 **/
@Service
public class SysPermServiceImpl implements SysPermService {
    @Resource
    SysPermDao sysPermDao;

    /**
     * <p>Title: selectByExample</p>
     * <p>Description:动态查询(selectByExample) </p>
     *
     * @param example
     * @return
     */
    @Override
    public List<SysPerm> selectAll(SysPermExample example) {
        return sysPermDao.selectByExample(example);
    }

    /**
     * <p>Title: selectByExample</p>
     * <p>Description:按主键查询 (selectByPrimaryKey)</p>
     *
     * @param id
     * @return
     */
    @Override
    public SysPerm selectById(Integer id) {
        return null;
    }

    /**
     * <p>Title: save</p>
     * <p>Description:添加(insertSelective) </p>
     *
     * @param entity
     * @return
     */
    @Transactional
    @Override
    public int save(SysPerm entity) {
        entity.setUpdate_time(new Date());
        return sysPermDao.insertSelective(entity);
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
    public int update(SysPerm entity, SysPermExample example) {
        entity.setUpdate_time(new Date());
        return sysPermDao.updateByExampleSelective(entity, example);
    }

    /**
     * <p>Title: updateById</p>
     * <p>Description:按主键修改(updateByPrimaryKeySelective) </p>
     *
     * @param entity
     * @return
     */
    @Override
    public int updateById(SysPerm entity) {
        entity.setUpdate_time(new Date());
        return sysPermDao.updateByPrimaryKeySelective(entity);
    }

    /**
     * <p>Title: count</p>
     * <p>Description: 动态查询总条数(countByExample)</p>
     *
     * @param example
     * @return
     */
    @Override
    public int count(SysPermExample example) {
        return 0;
    }

    /**
     * <p>Title: deleteByExample</p>
     * <p>Description:动态删除(deleteByExample) </p>
     *
     * @param example
     * @return
     */
    @Transactional
    @Override
    public int delete(SysPermExample example) {
        return sysPermDao.deleteByExample(example);
    }

    /**
     * <p>Title: deleteByPrimaryKey</p>
     * <p>Description:按主键删除(deleteByPrimaryKey) </p>
     *
     * @param id@return
     */
    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public SysPerm selectParentId(Integer[] perm_id) {

        return sysPermDao.selectParentId(perm_id);
    }

    @Override
    public void deleteAll(SysPerm perm_id) {
        sysPermDao.deleteAll(perm_id);
    }

    @Override
    public List<SysPerm> selectPermName() {
        return sysPermDao.selectPermName();
    }
}
