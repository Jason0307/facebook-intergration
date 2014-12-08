package org.zhubao.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.social.quickstart.dao.TokenDao;
import org.springframework.social.quickstart.model.UserAccessToken;
import org.springframework.social.quickstart.service.TokenService;

public class TestJdbcConnection extends BaseJunitTest{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TokenDao tokenDaoImp;
	
	@Autowired
	private TokenService tokenService;
	
	@Test
	public void testJdbcTemplate(){
		System.out.println(jdbcTemplate);
	}
	
	@Test
	public void testToken(){
		String appId = "755442001187377";
		UserAccessToken token = tokenDaoImp.getToken(appId);
		System.out.println(token.getAccessToken());
	}
	
	@Test
	public void testService(){
		String appId = "755442001187377";
		tokenService.getAccessToken(appId);
	}
	
	
	
}
