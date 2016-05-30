/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import model.Bestellung;
import model.Position;
import model.Warenkorb;

/**
 * @author Katrin
 *
 */
public class DBBestellungDAO implements BestellungDAO {
	//Daten für DB Verbindung
	private final String dbUrl = "jdbc:oracle:thin:@oracle-lab.cs.univie.ac.at:1521:lab";
	private final String user = "a1363772";
	private final String pwd = "PRise16";
	
	//Statements um Daten aus db zu holen(über SQL)
	private PreparedStatement savePositionStmt;
	private PreparedStatement saveBestellungStmt;
	
	private PreparedStatement loadPositionStmt;
	private PreparedStatement loadBestellungStmt;
	private PreparedStatement loadAllPositionStmtB;
	private PreparedStatement loadAllBestellungenStmt;
	
	private PreparedStatement deletePositionStmt;
	private PreparedStatement deleteBestellungStmt;

	public DBBestellungDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(dbUrl, user, pwd);
			
			savePositionStmt = con
					.prepareStatement("INSERT INTO ISE_ITEM(orderID,itemID,quantity,totalPrice,productID) VALUES(?,?,?,?,?)");
			saveBestellungStmt = con
					.prepareStatement("INSERT INTO ISE_ShoppingCart(orderID,orderDate,totalPrice,usrID) VALUES (?, ?, ?, ?)");
			
			loadPositionStmt = con.prepareStatement("SELECT * FROM ISE_Item WHERE orderID=? AND itemID=?");
			loadBestellungStmt = con.prepareStatement("SELECT * FROM ISE_ShoppingCart WHERE orderID=?");
			loadAllPositionStmtB = con.prepareStatement("SELECT * FROM ISE_ITEM WHERE orderID=?");
			loadAllBestellungenStmt = con.prepareStatement("SELECT * FROM ISE_ShoppingCart");
			
			deletePositionStmt = con.prepareStatement("DELETE FROM ISE_ITEM WHERE orderID=? AND itemID=?");
			deleteBestellungStmt = con.prepareStatement("DELETE FROM ISE_ShoppingCart WHERE orderID=?");			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Verbindungsaufbau zur DB nicht möglich!! ("+e.getMessage()+")");
		}
	}
	
	/* (non-Javadoc)
	 * @see dao.BestellungDAO#speicherePosition(model.Position)
	 */
	@Override
	public boolean speicherePosition(Position p) {
		if(!(getPositionByID(p.getPositionsNr(), p.getorderID()) == null)) //Position bereits vorhanden, null,....
			return false;
		try {
			System.out.println("DBBestellungDAO: speicherePosition: " + p.getorderID() + ", " + p.getPositionsNr());

			//saveUserStmt.setInt(1, b.getUsrID());
			savePositionStmt.setString(1, p.getorderID());
			savePositionStmt.setInt(2, p.getPositionsNr());
			savePositionStmt.setInt(3, p.getMenge());
			savePositionStmt.setDouble(4, p.getGesamtpreis());
			savePositionStmt.setInt(5, p.getProduktID());			
			
			savePositionStmt.executeUpdate();
			return true;
		}catch(NullPointerException e){
			System.out.println("DBBestellungDAO: speicherePosition: Übergebene Position (Parameter) ist null!!! ("+e.getMessage()+")");
			return false;
		}catch (Exception e) {
			System.out.println("DBBestellungDAO: speicherePosition: Fehler beim einfuegen der Position ("+e.getMessage()+")!!!");
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see dao.BestellungDAO#speichereBestellung(model.Bestellung)
	 */
	@Override
	public boolean speichereBestellung(Bestellung b) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see dao.BestellungDAO#getPositionListbyBestellung(java.lang.String)
	 */
	@Override
	public List<Position> getPositionListbyBestellung(String oID) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dao.BestellungDAO#getBestellungList()
	 */
	@Override
	public List<Bestellung> getBestellungList() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dao.BestellungDAO#getPositionByID(int, java.lang.String)
	 */
	@Override
	public Position getPositionByID(int pID, String oID) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dao.BestellungDAO#getBestellungByID(java.lang.String)
	 */
	@Override
	public Bestellung getBestellungByID(String oID) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dao.BestellungDAO#loeschePosition(int, java.lang.String)
	 */
	@Override
	public boolean loeschePosition(int pID, String oID) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see dao.BestellungDAO#loescheBestellung(java.lang.String)
	 */
	@Override
	public boolean loescheBestellung(String oID) {
		// TODO Auto-generated method stub
		return false;
	}
}