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
	
	

	
	
}
