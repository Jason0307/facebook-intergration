<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Sign In</title>
</head>
<body>
	<form action="<c:url value="/signin/facebook" />" method="POST">
		<button type="submit">Sign in with Facebook</button>
		<input type="hidden" name="scope"
			value="user_status,user_friends,email,read_friendlists,read_stream,publish_stream,publish_actions,offline_access" />
	</form>
	<div>
		<div><button class="share">Share</button></div>
		<div><img src="<%=request.getContextPath()%>/resources/images/1_008.jpg"/></div>
		<div><button class="apprequest">Apprequest</button></div>
	</div>
	<!-- <div class="fb-like" data-send="true" data-width="450"
		data-show-faces="true"></div> -->
   <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
	<script>
		window.fbAsyncInit = function() {
			FB.init({
				appId : '755442001187377',
				xfbml : true,
				version : 'v2.1'
			});

			$(".share").click(function(){
				FB.ui({
					  method: 'share',
					  href: 'https://wechatplatform.herokuapp.com/',
					}, function(response){});
			});
			
			$(".apprequest").click(function(){
				FB.ui({method: 'apprequests',
				      message: 'Hello,boy!',
				      to: '1569534989944592'
				    }, function(response){
				        console.log(response);
				    });
			});
			
			
			/* FB.ui(
				{
				  method: 'share_open_graph',
				  action_type: 'og.likes',
			       action_properties: JSON.stringify({
			       object:'https://developers.facebook.com/docs/',
			     })

				}, function(response){});*/
			/* FB.getLoginStatus(function(response) {
				if (response.status === 'connected') {
					console.log('Logged in.');
				} else {
					FB.login();
				}
			}); */
			
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
