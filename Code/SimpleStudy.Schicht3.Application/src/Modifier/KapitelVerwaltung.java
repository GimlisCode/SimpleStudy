package Modifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import Models.Kapitel;

public class KapitelVerwaltung
{

	static private HashMap<Integer, Kapitel> kapitel = new HashMap<>();
	static private KapitelVerwaltung kapitelVerwaltungSingleton = new KapitelVerwaltung();

	public KapitelVerwaltung()
	{
		super();
	}

	public static KapitelVerwaltung getInstance()
	{
		return kapitelVerwaltungSingleton;
	}

	public Kapitel get(int id)
	{
		return kapitel.get(id);
	}

	public HashMap<Integer, Kapitel> getAll()
	{
		return kapitel;
	}

	public void add(Kapitel neuesKapitel)
	{
		kapitel.put(neuesKapitel.getId(),
				neuesKapitel);
	}

	public void remove(Kapitel kapitel)
	{
		remove(kapitel.getId());
	}

	public void remove(int id)
	{
		kapitel.remove(id);
	}

	public void update(Kapitel kapitelUpdate)
	{
		kapitel.put(kapitelUpdate.getId(),
				kapitelUpdate);
	}

	public ArrayList<Kapitel> suche(String suchstring)
	{
		final ArrayList<Kapitel> passendeLernfaecherZumSuchstring = new ArrayList<>();
		for (final Entry<Integer, Kapitel> kapitel : kapitel.entrySet())
			if (kapitel.getValue()
					.toString()
					.contains(suchstring))
				passendeLernfaecherZumSuchstring.add(kapitel.getValue());

		if (passendeLernfaecherZumSuchstring.size() > 0)
			return passendeLernfaecherZumSuchstring;

		return null;
	}

}
