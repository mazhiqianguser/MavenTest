package cn.com.test.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * 操作日志pojo
 * @author cuidianlong
 * 2017-06-05
 */
@JsonIgnoreProperties({ "handler"})
public class TOperateLog {
    private Integer oplog_id;

    private Integer user_id;

    private String login_name;

    private String username;

    private String op_ip;

    private Integer function_id;

    private Integer operate_type;

    private String operate_content;
    private Date operate_time;
    
    private String function_name;
    
    private String operate_name;
    
    private	String start_time;//开始时间
    private String end_time;//结束时间

    private String phone;

    public TOperateLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public TOperateLog(Integer oplog_id, Integer user_id, String login_name, String username, String op_ip,
                       Integer function_id, Integer operate_type, String operate_content, Date operate_time,
                       String function_name, String operate_name, String start_time, String end_time, String phone) {
		super();
		this.oplog_id = oplog_id;
		this.user_id = user_id;
		this.login_name = login_name;
		this.username = username;
		this.op_ip = op_ip;
		this.function_id = function_id;
		this.operate_type = operate_type;
		this.operate_content = operate_content;
		this.operate_time = operate_time;
		this.function_name = function_name;
		this.operate_name = operate_name;
		this.start_time = start_time;
		this.end_time = end_time;
		this.phone = phone;
	}

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	/**
     * 功能权限名
     * @return
     */
    public String getFunction_name() {
		return function_name;
	}
    
    /**
     * 功能权限名
     * @param function_name
     */
	public void setFunction_name(String function_name) {
		this.function_name = function_name;
	}
	
	/**
	 * 操作名
	 * @return
	 */
	public String getOperate_name() {
		return operate_name;
	}
	
	/**
	 * 操作名
	 * @param operate_name
	 */
	public void setOperate_name(String operate_name) {
		this.operate_name = operate_name;
	}

	/**
     * id
     * @return
     */
    public Integer getOplog_id() {
        return oplog_id;
    }
    
    /**
     * id
     * @param oplog_id
     */
    public void setOplog_id(Integer oplog_id) {
        this.oplog_id = oplog_id;
    }
    
    /**
     * 用户id
     * @return
     */
    public Integer getUser_id() {
        return user_id;
    }
    
    /**
     * 用户id
     * @param user_id
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    
    /**
     * 登录账号
     * @return
     */
    public String getLogin_name() {
        return login_name;
    }
    
    /**
     * 登录账号
     * @param login_name
     */
    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }
    
    /**
     * 用户姓名
     * @return
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * 用户姓名
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * 登录ip
     * @return
     */
    public String getOp_ip() {
        return op_ip;
    }
    
    /**
     * 登录ip
     * @param op_ip
     */
    public void setOp_ip(String op_ip) {
        this.op_ip = op_ip;
    }
    
    /**
     * 功能权限id
     * @return
     */
    public Integer getFunction_id() {
        return function_id;
    }
    
    /**
     * 功能权限id
     * @param function_id
     */
    public void setFunction_id(Integer function_id) {
        this.function_id = function_id;
    }
    
    /**
     * 操作id
     * @return
     */
    public Integer getOperate_type() {
        return operate_type;
    }
    
    /**
     * 操作id
     * @param operate_type
     */
    public void setOperate_type(Integer operate_type) {
        this.operate_type = operate_type;
    }
    
    /**
     * 操作内容
     * @return
     */
    public String getOperate_content() {
        return operate_content;
    }
    
    /**
     * 操作内容
     * @param operate_content
     */
    public void setOperate_content(String operate_content) {
        this.operate_content = operate_content;
    }
   
    /**
     * 创建时间
     * @return
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getOperate_time() {
        return operate_time;
    }
    /**
     * 创建时间
     * @param operate_time
     */
    public void setOperate_time(Date operate_time) {
        this.operate_time = operate_time;
    }
}