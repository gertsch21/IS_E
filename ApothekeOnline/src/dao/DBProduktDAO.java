import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Locale.Category;

import model.Produkt;

public class DBProduktDAO implements ProduktDAO {
	
	String database = "jdbc:oracle:thin:@oracle-lab.cs.univie.ac.at:1521:lab";
	String user = "a1363772";
	String pwd = "PRise16";
	
//Statements um Daten aus DB zu holen
    
    	private PreparedStatement saveProduktStmt;
	private PreparedStatement saveCategoryStmt;
    
	private PreparedStatement loadProduktStmt;
    	private PreparedStatement loadProduktStmtID;
	private PreparedStatement loadCategoryStmt;
    	private PreparedStatement loadCategoryStmtID;
    
	private PreparedStatement loadAllProduktStmt;
	private PreparedStatement loadAllCategoryStmt;
	
	private PreparedStatement deleteProduktStmt;
	private PreparedStatement deleteCategoryStmt;
    
    public DBBenutzerDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(dbUrl, user, pwd);
			
			saveProduktStmt = con
					.prepareStatement("INSERT INTO ISE_PRODUCT(PRODUCTID, NAME, PRICE, DESCRIPTION, CATEGORYID) VALUES(?,?,?,?,?)");
			saveCategoryStmt = con
					.prepareStatement("INSERT INTO ISE_CATEGORY(CATEGORYID,NAME,DESCRIPTION) VALUES (?, ?, ?)");
			
			loadProduktStmtID; = con.prepareStatement("SELECT * FROM ISE_PRODUCT WHERE PRODUCTID=?");
			loadUserStmt = con.prepareStatement("SELECT * FROM ISE_PRODUCT WHERE NAME=?");
			loadAllCategoryStmtID; = con.prepareStatement("SELECT * FROM ISE_CATEGORY WHERE CATEGORYID=?");
			loadAllCategoryStmt; = con.prepareStatement("SELECT * FROM ISE_CATEGORY WHERE NAME=?");
			
			loadAllProduktStmt = con.prepareStatement("SELECT * FROM ISE_PRODUCT");
			loadAllCategoryStmt = con.prepareStatement("SELECT * FROM ISE_CATEGORY");
			
			deleteProduktStmt; = con.prepareStatement("DELETE FROM ISE_PRODUCT WHERE PRODUCTID=?");
			deleteCategoryStmt; = con.prepareStatement("DELETE FROM ISE_CATEGORY WHERE CATEGORYID=?");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Verbindungsaufbau zur DB nicht möglich!! ("+e.getMessage()+")");
		}

	}

	public boolean speichereProdukt(Produkt p) {
        try {
			System.out.println("DBProduktDB:speichereProdukt: " + p.getprodName());

			saveProduktStmt.setInt(1, p.getprodID());
			saveProduktStmt.setString(2, p.getprodName());
			saveProduktStmt.setDouble(3, b.getprice());
			saveProduktStmt.setString(4, b.getprodDescription());
			saveProduktStmt.setInt(5, p.getcategoryID());
			
			
			saveProduktStmt.executeUpdate();
			return true;
		}catch(NullPointerException e){
			System.out.println("DBProduktDB:speichereProdukt: Übergebenes Produkt(Parameter) ist null!!! ("+e.getMessage()+")");
			return false;
		}catch (Exception e) {
			System.out.println("DBProduktDB:speichereProdukt: Fehler beim einfuegen des Produkts zB.Schon vorhanden,...("+e.getMessage()+")!!!");
			return false;
		}

		return false;
	}

	public boolean speichereCategory(Category c) {
        try {
			System.out.println("DBProduktDB:speichereCategory: " + p.getcategoryName());

			saveCategoryStmt.setInt(1, c.getcategoryID());
			saveCategoryStmt.setString(2, c.getCategoryName());
			saveCategoryStmt.setString(4, c.getCategoryDescription());
			
			
			saveCategoryStmt.executeUpdate();
			return true;
		}catch(NullPointerException e){
			System.out.println("DBProduktDB:speichereCategory: Übergebene Kategorie(Parameter) ist null!!! ("+e.getMessage()+")");
			return false;
		}catch (Exception e) {
			System.out.println("DBProduktDB:speichereCategory: Fehler beim einfuegen der Kategorie zB.Schon vorhanden,...("+e.getMessage()+")!!!");
			return false;
		}

		return false;
	}


	@Override
	public List<Produkt> getProduktList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getCategoryList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produkt getProduktByProduktName(String prodName) {
		
		String  sql = "SELECT * FROM ISE_Product WHERE productName = ?";
		Connection con = DriverManager.getConnection(database, user, pwd);
		PreparedStatement pstm = con.prepareStatement(sql);
		
		pstm.setString(2,prodName);
		ResultSet result = pstm.executeQuery();
		
		Produkt p = new Produkt();
		
		while(result.next()) {
			
			p.setprodID(result.getInt(1));
			p.setprodName(result.getString(2));
			p.setprice(result.getDouble(3));
			p.setprodDescription(result.getString(4));
			p.setcategoryID(result.getInt(5));
			
		}
		return p;
	}
		// TODO Auto-generated method stub0	}

	@Override
	public Category getCategoryByCategoryID(int categoryID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean loescheProduktByProdID() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean loescheCategoryByCategoryID() {
		// TODO Auto-generated method stub
		return false;
	}

}
