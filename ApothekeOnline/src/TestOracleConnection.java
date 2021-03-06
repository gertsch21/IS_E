import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class TestOracleConnection {

	public static void main(String[] args) {
		
		//Daten von Peter, um auf seine Oracle DB zuzugreifen
		String database = "jdbc:oracle:thin:@oracle-lab.cs.univie.ac.at:1521:lab";
		String user = "a1363772";
		String pwd = "PRise16";
		
		try{
			//Treiber einbinden
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Verbindung aufbauen
			Connection con = DriverManager.getConnection(database, user, pwd);
			//statement vorbereiten
			PreparedStatement loadUserStmt = con.prepareStatement("SELECT * FROM ISE_USR");
			ResultSet result = loadUserStmt.executeQuery();
			
			//Solange Datensätze vorhanden
			while(result.next()){
				System.out.println("Name: "+result.getString("uname")+", id: "+result.getString("usrid"));
			}
			
			
			
			
		}catch(Exception e){
			System.out.println("Verbindung konnte nicht aufgebaut werden!");
		}
		
		
		


	}

}
