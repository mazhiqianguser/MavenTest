package cn.com.test.user.page;

/**
 * @Author mazhiqiang
 * @Description //TODO 分页
 * @Date 9:45 2019\3\14 0014
 * @Param
 * @return 
 **/
public class Page {
    private Integer totalRecord=null;        //总记录数  
    private Integer totalPage=null;    //总页数  
    private Integer pageSize=100;     //每页显示的条数
    private Integer currentPage=1;  //当前页数
    private Integer startSize=null;    //开始条数
    private Boolean isLimit=false; //分页
    /**
     * 分页
     * @param totalRecord 总记录数
     * @param pageSize 每页显示的条数，默认20
     * @param currentPage 当前页数
     */
    public void paging(boolean isLimit,Integer totalRecord,Integer pageSize,Integer currentPage){
    	if(isLimit){
	    	if(pageSize != null)
	        this.pageSize = pageSize;
	    	if(currentPage != null)
	        this.currentPage = currentPage;
	    	this.totalPage=(totalRecord+this.pageSize-1)/this.pageSize;
	        this.totalRecord = totalRecord;
	        this.startSize = this.pageSize*(this.currentPage-1);
	        this.setIsLimit(isLimit);
    	}
    }
    
	public Integer getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}

	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getStartSize() {
		return startSize;
	}
	public void setStartSize(Integer startSize) {
		this.startSize = startSize;
	}

	public Boolean getIsLimit() {
		return isLimit;
	}

	public void setIsLimit(Boolean isLimit) {
		this.isLimit = isLimit;
	} 
}  