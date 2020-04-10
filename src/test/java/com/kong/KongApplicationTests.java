package com.kong;

import com.kong.controller.UserController;
import com.kong.data.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KongApplication.class)
public class KongApplicationTests {
	@Autowired
	UserController userController;

	@Test
	public void testA() {
		User user = new User();
		user.setAccount("123456");
		user.setId("08d7b1ba-8eda-4363-b082-aea7ae5c7684");
		String a = userController.addUser1(user);
		System.out.println("a="+a);
	}

}
