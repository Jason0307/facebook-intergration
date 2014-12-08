package org.springframework.social.quickstart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.quickstart.dao.TokenDao;
import org.springframework.social.quickstart.model.UserAccessToken;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImp implements TokenService {

	@Autowired
	private TokenDao tokenDao;

	public void setTokenDao(TokenDao tokenDao) {
		this.tokenDao = tokenDao;
	}

	@Override
	public String getAccessToken(String appId) {
		UserAccessToken token = tokenDao.getToken(appId);
		if (null != token) {
			return token.getAccessToken();
		}
		return null;
	}

}
