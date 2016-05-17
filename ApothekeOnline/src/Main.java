import java.util.*;

import management.Benutzerverwaltung;
import model.Benutzer;


public class Main {
	public static void main(String[] args){
		Benutzerverwaltung benver = Benutzerverwaltung.getInstance();
		List<Benutzer> alleBenutzer = benver.getAlleBenutzer();
		
		for(Benutzer i : alleBenutzer)
			System.out.println(i.toString());
	
	}
}
