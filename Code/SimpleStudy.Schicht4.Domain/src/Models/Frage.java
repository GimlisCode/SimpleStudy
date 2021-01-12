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


	public int getNr() {
		return nr;
	}


	public void setNr(int nr) {
		this.nr = nr;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public int getTyp() {
		return typ;
	}


	public void setTyp(int typ) {
		this.typ = typ;
	}


	public ArrayList<Antwort> getAntworten() {
		return antworten;
	}


	public void setAntworten(ArrayList<Antwort> antworten) {
		this.antworten = antworten;
	}
	
	public void add(Antwort newAntwort) {
		if (!antworten.contains(newAntwort))		
			antworten.add(newAntwort);		
	}
	
	public void remove(Antwort removeAntwort) {
		if (antworten.contains(removeAntwort))
			antworten.remove(removeAntwort);
	}
	

}
