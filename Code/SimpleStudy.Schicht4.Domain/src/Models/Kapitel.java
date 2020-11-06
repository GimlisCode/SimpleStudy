package Models;

import java.util.ArrayList;

public class Kapitel extends Entity{
	
	private String name;
	private int nr;
	private ArrayList<Frage> Fragen = new ArrayList<>();
	
	
	public Kapitel(String name, int nr, ArrayList<Frage> fragen) {
		super();
		this.name = name;
		this.nr = nr;
		Fragen = fragen;
	}

	
	
}
