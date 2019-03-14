package cn.com.test.user.service;

import cn.com.test.user.entity.SysDept;
import cn.com.test.user.entity.SysDeptExample;
import cn.com.test.user.service.base.BaseService;

import java.util.List;

/**
 * @Author mazhiqiang
 * @Description //TODO
 * @Date 9:50 2019\3\14 0014
 * @Param
 * @return
 **/
public interface SysDeptService extends BaseService<SysDept, SysDeptExample> {
    List<SysDept> selectDeptsByParentId(int deptId);
}
