package Modifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import Models.Antwort;

public final class AntwortVerwaltung
{

	static private HashMap<Integer, Antwort> antworten = new HashMap<>();
	static private AntwortVerwaltung antwortVerwaltungSingleton = new AntwortVerwaltung();

	private AntwortVerwaltung()
	{
		super();
	}

	public static AntwortVerwaltung getInstance()
	{
		return antwortVerwaltungSingleton;
	}

	public static Antwort get(int id)
	{
		return antworten.get(id);
	}

	public HashMap<Integer, Antwort> getAll()
	{
		return antworten;
	}

	public void add(Antwort antwort)
	{
		antworten.put(antwort.getId(), antwort);
	}

	public void remove(Antwort antwort)
	{
		remove(antwort.getId());
	}

	public void remove(int id)
	{
		antworten.remove(id);
	}

	public void update(Antwort antwort)
	{
		antworten.put(antwort.getId(), antwort);
	}

	public ArrayList<Antwort> suche(String suchstring)
	{
		final ArrayList<Antwort> passendeAntwortenZumSuchstring = new ArrayList<>();
		for (final Entry<Integer, Antwort> antwort : antworten.entrySet())
			if (antwort.getValue().toString().contains(suchstring))
				passendeAntwortenZumSuchstring.add(antwort.getValue());

		if (passendeAntwortenZumSuchstring.size() > 0)
			return passendeAntwortenZumSuchstring;

		return null;

	}

}
