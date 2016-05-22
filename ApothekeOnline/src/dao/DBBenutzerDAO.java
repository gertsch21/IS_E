/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.*;

/**
 * @author Gerhard
 *
 * Diese Klasse ist dafür zuständig, die Daten aus der Datenbank zu holen und diese Managementklassen zu übergeben
 */
public class DBBenutzerDAO implements BenutzerDAO {
//Daten für DB Verbindung
	private final String dbUrl = "jdbc:mysql://localhost:3306/meineapotheketest";
	private final String user = "root";
	private final String pwd = "";
//Statements um Daten aus db zu holen(über SQL)
	private PreparedStatement saveKundeStmt;
	private PreparedStatement saveMitarStmt;
	private PreparedStatement saveUserStmt;
	private PreparedStatement loadKundeStmt;
	private PreparedStatement loadMitarStmt;
	private PreparedStatement loadUserStmt;
	private PreparedStatement loadUserStmtID;
	private PreparedStatement loadAllUserStmt;
	private PreparedStatement loadAllKundeStmt;
	
	private PreparedStatement deleteKundeStmt;
	private PreparedStatement deleteMitarStmt;
	private PreparedStatement deleteUserStmt;
	
	

	/**
	 * Im Konstruktor wird die Verbindung zur Datenbank hergestellt
	 * Es werden auch die Statements definiert, mit welchen man die Daten aus der Datenbank bekommt
	 * Achtung, die User werden aus der Datenbank mit dem Usernamen herausgeholt, wohingegen es beim Kunden und Mitarbeiter die UserID ist
	 */
	public DBBenutzerDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(dbUrl, user, pwd);
			
