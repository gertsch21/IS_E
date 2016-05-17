/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import model.Benutzer;

/**
 * @author Gerhard
 *
 */
public class DBBenutzerDAO implements BenutzerDAO {
	
	private PreparedStatement saveKundeStmt;
	private PreparedStatement saveMitarStmt;
	private PreparedStatement loadKundeStmt;
	private PreparedStatement loadMitarStmt;
	private final String dbUrl = "jdbc:mysql://localhost:3306/meineapotheketest";
	private final String user = "root";
	private final String pwd = "";
	/**
	 * 
	 */
	public DBBenutzerDAO() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(dbUrl, user, pwd);
			saveKundeStmt = con.prepareStatement("INSERT INTO person(usrID,uname,surname) "
                    +"VALUES (?, ?, ?)");
			loadKundeStmt = con.prepareStatement("SELECT * FROM person");
                    //+"WHERE isbn = ?");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Verbindungsaufbau zur DB nicht möglich!!");
		}
		
	}

	/* (non-Javadoc)
	 * @see dao.BenutzerDAO#speichereBenutzer(model.Benutzer)
	 */
	@Override
	public boolean speichereBenutzer(Benutzer b) {
		try{
			System.out.println("DBBenutzerDB: "+b.getUsrID().toString()+", "+b.getuName()+", "+b.getNachname());
			
			saveKundeStmt.setString(1, b.getUsrID().toString());
		    saveKundeStmt.setString(2, b.getuName());
		    saveKundeStmt.setString(3, b.getNachname());
		    saveKundeStmt.executeUpdate();
		    return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see dao.BenutzerDAO#getBenutzerList()
	 */
	@Override
	public List<Benutzer> getBenutzerList() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dao.BenutzerDAO#getBenutzerByUName(java.lang.String)
	 */
	@Override
	public Benutzer getBenutzerByUName(String uName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dao.BenutzerDAO#loescheBenutzer(java.lang.String)
	 */
	@Override
	public boolean loescheBenutzer(String uName) {
		// TODO Auto-generated method stub
		return false;
	}

}
