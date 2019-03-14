package cn.com.test.user.entity;

import java.io.Serializable;
import java.util.Date;

public class SysPerm implements Serializable{
    private Integer perm_id;

    private String perm_name;

    private String perm_url;

    private Integer parent_id;

    private String perm_icon;

    private Integer perm_type;

    private Integer perm_state;

    private String perm_code;

    private Integer sort;

    private Date create_time;

    private Date update_time;

    public Integer getPerm_id() {
        return perm_id;
    }

    public void setPerm_id(Integer perm_id) {
        this.perm_id = perm_id;
    }

    public String getPerm_name() {
        return perm_name;
    }

    public void setPerm_name(String perm_name) {
        this.perm_name = perm_name;
    }

    public String getPerm_url() {
        return perm_url;
    }

    public void setPerm_url(String perm_url) {
        this.perm_url = perm_url;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getPerm_icon() {
        return perm_icon;
    }

    public void setPerm_icon(String perm_icon) {
        this.perm_icon = perm_icon;
    }

    public Integer getPerm_type() {
        return perm_type;
    }

    public void setPerm_type(Integer perm_type) {
        this.perm_type = perm_type;
    }

    public Integer getPerm_state() {
        return perm_state;
    }

    public void setPerm_state(Integer perm_state) {
        this.perm_state = perm_state;
    }

    public String getPerm_code() {
        return perm_code;
    }

    public void setPerm_code(String perm_code) {
        this.perm_code = perm_code;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
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
}