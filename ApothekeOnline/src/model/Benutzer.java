package model;

import java.util.UUID;

/**
 * @author Gerhard
 *
 */
public class Benutzer {
	private String uName;
	private UUID usrID;
	private String password;
	private String vorname;
	private String nachname;
	private String email;
	private String land;
	private int plz;
	private String ort;
	private String strasse;
	private int hausNr;
	
	
	
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
	public Benutzer(String uName, UUID usrID, String password, String vorname,
			String nachname, String email, String land, int plz, String ort, String strasse,
			int hausNr) {
		super();
		this.uName = uName;
		this.usrID = usrID;
		this.password = password;
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
		this.land = land;
		this.plz = plz;
		this.ort = ort;
		this.strasse = strasse;
		this.hausNr = hausNr;
	}
	
	
	public String toString(){
		String s = "Uname: "+uName+", Name: "+vorname+" "+nachname+", email: "+email;
		return s;
	}
//getters	
	
	/**
	 * @return the uName
	 */
	public String getuName() {
		return uName;
	}
	/**
	 * @return the usrID
	 */
	public UUID getUsrID() {
		return usrID;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @return the vorname
	 */
	public String getVorname() {
		return vorname;
	}
	/**
	 * @return the nachname
	 */
	public String getNachname() {
		return nachname;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the land
	 */
	public String getLand() {
		return land;
	}
	/**
	 * @return the plz
	 */
	public int getPlz() {
		return plz;
	}
	/**
	 * @return the ort
	 */
	public String getOrt() {
		return ort;
	}
	/**
	 * @return the hausNr
	 */
	public int getHausNr() {
		return hausNr;
	}

	
	
/**
	 * @return the strasse
	 */
	public String getStrasse() {
		return strasse;
	}

	/**
	 * @param strasse the strasse to set
	 */
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	//setters
	/**
	 * @param uName the uName to set
	 */
	public void setuName(String uName) {
		this.uName = uName;
	}
	/**
	 * @param usrID the usrID to set
	 */
	public void setUsrID(UUID usrID) {
		this.usrID = usrID;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param vorname the vorname to set
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	/**
	 * @param nachname the nachname to set
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @param land the land to set
	 */
	public void setLand(String land) {
		this.land = land;
	}
	/**
	 * @param plz the plz to set
	 */
	public void setPlz(int plz) {
		this.plz = plz;
	}
	/**
	 * @param ort the ort to set
	 */
	public void setOrt(String ort) {
		this.ort = ort;
	}
	/**
	 * @param hausNr the hausNr to set
	 */
	public void setHausNr(int hausNr) {
		this.hausNr = hausNr;
	}
	
	
	
}
