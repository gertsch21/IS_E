import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

import management.Benutzerverwaltung;
import model.*;


public class Main {
	public static void main(String[] args) {
		Benutzerverwaltung benver = Benutzerverwaltung.getInstance();
		List<Benutzer> alleBenutzer = benver.getAlleBenutzer();
		DateFormat df = DateFormat.getDateInstance();
		
		
		for(Benutzer i : alleBenutzer)
			System.out.println(i.toString());
		Benutzer b = benver.getBenutzerByUname("nessi");

		
		benver.kundeAnlegen("ersterkunde", "ersterkunde", "ersterkunde", "ersterkunde", 123, "ersterkunde", "ersterkunde", 123, "ersterkunde", "ersterkunde", "2013.05.02", "m");
		System.out.println("\n\nKunde: \n"+benver.getCustomerByUname("ersterkunde").toString());
		
		benver.mitarbeiterAnlegen("ersterMitarbeiter", "ersterMitarbeiter", "ersterMitarbeiter", "ersterMitarbeiter", 132, "ersterMitarbeiter", "ersterMitarbeiter", 123, "ersterMitarbeiter", "ersterMitarbeiter", 123, 123);
		System.out.println("\n\nMitarbeiter: \n"+benver.getEmployeeByUname("ersterMitarbeiter"));
	}
}
