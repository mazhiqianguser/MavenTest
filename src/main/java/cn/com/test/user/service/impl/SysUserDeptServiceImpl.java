package cn.com.test.user.service.impl;

import cn.com.test.user.dao.SysDeptDao;
import cn.com.test.user.dao.SysUserDeptDao;
import cn.com.test.user.entity.SysDept;
import cn.com.test.user.entity.SysUserDept;
import cn.com.test.user.entity.SysUserDeptExample;
import cn.com.test.user.service.SysUserDeptService;
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
public class SysUserDeptServiceImpl implements SysUserDeptService {

    @Resource
    private SysUserDeptDao sysUserDeptDao;

    @Resource
    private SysDeptDao sysDeptDao;

    @Override
    public List<SysUserDept> selectAll(SysUserDeptExample example) {
        return sysUserDeptDao.selectByExample(example);
    }

    @Override
    public SysUserDept selectById(Integer id) {
        return sysUserDeptDao.selectByPrimaryKey(id);
    }

    @Override
    public int save(SysUserDept entity) {
        return sysUserDeptDao.insertSelective(entity);
    }

    @Override
    public int update(SysUserDept entity, SysUserDeptExample example) {
        return sysUserDeptDao.updateByExampleSelective(entity, example);
    }

    @Override
    public int updateById(SysUserDept entity) {
        return sysUserDeptDao.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int count(SysUserDeptExample example) {
        return sysUserDeptDao.countByExample(example);
    }

    @Override
    public int delete(SysUserDeptExample example) {
        return sysUserDeptDao.deleteByExample(example);
    }

    @Override
    public int deleteById(Integer id) {
        return sysUserDeptDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<SysUserDept> pageList(Integer deptId, Integer page, Integer limit) {
        if (page <= 0) {
            page = 1;
        }
        Integer start = (page - 1) * limit;
        return sysUserDeptDao.pageList(deptId, start, limit);
    }

    @Override
    public SysDept selectDeptByUserId(Integer userId) {
        SysUserDeptExample userDeptExample = new SysUserDeptExample();
        userDeptExample.createCriteria().andUser_idEqualTo(userId);
        // 获取用户的部门id
        List<SysUserDept> userDepts = sysUserDeptDao.selectByExample(userDeptExample);
        Integer deptId = null;
        if (!userDepts.isEmpty()) {
            deptId = userDepts.get(0).getDept_id();
        }
        if (deptId == null) {
            return null;
        } else {
            return sysDeptDao.selectByPrimaryKey(deptId);
        }
    }
}
