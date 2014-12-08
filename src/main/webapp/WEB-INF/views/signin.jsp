<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet" media="screen"
	href="<%=request.getContextPath()%>/resources/css/zzsc.css" />
<link rel="stylesheet" media="screen"
	href="<%=request.getContextPath()%>/resources/css/facebook.css" />
	<link rel="stylesheet" media="screen"
	href="<%=request.getContextPath()%>/resources/css/fdw-demo.css" />
	<link rel="stylesheet" media="screen"
	href="<%=request.getContextPath()%>/resources/css/style.css" />
<title>Sign In</title>
</head>
<body>
	<form class="signin-facebook"
		action="<c:url value="/signin/facebook" />" method="POST">
		<button type="submit" class="action-button">Sign in with
			Facebook</button>
		<input type="hidden" name="scope"
			value="user_status,user_friends,email,read_friendlists,read_stream,publish_stream,publish_actions,offline_access" />
	</form>
	<div class="form info">
		<div class="fs-title">Facebook Operations</div>
	</div>
	<div class="fb-operations">
		<div class="fb-oper">
			<button class="share action-button fb-btn">Share</button>
		</div>
		<div class="fb-oper">
			<button class="apprequest action-button fb-btn">Apprequest</button>
		</div>
		
		<div class="fb-oper">
			<button class="feed action-button fb-btn">Feed Share</button>
		</div>
		
		<div class="fb-oper">
			<button class="friends action-button fb-btn">Friends</button>
			<br/>
			<div class="fb-friends">
				<div id="container">
					<div class="freshdesignweb">
						<div class="image_grid portfolio_4col">
							<ul style="height: 495px;" id="list" class="portfolio_list da-thumbs">
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- <div class="fb-like" data-send="true" data-width="450"
		data-show-faces="true"></div> -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/jquery-v1.7.1.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/jquery-hover-effect.js"></script>
	<script>
		window.fbAsyncInit = function() {
			FB.init({
				appId : '755442001187377',
				xfbml : true,
				version : 'v2.1'
			});

			$(".share").click(function() {
				FB.ui({
					method : 'share',
					href : 'https://wechatplatform.herokuapp.com/',
				}, function(response) {
				});
			});

			$(".apprequest").click(function() {
				FB.ui({
					method : 'apprequests',
					message : 'Hello,boy!',
					to : '1569534989944592'
				}, function(response) {
					console.log(response);
				});
			});
			
			$(".feed").click(function() {
				FB.ui({
					  method: 'feed',
					  link: 'https://developers.facebook.com/docs/',
					  caption: 'An example caption',
					}, function(response){});
			});
			
			$(".friends").click(function(){
				$.ajax({
					url : "<%=request.getContextPath()%>/friends",
					async:false,
					type : "GET",
					dataType : "JSON",
					success : function(data) {
						var friends = data.data;
						var content = "";
						for (var i = 0; i < friends.length; i++) {
							content += '<li><img width="142" height="142" src="' + friends[i].picture.data.url + '"/>'
							        + '<article class="da-animate da-slideFromRight" style="display: block;">'
			                        + '<h3>' + friends[i].name + '</h3>'
			                        + '<em>Happy</em>'
			                        + '<span class="link_post"><a href="http://www.htmldrive.net"></a></span>'
			                        + '<span class="zoom"><a href="' + friends[i].picture.data.url + '"></a></span>'
			                        + '</article></li>';
								}
							$(".portfolio_list").html(content);
							$('ul.da-thumbs > li').hoverdir();
					}
				})
				
			});

			/* FB.ui(
				{
				  method: 'share_open_graph',
				  action_type: 'og.likes',
			       action_properties: JSON.stringify({
			       object:'https://developers.facebook.com/docs/',
			     })

				}, function(response){});*/
			FB.getLoginStatus(function(response) {
				if (response.status === 'connected') {
					console.log('Logged in.');
				} else {
					FB.login();
				}
			});

		};

		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id)) {
				return;
			}
			js = d.createElement(s);
			js.id = id;
			js.src = "https://connect.facebook.net/en_US/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>
</body>
</html>
