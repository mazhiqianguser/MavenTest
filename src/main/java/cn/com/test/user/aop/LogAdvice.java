package cn.com.test.user.aop;

import cn.com.test.user.entity.SysUser;
import cn.com.test.user.entity.TOperateLog;
import cn.com.test.user.service.OperateLogService;
import cn.com.test.user.utils.BaseResponse;
import cn.com.test.user.utils.IPUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 操作日志 aop切面类
 *
 * @author mazhiqiang
 * 2017-08-07
 */
public class LogAdvice {
    String function_id;
    String operate_type;
    String operate_content = "";
    @Autowired
    OperateLogService operateLogService;

    /**
     * 在核心业务执行前执行，不能阻止核心业务的调用。
     *
     * @param joinPoint
     */
    private void doBefore(JoinPoint joinPoint) {
    }

    /**
     * 手动控制调用核心业务逻辑，以及调用前和调用后的处理,
     * <p>
     * 注意：当核心业务抛异常后，立即退出，转向After Advice
     * 执行完毕After Advice，再转到Throwing Advice
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    private Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {


        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    operate_content = method.getAnnotation(Log.class).operate_content();
                    function_id = method.getAnnotation(Log.class).function_id();
                    operate_type = method.getAnnotation(Log.class).operate_type();
                    break;
                }
            }
        }

        boolean a = true;
        String returnCode = "成功";
        //调用核心逻辑
        Object retVal = null;
        try {
            retVal = joinPoint.proceed();
        } catch (Exception e) {
            a = false;
        }
        if (retVal != null) {
            BaseResponse response = (BaseResponse) retVal;
            String code = response.getCode();
            if (code != null && code != "") {
                if (code.equals("200")) {
                    returnCode = "成功";
                } else if (code.equals("300")) {
                    returnCode = "失败";
                }
            }
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (returnCode.equals("成功") && a == true) {
            //添加操作日志
            SysUser user = (SysUser) request.getSession().getAttribute("system_user");
            String ip = IPUtil.getIpAddress(request);
            Date date = new Date();
            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            if (request.getSession().getAttribute("content") != null && request.getSession().getAttribute("content") != "") {
                String content = (String) request.getSession().getAttribute("content");
                if (!content.contains("导入")) {
                    boolean result = (content.length() == content.getBytes().length);//true:无汉字  false:有汉字
                    if (result) {
                        if (content.endsWith(",")) {
                            content = content.substring(0, content.length() - 1);
                        }
                        operate_content += ":‘" + content + "’";
                    } else {
                        String reg1 = "[\\u4e00-\\u9fa5]+";
                        boolean result1 = content.matches(reg1);
                        if (result1) {
                            operate_content += ":‘" + content + "’";
                        } else {
                            if (operate_content.contains("部门") || operate_content.contains("员工") || content.contains("主叫号码")) {
                                if (content.endsWith(",")) {
                                    content = content.substring(0, content.length() - 1);
                                }
                                operate_content += ":‘" + content + "’";
                            } else {
                                int count = 0;
                                String reg = "[\\u4e00-\\u9fa5]";
                                Pattern p = Pattern.compile(reg);
                                Matcher m = p.matcher(content);
                                while (m.find()) {
                                    for (int i = 0; i <= m.groupCount(); i++) {
                                        count = count + 1;
                                    }
                                }
                                String reg2 = "[^\\u4e00-\\u9fa5]";
                                String str = content.replaceAll(reg2, " ");
                                content = content.substring(0, content.length() - count);
                                if (content.endsWith(",")) {
                                    content = content.substring(0, content.length() - 1);
                                }
                                operate_content += ":‘" + content + "’";
                                operate_content += str;
                            }
                        }
                    }
                } else {
                    operate_content += ":" + content + "";
                }
                request.getSession().removeAttribute("content");
            }
            String ischeck = (String) request.getSession().getAttribute("isCheck");
            if (ischeck != null && ischeck != "") {
                if (function_id.equals("24") || function_id.equalsIgnoreCase("27")) {
                    function_id = ischeck;
                }
            }
            if (function_id.equals("69")) {
                if (ischeck != null && ischeck != "") {
                    function_id = ischeck;
                }
            }
            //操作内容
            try {
                if (operate_type.equals("5")) {
                    if (operate_content.contains(":")) {
                        TOperateLog tOperateLog = new TOperateLog(null, user.getUser_id(), user.getUser_name(), user.getName(), ip, Integer.valueOf(function_id), Integer.valueOf(operate_type), operate_content + returnCode, new Date(), format, format, format, format, user.getPhone());
                        operateLogService.insert(tOperateLog);
                    } else {
                        //进行添加操作时 没有手机号不进行操作
                        TOperateLog tOperateLog = new TOperateLog(null, user.getUser_id(), user.getUser_name(), user.getName(), ip, Integer.valueOf(function_id), Integer.valueOf(operate_type), operate_content + "重复", new Date(), format, format, format, format, user.getPhone());
                        operateLogService.insert(tOperateLog);
                    }
                } else {
                    if (function_id.equals("73") && operate_type.equals("7")) {
                        int attribute = (int) request.getSession().getAttribute("size");
                        TOperateLog tOperateLog = new TOperateLog(null, user.getUser_id(), user.getUser_name(), user.getName(), ip, Integer.valueOf(function_id), Integer.valueOf(operate_type), operate_content + returnCode + attribute + "条", new Date(), format, format, format, format, user.getPhone());
                        operateLogService.insert(tOperateLog);
                    } else {
                        TOperateLog tOperateLog = new TOperateLog(null, user.getUser_id(), user.getUser_name(), user.getName(), ip, Integer.valueOf(function_id), Integer.valueOf(operate_type), operate_content + returnCode, new Date(), format, format, format, format, user.getPhone());
                        operateLogService.insert(tOperateLog);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            request.getSession().removeAttribute("content");
        }

        return retVal;
    }

    /**
     * 核心业务逻辑退出后（包括正常执行结束和异常退出），执行此Advice
     *
     * @param joinPoint
     */
    private void doAfter(JoinPoint joinPoint) {
    }

