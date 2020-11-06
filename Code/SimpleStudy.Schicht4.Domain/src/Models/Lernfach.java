package Models;

import java.util.ArrayList;

public class Lernfach extends Entity{
	
	private String name;
	private int semester;
	private int credits;
	private ArrayList<Kapitel> lernkapitel = new ArrayList<>();

}
