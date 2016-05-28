/**
 * 
 */
package model;

/**
 * @author Katrin
 * Deckt Positionen einer Bestellung ab. Produkt mit Menge und Gesamtpreis vereint.
 *
 */
public class Position extends Produkt{
	private int positionsNr;
	private int menge;
	private double gesamtpreis;
	
	public Position (int prodID, String prodName, double price, String prodDescription, int categoryID, int positionNr, int menge, double gesamtpreis) {
		super(prodID, prodName, price, prodDescription, categoryID);
		this.positionsNr = positionNr;
		this.menge = menge;
		this.gesamtpreis = gesamtpreis;
	}
	
	/**
	 * @return positionsNr
	 */
	public int getPositionsNr() {
		return positionsNr;
	}

	/**
	 * @return menge
	 */
	public int getMenge() {
		return menge;
	}

	/**
	 * @return gesamtpreis
	 */
	public double getGesamtpreis() {
		return gesamtpreis;
	}

	/**
	 * @param positionsNr the positionsNr to set
	 */
	public void setPositionsNr(int positionsNr) {
		this.positionsNr = positionsNr;
	}

	/**
	 * @param menge the menge to set
	 */
	public void setMenge(int menge) {
		this.menge = menge;
	}

	/**
	 * @param gesamtpreis the gesamtpreis to set
	 */
	public void setGesamtpreis(double gesamtpreis) {
		this.gesamtpreis = gesamtpreis;
	}
}



