package Modifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import Models.Antwort;

public class AntwortVerwaltung {

static private HashMap<Integer, Antwort> antworten = new HashMap<>();
	
	public AntwortVerwaltung() {
	
	}
	
	static Antwort get(int id)
	{		
		return antworten.get(id);
	}
	
	static HashMap<Integer, Antwort> getAll()
	{
		return antworten;
	}
	
	static void add(Antwort antwort)
	{		
		antworten.put(antwort.getId(), antwort);		
	}
		
	static void remove(Antwort antwort)
	{		
		remove(antwort.getId());				
	}
	
	static void remove(int id)
	{		
		antworten.remove(id);				
	}
	
	static void update(Antwort antwort)
	{		
		antworten.put(antwort.getId(), antwort);		
	}
	
	static ArrayList<Antwort> suche(String suchstring)
	{
		ArrayList<Antwort> passendeAntwortenZumSuchstring = new ArrayList<>();
		for (Entry<Integer, Antwort> antwort : antworten.entrySet()) 
			if (antwort.getValue().toString().contains(suchstring)) 
				passendeAntwortenZumSuchstring.add(antwort.getValue());				
			
		if (passendeAntwortenZumSuchstring.size() > 0)
			return passendeAntwortenZumSuchstring;
		
		return null;
		
		
	}

}
