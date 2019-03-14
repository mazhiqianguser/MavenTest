package cn.com.test.user.dao;

import cn.com.test.user.entity.TOperateLog;
import cn.com.test.user.page.PageHelper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作日志dao
 *
 * @author cuidianlong
 * 2017-06-05
 */
public interface TOperateLogDao {

    /**
     * 添加
     *
     * @param operateLog
     * @return
     */
    int insert(TOperateLog operateLog) throws Exception;

    /**
     * 按条件(分页)查询i
     *
     * @param operateLog
     * @return
     */
    //List<TOperateLog> queryLogs(PageHelper<TOperateLog> pageHelper) throws Exception;
    List<TOperateLog> queryLogs(TOperateLog operateLog, @Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 查询记录数量
     *
     * @param operateLog
     * @return
     * @throws Exception
     */
    int getTotal(TOperateLog operateLog) throws Exception;

    List<TOperateLog> getQueryAllLogs(PageHelper pageHelper);

    void updateOperate(TOperateLog tOperateLog);


    /**
     * 根据funanme查询funcid
     *
     */

    // Integer queryFunctionId(String funcname);

    /**
     * 查询操作日志中的功能菜单
     */
    ///List<Permission> queryOperateLogFunctions();


}