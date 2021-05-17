package Modifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import Models.Dozent;

public final class DozentenVerwaltung implements Verwalter<Dozent>
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

	public Dozent get(int id)
	{
		return dozenten.get(id);
	}

	public HashMap<Integer, Dozent> getAll()
	{
		return dozenten;
	}

	public void add(Dozent dozent)
	{
		dozenten.put(dozent.getId(),
				dozent);
	}

	public void remove(Dozent dozent)
	{
		remove(dozent.getId());
	}

	public void remove(int id)
	{
		dozenten.remove(id);
	}

	public void update(Dozent dozent)
	{
		dozenten.put(dozent.getId(),
				dozent);
	}

	public ArrayList<Dozent> suche(String suchstring)
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
