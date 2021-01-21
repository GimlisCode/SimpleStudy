package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Dozent extends Entity{
	
	private String name;
	private ArrayList<Lernfach> kurse = new ArrayList<>();
	
	public Dozent(String name, ArrayList<Lernfach> kurse) {
		super();
		this.name = name;
		this.kurse = kurse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Lernfach> getKurse() {
		return kurse;
	}

	public void addKurs(Lernfach l) {
		if(kurse.contains(l) != true) {
			kurse.add(l);
		}
	}
	
	public void removeKurs(Lernfach l) {
		if(kurse.contains(l)) {
			kurse.remove(l);
		}
	}

	@Override
	HashMap<String, Object> getDetails() {
		HashMap<String, Object> details = new HashMap<String, Object>();
		details.put("name", this.name);
		details.put("kurse", this.kurse);
		
		return details;
	}
	

}
