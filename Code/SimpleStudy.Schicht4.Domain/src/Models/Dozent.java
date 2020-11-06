package Models;

import java.util.ArrayList;

public class Dozent extends Entity{
	
	private String name;
	private ArrayList<Lernfach> kurse = new ArrayList<>();
	
	public Dozent(String name, ArrayList<Lernfach> kurse) {
		super();
		this.name = name;
		this.kurse = kurse;
	}
	
	

}
