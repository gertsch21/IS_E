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
 * Servlet implementation class Registriercontroller
 */
@WebServlet("/Registriercontroller")
public class Registriercontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registriercontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Im get vom Registriercontroller");
		request.getSession().invalidate();
		System.out.println("Weiterleiten zum Registrieren!");
		request.getRequestDispatcher("Registrieren.jsp").include(request, response);
		response.setContentType("text/html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Benutzerverwaltung benver = Benutzerverwaltung.getInstance();
		
		//zum einmaligen Einfügen eines Forschers und eines Admins, soll nur so möglich sein.
		//benver.adminAnlegen("adminVorname", "adminNachname", "admin@ibuy.com", "Waehringer Strasse 3", "ibuy city", "admin1", "admin1",4000);
		//benver.forscherAnlegen("forscherVorname", "forscherNachname", "forscher@ibuy.com", "Waehringer Strasse 3", "ibuy city", "forscher", "forscher",3000);
		
		
		if(request.getParameter("username")==null){//registrierbutton gedrückt
			System.out.println("RegistrierController: keinUsername:Weiterleiten zum Registrieren!");
			request.getRequestDispatcher("Registrieren.jsp").include(request, response);
			response.setContentType("text/html");
			return;
		}
		else{
			String vorname=request.getParameter("vorname");
			String nachname=request.getParameter("nachname");
			String email=request.getParameter("email");
			String land=request.getParameter("land");
			String plzString=request.getParameter("plz");
			String wohnort=request.getParameter("wohnort");
			String strasse=request.getParameter("strasse");
			String hausNrString=request.getParameter("nummer");
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String passwordW=request.getParameter("passwordW");
			int hausNr = 0;
			int plz = 0;
			try{
				hausNr = Integer.parseInt(hausNrString);
				plz = Integer.parseInt(plzString);
			}catch(Exception e){
				request.getSession(true).setAttribute("fehler", "Fehler: Hausnummer ist keine Nummer!");
				System.out.println("RegistrierungsController: Hausnummer ist keine Nummer!");
				request.getRequestDispatcher("Registrieren.jsp").include(request, response);
				response.setContentType("text/html");
				return;
			}
			
			if(username.length()<=5  || password.length()<=5 ){
				request.getSession(true).setAttribute("fehler", "Fehler: Username od. Passwort zu kurz!");
				System.out.println("RegistrierungsController: Pwd od. Username  <  5 Zeichen!");
				request.getRequestDispatcher("Registrieren.jsp").include(request, response);
				response.setContentType("text/html");
				return;
			}
			
			//Wiederholtes Passwort nicht korrekt
			if(!password.equals(passwordW) ){
				request.getSession(true).setAttribute("fehler", "Fehler: Passwortwiederholung nicht korrekt!");
				System.out.println("RegistrierungsController: Passwortwiederholung nicht korrekt!!");
				request.getRequestDispatcher("Registrieren.jsp").include(request, response);
				response.setContentType("text/html");
				return;
			}
			
			//Username enthält Abstände
			if(username.length()!=username.replaceAll(" ","").length()){
				request.getSession(true).setAttribute("fehler", "Fehler: Username darf keine Leerzeichen enthalten!");
				System.out.println("RegistrierungsController: Leerzeichen im Username!");
				response.sendRedirect("Registrieren.jsp");
				return;
			}
			
			
			//Nachdem Benutzer angelegt wurde, wird er automatisch(nicht über Login) zur Hauptseite.jsp weitergeleitet.
			if(benver.benutzerAnlegen(vorname, nachname, email, land, plz, wohnort, strasse, hausNr, username, password)){
				HttpSession session = request.getSession(true);
				session.setAttribute("username", username);
				System.out.println("RegistrierungsController: Person angelegt: "+vorname+" "+nachname+" "+email+" "+strasse+" "+wohnort+" "+username+" "+password);
				session.setAttribute("fehler", "");
				request.getRequestDispatcher("Hauptseite.jsp").include(request, response);
				response.setContentType("text/html");
				return;
			}
			//eingabe nicht erfolgreich:
			else{
				System.out.println("RegistrierungsController: Person konnte nicht angelegt werden: "+vorname+" "+nachname+" "+email+" "+strasse+" "+wohnort+" "+username+" "+password);
				response.sendRedirect("Login.jsp");
			}
		}
		
		
	
	}

}
