/**
 * 
 */
package dao;

import java.util.List;
import model.*;

/**
 * @author Katrin Interface zum Zugriff auf die jeweilige Datenbank. Definition
 *         der wichtigsten Funktionen. Funktionen werden im Management
 *         umgesetzt.
 */
public interface BestellungDAO {
	/**
	 * Erm�glicht, dass Speichern einer �bergebenen Position p in der Datenbank.
	 * Retourniert false wenn ein Fehler auftritt.
	 * 
	 * @param p
	 * @return true/false: Boolean
	 */
	public boolean speicherePosition(Position p);

	/**
	 * Erm�glicht das Speichern einer �bergebenen Bestellung b in der Datenbank.
	 * Retourniert false wenn ein Fehler auftritt.
	 * 
	 * @param b
	 * @return true/false: Boolean
	 */
	public boolean speichereBestellung(Bestellung b);

	/**
	 * Gibt eine Liste mit allen Positionen einer Bestellung, welche anhand der
	 * �bergebenen BestellungsID oID identifiziert wird retour.
	 * 
	 * @param oID:
	 *            String
	 * @return Liste mit Positionen einer Bestellung
	 */
	public List<Position> getPositionListbyBestellung(String oID);

	/**
	 * Gibt eine Liste mit allen Bestellungen retour.
	 * 
	 * @return Liste mit Bestellungen
	 */
	public List<Bestellung> getBestellungList();

	/**
	 * Liefert eine Position aus der Datenbank retour, welche anhand der
	 * �bergebenen BestellungsID oID und der PositionsNr pID identifiziert wird.
	 * 
	 * @param pID:
	 *            int
	 * @param oID:
	 *            String
	 * @return Position
	 */
	public Position getPositionByID(int pID, String oID);

	/**
	 * Liefert eine Position aus der Datenbank retour, welche anhand der
	 * �bergebenen BestellungsID oID und der PositionsNr pID identifiziert wird
	 * 
	 * @param oID:
	 *            String
	 * @return Bestellung
	 */
	public Bestellung getBestellungByID(String oID);

	/**
	 * L�scht die Position an der �bergebenen Positionsnummer pID aus der
	 * Bestellung mit der �bergebenen BestellungID oID. Liefert bei Fehlern
	 * false retour.
	 * 
	 * @param pID:
	 *            int
	 * @param oID:
	 *            String
	 * @return true/false: Boolean
	 */
	public boolean loeschePosition(int pID, String oID);

	/**
	 * L�scht die Bestellung mit der �bergebenen BestellungsID oID aus der
	 * Datenbank. Liefert false wenn ein Fehler auftritt.
	 * 
	 * @param oID:
	 *            String
	 * @return true/false: Boolean
	 */
	public boolean loescheBestellung(String oID);
}
