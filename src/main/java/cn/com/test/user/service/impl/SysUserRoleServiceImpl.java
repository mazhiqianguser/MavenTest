package cn.com.test.user.service.impl;

import cn.com.test.user.dao.SysUserRoleDao;
import cn.com.test.user.entity.SysUserRole;
import cn.com.test.user.entity.SysUserRoleExample;
import cn.com.test.user.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author mazhiqiang
 * @Description //TODO 用户角色关系处理
 * @Date 9:58 2019\3\14 0014
 * @Param
 * @return
 **/
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Resource
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public List<SysUserRole> selectAll(SysUserRoleExample example) {
        return sysUserRoleDao.selectByExample(example);
    }

    @Override
    public SysUserRole selectById(Integer id) {
        return sysUserRoleDao.selectByPrimaryKey(id);
    }

    @Override
    public int save(SysUserRole entity) {
        return sysUserRoleDao.insertSelective(entity);
    }

    @Override
    public int update(SysUserRole entity, SysUserRoleExample example) {
        return sysUserRoleDao.updateByExampleSelective(entity, example);
    }

    @Override
    public int updateById(SysUserRole entity) {
        return sysUserRoleDao.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int count(SysUserRoleExample example) {
        return sysUserRoleDao.countByExample(example);
    }

    @Override
    public int delete(SysUserRoleExample example) {
        return sysUserRoleDao.deleteByExample(example);
    }

    @Override
    public int deleteById(Integer id) {
        return sysUserRoleDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<SysUserRole> getRoleById(Integer role_id) {
        return sysUserRoleDao.getRoleById(role_id);
    }
}
