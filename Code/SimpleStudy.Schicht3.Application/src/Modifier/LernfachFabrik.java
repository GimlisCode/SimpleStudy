package Modifier;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Kapitel;
import Models.Lernfach;

public class LernfachFabrik
{

	private static Lernfach neuesLernfach = new Lernfach("", 0, 0, null);
	private static LernfachVerwaltung lernfachVerwaltung;
	private static LernfachFabrik lernfachFabrikSingleton = new LernfachFabrik();
	private static ArrayList<Tupel<Integer, Integer>> kapitelReferenzen = new ArrayList<>();

	public LernfachFabrik()
	{
		lernfachVerwaltung = LernfachVerwaltung.getInstance();
	}

	public static LernfachFabrik getInstance()
	{
		return lernfachFabrikSingleton;
	}

	public static HashMap<String, String> getLernfachAttribute()
	{
		HashMap<String, String> lernfachAttribute = new HashMap<>();
		String[] attributNamen = neuesLernfach.getAttributeNames();
		for (String attributName : attributNamen)
			lernfachAttribute.put(attributName, "");

		return lernfachAttribute;
	}

	public static void create(HashMap<String, String> lernfachAttribute)
	{
		neuesLernfach.setId(Integer.parseInt(lernfachAttribute.get(Lernfach.idText)));
		neuesLernfach.setName(lernfachAttribute.get(Lernfach.nameText));
		neuesLernfach.setSemester(Integer.parseInt(lernfachAttribute.get(Lernfach.semesterText)));
		neuesLernfach.setCredits(Integer.parseInt(lernfachAttribute.get(Lernfach.creditsText)));
		String[] alleKapitelId = lernfachAttribute.get(Lernfach.kapitelText).split(";");
		for (String kapitelId : alleKapitelId)
		{
			Kapitel kapitel = KapitelVerwaltung.get(Integer.parseInt(kapitelId));
			if (kapitel != null)
			{
				neuesLernfach.add(kapitel);
			}
			else
			{
				kapitelReferenzen.add(new Tupel<>(neuesLernfach.getId(), Integer.parseInt(kapitelId)));
			}

		}
		lernfachVerwaltung.add(neuesLernfach);
		neuesLernfach = new Lernfach("", 0, 0, null);
	}

	public static void resolveReferences()
	{
		for (Tupel<Integer, Integer> tupel : kapitelReferenzen)
		{
			Kapitel kapitel = KapitelVerwaltung.get(tupel.y);
			if (kapitel != null)
				lernfachVerwaltung.get(tupel.x).add(kapitel);
			// else
			// throw error ask User

		}
	}
}
