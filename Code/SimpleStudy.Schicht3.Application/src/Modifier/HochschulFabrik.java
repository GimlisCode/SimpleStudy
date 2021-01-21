package Modifier;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Dozent;
import Models.Hochschule;

public class HochschulFabrik {
	
	private static Hochschule neueHochschule = new Hochschule(null, null);
	private static HochschulVerwaltung hochschulVerwaltung;	
	private static HochschulFabrik hochschulFabrikSingleton = new HochschulFabrik();
	private static ArrayList<Tupel<Integer, Integer>> hochschulReferenzen = new ArrayList<>();
	
	private HochschulFabrik() {

		hochschulVerwaltung = HochschulVerwaltung.getInstance();
	}
	
	public static HochschulFabrik getInstance()
	{
		return hochschulFabrikSingleton;
	}
	
	public static HashMap<String,String> getHochschulAttribute()
	{
		 HashMap<String,String> hochschulAttribute = new HashMap<>();
		String[] attributNamen = neueHochschule.getAttributeNames();
				for (String attributName : attributNamen)
					hochschulAttribute.put(attributName, "");
				
		return hochschulAttribute;
	}
	
	public static void create(HashMap<String,String> hochschulAttribute) {		
		neueHochschule.setId(Integer.parseInt(hochschulAttribute.get("id")));
		neueHochschule.setName(hochschulAttribute.get("name"));		
		String [] alleDozentenId = hochschulAttribute.get("kurse").split(";");
		for (String dozentenId : alleDozentenId) {
			Dozent dozent = DozentenVerwaltung.get(Integer.parseInt(dozentenId));	
			if (dozent != null) {
				neueHochschule.add(dozent);								
			}
			else {
				hochschulReferenzen.add(new Tupel<>(neueHochschule.getId(), Integer.parseInt(dozentenId)));				
			}
			
		}		
		hochschulVerwaltung.add(neueHochschule);
		neueHochschule = new Hochschule(null, null);
	}


}
