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

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	private final Facebook facebook;

	@Inject
	public HomeController(Facebook facebook) {
		this.facebook = facebook;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		System.out.println("username : "
				+ facebook.userOperations().getUserProfile().getUsername());
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
		for(Reference reference : facebook.friendOperations().getFriendLists()){
			System.out.println("Reference : " + JSON.toJSONString(reference));
		}
		System.out.println("friends size : " + friends.size());
		FacebookProfile profile = facebook.userOperations().getUserProfile();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println("Profile info :"
					+ objectMapper.writeValueAsString(profile));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Reference friend : friends) {
			System.out.println("friend : " + friend.getName());
			FacebookProfile fProfile = facebook.userOperations().getUserProfile(friend.getId());
			System.out.println(JSON.toJSONString(fProfile));
			//facebook.feedOperations().post(friend.getId(), "What's your name?");
		}
		
		System.out.println("==============================");
		facebook.feedOperations().post(profile.getId(), "What's your name?");
		model.addAttribute("friends", friends);
		return "home";
	}
	
	public static void main(String[] args) {
		
		Date date = new Date(1416816000000L);
		System.out.println(date);
	}

}
