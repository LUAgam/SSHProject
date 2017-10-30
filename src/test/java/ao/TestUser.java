/**

* Title: TestUser.java

* Description: 

* Copyright: Copyright (c) 2017

* @author AMGuo

* @date 2017��8��7��

* @version 1.0

*/
package ao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.aomen.guo.entity.User;
import io.aomen.guo.service.UserService;

/**
 * 
 * Title: TestUser
 * 
 * Description:
 * 
 * @author AMGuo
 * 
 * @date 2017��8��7��
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class TestUser {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestUser.class);
	
	@Autowired
	private UserService userService;
	
	@Test
	public void demo_1(){
		List<User> users = userService.findAll();
		System.out.println(users.toString());
	}
	
	
	

}
