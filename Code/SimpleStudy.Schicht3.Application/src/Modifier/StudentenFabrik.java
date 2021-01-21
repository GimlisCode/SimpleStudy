package Modifier;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Dozent;
import Models.Lernfach;
import Models.Statistik;
import Models.Student;

public class StudentenFabrik {
	private static Student neuerStudent = new Student(null, null);
	private static StudentenVerwaltung studentenVerwaltung;
	private static StudentenFabrik studentenFabrikSingleton;
	private static ArrayList<Tupel<Integer, Integer>> StatistikReferenzen = new ArrayList<>();
	
	
	private StudentenFabrik()
	{
		studentenVerwaltung = StudentenVerwaltung.getInstance();
	}
	
	public static StudentenFabrik getInstance()
	{
		return studentenFabrikSingleton; 
	}
	
	
	public static HashMap<String,String> getStudentenAttribute()
	{
		 HashMap<String,String> studentenAttribute = new HashMap<>();
		String[] attributNamen = neuerStudent.getAttributeNames();
				for (String attributName : attributNamen)
					studentenAttribute.put(attributName, "");
				
		return studentenAttribute;
	}
	
	public static void create(HashMap<String,String> studentenAttribute) {		
		neuerStudent.setId(Integer.parseInt(studentenAttribute.get("Id")));
		neuerStudent.setName(studentenAttribute.get("name"));		
		String statistikId = studentenAttribute.get("statistik");
		
		Statistik statistik = StatistikVerwaltung.get(Integer.parseInt(statistikId));	
		if (statistik != null) {
			neuerStudent.setStatistik(statistik);								
		}
		else {
			StatistikReferenzen.add(new Tupel<>(neuerStudent.getId(), Integer.parseInt(statistikId)));				
		}
					
		studentenVerwaltung.add(neuerStudent);
		neuerStudent = new Student("",null);
	}
}
