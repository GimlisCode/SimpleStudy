package Modifier;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Dozent;
import Models.Lernfach;

public class DozentenFabrik
{

	private static Dozent neuerDozent = new Dozent(null, null);
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
		HashMap<String, String> dozentAttribute = new HashMap<>();
		String[] attributNamen = neuerDozent.getAttributeNames();
		for (String attributName : attributNamen)
			dozentAttribute.put(attributName, "");

		return dozentAttribute;
	}

	public static void create(HashMap<String, String> dozentAttribute)
	{
		neuerDozent.setId(Integer.parseInt(dozentAttribute.get(Dozent.idtext)));
		neuerDozent.setName(dozentAttribute.get(Dozent.nameText));
		String[] alleKursId = dozentAttribute.get(Dozent.kurseText).split(";");
		for (String kursId : alleKursId)
		{
			Lernfach fach = LernfachVerwaltung.get(Integer.parseInt(kursId));
			if (fach != null)
			{
				neuerDozent.addKurs(fach);
			}
			else
			{
				lernfachReferenzen.add(new Tupel<>(neuerDozent.getId(), Integer.parseInt(kursId)));
			}

		}
		dozentenVerwaltung.add(neuerDozent);
		neuerDozent = new Dozent(null, null);
	}

	public static void resolveReferences()
	{
		for (Tupel<Integer, Integer> tupel : lernfachReferenzen)
		{
			Lernfach fach = LernfachVerwaltung.get(tupel.y);
			if (fach != null)
				dozentenVerwaltung.get(tupel.x).addKurs(fach);
			// else
			// throw error ask User

		}
	}

}
