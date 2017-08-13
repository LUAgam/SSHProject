/**

* <p>Title: BaseDao.java</p>

* <p>Description: </p>

* <p>Copyright: Copyright (c) 2017</p>

* @author AMGuo

* @date 2017��8��7��

* @version 1.0

*/
package com.aomen.guo.dao;

import java.io.Serializable;
import java.util.List;

import com.aomen.guo.entity.BaseEntity;

/**
 * 
 * Title: BaseDao
 * 
 * Description:
 * 
 * @author AMGuo
 * 
 * @date 2017��8��7��
 * 
 */
public interface BaseDao<T, PK extends Serializable> {

	T load(PK id);

	T get(PK id);

	List<T> findAll();

	PK save(T entity);

	void saveOrUpdate(T entity);

	void delete(PK id);

	void flush();

}
