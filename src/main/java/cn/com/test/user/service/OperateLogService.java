package cn.com.test.user.service;


import cn.com.test.user.entity.TOperateLog;
import cn.com.test.user.page.PageHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 操作日志service
 *
 * @author mazhiqiang
 * 2017-06-05
 */
public interface OperateLogService {
    /**
     * 添加
     *
     * @param operateLog
     * @return
     */
    int insert(TOperateLog operateLog) throws Exception;

    /**
     * 按条件(分页)查询
     *
     * @param operateLog
     * @return
     */
    // List<TOperateLog> queryLogs(PageHelper<TOperateLog> pageHelper) throws Exception;
    List<TOperateLog> queryLogs(TOperateLog operateLog, Integer page, Integer limit) throws Exception;

    /**
     * 查询记录数量
     *
     * @param operateLog
     * @return
     * @throws Exception
     */
    int getTotal(TOperateLog operateLog) throws Exception;

    /**
     * 添加日志
     */
    int addTOperateLog(TOperateLog tOperateLog) throws Exception;

    /**
     * 添加日志
     */
    int addTOperateLog(Integer functionId, Integer operate_type, String Content, HttpServletRequest request) throws Exception;

    List<TOperateLog> getQueryAllLogs(PageHelper pageHelper) throws Exception;

    void updateOperate(TOperateLog tOperateLog);


    /**
     * 根据funanme查询funcid
     *
     */

    //  public Integer queryFunctionId(String funcname);


    /**
     * 查询操作日志中的功能菜单
     */
    //List<Permission> queryOperateLogFunctions();


}
