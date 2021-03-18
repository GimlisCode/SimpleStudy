package Modifier;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Dozent;
import Models.Lernfach;

public class DozentenFabrik
{

	private static Dozent neuerDozent = new Dozent("");
	private static DozentenVerwaltung dozentenVerwaltung;
	private static DozentenFabrik dozentenFabrikSingleton = new DozentenFabrik();
	private static ArrayList<Tupel<Integer, Integer>> lernfachReferenzen = new ArrayList<>();

	private DozentenFabrik()
	{

		dozentenVerwaltung = DozentenVerwaltung.getInstance();
	}

	public static DozentenFabrik getInstance()
	{
		return dozentenFabrikSingleton;
	}

	public static HashMap<String, String> getDozentAttribute()
	{
		final HashMap<String, String> dozentAttribute = new HashMap<>();
		final String[] attributNamen = neuerDozent.getAttributeNames();
		for (final String attributName : attributNamen)
			dozentAttribute.put(attributName,
					"");

		return dozentAttribute;
	}

	public static void create(HashMap<String, String> dozentAttribute)
	{
		neuerDozent.setId(Integer.parseInt(dozentAttribute.get(Dozent.idText)));
		neuerDozent.setName(dozentAttribute.get(Dozent.nameText));

		final String kursIds = dozentAttribute.get(Dozent.kurseText);
		if (kursIds != null)
		{
			final String[] alleKursId = kursIds.split(";");
			for (final String kursId : alleKursId)
			{
				final Lernfach fach = LernfachVerwaltung.get(Integer.parseInt(kursId));
				if (fach != null)
					neuerDozent.addKurs(fach);
				else
					lernfachReferenzen.add(new Tupel<>(neuerDozent.getId(), Integer.parseInt(kursId)));
			}
		}

		dozentenVerwaltung.add(neuerDozent);
		neuerDozent = new Dozent("");
	}

	public static void resolveReferences()
	{
		for (final Tupel<Integer, Integer> tupel : lernfachReferenzen)
		{
			final Lernfach fach = LernfachVerwaltung.get(tupel.y);
			if (fach != null)
				dozentenVerwaltung.get(tupel.x)
						.addKurs(fach);
			// else
			// throw error ask User

		}
	}

}
