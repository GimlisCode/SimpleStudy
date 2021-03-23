package Modifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import Models.Hochschule;

public class HochschulVerwaltung
{

	static private HashMap<Integer, Hochschule> hochschulen = new HashMap<>();
	static private HochschulVerwaltung hochschulVerwaltungSingleton = new HochschulVerwaltung();

	public HochschulVerwaltung()
	{
		super();
	}

	public static HochschulVerwaltung getInstance()
	{
		return hochschulVerwaltungSingleton;
	}

	public static Hochschule get(int id)
	{
		return hochschulen.get(id);
	}

	public static HashMap<Integer, Hochschule> getAll()
	{
		return hochschulen;
	}

	static void add(Hochschule neuesKapitel)
	{
		hochschulen.put(neuesKapitel.getId(),
				neuesKapitel);
	}

	static void remove(Hochschule kapitel)
	{
		remove(kapitel.getId());
	}

	public static void remove(int id)
	{
		hochschulen.remove(id);
	}

	static void update(Hochschule kapitelUpdate)
	{
		hochschulen.put(kapitelUpdate.getId(),
				kapitelUpdate);
	}

	static ArrayList<Hochschule> suche(String suchstring)
	{
		final ArrayList<Hochschule> passendeLernfaecherZumSuchstring = new ArrayList<>();
		for (final Entry<Integer, Hochschule> hochschule : hochschulen.entrySet())
			if (hochschule.getValue()
					.toString()
					.contains(suchstring))
				passendeLernfaecherZumSuchstring.add(hochschule.getValue());

		if (passendeLernfaecherZumSuchstring.size() > 0)
			return passendeLernfaecherZumSuchstring;

		return null;
	}

}
