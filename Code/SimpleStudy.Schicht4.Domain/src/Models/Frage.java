package Models;

import java.util.ArrayList;

public class Frage extends Entity {
	
	private int nr;
	private String text;
	private int typ;
	private ArrayList<Antwort> antworten = new ArrayList<>();
	
	
	public Frage(int nr, String text, int typ, ArrayList<Antwort> antworten) {
		super();
		this.nr = nr;
		this.text = text;
		this.typ = typ;
		this.antworten = antworten;
	}
	
	

}
