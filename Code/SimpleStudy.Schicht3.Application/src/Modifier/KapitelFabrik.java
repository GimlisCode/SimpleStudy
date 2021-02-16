package Modifier;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Frage;
import Models.Kapitel;

public class KapitelFabrik
{

	private static Kapitel neuesKapitel = new Kapitel(null, 0, null);
	private static KapitelVerwaltung kapitelVerwaltung;
	private static KapitelFabrik kapitelFabrikSingleton = new KapitelFabrik();
	private static ArrayList<Tupel<Integer, Integer>> fragenReferenzen = new ArrayList<>();

	private KapitelFabrik()
	{

		kapitelVerwaltung = KapitelVerwaltung.getInstance();
	}

	public static KapitelFabrik getInstance()
	{
		return kapitelFabrikSingleton;
	}

	public static HashMap<String, String> getKaptielAttribute()
	{
		HashMap<String, String> kapitelAttribute = new HashMap<>();
		String[] attributNamen = neuesKapitel.getAttributeNames();
		for (String attributName : attributNamen)
			kapitelAttribute.put(attributName, "");

		return kapitelAttribute;
	}

	public static void create(HashMap<String, String> kapitelAttribute)
	{
		neuesKapitel.setId(Integer.parseInt(kapitelAttribute.get(Kapitel.idtext)));
		neuesKapitel.setName(kapitelAttribute.get(Kapitel.nameText));
		neuesKapitel.setNr(Integer.parseInt(Kapitel.nrText));
		String[] alleFragenId = kapitelAttribute.get(Kapitel.fragenText).split(";");
		for (String fragenId : alleFragenId)
		{
			Frage frage = FragenVerwaltung.get(Integer.parseInt(fragenId));
			if (frage != null)
			{
				neuesKapitel.add(frage);
			}
			else
			{
				fragenReferenzen.add(new Tupel<>(neuesKapitel.getId(), Integer.parseInt(fragenId)));
			}

		}
		KapitelVerwaltung.add(neuesKapitel);
		neuesKapitel = new Kapitel(null, 0, null);
	}

	public static void resolveReferences()
	{
		for (Tupel<Integer, Integer> tupel : fragenReferenzen)
		{
			Frage frage = FragenVerwaltung.get(tupel.y);
			if (frage != null)
				kapitelVerwaltung.get(tupel.x).add(frage);
			// else
			// throw error ask User

		}
	}
}
