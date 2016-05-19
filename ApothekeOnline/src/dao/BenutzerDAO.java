/**
 * 
 */
package dao;

import java.util.List;

import model.*;

/**
 * @author Gerhard
 * Mithilfe von diesem Interface soll man auf die jeweilige Datenbank zugreifen können. 
 */
public interface BenutzerDAO {
	public boolean speichereBenutzer(Benutzer b);
	public boolean speichereKunde(Kunde k);
	public boolean speichereMitarbeiter(Mitarbeiter m);
	public List<Benutzer> getBenutzerList();
	public Benutzer getBenutzerByUName(String uName);
	public boolean loescheBenutzer(String uName);
	public Kunde getKundeByUsername(String uName);
	public Mitarbeiter getMitarbeiterByUsername(String uName);
}


//TO_DATE('2016/05/09', 'yyyy/mm/dd')