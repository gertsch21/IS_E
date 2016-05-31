package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import management.Produktmanagement;

/**
 * Servlet implementation class ShopController
 */
@WebServlet("/ShopController")
public class ShopController extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String productID = request.getParameter("product_id"); 
		HttpSession session = request.getSession();
		Map<String, Integer> cart = new HashMap();


		if ( session.getAttribute("cart") != null ){
			// if session variable "cart" is already set, store content in local cart
			cart = (Map<String, Integer>) session.getAttribute("cart");
		} 

		if ( cart.containsKey(productID) ) {
			int quantity = (Integer) cart.get(productID);
			cart.put(productID, quantity + 1);
		
		} else {
			cart.put(productID, 1);
		}
		
		session.setAttribute("cart", cart);

		
		// ---------------- Warenkorb ------------------
		
		StringBuffer cartOut = new StringBuffer();
		
	       	
	        	cartOut.append(""
	        			+ "		<table class=\"cart\">"
    	        		+ "			<tr>");
	        	
	        	 // Iterate over all Key-Value-Pairs
	        	 Iterator it = cart.entrySet().iterator();
	        	 
	        	 while (it.hasNext()) {
	        	    	Map.Entry pair = (Map.Entry)it.next();
	        	    	
	        	        cartOut.append(""
	        	        		+ "<td>" + pair.getKey() + "</td>"
	        	        		+ "<td>&nbsp; x &nbsp;</td>"
	        	        		+ "<td>" + pair.getValue() + "</td>");
	        	    
	        	        it.remove(); // avoids a ConcurrentModificationException
	        	 }
        	 
        	    cartOut.append("	</tr>"
    	        		+ "		</table>");
		
		session.setAttribute("cartOut", cartOut);
		
		
		
		request.getRequestDispatcher("HauptseiteKunde.jsp").include(request, response);
		
		return;
		
	
	}
	

}
