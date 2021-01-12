package Models;

import java.util.ArrayList;

public class Lernstufe {
	
	private int Stufe;
	private ArrayList<Frage> fragen = new ArrayList<>();
	
	
	public Lernstufe(int stufe, ArrayList<Frage> fragen) {
		super();
		Stufe = stufe;
		this.fragen = fragen;
	}


	public Lernstufe(int stufe) {
		super();
		Stufe = stufe;
	}


	public int getStufe() {
		return Stufe;
	}


	public void setStufe(int stufe) {
		Stufe = stufe;
	}


	public ArrayList<Frage> getFragen() {
		return fragen;
	}


	public void setFragen(ArrayList<Frage> fragen) {
		this.fragen = fragen;
	}
	
	
	public void add(Frage newFrage) {
		if (!fragen.contains(newFrage))		
			fragen.add(newFrage);		
	}
	
	public void remove(Frage removeFrage) {
		if (fragen.contains(removeFrage))
			fragen.remove(removeFrage);
	}
	
	
}
