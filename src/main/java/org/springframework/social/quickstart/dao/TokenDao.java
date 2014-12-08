package org.springframework.social.quickstart.dao;

import org.springframework.social.quickstart.model.UserAccessToken;

public interface TokenDao {

	UserAccessToken getToken(String appId);
}
