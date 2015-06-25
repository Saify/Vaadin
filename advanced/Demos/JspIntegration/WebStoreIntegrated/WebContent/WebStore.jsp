<%@page import="backend.Backend"%>
<jsp:useBean id="user" class="data.User" scope="session" />
<jsp:setProperty name="user" property="username" />
<%
	if (user.getUsername() == null) {
		response.sendRedirect("login.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=10;chrome=1" />
<style type="text/css">html, body {height:100%;margin:0;}</style>
<title>Web Store</title>
<link href="./css/style.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<script>
	function updateTotalPrice(price) {
		$('#totalprice').text(price);
	}
</script>
</head>
<body>
	<div class="menu">
		Shopping cart: <b id="totalprice"> <%=Backend.getShoppingCartTotalPriceFor(user)%></b>
	</div>
	<div class="content">
		<div id="VaadinShoppingCart"
			class=" v-app webstoreshoppingcart">
			<div class=" v-app-loading"></div>
			<noscript>You have to enable javascript in your browser to
				use an application built with Vaadin.</noscript>
		</div>
	</div>
	<iframe tabindex="-1" id="__gwt_historyFrame"
		style="position: absolute; width: 0; height: 0; border: 0; overflow: hidden"
		src="javascript:false"></iframe>
	<script type="text/javascript" src="./VAADIN/vaadinBootstrap.js"></script>
	<script type="text/javascript">
		//<![CDATA[
		if (!window.vaadin)
			alert("Failed to load the bootstrap javascript: ./VAADIN/vaadinBootstrap.js");
		if (typeof window.__gwtStatsEvent != 'function') {
			vaadin.gwtStatsEvents = [];
			window.__gwtStatsEvent = function(event) {
				vaadin.gwtStatsEvents.push(event);
				return true;
			};
		}
		vaadin
				.initApplication(
						"VaadinShoppingCart",
						{
							"browserDetailsUrl": "./ShoppingCart/",
							"serviceUrl": "./ShoppingCart/",
							"heartbeatInterval" : 300,
							"versionInfo" : {
								"vaadinVersion" : "7.1.10"
							},
							"vaadinDir" : "./VAADIN/",
							"authErrMsg" : {
								"message" : "Take note of any unsaved data, and <u>click here<\/u> to continue.",
								"caption" : "Authentication problem"
							},
							"widgetset" : "com.vaadin.DefaultWidgetSet",
							"theme" : "reindeer",
							"debug" : true,
							"comErrMsg" : {
								"message" : "Take note of any unsaved data, and <u>click here<\/u> to continue.",
								"caption" : "Communication problem"
							},
							"standalone" : false,
							"sessExpMsg" : {
								"message" : "Take note of any unsaved data, and <u>click here<\/u> to continue.",
								"caption" : "Session Expired"
							}
						});
		//]]>
	</script>
</body>
</html>