			saveUserStmt = con
					.prepareStatement("INSERT INTO user(usrID,uName,firstname,surname,email,pwd,street,city,country,zip,number) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			saveKundeStmt = con
					.prepareStatement("INSERT INTO customer(usrID,birthday,sex) VALUES (?, ?, ?)");
			saveMitarStmt = con
					.prepareStatement("INSERT INTO employee(usrID,staffNo,sallary) VALUES (?, ?, ?)");
			
			loadUserStmtID = con.prepareStatement("SELECT * FROM user WHERE usrID=?");
			loadUserStmt = con.prepareStatement("SELECT * FROM user WHERE uName=?");
			loadKundeStmt = con.prepareStatement("SELECT * FROM customer WHERE usrID=?");
			loadMitarStmt = con.prepareStatement("SELECT * FROM employee WHERE usrID=?");
			
			loadAllUserStmt = con.prepareStatement("SELECT * FROM user");
			loadAllKundeStmt = con.prepareStatement("SELECT * FROM customer");
			
			deleteUserStmt = con.prepareStatement("DELETE FROM user WHERE usrID=?");
			deleteKundeStmt = con.prepareStatement("DELETE FROM customer WHERE usrID=?");
			deleteMitarStmt = con.prepareStatement("DELETE FROM employee WHERE usrID=?");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Verbindungsaufbau zur DB nicht möglich!!");
		}

	}

	/*
	 * 
	 * zum speichern in der Tabelle Benutzer, benötigt, um Kunden und Mitarbeiter zu speichern
	 */
	private boolean speichereBenutzer(Benutzer b) {
		try {
			System.out.println("DBBenutzerDB:speichereBenutzer: " + b.getUsrID().toString()
					+ ", " + b.getuName() + ", " + b.getNachname());

			saveUserStmt.setString(1, b.getUsrID().toString());
			saveUserStmt.setString(2, b.getuName());
			saveUserStmt.setString(3, b.getVorname());
			saveUserStmt.setString(4, b.getNachname());
			saveUserStmt.setString(5, b.getEmail());
			saveUserStmt.setString(6, b.getPassword());
			saveUserStmt.setString(7, b.getStrasse());
			saveUserStmt.setString(8, b.getOrt());
			saveUserStmt.setString(9, b.getLand());
			saveUserStmt.setInt(10, b.getPlz());
			saveUserStmt.setInt(11, b.getHausNr());
			
			
			saveUserStmt.executeUpdate();
			return true;
		}catch(NullPointerException e){
			System.out.println("DBBenutzerDB:speichereBenutzer: Übergebener Benutzer(Parameter) ist null!!!");
			return false;
		}catch (Exception e) {
			System.out.println("DBBenutzerDB:speichereBenutzer: Fehler beim einfuegen des Benutzers zBSchon vorhanden,...("+e.getMessage()+")!!!");
			return false;
		}
	}

	@Override
	public boolean speichereKunde(Kunde k) {
		if(!speichereBenutzer(k))//Kunde bereits eingefügt, oder null übergeben,....
			return false;
		
		try {
			System.out.println("DBBenutzerDB:speichereKunden: " + k.getUsrID().toString()
					+ ", " + k.getuName() + ", " + k.getNachname());

			saveKundeStmt.setString(1, k.getUsrID().toString());
			saveKundeStmt.setString(2, k.getBirthday());
			saveKundeStmt.setString(3, k.getSex());
			
			
			saveKundeStmt.executeUpdate();
			return true;
		}catch(NullPointerException e){
			System.out.println("DBBenutzerDB:speichereBenutzer: Übergebener Benutzer(Parameter) ist null!!!");
			return false;
		}catch (Exception e) {
			System.out.println("DBBenutzerDB:speichereBenutzer: Fehler beim einfuegen des Benutzers zBSchon vorhanden,...("+e.getMessage()+")!!!");
			return false;
		}

	}

	@Override
	public boolean speichereMitarbeiter(Mitarbeiter m) {
		if(!speichereBenutzer(m))
			return false;
		
		try {
			System.out.println("DBBenutzerDB:speichereMitarbeiter: " + m.getUsrID().toString()
					+ ", " + m.getuName() + ", " + m.getNachname());

			saveMitarStmt.setString(1, m.getUsrID().toString());
			saveMitarStmt.setString(2, String.valueOf(m.getStaffNo()) );
			saveMitarStmt.setString(3, String.valueOf(m.getSallary()) );
			
			
			saveMitarStmt.executeUpdate();
			return true;
		}catch(NullPointerException e){
			System.out.println("DBBenutzerDB:speichereMitarbeiter: Übergebener Benutzer(Parameter) ist null!!!");
			return false;
		}catch (Exception e) {
			System.out.println("DBBenutzerDB:speichereMitarbeiter: Fehler beim einfuegen des Benutzers zBSchon vorhanden,...("+e.getMessage()+")!!!");
			return false;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.BenutzerDAO#getBenutzerList()
	 */
	@Override
	public List<Benutzer> getBenutzerList() {
		List<Benutzer> liste = new ArrayList<Benutzer>();
		try {
			ResultSet result = loadAllUserStmt.executeQuery();
			int anzBenutzer = 0;
			
			while(result.next()){
				String uName = result.getString("uName");
				UUID uID = UUID.fromString(result.getString("usrID"));
				String firstname = result.getString("firstname");
				String surname = result.getString("surname");
				String email = result.getString("email");
				String pwd = result.getString("pwd");
				String country = result.getString("country");
				String street = result.getString("street");
				String city = result.getString("city");
				try {
					int zip = Integer.parseInt(result.getString("zip"));
					int num = Integer.parseInt(result.getString("number"));
					
					liste.add(new Benutzer(uName, uID, pwd, firstname, surname, email,
							country, zip, city, street, num));
					anzBenutzer++;
				} catch (NumberFormatException e) {
					liste.add(new Benutzer(uName, uID, pwd, firstname, surname, email,
							country, 0, city, street, 0));
					anzBenutzer++;
				}
			}
			
			System.out.println("DBBenutzerDao: getBenutzerList: Anzahl Benutzer: " + anzBenutzer);

			return liste;
			
		} catch (Exception e) {
			System.out.println("DBBenutzerDAO: getBenutzerListe: Error");
			return null;
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.BenutzerDAO#getBenutzerList()
	 */
	@Override
	public List<Benutzer> getKundenList() {
		List<String> alleUsrIDKunden = new ArrayList<String>();
		List<Benutzer> benutzerListe = new ArrayList<Benutzer>();
		List<Benutzer> benutzerAlsKundeListe = new ArrayList<Benutzer>();
		List<Kunde> kundenListe = new ArrayList<Kunde>();
		try {
			ResultSet result = loadAllKundeStmt.executeQuery();
			int anzKunden = 0;
			
			while(result.next()){
				String usrID = result.getString("usrID");
				
				alleUsrIDKunden.add(usrID);
				anzKunden++;
			}
			
			
			for(String usrID : alleUsrIDKunden){
				benutzerAlsKundeListe.add(getBenutzerByUsrID(usrID));
			}
			
			return benutzerAlsKundeListe;
			
		} catch (Exception e) {
			System.out.println("DBBenutzerDAO: getKundenList: Error");
			return null;
		}
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.BenutzerDAO#getBenutzerByUName(java.lang.String)
	 */
	@Override
	public Benutzer getBenutzerByUName(String uName) {
		try {
			loadUserStmt.setString(1, uName);
			ResultSet result = loadUserStmt.executeQuery();
			if (!result.next()){
				System.out.println("DBBenutzerDAO: getBenutzerByUName: Kein Benutzer gefunden!");
				return null;
			}
			
			UUID uID = UUID.fromString(result.getString("usrID"));
			String firstname = result.getString("firstname");
			String surname = result.getString("surname");
			String email = result.getString("email");
			String pwd = result.getString("pwd");
			String country = result.getString("country");
			String street = result.getString("street");
			String city = result.getString("city");
			try {
				int zip = Integer.parseInt(result.getString("zip"));
				int num = Integer.parseInt(result.getString("number"));

				return new Benutzer(uName, uID, pwd, firstname, surname, email,
						country, zip, city, street, num);
			} catch (NumberFormatException e) {
				System.out.println("DBBenutzerDAO: getBenutzerByUName: achtung, zip oder number sind in db keine Integer!!");
				return new Benutzer(uName, uID, pwd, firstname, surname, email,
						country, 0, city, street, 0);
			}
		} catch (Exception e) {
			System.out.println("DBBenutzerDAO: getBenutzerByUName: Error");
			return null;
		}
	}

	private Benutzer getBenutzerByUsrID(String usrID) {
		try {
			loadUserStmtID.setString(1, usrID);
			ResultSet result = loadUserStmtID.executeQuery();
			if (!result.next()){
				System.out.println("DBBenutzerDAO: getBenutzerByUName: Kein Benutzer gefunden!");
				return null;
			}
			
			
			UUID uID = UUID.fromString(result.getString("usrID"));
			String uName = result.getString("uName");
			String firstname = result.getString("firstname");
			String surname = result.getString("surname");
			String email = result.getString("email");
			String pwd = result.getString("pwd");
			String country = result.getString("country");
			String street = result.getString("street");
			String city = result.getString("city");
			try {
				int zip = Integer.parseInt(result.getString("zip"));
				int num = Integer.parseInt(result.getString("number"));

				return new Benutzer(uName, uID, pwd, firstname, surname, email,
						country, zip, city, street, num);
			} catch (NumberFormatException e) {
				System.out.println("DBBenutzerDAO: getBenutzerByUName: achtung, zip oder number sind in db keine Integer!!");
				return new Benutzer(uName, uID, pwd, firstname, surname, email,
						country, 0, city, street, 0);
			}
		} catch (Exception e) {
			System.out.println("DBBenutzerDAO: getBenutzerByUName: Error");
			return null;
		}
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.BenutzerDAO#getKundeByUsername(java.lang.String)
	 */
	public Kunde getKundeByUsername(String uName){
		Benutzer b = this.getBenutzerByUName(uName);//Kunde als Benutzer(in DB gespeichert)
		if(b == null){
			System.out.println("DBBenutzerDAO: getKundeByUsername: kunde nicht als benutzer gespeichert --> nicht im System, oder Error beim Kundensuchen");
			return null;
		}
		
		try{
			loadKundeStmt.setString(1, b.getUsrID().toString());
			ResultSet result = loadKundeStmt.executeQuery();
			
			if (!result.next()){
				System.out.println("DBBenutzerDAO: getKundeByUsername: bei dem uName handelt es sich um keinen Kunden");
				return null; 
			}

//Achtung hier muss noch das richtige Datum umgewandelt werden!!!!
			String birthday = result.getString("birthday");			
			String sex = result.getString("sex");
			
			return new Kunde(b.getuName(),b.getUsrID(),b.getPassword(),b.getVorname(),b.getNachname(),b.getEmail(),b.getLand(),b.getPlz(),b.getOrt(),b.getStrasse(),b.getHausNr(),birthday,sex);
		} catch (Exception e) {
			System.out.println("DBBenutzerDAO: getKundeByUName: Error");
			return null;
		}
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.BenutzerDAO#getMitarbeiterByUsername(java.lang.String)
	 */
	public Mitarbeiter getMitarbeiterByUsername(String uName){
		Benutzer b = this.getBenutzerByUName(uName);//Kunde als Benutzer(in DB gespeichert)
		if(b == null){
			System.out.println("DBBenutzerDAO: getKundeByUsername: kunde nicht als benutzer gespeichert --> nicht im System");
			return null;
		}
		
		try{
			loadMitarStmt.setString(1, b.getUsrID().toString());
			ResultSet result = loadMitarStmt.executeQuery();
			
			if (!result.next()){
				System.out.println("DBBenutzerDAO: getKundeByUsername: bei dem uName handelt es sich um keinen Mitarbeiter");
				return null; 
			}
			
			int sallary=0;
			int staffNo=0;
			
			try{
				staffNo = Integer.parseInt(result.getString("staffNo"));
				sallary = Integer.parseInt(result.getString("sallary"));
			}catch(NumberFormatException e){
				System.out.println("DBBenutzerDAO: getBenutzerByUName: achtung, zip oder number sind in db keine Integer!!");
			}
			
			
			return new Mitarbeiter(b.getuName(),b.getUsrID(),b.getPassword(),b.getVorname(),b.getNachname(),b.getEmail(),b.getLand(),b.getPlz(),b.getOrt(),b.getStrasse(),b.getHausNr(),staffNo,sallary);
		} catch (Exception e) {
			System.out.println("DBBenutzerDAO: getMitarbeiterByUName: Error");
			return null;
		}
	}
	
	/*
	 * Ist benötigt, um Einträge aus der Tabelle Benutzer zu löschen
	 * Wird benötigt um Kunden und Mitarbeiter zu löschen
	 * 
	*/
	private boolean loescheBenutzer(String usrID) {
		if(usrID.equals(""))
			return false; //kein Benutzer mit diesem usernamen
		
		try{
			deleteUserStmt.setString(1, usrID);
			deleteUserStmt.executeUpdate();
		}catch(SQLException e){
			System.out.println("DBBenutzerDAO: LoescheBenutzer: "+e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean loescheKundeByUname(String uName) {
		String usrID = getUsrIDFromUName(uName);
		if(usrID.equals(""))
			return false; //kein Benutzer mit diesem usernamen
		
		try{
			deleteKundeStmt.setString(1, usrID);
			deleteKundeStmt.executeUpdate();
		}catch(SQLException e){
			System.out.println("DBBenutzerDAO: loescheKundeByUname:" +e.getMessage());
			return false;
		}
		
		if(!loescheBenutzer(usrID))
			return false;
		
		return true;
	}

	@Override
	public boolean loescheMitarbeiterByUname(String uName) {
		String usrID = getUsrIDFromUName(uName);
		if(usrID.equals(""))
			return false; //kein Benutzer mit diesem usernamen
		
		try{
			deleteMitarStmt.setString(1, usrID);
			deleteMitarStmt.executeUpdate();
		}catch(SQLException e){
			System.out.println("DBBenutzerDAO: loescheMitarbeiterByUname:" +e.getMessage());
			return false;
		}
		
		if(!loescheBenutzer(usrID))
			return false;
		
		return true;
	}

	private String getUsrIDFromUName(String uName){
		
		Benutzer b = getBenutzerByUName(uName);
		
		String usrID="";
		
		try{
			usrID = b.getUsrID().toString();
		}catch(NullPointerException e){
			return "";
		}
		
		return usrID;
	}

}
