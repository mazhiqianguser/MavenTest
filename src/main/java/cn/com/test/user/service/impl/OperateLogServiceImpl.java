package cn.com.test.user.service.impl;

import cn.com.test.user.dao.TOperateLogDao;
import cn.com.test.user.entity.TOperateLog;
import cn.com.test.user.page.PageHelper;
import cn.com.test.user.service.OperateLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author mazhiqiang
 * @Description //TODO 
 * @Date 9:58 2019\3\14 0014
 * @Param 
 * @return 
 **/
@Service
public class OperateLogServiceImpl implements OperateLogService {
	@Resource
	TOperateLogDao operateLogDao;
	
	public int insert(TOperateLog operateLog) throws Exception {
		return operateLogDao.insert(operateLog);
	}

    @Override
    public List<TOperateLog> queryLogs(TOperateLog operateLog, Integer page, Integer limit) throws Exception{
		if (page <= 0) {
			page = 1;
		}
		Integer start = (page - 1) * limit;
        return operateLogDao.queryLogs(operateLog,start,limit);
    }
    /*条件查询*/
    @Override
    public List<TOperateLog> getQueryAllLogs(PageHelper pageHelper) throws Exception {
        return  operateLogDao.getQueryAllLogs(pageHelper);
    }

	@Override
	public void updateOperate(TOperateLog tOperateLog) {
		operateLogDao.updateOperate(tOperateLog);
	}

   /* public List<TOperateLog> queryLogs(PageHelper<TOperateLog> pageHelper) throws Exception {
		return operateLogDao.queryLogs(pageHelper);
	}*/

	public int getTotal(TOperateLog operateLog) throws Exception {
		return operateLogDao.getTotal(operateLog);
	}

	/**
	 * 添加日志
	 * @throws Exception 
	 */
	public int addTOperateLog(TOperateLog tOperateLog) throws Exception {
		int insert = operateLogDao.insert(tOperateLog);
		return insert;
	}

	@Override
	public int addTOperateLog(Integer functionId, Integer operate_type, String Content, HttpServletRequest request) throws Exception {
		return 0;
	}





	/*@Override
	public int addTOperateLog(Integer functionId, Integer operate_type, String content, HttpServletRequest request)
			throws Exception {
		 //添加操作日志
		SysUser user = (SysUser) request.getSession().getAttribute("user");
		String ip = IPUtil.getIpAddress(request);
		ip = ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
		Date date = new Date();
		String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		//操作内容
		TOperateLog tOperateLog = new TOperateLog(null,user.getUser_id(),user.getUser_name(),user.getName(),ip,functionId,operate_type,content,new Date(),format, format, format, format,user.getPhone());
		operateLogDao.insert(tOperateLog);
		return 0;
	}*/
/*
	@Override
	public Integer queryFunctionId(String funcname) {

		return operateLogDao.queryFunctionId(funcname);
	}*/
	
	 /**
     * 查询操作日志中的功能菜单*/

    /*public List<Permission> queryOperateLogFunctions(){
    	return operateLogDao.queryOperateLogFunctions();
    }*/
}
