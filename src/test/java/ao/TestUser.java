/**

* Title: TestUser.java

* Description: 

* Copyright: Copyright (c) 2017

* @author AMGuo

* @date 2017��8��7��

* @version 1.0

*/
package ao;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
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
	public void demo_1() {
		List<User> users = userService.findAll();
		System.out.println(users.toString());
	}

	public static void main(String[] args) throws Exception, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		// 创建Get方法实例
		HttpPost httpgets = new HttpPost("https://kyfw.12306.cn/otn/leftTicket/queryA?leftTicketDTO.train_date=2018-01-06&leftTicketDTO.from_station=SHH&leftTicketDTO.to_station=DKH&purpose_codes=ADULT");
		HttpResponse response = httpclient.execute(httpgets);
		HttpEntity entity = (HttpEntity) response.getEntity();
		if (entity != null) {
			String content = EntityUtils.toString(entity);
			System.out.println("Do something");
			System.out.println(content);
		}
	}

}
