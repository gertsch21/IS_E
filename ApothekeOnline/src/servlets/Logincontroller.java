package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import management.Benutzerverwaltung;

/**
 * Servlet implementation class Logincontroller
 */
@WebServlet("/Logincontroller")
public class Logincontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logincontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		System.out.println("LoginController: Weiterleiten zum Login!");
		request.getRequestDispatcher("Login.jsp").include(request, response);
		response.setContentType("text/html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Benutzerverwaltung benver = Benutzerverwaltung.getInstance();
		String username = request.getParameter("username"); 
		String password = request.getParameter("password");
		System.out.println("LoginController: Pruefe Login: '"+username+"' mit PWD:'"+password+"'.");
		if(!benver.pruefeLogin(username, password)){
			request.getSession().invalidate();
			System.out.println("LoginController: Weiterleiten zum Login!");
			request.getRequestDispatcher("Login.jsp").include(request, response);
			response.setContentType("text/html");
		}
		else{
			request.getSession().invalidate();
			System.out.println("LoginController: Erfolgreiche Pruefung: Weiterleiten zur Hauptseite!");
			request.getRequestDispatcher("Hauptseite.jsp").include(request, response);
			response.setContentType("text/html");
		}
	}

}
