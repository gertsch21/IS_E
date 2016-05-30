/**
 * 
 */
package management;

import dao.BestellungDAO;
import dao.DBBestellungDAO;

/**
 * @author Katrin
 *
 */
public class Bestellungsmanagement {
	private static Bestellungsmanagement bestellungsmanagementInstance=null;
	private BestellungDAO dao;
	
	/**
	 * Konstruktor der Bestellungsmanagementverwaltung
	 */
	private Bestellungsmanagement() {
		this.dao = new DBBestellungDAO();
	}
	

	/**
	 *  Singleton Design-pattern
	 * @return Referenz auf das Benutzerverwaltungsobjekt
	 */
	public static Bestellungsmanagement getInstance(){
		if(bestellungsmanagementInstance == null) bestellungsmanagementInstance = new Bestellungsmanagement();
		return bestellungsmanagementInstance;
	}
	
	//Hier sollte noch die Bestellungslogik eingefügt werden
}
