package cn.com.test.user.service;

import cn.com.test.user.entity.SysPerm;
import cn.com.test.user.entity.SysPermExample;
import cn.com.test.user.service.base.BaseService;

import java.util.List;


/**
 * @Author mazhiqiang
 * @Description //TODO
 * @Date 9:51 2019\3\14 0014
 * @Param
 * @return
 **/
public interface SysPermService extends BaseService<SysPerm, SysPermExample> {


    SysPerm selectParentId(Integer[] perm_id);

    void deleteAll(SysPerm perm_id);

    List<SysPerm> selectPermName();
}
