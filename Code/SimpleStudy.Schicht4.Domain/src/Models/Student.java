package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Student extends Entity{
	
	private String name;
	private Statistik statistik;
	
	public Student(String name, Statistik statistik) {
		super();
		this.name = name;
		this.statistik = statistik;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Statistik getStatistik() {
		return statistik;
	}
	public void setStatistik(Statistik statistik) {
		this.statistik = statistik;
	}
	@Override
	HashMap<String, Object> getDetails() {
		HashMap<String, Object> details = new HashMap<String, Object>();
		details.put("id", this.id);
		details.put("name", this.name);		
		details.put("statistik", this.statistik);
		
		return details;
	}
	
//	public void add(Statistik newStatistik) {
//		if (!statistik.contains(newStatistik))		
//			statistik.add(newStatistik);		
//	}
//	
//	public void remove(Statistik removeStatistik) {
//		if (statistik.contains(removeStatistik))
//			statistik.remove(removeStatistik);
//	}
}
