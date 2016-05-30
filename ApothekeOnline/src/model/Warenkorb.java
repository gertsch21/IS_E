/**
 * 
 */
package model;

/**
 * @author Katrin, stellt den Warenkorb der Kunden dar
 *
 */
public class Warenkorb {
	int [] positionsID;
	int kundenID;
	int warenkorbID;
	
	public Warenkorb(int[] positionsID, int kundenID, int warenkorbID) {
		this.positionsID = positionsID;
		this.kundenID = kundenID;
		this.warenkorbID = warenkorbID;
	}

	/**
	 * @return the positionsID
	 */
	public int[] getPositionsID() {
		return positionsID;
	}

	/**
	 * @return the kundenID
	 */
	public int getKundenID() {
		return kundenID;
	}

	/**
	 * @return the warenkorbID
	 */
	public int getWarenkorbID() {
		return warenkorbID;
	}

	/**
	 * @param positionsID the positionsID to set
	 */
	public void setPositionsID(int[] positionsID) {
		this.positionsID = positionsID;
	}

	/**
	 * @param kundenID the kundenID to set
	 */
	public void setKundenID(int kundenID) {
		this.kundenID = kundenID;
	}

	/**
	 * @param warenkorbID the warenkorbID to set
	 */
	public void setWarenkorbID(int warenkorbID) {
		this.warenkorbID = warenkorbID;
	}
	
}