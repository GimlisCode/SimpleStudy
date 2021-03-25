package Models;

import java.util.ArrayList;

public class Abfrage
{

	private Ergebnis ergebnis;
	private Modus modus;
	private Abfragesystem abfragesystem;
	private ArrayList<Frage> fragen;

	public Abfrage(Modus modus, Abfragesystem abfragesystem, ArrayList<Frage> fragen)
	{
		this.ergebnis = new Ergebnis();
		this.modus = modus;
		this.abfragesystem = abfragesystem;
		this.fragen = fragen;
	}

	public void add(Frage f)
	{
		if (fragen.contains(f) != true)
		{
			fragen.add(f);
		}
	}

	public Ergebnis getErgebnis()
	{
		return ergebnis;
	}

	public void setErgebnis(Ergebnis ergebnis)
	{
		this.ergebnis = ergebnis;
	}

	public ArrayList<Frage> getFragen()
	{
		return fragen;
	}

}
