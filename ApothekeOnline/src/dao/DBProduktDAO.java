import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import model.Produkt;

public class DBProduktDAO implements ProduktDAO {
	
	String database = "jdbc:oracle:thin:@oracle-lab.cs.univie.ac.at:1521:lab";
	String user = "a1363772";
	String pwd = "PRise16";

	@Override
	public boolean speichereProdukt(Produkt p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean speichereCategory(Category c) {
		// TODO Auto-generated method stub
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
	public Produkt getProduktByProduktID(int prodID) {
		
		String  sql = "SELECT * FROM ISE_Product WHERE productID = ?";
		Connection con = DriverManager.getConnection(database, user, pwd);
		PreparedStatement pstm = con.prepareStatement(sql);
		
		pstm.setInt(1,prodID);
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
