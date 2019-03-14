package cn.com.test.user.entity;

public class SystemConfig {
    private Integer id;

    private String project_name;

    private String page_directory;

    private Integer menu_bar1_id;

    private Integer menu_bar2_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getPage_directory() {
        return page_directory;
    }

    public void setPage_directory(String page_directory) {
        this.page_directory = page_directory;
    }

    public Integer getMenu_bar1_id() {
        return menu_bar1_id;
    }

    public void setMenu_bar1_id(Integer menu_bar1_id) {
        this.menu_bar1_id = menu_bar1_id;
    }

    public Integer getMenu_bar2_id() {
        return menu_bar2_id;
    }

    public void setMenu_bar2_id(Integer menu_bar2_id) {
        this.menu_bar2_id = menu_bar2_id;
    }
}