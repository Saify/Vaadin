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
<title>Web Store</title>
<style type="text/css">html, body {height:100%;margin:0;}</style>
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
		<jsp:include page="ShoppingCart.jsp"/>
	</div>
</body>
</html>
