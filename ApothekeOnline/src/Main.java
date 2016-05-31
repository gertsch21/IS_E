
import java.util.*;

import dao.BenutzerDAO;
import dao.DBBenutzerDAO;
import management.Benutzerverwaltung;
import model.*;


public class Main {
	public static void main(String[] args) {

		
/*		Random randomGenerator = new Random();
		for(int i=0;i<100;i++){
			System.out.println(randomGenerator.nextInt(Integer.MAX_VALUE));
		}
*/		
		GregorianCalendar g = new GregorianCalendar(1993, 11, 21);
		System.out.println(g.get(GregorianCalendar.MONTH));
		
		String birthday = "1993.11.12";
		

	}
}
