import java.util.UUID;

import management.Benutzerverwaltung;
import model.Benutzer;


public class Main {
	public static void main(String[] args){
		Benutzerverwaltung benver = Benutzerverwaltung.getInstance();
		Benutzer b = new Benutzer("uname", UUID.randomUUID(),"passwort","vorname","nachname","email","land",13,"ort","strasse",12);
		benver.benutzerAnlegen(b.getVorname(), b.getNachname(), b.getEmail(), b.getLand(), b.getPlz(), b.getOrt(), b.getStrasse(), b.getHausNr(), b.getuName(), b.getPassword());
	}
}
