package webstore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.Backend;
import data.User;

/**
 * Servlet implementation class ActionServlet
 */
@WebServlet("/ActionServlet")
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int shoppingItemId = Integer.parseInt(request.getParameter("shoppingItemId"));
	    int quantity = Integer.parseInt(request.getParameter("quantity"));
	    User user = (User) request.getSession().getAttribute("user");
	    
	    Backend.setQuantity(shoppingItemId, quantity);
	    double price = Backend.getShoppingCartTotalPriceFor(user);
	    double shoppingItemTotalPrice = Backend.getTotalPriceFor(shoppingItemId);
	    
	    response.setContentType("text/plain");  
	    response.setCharacterEncoding("UTF-8");
	    String responses = String.valueOf(price) + "," + shoppingItemId + ","+ shoppingItemTotalPrice;
	    response.getWriter().write(responses); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
