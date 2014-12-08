package org.springframework.social.quickstart.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.social.quickstart.model.UserAccessToken;
import org.springframework.stereotype.Repository;

@Repository
public class TokenDaoImp implements TokenDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public UserAccessToken getToken(String appId) {
		List<UserAccessToken> accessTokens = jdbcTemplate.query(
				"SELECT * FROM UserAccessToken WHERE appId = ?",
				new Object[]{appId},new BeanPropertyRowMapper(UserAccessToken.class));
		if(accessTokens.isEmpty()){
			return null;
		}
		return accessTokens.get(0);
	}

}
