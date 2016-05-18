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
	public List<Benutzer> getBenutzerList();
	public Benutzer getBenutzerByUName(String uName);
	public boolean loescheBenutzer(String uName);
	public Kunde getKundeByUsername(String uName);
	public Mitarbeiter getMitarbeiterByUsername(String uName);
}
