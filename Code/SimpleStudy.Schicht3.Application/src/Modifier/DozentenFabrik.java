package Modifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import Models.Dozent;
import Models.Lernfach;


public class DozentenFabrik {
	
	private static Dozent neuerDozent = new Dozent(null, null);
	private static DozentenVerwaltung dozentenVerwaltung;	
	private static DozentenFabrik dozentenFabrikSingleton = new DozentenFabrik();
	private static ArrayList<Tupel<Integer, Integer>> lernfachReferenzen = new ArrayList<>();
	
	private DozentenFabrik() {

		dozentenVerwaltung = DozentenVerwaltung.getInstance();
	}
	
	public static DozentenFabrik getInstance()
	{
		return dozentenFabrikSingleton;
	}
	
	public static HashMap<String,String> getDozentAttribute()
	{
		 HashMap<String,String> dozentAttribute = new HashMap<>();
		String[] attributNamen = neuerDozent.getAttributeNames();
				for (String attributName : attributNamen)
					dozentAttribute.put(attributName, "");
				
		return dozentAttribute;
	}
	
	public static void create(HashMap<String,String> dozentAttribute) {		
		neuerDozent.setId(Integer.parseInt(dozentAttribute.get("Id")));
		neuerDozent.setName(dozentAttribute.get("name"));		
		String [] alleKursId = dozentAttribute.get("kurse").split(";");
		ArrayList<Lernfach> kurse = new ArrayList<>();
		for (String kursId : alleKursId) {
			Lernfach fach = LernfachVerwaltung.get(Integer.parseInt(kursId));	
			if (fach != null) {
				neuerDozent.addKurs(fach);								
			}
			else {
				lernfachReferenzen.add(new Tupel<>(neuerDozent.getId(), Integer.parseInt(kursId)));				
			}
			
		}		
		dozentenVerwaltung.add(neuerDozent);
		neuerDozent = new Dozent(null,null);
	}

}
