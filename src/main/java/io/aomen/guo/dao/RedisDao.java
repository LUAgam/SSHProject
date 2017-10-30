/**

* Title: RedisDao.java

* Description: 

* Copyright: Copyright (c) 2017

* @author AMGuo

* @date 2017年8月13日

* @version 1.0

*/
package io.aomen.guo.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * Title: RedisDao
 * 
 * Description:
 * 
 * @author AMGuo
 * 
 * @date 2017年8月13日
 * 
 */
public interface RedisDao<T, PK extends Serializable> {

	public void put(T entity);

	public void delete(T entity);

	public T get(T entity);
	
	public List<T> findAll(T entity);

}
