/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.Benutzer;

/**
 * @author Gerhard
 *
 */
public class DBBenutzerDAO implements BenutzerDAO {

	private PreparedStatement saveKundeStmt;
	private PreparedStatement saveMitarStmt;
	private PreparedStatement saveUserStmt;
	private PreparedStatement loadKundeStmt;
	private PreparedStatement loadMitarStmt;
	private PreparedStatement loadUserStmt;
	private PreparedStatement loadAllUserStmt;
	private final String dbUrl = "jdbc:mysql://localhost:3306/meineapotheketest";
	private final String user = "root";
	private final String pwd = "";

	/**
	 * 
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
			loadUserStmt = con
					.prepareStatement("SELECT * FROM user WHERE uName=?");
			loadKundeStmt = con.prepareStatement("SELECT * FROM customer");
			loadMitarStmt = con.prepareStatement("SELECT * FROM employee");
			
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
			if (!result.next())
				return null;
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
	 * @see dao.BenutzerDAO#loescheBenutzer(java.lang.String)
	 */
	@Override
	public boolean loescheBenutzer(String uName) {
		// TODO Auto-generated method stub
		return false;
	}

}
