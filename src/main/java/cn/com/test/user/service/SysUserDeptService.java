package cn.com.test.user.service;

import cn.com.test.user.entity.SysDept;
import cn.com.test.user.entity.SysUserDept;
import cn.com.test.user.entity.SysUserDeptExample;
import cn.com.test.user.service.base.BaseService;

import java.util.List;

/**
 * @Author mazhiqiang
 * @Description //TODO
 * @Date 9:52 2019\3\14 0014
 * @Param
 * @return
 **/
public interface SysUserDeptService extends BaseService<SysUserDept, SysUserDeptExample> {
    /**
     * 用户部门信息 进行分页
     *
     * @param deptId 部门id
     * @param page   第几页
     * @param limit  页面大小
     * @return 分页结果
     */
    List<SysUserDept> pageList(Integer deptId, Integer page, Integer limit);

    SysDept selectDeptByUserId(Integer userId);
}
