package com.userContactManager.userContactManager;

import com.userContactManager.userContactManager.controller.Calculater;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserContactManagerApplicationTests {

	Calculater cal = new Calculater();

	@Test
	void contextLoads() {
	}

	@Test
	public void sumTest()
	{
		int result = cal.sum(1,2,4);
		int expResult = 7;

		Assertions.assertEquals(result, expResult);

	}

	@Test
	public void mulTest()
	{
		int result = cal.mul(2,4);
		int expResult = 7;

		Assertions.assertEquals(result, expResult);

	}

	@Test
	public void compTest()
	{
		boolean b = cal.comp(3,3);
		Assertions.assertTrue(b);
	}

}
