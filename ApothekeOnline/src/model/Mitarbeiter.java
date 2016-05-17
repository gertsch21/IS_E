/**
 * 
 */
package model;

import java.util.UUID;

/**
 * @author Gerhard
 *
 */
public class Mitarbeiter extends Benutzer {

	private int staffNo;
	private int sallary; //in cent
	
	
	/**
	 * @param uName
	 * @param usrID
	 * @param password
	 * @param vorname
	 * @param nachname
	 * @param email
	 * @param land
	 * @param plz
	 * @param ort
	 * @param hausNr
	 */
	public Mitarbeiter(String uName, UUID usrID, String password,
			String vorname, String nachname, String email, String land,
			int plz, String ort, String strasse, int hausNr, int staffNo, int sallary) {
		super(uName, usrID, password, vorname, nachname, email, land, plz, ort,
				strasse, hausNr);
		
		this.staffNo = staffNo;
		this.sallary = sallary;
		
	}

	
//getters
	/**
	 * @return the staffNo
	 */
	public int getStaffNo() {
		return staffNo;
	}

	/**
	 * @return the sallary
	 */
	public int getSallary() {
		return sallary;
	}

	
//setters
	/**
	 * @param staffNo the staffNo to set
	 */
	public void setStaffNo(int staffNo) {
		this.staffNo = staffNo;
	}

	/**
	 * @param sallary the sallary to set
	 */
	public void setSallary(int sallary) {
		this.sallary = sallary;
	}

	
	
}
