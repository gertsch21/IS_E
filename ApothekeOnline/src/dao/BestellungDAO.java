/**
 * 
 */
package dao;

import java.util.List;
import model.*;

/**
 * @author Katrin
 * Interface zum Zugriff auf jeweilige Datenbank. Definition der wichtigsten Funktionen.
 * Funktionen werden im Management umgesetzt.
 */
public interface BestellungDAO {
	public boolean speicherePosition(Position p);
	public boolean speichereWarenkorb(Warenkorb w);
	
	public List<Position> getPositionListbyWarenkorb(int wID);
	public List<Position> getPositionListAll();
	public List<Warenkorb> getWarenkorbList();
	public Position getPositionbyID(int pID, int wID);
	public Warenkorb getWarenkorbbyID(int id);
	
	public boolean loeschePosition(int wID, int pID);
	public boolean loescheWarenkorb(int wID);
}
