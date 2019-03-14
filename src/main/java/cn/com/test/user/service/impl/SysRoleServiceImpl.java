package cn.com.test.user.service.impl;

import cn.com.test.user.dao.SysRoleDao;
import cn.com.test.user.dao.SysRolePermDao;
import cn.com.test.user.entity.SysRole;
import cn.com.test.user.entity.SysRoleExample;
import cn.com.test.user.entity.SysRolePermExample;
import cn.com.test.user.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SysRoleServiceImpl
 * @Description TODO
 * @Author cuidianlong
 * @Date 2018-06-07 11:45
 * @Version 1.0
 **/
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleDao sysRoleDao;

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
    public List<SysRole> selectAll(SysRoleExample example) {
        return sysRoleDao.selectByExample(example);
    }

    /**
     * <p>Title: selectByExample</p>
     * <p>Description:按主键查询 (selectByPrimaryKey)</p>
     *
     * @param id
     * @return
     */
    @Override
    public SysRole selectById(Integer id) {
        return sysRoleDao.selectByPrimaryKey(id);
    }

    /**
     * <p>Title: save</p>
     * <p>Description:添加(insertSelective) </p>
     *
     * @param entity
     * @return
     */
    @Override
    public int save(SysRole entity) {
        return sysRoleDao.insertSelective(entity);
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
    public int update(SysRole entity, SysRoleExample example) {
        return sysRoleDao.updateByExampleSelective(entity, example);
    }

    /**
     * <p>Title: updateById</p>
     * <p>Description:按主键修改(updateByPrimaryKeySelective) </p>
     *
     * @param entity
     * @return
     */
    @Override
    public int updateById(SysRole entity) {
        return sysRoleDao.updateByPrimaryKeySelective(entity);
    }

    /**
     * <p>Title: count</p>
     * <p>Description: 动态查询总条数(countByExample)</p>
     *
     * @param example
     * @return
     */
    @Override
    public int count(SysRoleExample example) {
        return sysRoleDao.countByExample(example);
    }

    /**
     * <p>Title: deleteByExample</p>
     * <p>Description:动态删除(deleteByExample) </p>
     *
     * @param example
     * @return
     */
    @Override
    public int delete(SysRoleExample example) {
        return sysRoleDao.deleteByExample(example);
    }

    /**
     * <p>Title: deleteByPrimaryKey</p>
     * <p>Description:按主键删除(deleteByPrimaryKey) </p>
     *
     * @param id@return
     */
    @Override
    public int deleteById(Integer id) {
        // 删除角色 需要删除角色关联的权限表数据
        SysRolePermExample sysRolePermExample = new SysRolePermExample();
        SysRolePermExample.Criteria criteria = sysRolePermExample.createCriteria();
        criteria.andRole_idEqualTo(id);
        // 删除跟角色相关联的权限id
        sysRolePermDao.deleteByExample(sysRolePermExample);
        // 然后角色设置为不可用状态
       /* SysRole sysRole = new SysRole();
        sysRole.setRole_id(id);
        sysRole.setRole_state(0);*/
        /*return sysRoleDao.updateByPrimaryKeySelective(sysRole);*/
        return sysRoleDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<SysRole> pageList(Integer page, Integer limit) {
        if (page <= 0) {
            page = 1;
        }
        Integer start = (page - 1) * limit;
        return sysRoleDao.pageList(start, limit);
    }

    @Override
    public List<SysRole> selectRoleByUserId(Integer userId) {
        return sysRoleDao.selectRoleByUserId(userId);
    }

    /*查询角色权限*/
    @Override
    public Map<String, String> selectRoleAndPerm(HashMap<Object, Object> map) {

        return sysRoleDao.selectRoleAndPerm(map);
    }

    @Override
    public List<SysRole> selectRoleName() {
        return sysRoleDao.selectRoleName();
    }
}
