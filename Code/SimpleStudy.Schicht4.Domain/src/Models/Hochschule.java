package Models;

import java.util.ArrayList;

public class Hochschule extends Entity{
	
	private String name;
	private ArrayList<Dozent> dozenten= new ArrayList<>();
	
	
	public Hochschule(String name, ArrayList<Dozent> dozenten) {
		super();
		this.name = name;
		this.dozenten = dozenten;
	}


	public Hochschule(String name) {
		super();
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public ArrayList<Dozent> getDozenten() {
		return dozenten;
	}


	public void setDozenten(ArrayList<Dozent> dozenten) {
		this.dozenten = dozenten;
	}
	
	
	public void add(Dozent newDozent) {
		if (!dozenten.contains(newDozent))		
			dozenten.add(newDozent);		
	}
	
	public void remove(Dozent removeDozent) {
		if (dozenten.contains(removeDozent))
			dozenten.remove(removeDozent);
	}
	
	

}
