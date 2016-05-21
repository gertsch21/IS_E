/**
 * 
 */
package dao;

import java.util.List;

import model.*;

/**
 * @author Gerhard
 * Mithilfe von diesem Interface soll man auf die jeweilige Datenbank zugreifen können. 
 * Die wichtigsten Funktionen werden hier definiert. Mithilfe des Managements wird dann mit diesen
 * "Low-Level" Funktionen umgegangen.
 */
public interface BenutzerDAO {
	//soll nicht nach außen möglich sein
	//public boolean speichereBenutzer(Benutzer b);
	public boolean speichereKunde(Kunde k);
	public boolean speichereMitarbeiter(Mitarbeiter m);
	
	public List<Benutzer> getBenutzerList();
	public Benutzer getBenutzerByUName(String uName);
	public Kunde getKundeByUsername(String uName);
	public Mitarbeiter getMitarbeiterByUsername(String uName);
	
	//soll nicht nach außen möglich sein
	//public boolean loescheBenutzer(String uName);
	public boolean loescheKunde(String uName);
	public boolean loescheMitarbeiter(String uName);
	
}


//FÜR ORACLE!!!!! TO_DATE('2016/05/09', 'yyyy/mm/dd')