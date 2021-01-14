package Modifier;

import java.util.ArrayList;

import Models.Abfrage;
import Models.Abfragesystem;
import Models.Frage;
import Models.Modus;
import Models.Student;

public class AbfrageVerwaltung {
	
	private Abfrage abfrage;
	private Student currentStudent;
	
	public AbfrageVerwaltung() {
		
	}
	
	public void neueAbfrage(Modus modus, Abfragesystem abfragesystem, ArrayList<Frage> fragen)
	{
		abfrage = new Abfrage(modus, abfragesystem, fragen);
	}
	
	public void abfrageAuswerten()
	{
		StatistikVerwaltung.update(currentStudent, abfrage.getErgebnis());
	}
	
}
