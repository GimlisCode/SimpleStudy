package Modifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import Models.Dozent;

public final class DozentenVerwaltung
{

	static private HashMap<Integer, Dozent> dozenten = new HashMap<>();
	static private DozentenVerwaltung dozentenVerwaltungSingleton = new DozentenVerwaltung();

	private DozentenVerwaltung()
	{
		super();
	}

	public static DozentenVerwaltung getInstance()
	{
		return dozentenVerwaltungSingleton;
	}

	public static Dozent get(int id)
	{
		return dozenten.get(id);
	}

	public static HashMap<Integer, Dozent> getAll()
	{
		return dozenten;
	}

	static void add(Dozent dozent)
	{
		dozenten.put(dozent.getId(),
				dozent);
	}

	static void remove(Dozent dozent)
	{
		remove(dozent.getId());
	}

	public static void remove(int id)
	{
		dozenten.remove(id);
	}

	static void update(Dozent dozent)
	{
		dozenten.put(dozent.getId(),
				dozent);
	}

	static ArrayList<Dozent> suche(String suchstring)
	{
		final ArrayList<Dozent> passendeDozentenZumSuchstring = new ArrayList<>();
		for (final Entry<Integer, Dozent> dozent : dozenten.entrySet())
			if (dozent.getValue()
					.toString()
					.contains(suchstring))
				passendeDozentenZumSuchstring.add(dozent.getValue());

		if (passendeDozentenZumSuchstring.size() > 0)
			return passendeDozentenZumSuchstring;

		return null;

	}

}
