/**
 * 
 */
package management;

import java.util.UUID;

import model.Benutzer;
import dao.BenutzerDAO;
import dao.DBBenutzerDAO;

/**
 * @author Gerhard
 *
 */
public class Benutzerverwaltung {
	private static Benutzerverwaltung benutzerverwaltungInstance=null;
	private BenutzerDAO dao;
	
	/**
	 * Konstruktor der Benutzerverwaltung, hier wird die private Variable vom Typ PersonDAO angelegt.
	 * 
	 */
	private Benutzerverwaltung() {
		dao = new DBBenutzerDAO();
	}
	
	/**
	 *  Singleton Design-pattern
	 *  
	 * @return Referenz auf das Benutzerverwaltungsobjekt
	 */
	public static Benutzerverwaltung getInstance(){
		if(benutzerverwaltungInstance == null) benutzerverwaltungInstance = new Benutzerverwaltung();
		return benutzerverwaltungInstance;
	}

//zum testen
	public boolean benutzerAnlegen(String vorname, String nachname, String email, String land, int plz, String wohnort, String strasse, int hausNr, String username, String password){//Verbesserungswürdig!!!
		UUID id = UUID.randomUUID();
	System.out.println("benutzerAnlegen: "+id+", "+vorname+" "+nachname+", "+email+", "+land+" "+plz+" "+" "+wohnort+" "+strasse+", "+username+" "+password);
		return dao.speichereBenutzer(new Benutzer(username,id,password,vorname,nachname,email,land,plz,wohnort,strasse,hausNr));
	}

}