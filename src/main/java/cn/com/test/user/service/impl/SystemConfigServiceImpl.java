package cn.com.test.user.service.impl;

import cn.com.test.user.dao.SystemConfigDao;
import cn.com.test.user.entity.SystemConfig;
import cn.com.test.user.entity.SystemConfigExample;
import cn.com.test.user.service.SystemConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SystemConfigServiceImpl
 * @Description TODO
 * @Author cuidianlong
 * @Date 2018-07-04 15:57
 * @Version 1.0
 **/
@Service
public class SystemConfigServiceImpl implements SystemConfigService {
    @Resource
    SystemConfigDao systemConfigDao;

    @Override
    public List<SystemConfig> selectAll(SystemConfigExample example) {
        return systemConfigDao.selectByExample(example);
    }

    @Override
    public SystemConfig selectById(Integer id) {
        return systemConfigDao.selectByPrimaryKey(id);
    }

    @Override
    public int save(SystemConfig entity) {
        return systemConfigDao.insertSelective(entity);
    }

    @Override
    public int update(SystemConfig entity, SystemConfigExample example) {
        return systemConfigDao.updateByExampleSelective(entity, example);
    }

    @Override
    public int updateById(SystemConfig entity) {
        return systemConfigDao.updateByPrimaryKey(entity);
    }

    @Override
    public int count(SystemConfigExample example) {
        return systemConfigDao.countByExample(example);
    }

    @Override
    public int delete(SystemConfigExample example) {
        return systemConfigDao.deleteByExample(example);
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }
}
