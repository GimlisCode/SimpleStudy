package Modifier;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Antwort;
import Models.Frage;

public class FragenFabrik {
	
	private static Frage neueFrage = new Frage(null, 0, null);
	private static FragenVerwaltung fragenVerwaltung;	
	private static FragenFabrik fragenFabrikSingleton = new FragenFabrik();
	private static ArrayList<Tupel<Integer, Integer>> fragenReferenzen = new ArrayList<>();
	
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
		neueFrage.setId(Integer.parseInt(fragenAttribute.get("Id")));             
		neueFrage.setText(fragenAttribute.get("Text"));
		neueFrage.setTyp(Integer.parseInt(fragenAttribute.get("typ")));
		String[] alleAntwortId = fragenAttribute.get("antworten").split(";");
		for (String	antwortenID : alleAntwortId) {
			Antwort antwort = AntwortVerwaltung.get(Integer.parseInt(antwortenID));
			if (antwort != null) {
				neueFrage.add(antwort);				
			}
			else {
				fragenReferenzen.add(new Tupel<>(neueFrage.getId(), Integer.parseInt(antwortenID)));
			}
			
		}
		fragenVerwaltung.add(neueFrage);
		neueFrage = new Frage(null, 0, null);
	}

}
