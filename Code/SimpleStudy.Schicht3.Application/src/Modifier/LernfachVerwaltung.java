package Modifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import Models.Lernfach;

public class LernfachVerwaltung
{

	static private HashMap<Integer, Lernfach> lernfaecher = new HashMap<>();
	static private LernfachVerwaltung lernfachVerwaltungSingleton = new LernfachVerwaltung();

	private LernfachVerwaltung()
	{
		super();
	}

	public static LernfachVerwaltung getInstance()
	{
		return lernfachVerwaltungSingleton;
	}

	public static Lernfach get(int id)
	{
		return lernfaecher.get(id);
	}

	public static HashMap<Integer, Lernfach> getAll()
	{
		return lernfaecher;
	}

	static void add(Lernfach lernfach)
	{
		lernfaecher.put(lernfach.getId(),
				lernfach);
	}

	public static void remove(Lernfach lernfach)
	{
		remove(lernfach.getId());
	}

	public static void remove(int id)
	{
		lernfaecher.remove(id);
	}

	static void update(Lernfach lernfach)
	{
		lernfaecher.put(lernfach.getId(),
				lernfach);
	}

	static ArrayList<Lernfach> suche(String suchstring)
	{
		final ArrayList<Lernfach> passendeLernfaecherZumSuchstring = new ArrayList<>();
		for (final Entry<Integer, Lernfach> student : lernfaecher.entrySet())
			if (student.getValue()
					.toString()
					.contains(suchstring))
				passendeLernfaecherZumSuchstring.add(student.getValue());

		if (passendeLernfaecherZumSuchstring.size() > 0)
			return passendeLernfaecherZumSuchstring;

		return null;
	}

}
