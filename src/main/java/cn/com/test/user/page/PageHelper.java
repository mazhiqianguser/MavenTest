package cn.com.test.user.page;
/**
 * @Author mazhiqiang
 * @Description //TODO 分页插件
 * @Date 9:44 2019\3\14 0014
 * @Param
 * @return 
 **/
public class PageHelper<T> {
	private T object;
	private Page page;
	private String condition;
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public T getObject() {
		return object;
	}
	public void setObject(T object) {
		this.object = object;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	
}	
