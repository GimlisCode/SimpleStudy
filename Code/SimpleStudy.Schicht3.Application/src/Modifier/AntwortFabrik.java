package Modifier;

import java.util.HashMap;

import Models.Antwort;

public class AntwortFabrik {
	private static Antwort neueAntwort = new Antwort(null, false);
	private static AntwortVerwaltung antwortenVerwaltung;	
	private static AntwortFabrik antwortFabrikSingleton = new AntwortFabrik();
	
	private AntwortFabrik() {

		antwortenVerwaltung = AntwortVerwaltung.getInstance();
	}
	
	public static AntwortFabrik getInstance()
	{
		return antwortFabrikSingleton;
	}
	
	public static HashMap<String,String> getAntwortAttribute()
	{
		 HashMap<String,String> antwortAttribute = new HashMap<>();
		String[] attributNamen = neueAntwort.getAttributeNames();
				for (String attributName : attributNamen)
					antwortAttribute.put(attributName, "");
				
		return antwortAttribute;
	}
	
	public static void create(HashMap<String,String> antwortAttribute) {		
		neueAntwort.setId(Integer.parseInt(antwortAttribute.get("id")));
		neueAntwort.setText(antwortAttribute.get("text"));
		neueAntwort.setCorrect(Boolean.getBoolean(antwortAttribute.get("correct")));
		antwortenVerwaltung.add(neueAntwort);
		neueAntwort = new Antwort(null, false);
	}
}
