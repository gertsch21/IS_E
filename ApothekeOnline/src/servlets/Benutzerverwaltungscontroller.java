package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import management.Benutzerverwaltung;
import model.Benutzer;

/**
 * Servlet implementation class Benutzerverwaltungscontroller
 */
@WebServlet("/Benutzerverwaltungscontroller")
public class Benutzerverwaltungscontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Benutzerverwaltungscontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Benutzerverwaltungscontroller: Weiterleiten zu Login.jsp");
		request.getRequestDispatcher("Login.jsp").include(request, response);
		response.setContentType("text/html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Benutzerverwaltung benver = Benutzerverwaltung.getInstance();

		if(request.getParameter("zuLoeschen") != null){
			benver.loescheKunden(request.getParameter("zuLoeschen"));
		}
		
		
		List<Benutzer> alleKunden = benver.getAlleKunden();
		
		request.getSession().setAttribute("alleKunden", alleKunden); //Benutzer an JSP übergeben
		
		request.getRequestDispatcher("Kundenanzeige.jsp").include(request, response);
		response.setContentType("text/html");
		
	}

}
