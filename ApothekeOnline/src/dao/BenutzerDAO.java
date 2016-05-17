/**
 * 
 */
package dao;

import java.util.List;

import model.Benutzer;

/**
 * @author Gerhard
 *
 */
public interface BenutzerDAO {
	public boolean speichereBenutzer(Benutzer b);
	public List<Benutzer> getBenutzerList();
	public Benutzer getBenutzerByUName(String uName);
	public boolean loescheBenutzer(String uName);
}
