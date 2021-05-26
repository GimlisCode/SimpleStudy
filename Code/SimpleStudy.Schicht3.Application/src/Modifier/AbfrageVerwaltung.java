package Modifier;

import java.util.ArrayList;

import Models.Abfrage;
import Models.Abfragesystem;
import Models.Frage;
import Models.Modus;
import Models.Student;

public class AbfrageVerwaltung
{
	private static AbfrageVerwaltung abfrageVerwaltungSingleton = new AbfrageVerwaltung();

	private AbfrageVerwaltung()
	{
		super();
	}

	public static AbfrageVerwaltung getInstance()
	{
		return abfrageVerwaltungSingleton;
	}

	public Abfrage create(Modus modus, Abfragesystem abfragesystem, ArrayList<Frage> fragen, Student student)
	{
		return new Abfrage(modus, abfragesystem, fragen, student);
	}

	public void abfrageAuswerten(Abfrage abfrage)
	{
		StatistikVerwaltung.getInstance()
				.update(abfrage.getStudent()
						.getStatistik(),
						abfrage.getErgebnis());
	}

}
