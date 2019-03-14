package cn.com.test.user.service.impl;

import cn.com.test.user.dao.SysRolePermDao;
import cn.com.test.user.entity.SysPerm;
import cn.com.test.user.entity.SysRolePerm;
import cn.com.test.user.entity.SysRolePermExample;
import cn.com.test.user.service.SysRolePermService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author mazhiqiang
 * @Description //TODO
 * @Date 9:56 2019\3\14 0014
 * @Param
 * @return
 **/
@Service
public class SysRolePermServiceImpl implements SysRolePermService {
    @Resource
    private SysRolePermDao sysRolePermDao;

    @Override
    public List<SysRolePerm> selectAll(SysRolePermExample example) {
        return sysRolePermDao.selectByExample(example);
    }

    @Override
    public SysRolePerm selectById(Integer id) {
        return null;
    }

    @Override
    public int save(SysRolePerm entity) {
        return sysRolePermDao.insertSelective(entity);
    }

    @Override
    public int update(SysRolePerm entity, SysRolePermExample example) {
        return 0;
    }

    @Override
    public int updateById(SysRolePerm entity) {
        return 0;
    }

    @Override
    public int count(SysRolePermExample example) {
        return 0;
    }

    @Override
    public int delete(SysRolePermExample example) {
        return sysRolePermDao.deleteByExample(example);
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public List<SysPerm> selectByRoleId(Integer roleId) {
        return sysRolePermDao.selectByRoleId(roleId);
    }


    @Override
    public List<SysRolePerm> selectAllId() {
        return sysRolePermDao.selectAllId();
    }


}
