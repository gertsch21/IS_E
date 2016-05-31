package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	 * Falls ein Get request kommt, soll auf die Loginseite verwiesen werden.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		System.out.println("LoginController: Weiterleiten zum Login!");
		request.getRequestDispatcher("Login.jsp").include(request, response);
		response.setContentType("text/html");
	}

	/**
	 * Hier wird geprüft ob die eingegebenen Daten korrekt sind, falls ja, war der Login erfolgreich und es wird zur Hauptseite verwiesen,
	 * falls nein, dann wird die jeweilige Fehlermeldung ausgegeben.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Benutzerverwaltung benver = Benutzerverwaltung.getInstance();
		String username = request.getParameter("username"); 
		String password = request.getParameter("password");
		System.out.println("LoginController: Pruefe Login: '"+username+"' mit PWD:'"+password+".");
		
		if(benver.getCustomerByUname(username) !=null){
			if(benver.getCustomerByUname(username).getPassword().equals(password)){
				request.getSession().invalidate();
				System.out.println("LoginController: Erfolgreiche Pruefung(istKunde): Weiterleiten zur Hauptseite des Kunden!");
				HttpSession session = request.getSession(true);
				session.setAttribute("username", username);
				session.setAttribute("fehler", null);
				request.getRequestDispatcher("HauptseiteKunde.jsp").include(request, response);
				response.setContentType("text/html");
				return;
			}
			System.out.println("Logincontroller: Falsches Passwort eingegeben von '"+username+"'");
		}
		if(benver.getEmployeeByUname(username) != null){
			if(benver.getEmployeeByUname(username).equals(password)){
				request.getSession().invalidate();
				System.out.println("LoginController: Erfolgreiche Pruefung(istMitarbeiter): Weiterleiten zur Hauptseite des Mitarbeiters!");
				HttpSession session = request.getSession(true);
				session.setAttribute("username", username);
				session.setAttribute("fehler", null);
				request.getRequestDispatcher("HauptseiteMitarbeiter.jsp").include(request, response);
				response.setContentType("text/html");
				return;
			}
			System.out.println("Logincontroller: Falsches Passwort eingegeben von '"+username+"'");
		}

//Falls nichts von beiden
		request.getSession().invalidate();
		System.out.println("LoginController: Weiterleiten zum Login!");
		request.getSession(true).setAttribute("fehler", "Falsche Username/PWD Kombi!");
		request.getRequestDispatcher("Login.jsp").include(request, response);
		response.setContentType("text/html");
	}

}
