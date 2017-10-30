/**

* Title: UserDaoImpl.java

* Description: 

* Copyright: Copyright (c) 2017

* @author AMGuo

* @date 2017��8��7��

* @version 1.0

*/
package com.aomen.guo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.aomen.guo.entity.User;

/**
 * 
 * Title: UserDaoImpl
 * 
 * Description:
 * 
 * @author AMGuo
 * 
 * @date 2017��8��7��
 * 
 */
public interface UserDao extends BaseRepository<User, Long>, JpaSpecificationExecutor<Long> {

	@Query(value = "select * from tbl_user_0 limit 3",nativeQuery = true)
	List<User> findMyBody();

}
