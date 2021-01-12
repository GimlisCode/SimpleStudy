package Models;

import java.util.ArrayList;

public class Student extends Entity{
	
	private String name;
	private ArrayList<Statistik> statistik = new ArrayList<>();
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Statistik> getStatistik() {
		return statistik;
	}
	public void setStatistik(ArrayList<Statistik> statistik) {
		this.statistik = statistik;
	}
	
	public void add(Statistik newStatistik) {
		if (statistik.indexOf(newStatistik) != -1)		
			statistik.add(newStatistik);		
	}
	
	public void remove(Statistik removeStatistik) {
		int index = statistik.indexOf(removeStatistik);
		if (index != -1)
			statistik.remove(index);
	}
}
