
import java.util.*;

import dao.BenutzerDAO;
import dao.DBBenutzerDAO;
import management.Benutzerverwaltung;
import model.*;


public class Main {
	public static void main(String[] args) {
		Benutzerverwaltung benver = Benutzerverwaltung.getInstance();

		BenutzerDAO dao = new DBBenutzerDAO();
		
		for(Benutzer b : dao.getKundenList()){
			System.out.println(b.getuName());
		}
		
	}
}
