package cn.com.test.user.service.base;

import java.util.List;

/**
* <p>Title: BaseService.java</p>
* <p>Description: BaseService</p>
* <p>批量添加，批量修改多表关联查询需要手动实现</p>
* <p>泛型参数 : T为pojo类,V为范例example类</p>
* <p>注意：在service中，不要在一个方法中调用service本类中的另一个方法。严格来说service层中，方法不要相互调用。http://blog.csdn.net/dapinxiaohuo/article/details/52092447</p>
* <p>Copyright: Copyright (c) 2017</p>
* <p>Company: cintel</p>
* @author cuidianlong
* @date 2017年8月17日
* @version 1.0
 */
public interface BaseService<T,V> {
	/**
	* <p>Title: selectByExample</p>
	* <p>Description:动态查询(selectByExample) </p>
	* @param example
	* @return
	 */
	public List<T> selectAll(V example);
	
	/**
	* <p>Title: selectByExample</p>
	* <p>Description:按主键查询 (selectByPrimaryKey)</p>
	* @param id
	* @return
	 */
	public T selectById(Integer id);
	
	/**
	* <p>Title: save</p>
	* <p>Description:添加(insertSelective) </p>
	* @param entity
	* @return
	 */
	public int save(T entity);
	
	/**
	* <p>Title: update</p>
	* <p>Description: 动态修改(updateByExampleSelective)</p>
	* @param entity
	* @param example
	* @return
	 */
	public int update(T entity, V example);
	
	/**
	* <p>Title: updateById</p>
	* <p>Description:按主键修改(updateByPrimaryKeySelective) </p>
	* @param entity
	* @return
	 */
	public int updateById(T entity);
	/**
	* <p>Title: count</p>
	* <p>Description: 动态查询总条数(countByExample)</p>
	* @param example
	* @return
	 */
	public int count(V example);
	
	/**
	* <p>Title: deleteByExample</p>
	* <p>Description:动态删除(deleteByExample) </p>
	* @param example
	* @return
	 */
	public int delete(V example);
	
	/**
	* <p>Title: deleteByPrimaryKey</p>
	* <p>Description:按主键删除(deleteByPrimaryKey) </p>
	* @param user_id
	* @return
	 */
	public int deleteById(Integer id);
}