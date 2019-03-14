package cn.com.test.user.service.impl;

import cn.com.test.user.dao.SysUserPermDao;
import cn.com.test.user.entity.SysPerm;
import cn.com.test.user.entity.SysUserPerm;
import cn.com.test.user.entity.SysUserPermExample;
import cn.com.test.user.service.SysUserPermService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author mazhiqiang
 * @Description //TODO
 * @Date 9:57 2019\3\14 0014
 * @Param
 * @return
 **/
@Service
public class SysUserPermServiceImpl implements SysUserPermService {
    @Resource
    private SysUserPermDao sysUserPermDao;

    @Override
    public List<SysUserPerm> selectAll(SysUserPermExample example) {
        return sysUserPermDao.selectByExample(example);
    }

    @Override
    public SysUserPerm selectById(Integer id) {
        return sysUserPermDao.selectByPrimaryKey(id);
    }

    @Override
    public int save(SysUserPerm entity) {
        return sysUserPermDao.insertSelective(entity);
    }

    @Override
    public int update(SysUserPerm entity, SysUserPermExample example) {
        return sysUserPermDao.updateByExampleSelective(entity, example);
    }

    @Override
    public int updateById(SysUserPerm entity) {
        return sysUserPermDao.updateByPrimaryKey(entity);
    }

    @Override
    public int count(SysUserPermExample example) {
        return sysUserPermDao.countByExample(example);
    }

    @Override
    public int delete(SysUserPermExample example) {
        return sysUserPermDao.deleteByExample(example);
    }

    @Override
    public int deleteById(Integer id) {
        return sysUserPermDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<SysPerm> selectByUserId(Integer userId) {
        return sysUserPermDao.selectByUserId(userId);
    }
}
