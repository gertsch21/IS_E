/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
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
	private PreparedStatement loadAllUserStmt;
	

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
					.prepareStatement("INSERT INTO user(usrID,uName,firstname,surname,email,pwd) VALUES(?,?,?,?,?,?)");
			saveKundeStmt = con
					.prepareStatement("INSERT INTO customer(usrID,birthday,sex) VALUES (?, ?, ?)");
			saveMitarStmt = con
					.prepareStatement("INSERT INTO employee(usrID,staffNo,sallary) VALUES (?, ?, ?)");
			
			loadUserStmt = con.prepareStatement("SELECT * FROM user WHERE uName=?");
			loadKundeStmt = con.prepareStatement("SELECT * FROM customer WHERE usrID=?");
			loadMitarStmt = con.prepareStatement("SELECT * FROM employee WHERE usrID=?");
			
			loadAllUserStmt = con.prepareStatement("SELECT * FROM user");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Verbindungsaufbau zur DB nicht möglich!!");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.BenutzerDAO#speichereBenutzer(model.Benutzer)
	 */
	@Override
	public boolean speichereBenutzer(Benutzer b) {
		try {
			System.out.println("DBBenutzerDB: " + b.getUsrID().toString()
					+ ", " + b.getuName() + ", " + b.getNachname());

			saveKundeStmt.setString(1, b.getUsrID().toString());
			saveKundeStmt.setString(2, b.getuName());
			saveKundeStmt.setString(3, b.getNachname());
			saveKundeStmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
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
			Date birth = new Date(); //Muss eig birthday von String konvertieren!!!!!!!!!!!!!!!!!!!!!!!!!
			
			String sex = result.getString("sex");
			
			return new Kunde(b.getuName(),b.getUsrID(),b.getPassword(),b.getVorname(),b.getNachname(),b.getEmail(),b.getLand(),b.getPlz(),b.getOrt(),b.getStrasse(),b.getHausNr(),birth,sex);
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
	 * (non-Javadoc)
	 * 
	 * @see dao.BenutzerDAO#loescheBenutzer(java.lang.String)
	 */
	@Override
	public boolean loescheBenutzer(String uName) {
		// TODO Auto-generated method stub
		return false;
	}

}
