package Modifier;

import java.util.HashMap;

import Models.Antwort;
import Models.Frage;

public class FragenFabrik {
	
	private static Frage neueFrage = new Frage(null, "", null, null);
	private static FragenVerwaltung fragenVerwaltung;	
	private static FragenFabrik fragenFabrikSingleton = new FragenFabrik();
	
	private FragenFabrik() {

		fragenVerwaltung = FragenVerwaltung.getInstance();
	}
	
	public static FragenFabrik getInstance()
	{
		return fragenFabrikSingleton;
	}
	
	public static HashMap<String,String> getFragenAttribute()
	{
		 HashMap<String,String> antwortAttribute = new HashMap<>();
		String[] attributNamen = neueFrage.getAttributeNames();
				for (String attributName : attributNamen)
					antwortAttribute.put(attributName, "");
				
		return antwortAttribute;
	}
	
	public static void create(HashMap<String,String> fragenAttribute) {		
		neueFrage.setId(Integer.parseInt(fragenAttribute.get("Id")));                 //ID ODER NUMMER??
		neueFrage.setText(fragenAttribute.get("Text"));
		
		fragenVerwaltung.add(neueAntwort);
		neueFrage = new Frage
	}

}
