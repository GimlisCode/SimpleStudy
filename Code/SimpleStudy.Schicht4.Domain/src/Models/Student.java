package Models;

import java.util.ArrayList;

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
