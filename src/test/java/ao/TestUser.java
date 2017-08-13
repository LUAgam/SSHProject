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

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aomen.guo.entity.User;
import com.aomen.guo.service.impl.UserServiceImpl;

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
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:hibernate.xml" })
public class TestUser {

	private static final Logger LOGGER = Logger.getLogger(TestUser.class);
	
	@Autowired
	private UserServiceImpl userService;
	
	@Test
	public void demo_1(){
		List<User> users = userService.findAll();
	}
	
	
	

}
