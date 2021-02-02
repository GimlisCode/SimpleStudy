package Modifier;

import java.util.HashMap;

import Models.Lernfach;
import Models.Richtigkeit;
import Models.Statistik;
import Models.Student;

public class StatistikFabrik {
	private static Statistik neueStatistik = new Statistik();
	private static StatistikVerwaltung statistikVerwaltung;
	private static StatistikFabrik statistikFabrikSingleton;
	
	private StatistikFabrik()
	{
		statistikVerwaltung = StatistikVerwaltung.getInstance();
	}
	
	public static StatistikFabrik getInstance()
	{
		return statistikFabrikSingleton; 
	}
	
	
	public static HashMap<String,String> getStatistikAttribute()
	{
		 HashMap<String,String> statistikAttribute = new HashMap<>();
		String[] attributNamen = neueStatistik.getAttributeNames();
				for (String attributName : attributNamen)
					statistikAttribute.put(attributName, "");
				
		return statistikAttribute;
	}
	
	public static void create(HashMap<String,String> statistikAttribute) {		
		neueStatistik.setId(Integer.parseInt(statistikAttribute.get("id")));
		
		String [] alleRichtigkeiten = statistikAttribute.get("richtigkeiten").split(";");
		for (String richtigkeit : alleRichtigkeiten) {
			String[] richtigkeitenWerte = richtigkeit.split(",");
				neueStatistik.addToStatistik(Integer.parseInt(richtigkeitenWerte[0]), new Richtigkeit(Integer.parseInt(richtigkeitenWerte[1]),Integer.parseInt(richtigkeitenWerte[2])));			
		}		
		
		String [] alleFragenstufen = statistikAttribute.get("fragenstufe").split(";");
		for (String fragenstufe : alleFragenstufen) {
			String[] fragenstufenwerte = fragenstufe.split(",");
				neueStatistik.addFragenstufe(Integer.parseInt(fragenstufenwerte[0]), Integer.parseInt(fragenstufenwerte[1]));			
		}
		
		statistikVerwaltung.add(neueStatistik);
		neueStatistik = new Statistik();
	}
}
