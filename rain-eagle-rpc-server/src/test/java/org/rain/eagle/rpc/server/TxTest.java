package org.rain.eagle.rpc.server;

import org.junit.Test;
import org.rain.eagle.rpc.server.entity.EagleUser;
import org.rain.eagle.rpc.server.service.EagleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.alibaba.fastjson.JSON;

@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class TxTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	@Qualifier(value = "eagleUserServiceImpl")
	private EagleUserService eagleUserService;

	@Test
	public void test() {
		System.out.println(eagleUserService.getClass().getName());
		EagleUser eagleUser = eagleUserService.selectByPrimaryKey((long) 5);
		System.err.println(eagleUser.getUserName() + "\t" + JSON.toJSONString(eagleUser));
		
		try {
			eagleUser.setUserName("事务测试C");
			int update = eagleUserService.updateByPrimaryKeySelective(eagleUser);
			System.err.println(update);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		eagleUser = eagleUserService.selectByPrimaryKey((long) 5);
		System.err.println(eagleUser.getUserName() + "\t" + JSON.toJSONString(eagleUser));
	}

}
