/**
 * 
 */
package model;

import java.util.UUID;

/**
 * @author Gerhard
 *
 */
public class Kunde extends Benutzer {

	private String birthday;
	private String sex;
	
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
	public Kunde(String uName, UUID usrID, String password, String vorname,
			String nachname, String email, String land, int plz, String ort, String strasse,
			int hausNr, String birthday, String sex) {
		super(uName, usrID, password, vorname, nachname, email, land, plz, ort,
				strasse,hausNr);
		this.birthday = birthday;
		this.sex = sex;
		
	}

	
//getters
	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	
//setters
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String toString(){
		String s = super.toString();
		return s+", birthday: "+birthday+", Geschlecht: "+sex;
	}

}
