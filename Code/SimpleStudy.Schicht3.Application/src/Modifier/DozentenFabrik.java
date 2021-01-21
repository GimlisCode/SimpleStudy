package Modifier;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Dozent;
import Models.Lernfach;

public class DozentenFabrik {
	
	private static Dozent neuerDozent = new Dozent(null, null)
	private static DozentenVerwaltung dozentenVerwaltung;	
	private static DozentenFabrik dozentenFabrikSingleton = new DozentenFabrik();
	
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
		for (int i = 0; i < Integer.parseInt(dozentAttribute.get("länge")); i++) {
			neuerDozent.addKurs(dozentAttribute.get("kurs"+i));
		}
		neuerDozent.setName(dozentAttribute.get("name"));
		DozentenVerwaltung.add(neuerDozent);
		neuerDozent = new Dozent(null,null);
	}

}
