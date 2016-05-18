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
		Benutzer c = benver.getCustomerByUname("nessi");
		Benutzer m = benver.getEmployeeByUname("nessi");
		System.out.println("Kunde: "+c.toString());
		System.out.println("Mitarbeiter: "+m.toString());
	}
}
