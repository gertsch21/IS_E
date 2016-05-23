
import java.util.*;

import dao.BenutzerDAO;
import dao.DBBenutzerDAO;
import management.Benutzerverwaltung;
import model.*;


public class Main {
	public static void main(String[] args) {
		Benutzerverwaltung benver = Benutzerverwaltung.getInstance();

		BenutzerDAO dao = new DBBenutzerDAO();
		
		if(benver.kundeAnlegen("kunfdeJava", "kunfdeJava", "kundeJava", "kundeJava", 123, "kundeJava", "kundeJava", 123, "kundeJava", "kundeJava", "12.12.12", "m"))
			System.out.println("juhU");
		
/*		Random randomGenerator = new Random();
		for(int i=0;i<100;i++){
			System.out.println(randomGenerator.nextInt(Integer.MAX_VALUE));
		}
*/		
	}
}
