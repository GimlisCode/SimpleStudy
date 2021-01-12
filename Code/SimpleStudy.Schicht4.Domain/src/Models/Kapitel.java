package Models;

import java.util.ArrayList;

public class Kapitel extends Entity{
	
	private String name;
	private int nr;
	private ArrayList<Frage> fragen = new ArrayList<>();
	
	
	public Kapitel(String name, int nr, ArrayList<Frage> fragen) {
		super();
		this.name = name;
		this.nr = nr;
		this.fragen = fragen;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getNr() {
		return nr;
	}


	public void setNr(int nr) {
		this.nr = nr;
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
