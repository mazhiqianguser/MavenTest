package cn.com.test.user.controller;

import cn.com.test.user.entity.SysUser;
import cn.com.test.user.page.Page;
import cn.com.test.user.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author mazhiqiang
 * @Description //TODO
 * @Date 9:41 2019\3\14 0014
 * @Param 
 * @return 
 **/
public class BaseController { 
	@Value("${success.deal}")
	public String DEAL_SUCCESS; //处置成功
	@Value("${fail.deal}")
	public String DEAL_FAIL; //处置失败
	@Value("${success.save}")
	public String SAVE_SUCCESS; //添加成功
	@Value("${fail.save}")
	public String SAVE_FAIL; //添加失败
	@Value("${success.update}")
	public String UPDATE_SUCCESS; //修改成功
	@Value("${fail.update}")
	public String UPDATE_FAIL; //修改失败
	@Value("${success.delete}")
	public String DELETE_SUCCESS; //删除成功
	@Value("${fail.delete}")
	public String DELETE_FAIL; //删除失败
	@Value("${success.find}")
	public String FIND_SUCCESS; //查询成功
	@Value("${fail.find}")
	public String FIND_FAIL; //查询失败
	@Value("${success.quit}")
	public String QUIT_SUCCESS;//成功退出
	@Value("${fail.login}")
	public String LOGIN_FAIL; //登入失败
	@Value("${success.login}")
	public String LOGIN_SUCCESS;//登入成功

	@Value("${unknown.account}")
	public String UNKNOWN_ACCOUNT;//未知账号
	@Value("${incorrect.password}")                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
	public String INCORRECT_PASSWORD;//密码不正确
	@Value("${account.locked}")
	public String ACCOUNT_LOCKED;//账号被锁定
	@Value("${too.many.mistakes}")
	public String TOO_MANY_MISTAKES;//错误次数过多
	@Value("${username.or.password.incorrect}")
	public String USERNAME_OR_PASSWORD_INCORRECT;//用户名或密码不正确
	@Value("${privilege.grant.failed}")
	public String PRIVILEGE_GRANT_FAILED;//授权失败

	@Value("${reset.password}")
	public String RESET_PASSWORD;//重置密码

	@Value("${default.role.name}")
	public String DEFAULT_ROLE_NAME;//默认角色名称

	//状态码
	public static final String SUCCESS_CODE="200";
	public static final String FAIL_CODE="300";
	/*角色验证  /^[A-Za-z0-9\u4e00-\u9fa5]+$/*/
	public static final String INSERT_ROLE = "^{2,10}+$";
	/*验证用户名   /^[a-zA-Z\d]+$/     */
	public static final String USER_LOGIN = "^[a-zA-Z_\\d]{2,20}$";
	/*验证密码 */
	public static final String PASSWORD_LOGIN = "^[a-z][a-z_0-9]{5,19}$";
	/*验证邮箱*/
	public
	static final String
			REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	/*验证手机号*/
	public static final String REGEX_MOBILE = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";

	/*序号不能为负数*/
	public static  final String   REGEX_SORT ="^\\d*$";
	//页面配置
	//session缓存
	public static final String SESSION_USER="system_user";
	//分页 每页显示的记录数
	public static final Integer PAGE_SIZE = 20;
	/**
	 * 从session中获取user
	 * @param request
	 * @return
	 */
	public SysUser getUser(HttpServletRequest request){
		return (SysUser) request.getSession().getAttribute(SESSION_USER);
	}

	/**
	 * 成功结果集
	 * @param data 数据
	 * @param desc 描述
	 * @param code 状态码
	 * @return
	 */
	public BaseResponse ajaxSucc(Object data, String desc, String code) {
		 BaseResponse baseResponse = new BaseResponse();
	     baseResponse.setCode(code);
	     baseResponse.setDesc(desc);
	     baseResponse.setTime(new Date().getTime() + "");
	     baseResponse.setData(data);
	     return baseResponse;
	}
	
	public BaseResponse ajaxSucc(Object data,Integer totalRecord, String desc,String code) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(code);
        baseResponse.setDesc(desc);
        baseResponse.setTime(new Date().getTime() + "");
        baseResponse.setData(data);
        baseResponse.setTotalRecord(totalRecord);
        return baseResponse;
   }
	
	/**
	 * 失败结果集
	 * @param desc 描述
	 * @param code 状态码
	 * @return
	 */
	public BaseResponse ajaxFail(Object desc, String code) {
		 BaseResponse baseResponse = new BaseResponse();
	     baseResponse.setCode(code);
	     baseResponse.setDesc(desc);
	     baseResponse.setData("{}");
	     baseResponse.setTime(new Date().getTime() + "");
		 return baseResponse;
    }
	
	/**
	 * 成功结果集（分页）
	 * @param data 数据
	 * @param desc 描述
	 * @param code 状态码
	 * @return
	 */
	public BaseResponse ajaxSucc(Object data, String desc,String code,com.github.pagehelper.Page page) {
		
		Map<String,Object> result = new HashMap<String, Object>(); 
		result.put("data", data);//数据
		result.put("total", page.getTotal());//总数
		result.put("pages", page.getPages());//总页数
		result.put("pageNum", page.getPageNum());//页码
		result.put("pageSize", page.getPageSize());//每页显示记录数
		
		BaseResponse baseResponse = new BaseResponse();
	     baseResponse.setCode(code);
	     baseResponse.setDesc(desc);
	     baseResponse.setTime(new Date().getTime() + "");
	     baseResponse.setData(result);
	     return baseResponse;
	}

	/**
	 * 分页结果集
	 * @param object
	 * @param page
	 * @return
	 */
	public Map<String, Object> getResult(Object object, Page page) {
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("data", object);//数据
		result.put("totalRecord", page.getTotalRecord());//总记录数
		result.put("currentPage", page.getCurrentPage());//当前页数
		result.put("pageSize", page.getPageSize());//每页显示条数
		return result;
	}
}
