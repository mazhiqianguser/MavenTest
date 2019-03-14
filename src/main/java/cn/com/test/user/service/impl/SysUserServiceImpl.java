package cn.com.test.user.service.impl;

import cn.com.test.user.dao.SysRolePermDao;
import cn.com.test.user.dao.SysUserDao;
import cn.com.test.user.dao.SysUserDeptDao;
import cn.com.test.user.dao.SysUserRoleDao;
import cn.com.test.user.entity.*;
import cn.com.test.user.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author mazhiqiang
 * @Description //TODO
 * @Date 9:58 2019\3\14 0014
 * @Param
 * @return
 **/
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private SysUserDeptDao sysUserDeptDao;

    @Resource
    private SysUserRoleDao sysUserRoleDao;

    @Resource
    private SysRolePermDao sysRolePermDao;


    /**
     * <p>Title: selectByExample</p>
     * <p>Description:动态查询(selectByExample) </p>
     *
     * @param example
     * @return
     */
    @Override
    public List<SysUser> selectAll(SysUserExample example) {
        return sysUserDao.selectByExample(example);
    }

    /**
     * <p>Title: selectByExample</p>
     * <p>Description:按主键查询 (selectByPrimaryKey)</p>
     *
     * @param id
     * @return
     */
    @Override
    public SysUser selectById(Integer id) {
        return sysUserDao.selectByPrimaryKey(id);
    }

    /**
     * <p>Title: save</p>
     * <p>Description:添加(insertSelective) </p>
     *
     * @param entity
     * @return
     */
    @Override
    public int save(SysUser entity) {
        entity.setCreate_time(new Date());
        return sysUserDao.insertSelective(entity);
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
    public int update(SysUser entity, SysUserExample example) {
        entity.setUpdate_time(new Date());
        return sysUserDao.updateByExampleSelective(entity, example);
    }

    /**
     * <p>Title: updateById</p>
     * <p>Description:按主键修改(updateByPrimaryKeySelective) </p>
     *
     * @param entity
     * @return
     */
    @Override
    public int updateById(SysUser entity) {
        entity.setUpdate_time(new Date());
        return sysUserDao.updateByPrimaryKeySelective(entity);
    }

    /**
     * @return
     * @Author mazhiqiang
     * @Description //TODO
     * @Date 16:14 2018\9\27 0027
     * @Param
     **/

    @Override
    public int updatapassword(SysUser sysUser) {
        sysUser.setUpdate_time(new Date());
        return sysUserDao.updateByPrimaryKeySelective(sysUser);
    }

    /*查询用户*/
    @Override
    public List<SysUser> getByUserName() {
        return sysUserDao.getByUserName();
    }

    /*查询用户*/
    @Override
    public SysUser getUserName(String username) {
        return sysUserDao.getUserName(username);
    }

    /**
     * <p>Title: count</p>
     * <p>Description: 动态查询总条数(countByExample)</p>
     *
     * @param example
     * @return
     */
    @Override
    public int count(SysUserExample example) {
        return sysUserDao.countByExample(example);
    }

    /**
     * <p>Title: deleteByExample</p>
     * <p>Description:动态删除(deleteByExample) </p>
     *
     * @param example
     * @return
     */
    @Override
    public int delete(SysUserExample example) {
        return sysUserDao.deleteByExample(example);
    }

    /**
     * <p>Title: deleteByPrimaryKey</p>
     * <p>Description:按主键删除(deleteByPrimaryKey) </p>
     * 删除用户之前 先删除用户关联的角色 角色关联的权限 用户关联的部门信息
     *
     * @param id@return
     */
    @Override
    public int deleteById(Integer id) {
        // 构造用户角色SQL
        SysUserRoleExample userRoleExample = new SysUserRoleExample();
        SysUserRoleExample.Criteria userRoleExampleCriteria = userRoleExample.createCriteria();
        userRoleExampleCriteria.andUser_idEqualTo(id);
        // 构造角色权限SQL
        SysRolePermExample rolePermExample = new SysRolePermExample();
        SysRolePermExample.Criteria rolePermExampleCriteria = rolePermExample.createCriteria();
        // 构造用户部门SQL
        SysUserDeptExample sysUserDeptExample = new SysUserDeptExample();
        SysUserDeptExample.Criteria sysUserDeptExampleCriteria = sysUserDeptExample.createCriteria();

        sysUserRoleDao.selectByExample(userRoleExample).forEach(sysUserRole -> {
            // 删除用户当前角色的权限
            rolePermExampleCriteria.andRole_idEqualTo(sysUserRole.getRole_id());
            sysRolePermDao.deleteByExample(rolePermExample);

        });
        // 删除用户关联的部门记录
        sysUserDeptExampleCriteria.andUser_idEqualTo(id);
        sysUserDeptDao.deleteByExample(sysUserDeptExample);

        // 删除当前用户的角色
        sysUserRoleDao.deleteByExample(userRoleExample);
        // 1、删除用户
        // SysUser sysUser = new SysUser();
        // sysUser.setUser_id(id);
        // sysUser.setUpdate_time(new Date());
        // 状态改为0 模拟删除
        // sysUser.setStatus(0);
        return sysUserDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<SysUser> pageList(Integer page, Integer limit) {
        if (page <= 0) {
            page = 1;
        }
        Integer start = (page - 1) * limit;
        return sysUserDao.pageList(start, limit);
    }

}
