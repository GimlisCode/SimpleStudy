package Models;

import java.util.ArrayList;

public class Lernfach extends Entity{
	
	private String name;
	private int semester;
	private int credits;
	private ArrayList<Kapitel> lernkapitel = new ArrayList<>();
	
	
	public Lernfach(String name, int semester, int credits, ArrayList<Kapitel> lernkapitel) {
		super();
		this.name = name;
		this.semester = semester;
		this.credits = credits;
		this.lernkapitel = lernkapitel;
	}
	
	

}
