package Modifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import Models.Frage;

public class FragenVerwaltung
{

	static FragenVerwaltung fragenVerwaltungSingleton = new FragenVerwaltung();
	static private HashMap<Integer, Frage> fragen = new HashMap<>();

	private FragenVerwaltung()
	{
		super();
	}

	static FragenVerwaltung getInstance()
	{
		return fragenVerwaltungSingleton;
	}

	public static Frage get(int id)
	{
		return fragen.get(id);
	}

	public static HashMap<Integer, Frage> getAll()
	{
		return fragen;
	}

	static void add(Frage frage)
	{
		fragen.put(frage.getId(),
				frage);
	}

	public static void remove(Frage frage)
	{
		remove(frage.getId());
	}

	public static void remove(int id)
	{
		fragen.remove(id);
	}

	static void update(Frage frage)
	{
		fragen.put(frage.getId(),
				frage);
	}

	static ArrayList<Frage> suche(String suchstring)
	{
		final ArrayList<Frage> passendeFragenZumSuchstring = new ArrayList<>();
		for (final Entry<Integer, Frage> frage : fragen.entrySet())
			if (frage.getValue()
					.toString()
					.contains(suchstring))
				passendeFragenZumSuchstring.add(frage.getValue());

		if (passendeFragenZumSuchstring.size() > 0)
			return passendeFragenZumSuchstring;

		return null;

	}

}
