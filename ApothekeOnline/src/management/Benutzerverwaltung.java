/**
 * 
 */
package management;

import java.util.List;
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
	
	public boolean pruefeLogin(String username,String password){
		
		try{
			Benutzer p = dao.getBenutzerByUName(username);
		
			System.out.println("Prüfe login von: "+username+", korrektes pwd: "+p.getPassword());
		
			
			if(p.getPassword().equals(password)){
					return true;
			}
			return false;//falls kombi nicht passt
		}catch(NullPointerException e){
			System.out.println("Benutzerverwaltung: Benutzer mit dem Username '"+username+"' nicht gefunden");
			return false;//null, da p null ist und p.getPassword aufgerufen wird(es gibt den benutzer nicht)
		}
	}
	
	/**
	 * In dierer Methode wird ein vorhandener Benutzer gelöscht.
	 * 
	 * @param username Der eineutige Username des neuen Benutzers 
	 * @return Falls erfolgreich wird true rückgegeben, bei einem Fehler: false
	 */
	public boolean benutzerloeschen(String username){//Achtung person nicht benutzer wird gelöscht!!!
		return this.dao.loescheBenutzer(username);
	}
	
	public List<Benutzer> getAlleBenutzer(){
		return dao.getBenutzerList();
	}
	
	public Benutzer getBenutzerByUname(String uName){
		return dao.getBenutzerByUName(uName);
	}

}