    /**
     * 核心业务逻辑调用正常退出后，不管是否有返回值，正常退出后，均执行此Advice
     *
     * @param joinPoint
     */
    private void doReturn(JoinPoint joinPoint) {
    }


    /**
     * 核心业务逻辑调用异常退出后，执行此Advice，处理错误信息
     *
     * @param joinPoint
     * @param ex
     */
    private void doThrowing(JoinPoint joinPoint, Throwable ex) {
    	/*HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
   	 //添加操作日志
		TUser user = (TUser) request.getSession().getAttribute("user");
		String ip = IPUtil.getIpAddress(request);
		ip = ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
		Date date = new Date();
		String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		//操作内容
		TOperateLog tOperateLog = new TOperateLog(null,user.getUser_id(),user.getLogin_name(),user.getUsername(),ip,Integer.valueOf(function_id),Integer.valueOf(operate_type),operate_content+"失败",format,format, format, format, format);
		try {
			operateLogService.insert(tOperateLog);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
    }

    public static void main(String[] args) {
        String operate_content = null;
        String content = "主叫号码156150154下a.wav";

        int count = 0;
        String reg = "[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(content);
        while (m.find()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                count = count + 1;
            }
        }
        String reg2 = "[^\u4e00-\u9fa5]";
        String str = content.replaceAll(reg2, " ");
        content = content.substring(0, content.length() - count);
        operate_content += ":‘" + content + "’";
        System.out.println(operate_content);
        //	boolean result = (content.length() == content.getBytes().length);//true:无汉字  false:有汉字
        //System.out.println(result);
        //String a = "1111：汉子";
		  /*String reg = "[\\u4e00-\\u9fa5]+" ; 
			        boolean result1 = a.matches(reg);
			        System.out.println(result1);
*/
    }

    public static boolean isChinese(String str) {
        String regEx = "[\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(regEx);
        Matcher matcher = pat.matcher(str);
        boolean flg = false;
        if (matcher.find())
            flg = true;

        return flg;
    }
}
