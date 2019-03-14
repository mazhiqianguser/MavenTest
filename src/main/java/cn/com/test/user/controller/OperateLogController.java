package cn.com.test.user.controller;


import cn.com.test.user.entity.TOperateLog;
import cn.com.test.user.page.Page;
import cn.com.test.user.page.PageHelper;
import cn.com.test.user.service.OperateLogService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 操作日志controller
 *
 * @author mazhiqiang
 * 2017-06-08
 */
@Controller
@RequestMapping("/operateLogControl")
public class OperateLogController extends BaseController {
    @Resource
    OperateLogService operateLogService;

    private static final Logger log = Logger.getLogger(OperateLogController.class);

    /**
     * 操作日志页
     *
     * @param operateLog
     * @param page
     * @param limit
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/operateLog", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String operateLog(@ModelAttribute TOperateLog operateLog, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) throws Exception {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", 0);
            jsonObject.put("msg", "");
            int totalRecord = operateLogService.getTotal(operateLog);
            log.info(totalRecord + "总共有多少条数据 count +++++++++");
            List<TOperateLog> operateLogs = this.operateLogService.queryLogs(operateLog, page, limit);
            log.info(operateLogs + "查询TOperateLog ++ list集合 +++   数据 +  分页 ++ page limit");
            if (operateLogs != null) {
                jsonObject.put("count", totalRecord);
                jsonObject.put("data", operateLogs);
                return JSON.toJSONString(jsonObject);
            }
            log.info(jsonObject + " 返回的 model 数据 ");
            return JSON.toJSONString(jsonObject);
        } catch (NumberFormatException e) {
            return JSON.toJSONString("数据异常" + e);
        }
    }

    /**
     * 按条件(分页)查询
     */
    @RequestMapping(
            value = "/selectOperateLogs", produces = "application/json;charset=utf-8"
    )
    @ResponseBody
    public String selectOperateLogs(@ModelAttribute TOperateLog operateLog,
                                    @RequestParam(required = false) Integer page,
                                    @RequestParam(required = false) Integer limit,
                                    HttpServletRequest request) throws Exception {
        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", 0);
            jsonObject.put("msg", "");
            /*时间处理*/
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (operateLog.getEnd_time() != null && !operateLog.getEnd_time().equals("")) {
                String end_time = operateLog.getEnd_time();
                Date parse = simpleDateFormat.parse(end_time);
                Calendar c = Calendar.getInstance();
                c.setTime(parse);
                c.add(Calendar.DAY_OF_MONTH, 1);
                Date tomorrow = c.getTime();
                String format = simpleDateFormat.format(tomorrow);
            /*long time = parse.getTime();
            long l = time + 24 * 60 * 60;
            Date date = new Date(l);
            String format = simpleDateFormat.format(date);*/
                operateLog.setEnd_time(format);
            }
            int totalRecord = operateLogService.getTotal(operateLog);

            log.info(totalRecord + "根据条件查询总条数 ++++++++++++++++++++++++");
            //分页
            PageHelper<TOperateLog> pageHelper = new PageHelper<TOperateLog>();
            pageHelper.setObject(operateLog);
            Page page1 = new Page();
            /*if (page <= 0) {
                page = 1;
            }
            Integer start = (page - 1) * limit;*/
            /*每页显示条数*/
            page1.setPageSize(limit);
            /*当前页数*/
            page1.setCurrentPage(page);
            page1.paging(true, totalRecord, page1.getPageSize(), page1.getCurrentPage());
            pageHelper.setPage(page1);

            List<TOperateLog> operateLogs = operateLogService.getQueryAllLogs(pageHelper);

            if (operateLogs != null) {
                jsonObject.put("count", totalRecord);
                jsonObject.put("data", operateLogs);
                log.info(jsonObject + "条件分页返回 model 数据 +++++++++++++++++++");
                return JSON.toJSONString(jsonObject);
            }
            return JSON.toJSONString(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(FIND_FAIL + e + FAIL_CODE);
        }

    }

    /**
     * 查询操作日志中的function
     */
	/*@RequestMapping("/selectOperateLogFunctions")
	@ResponseBody
	public List<Permission> selectOperateLogFunctions(){
		List<Permission> queryOperateLogFunctions = operateLogService.queryOperateLogFunctions();
		return queryOperateLogFunctions;
	}*/
}
