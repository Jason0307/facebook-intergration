/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.quickstart;

import java.util.List;

import javax.inject.Inject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.quickstart.entity.FriendDetailVo;
import org.springframework.social.quickstart.service.TokenService;
import org.springframework.social.quickstart.util.PropertiesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * Simple little @Controller that invokes Facebook and renders the result. The
 * injected {@link Facebook} reference is configured with the required
 * authorization credentials for the current user behind the scenes.
 * 
 * @author Keith Donald
 */
@Controller
public class HomeController {
	private Facebook facebook;
	private TokenService tokenService;
	
	@Inject
	public HomeController(Facebook facebook) {
		this.facebook = facebook;
	}

	public void setTokenService(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) throws Exception {
		System.out.println("facebook : " + facebook);
		System.out.println("email : "
				+ facebook.userOperations().getUserProfile().getEmail());
		System.out.println("fName : "
				+ facebook.userOperations().getUserProfile().getFirstName());
		System.out.println("Auth : " + facebook.isAuthorized());
		System.out.println("id : "
				+ facebook.userOperations().getUserProfile().getId());
		System.out.println("name : "
				+ facebook.userOperations().getUserProfile().getName());
		List<Reference> friends = facebook.friendOperations().getFriends();
		System.out.println("friends sizes : "
				+ facebook.friendOperations().getFriendLists().size());
		for (Reference reference : facebook.friendOperations().getFriendLists()) {
			System.out.println("Reference : " + JSON.toJSONString(reference));
		}
		System.out.println("friends size : " + friends.size());
		FacebookProfile profile = facebook.userOperations().getUserProfile();
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println("Profile info :"
				+ objectMapper.writeValueAsString(profile));
		for (Reference friend : friends) {
			System.out.println("friend : " + friend.getName());
			FacebookProfile fProfile = facebook.userOperations()
					.getUserProfile(friend.getId());
			System.out.println(JSON.toJSONString(fProfile));
			// facebook.feedOperations().post(friend.getId(),
			// "What's your name?");
		}
		List<Post> posts = facebook.feedOperations().getFeed(profile.getId());
		for (Post post : posts) {
			System.out.println("Profile info :"
					+ objectMapper.writeValueAsString(post));
		}
		System.out.println("==============================");
		System.out.println(tokenService);
		/*String appId = PropertiesUtil.getInstance().getAppId();
		String accessToken = tokenService.getAccessToken(appId);
		FriendDetailVo friendDetailVo = facebook
				.restOperations()
				.getForObject(
						"https://graph.facebook.com/v2.2/me/taggable_friends?accessToken={accessToken}",
						FriendDetailVo.class, accessToken);
		System.out.println("Friends : " + friendDetailVo);*/
		model.addAttribute("friends", friends);
		return "home";
	}

	@RequestMapping(value = "/friends", method = RequestMethod.GET)
	public @ResponseBody FriendDetailVo listFacebookFriends() throws Exception {
		String appId = PropertiesUtil.getInstance().getAppId();
		String accessToken = tokenService.getAccessToken(appId);
		HttpGet httpGet = new HttpGet(
				"https://graph.facebook.com/v2.2/me/taggable_friends?access_token="
						+ accessToken);
		HttpResponse response = new DefaultHttpClient().execute(httpGet);
		String result = "";
		FriendDetailVo detailVo = new FriendDetailVo();
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
			System.out.println(result);
			detailVo = new ObjectMapper().readValue(result,
					FriendDetailVo.class);
		}
		System.out.println("response : " + detailVo);
		return detailVo;
	}
	
	@RequestMapping(value = "/access_token", method = RequestMethod.GET)
	public @ResponseBody FriendDetailVo getAccessToken() throws Exception {
		HttpGet httpGet = new HttpGet(
				"https://www.facebook.com/dialog/oauth");
		HttpParams httpParams = new BasicHttpParams();
		httpParams.setParameter("client_id", PropertiesUtil.getInstance().getAppId());
		httpParams.setParameter("redirect_uri", PropertiesUtil.getInstance().getRedirectUrl());
		httpParams.setParameter("display", PropertiesUtil.getInstance().getDisplay());
		httpParams.setParameter("response_type", PropertiesUtil.getInstance().getResponseType());
		httpGet.setParams(httpParams);
		HttpResponse response = new DefaultHttpClient().execute(httpGet);
		String result = "";
		if (response.getStatusLine().getStatusCode() == 200) {
			
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
			System.out.println(result);
		}
		return null;
	}

}
