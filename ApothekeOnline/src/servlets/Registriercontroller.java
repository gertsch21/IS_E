package servlets;

import java.io.IOException;
import java.util.GregorianCalendar;

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
	 * Falls ein Get request kommt, soll auf die Registrierseite verwiesen werden.
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
	 * Hier wird geprüft ob die eingegebenen Daten korrekt sind, falls ja, war die Registrieren erfolgreich und es wird zur Hauptseite verwiesen,
	 * falls nein, dann wird die jeweilige Fehlermeldung ausgegeben.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Benutzerverwaltung benver = Benutzerverwaltung.getInstance();
		
		
		
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
			String birthday=request.getParameter("bday");
			
			String land=request.getParameter("land");
			String plzString=request.getParameter("plz");
			String wohnort=request.getParameter("wohnort");
			String strasse=request.getParameter("strasse");
			String hausNrString=request.getParameter("nummer");
			
			String geschlecht = request.getParameter("gender");
			
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String passwordW=request.getParameter("passwordW");
			
			
			//Splitte nach bindestrichen
			String[] geb = birthday.split("-");
			
			//Falls nicht mit Bindestrichen getrennt eingegeben wurde -> splitte nach punkten
			if(geb.length<3) geb=birthday.split("\\.");
		
			//reine Kontrollausgabe
			for(String i: geb)
				System.out.println("Geb: "+i);
			try{
				int jahr = Integer.parseInt(geb[0]);
				int monat = Integer.parseInt(geb[1]);
				int tag = Integer.parseInt(geb[2]);
				
				//Falls in falscher Reihenfolge eingegeben
				if(jahr<1900){//Falls Tag-Monat-Jahr Reihenfolge eingegeben wurde
					int zwischen =jahr;
					jahr=tag;
					tag=jahr;
				}
				
				//falls jemand vor 1900 geboren wurde, wird er nicht hier gespeichert
				if(jahr <1900) throw new Exception("Jahr vor 1900");
					
				//wenn 18 jahre, dann müsste er min in diesem jahr geboren worden sein
				int minAchtzehn = new GregorianCalendar().get(GregorianCalendar.YEAR)-18;
				GregorianCalendar calendar = new GregorianCalendar(jahr, monat,tag);
				if(!(jahr<(minAchtzehn))){
					request.getSession(true).setAttribute("fehler", "Sie muessen 18 sein!");
					throw new Exception("Da ist jemand noch nicht 18");
				}
				
				//in der notation tag.monat.jahr in DB speichern
				birthday = tag+"."+monat+"."+jahr;
				
			}catch(NullPointerException e){
				request.getSession(true).setAttribute("fehler", "Pruefen sie nochmal ihr Geburtsdatum!");
				System.out.println("RegistrierungsController: Geburtsdatum inkorrekt! "+e.getMessage());
				request.getRequestDispatcher("Registrieren.jsp").include(request, response);
				response.setContentType("text/html");
				return;
			}catch(Exception e){
				if(request.getSession(true).getAttribute("fehler")==null)
					request.getSession(true).setAttribute("fehler", "Pruefen sie nochmal ihr Geburtsdatum!");
				System.out.println("RegistrierungsController: Geburtsdatum inkorrekt! "+e.getMessage());
				request.getRequestDispatcher("Registrieren.jsp").include(request, response);
				response.setContentType("text/html");
				return;
			}
			
			System.out.println("RegistrierController: Post: geschlecht: "+geschlecht+", Geb: "+birthday);
			
			int hausNr = 0;
			int plz = 0;
			try{
				hausNr = Integer.parseInt(hausNrString);
				plz = Integer.parseInt(plzString);
			}catch(Exception e){
				request.getSession(true).setAttribute("fehler", "Hausnummer oder PLZ ist keine Nummer oder zu lang!");
				System.out.println("RegistrierungsController: Hausnummer oder PLZ ist keine Nummer!");
				request.getRequestDispatcher("Registrieren.jsp").include(request, response);
				response.setContentType("text/html");
				return;
			}
			
			if(username.length()<5  || password.length()<5 ){
				request.getSession(true).setAttribute("fehler", "Username od. Passwort zu kurz(mindestens 5 Zeichen benötigt)!");
				System.out.println("RegistrierungsController: Pwd od. Username  <  5 Zeichen!");
				request.getRequestDispatcher("Registrieren.jsp").include(request, response);
				response.setContentType("text/html");
				return;
			}
			
			//Wiederholtes Passwort nicht korrekt
			if(!password.equals(passwordW) ){
				request.getSession(true).setAttribute("fehler", "Passwortwiederholung nicht korrekt!");
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
			if(benver.kundeAnlegen(vorname, nachname, email, land, plz, wohnort, strasse, hausNr, username, password,birthday,geschlecht)){
				HttpSession session = request.getSession(true);
				session.setAttribute("username", username);
				session.setAttribute("message", "Wilkommen "+username+" bei uns!");
				System.out.println("RegistrierungsController: Kunde angelegt: "+vorname+" "+nachname+" "+email+" "+strasse+" "+wohnort+" "+username+" "+password);
				session.setAttribute("fehler", null);
				request.getRequestDispatcher("HauptseiteKunde.jsp").include(request, response);
				response.setContentType("text/html");
				return;
			}
			//eingabe nicht erfolgreich:
			else{
				System.out.println("RegistrierungsController: Person konnte nicht angelegt werden: "+vorname+" "+nachname+" "+email+" "+strasse+" "+wohnort+" "+username+" "+password);
				response.sendRedirect("Registrieren.jsp");
			}
		}
		
		
	
	}

}
