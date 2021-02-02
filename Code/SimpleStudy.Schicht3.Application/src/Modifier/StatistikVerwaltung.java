package Modifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import Models.Dozent;
import Models.Ergebnis;
import Models.Lernfach;
import Models.Richtigkeit;
import Models.Statistik;
import Models.Student;

public class StatistikVerwaltung {
	
	static private StatistikVerwaltung statistikVerwaltungSingleton = new StatistikVerwaltung();
	static private HashMap<Integer, Statistik> statistiken = new HashMap<>();
	
	private StatistikVerwaltung() {
		super();
	}
	
	public static StatistikVerwaltung getInstance() {
		return statistikVerwaltungSingleton;
	}
	
	static Statistik get(int id)
	{		
		return statistiken.get(id);
	}
	
	static HashMap<Integer, Statistik> getAll()
	{
		return statistiken;
	}
	
	static void add(Statistik statistik)
	{		
		statistiken.put(statistik.getId(), statistik);		
	}
		
	static void remove(Statistik statistik)
	{		
		statistiken.remove(statistik.getId());				
	}
	
	static void remove(int id)
	{		
		statistiken.remove(id);				
	}
	
	static void update(Statistik statistik)
	{		
		statistiken.put(statistik.getId(), statistik);		
	}
	
	static ArrayList<Statistik> suche(String suchstring)
	{
		ArrayList<Statistik> passendeStatistikZumSuchstring = new ArrayList<>();
		for (Entry<Integer, Statistik> statistik : statistiken.entrySet()) 
			if (statistik.getValue().toString().contains(suchstring)) 
				passendeStatistikZumSuchstring.add(statistik.getValue());				
			
		if (passendeStatistikZumSuchstring.size() > 0)
			return passendeStatistikZumSuchstring;
		
		return null;
		
		
	}
	
	//TODO: Fragenstufe

	public static void update(Statistik currentStatistik, Ergebnis ergebnis) {						
		for (Entry<Integer, Boolean> ergebnisEinerFrage: ergebnis.getErgebnis().entrySet()) {
			Richtigkeit richtigkeit = new Richtigkeit();
			if(currentStatistik.getStatistik().containsKey(ergebnisEinerFrage.getKey()))
				richtigkeit = currentStatistik.getStatistik().get(ergebnisEinerFrage.getKey());
				
			if (ergebnisEinerFrage.getValue())
				richtigkeit.addRichtig();
			else
				richtigkeit.addFalsch();
				
			currentStatistik.updateStatistik(ergebnisEinerFrage.getKey(), richtigkeit);					
		}
		
	}
	
	
	
}
	

	

