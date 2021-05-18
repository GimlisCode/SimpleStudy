package Models;

import java.util.ArrayList;

public class Abfrage
{

	private final Ergebnis ergebnis;
	private final Modus modus;
	private final Abfragesystem abfragesystem;
	private final ArrayList<Frage> fragen;
	private final Student currentStudent;

	public Abfrage(Modus modus, Abfragesystem abfragesystem, ArrayList<Frage> fragen, Student currentStudent)
	{
		ergebnis = new Ergebnis();
		this.modus = modus;
		this.abfragesystem = abfragesystem;
		this.fragen = fragen;
		this.currentStudent = currentStudent;
	}

	public void add(Frage f)
	{
		if (fragen.contains(f) != true)
			fragen.add(f);
	}

	public Ergebnis getErgebnis()
	{
		return ergebnis;
	}

	public Student getStudent()
	{

		return currentStudent;
	}

	public ArrayList<Frage> getFragen()
	{
		return fragen;
	}

}
