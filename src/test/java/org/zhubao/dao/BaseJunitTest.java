package org.zhubao.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:root-context.xml")
public class BaseJunitTest {

	@Before
	public void startUp(){
		System.out.println("================ start to execute test case ================");
	}
	
	@After
	public void destroy(){
		
	}
}
