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
	
	
	
	
	

}
