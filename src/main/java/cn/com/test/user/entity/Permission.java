package cn.com.test.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Permission implements Serializable{
    /** serialVersionUID*/
	private static final long serialVersionUID = -7186038020906954110L;

	private Integer perm_id;

    private Integer parent_perm_id;

    private String perm_name;

    private String perm_action;

    private String icon;

    private String rule;

    private String perm_describe;

    private Boolean perm_status;

    private Integer perm_type;

    private Double sort;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date create_time;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date update_time;

    private Integer perm_level;
    
    private List<Permission> permissions;
    
    public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public Integer getPerm_id() {
        return perm_id;
    }

    public void setPerm_id(Integer perm_id) {
        this.perm_id = perm_id;
    }

    public Integer getParent_perm_id() {
        return parent_perm_id;
    }

    public void setParent_perm_id(Integer parent_perm_id) {
        this.parent_perm_id = parent_perm_id;
    }

    public String getPerm_name() {
        return perm_name;
    }

    public void setPerm_name(String perm_name) {
        this.perm_name = perm_name;
    }

    public String getPerm_action() {
        return perm_action;
    }

    public void setPerm_action(String perm_action) {
        this.perm_action = perm_action;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getPerm_describe() {
        return perm_describe;
    }

    public void setPerm_describe(String perm_describe) {
        this.perm_describe = perm_describe;
    }

    public Boolean getPerm_status() {
        return perm_status;
    }

    public void setPerm_status(Boolean perm_status) {
        this.perm_status = perm_status;
    }

    public Integer getPerm_type() {
        return perm_type;
    }

    public void setPerm_type(Integer perm_type) {
        this.perm_type = perm_type;
    }

    public Double getSort() {
        return sort;
    }

    public void setSort(Double sort) {
        this.sort = sort;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getPerm_level() {
        return perm_level;
    }

    public void setPerm_level(Integer perm_level) {
        this.perm_level = perm_level;
    }
}