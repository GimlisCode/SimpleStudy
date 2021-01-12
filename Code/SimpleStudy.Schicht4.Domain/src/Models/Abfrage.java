package Models;

import java.util.ArrayList;

public class Abfrage {
	
	private Ergebnis ergebnis;
	private Modus modus;
	private Abfragesystem abfragesystem;
	private ArrayList<Frage> fragen;
	
	public Abfrage(Ergebnis ergebnis, Modus modus, Abfragesystem abfragesystem, ArrayList<Frage> fragen) {
		super();
		this.ergebnis = ergebnis;
		this.modus = modus;
		this.abfragesystem = abfragesystem;
		this.fragen = fragen;
	}
	
	public void add (Frage f) {
		if(fragen.contains(f) != true) {
			fragen.add(f);
		}
	}

	public Ergebnis getErgebnis() {
		return ergebnis;
	}

	public void setErgebnis(Ergebnis ergebnis) {
		this.ergebnis = ergebnis;
	}

   

}
