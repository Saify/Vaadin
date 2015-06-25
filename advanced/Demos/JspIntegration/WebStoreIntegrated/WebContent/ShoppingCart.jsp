<%@page import="backend.Backend"%>
<%@page import="java.util.*" %>
<%@page import="data.*" %>
<jsp:useBean id="user" class="data.User" scope="session"/>
<html>
<script>
    function quantityChangedFor(itemId) {
      var newQuantity = $('#quantity' + itemId).val();
      $.get('ActionServlet',{shoppingItemId:itemId, quantity:newQuantity},function(responseText) {
    	  var responses = responseText.split(",");
    	  var totalprice = responses[0];
    	  var itemId = responses[1];
    	  var itemTotalPrice = responses[2];
          updateTotalPrice(totalprice);
          updateShoppingItem(itemId, itemTotalPrice);
      });
    }
    function updateShoppingItem(itemId, itemTotalPrice) {
    	$('#totalprice' + itemId).text(itemTotalPrice);
    }
 </script>
<body>
Shopping cart for <%= user.getUsername()%>
<TABLE BORDER=1>
	<TR>
		<TH>Product</TH>
		<TH>Quantity</TH>
		<TH>Unit price</TH> 
		<TH>Total price</TH> 
	</TR>
<%
	List<ShoppingItem> shoppingCart = Backend.getShoppingCartFor(user);
    for (ShoppingItem item : shoppingCart) {
    	int id = item.getId();
        %>
        <TR>
        <TD><%= item.getProduct() %></TD>
        <TD><INPUT id="quantity<%=id%>" type="text" value=<%= item.getQuantity() %> onchange="quantityChangedFor('<%=id%>');">
        <TD><%= item.getUnitPrice() %></TD>
        <TD id="totalprice<%=id%>"><%= item.getTotalPrice() %></TD>
        </TR>
        <%
    }
%>
</TABLE>


</body>
</html>
