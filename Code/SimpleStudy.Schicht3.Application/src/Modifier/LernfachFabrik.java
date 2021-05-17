package Modifier;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Kapitel;
import Models.Lernfach;

public class LernfachFabrik
{

	private static Lernfach neuesLernfach = new Lernfach("", 0, 0);
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

	public HashMap<String, String> getLernfachAttribute()
	{
		final HashMap<String, String> lernfachAttribute = new HashMap<>();
		final String[] attributNamen = neuesLernfach.getAttributeNames();
		for (final String attributName : attributNamen)
			lernfachAttribute.put(attributName,
					"");

		return lernfachAttribute;
	}

	public void create(HashMap<String, String> lernfachAttribute)
	{
		neuesLernfach.setId(Integer.parseInt(lernfachAttribute.get(Lernfach.idText)));
		neuesLernfach.setName(lernfachAttribute.get(Lernfach.nameText));
		neuesLernfach.setSemester(Integer.parseInt(lernfachAttribute.get(Lernfach.semesterText)));
		neuesLernfach.setCredits(Integer.parseInt(lernfachAttribute.get(Lernfach.creditsText)));

		final String kapitelIds = lernfachAttribute.get(Lernfach.kapitelText);
		if (kapitelIds != null)
		{
			final String[] alleKapitelId = kapitelIds.split(";");
			for (final String kapitelId : alleKapitelId)
			{
				final Kapitel kapitel = KapitelVerwaltung.getInstance()
						.get(Integer.parseInt(kapitelId));
				if (kapitel != null)
					neuesLernfach.add(kapitel);
				else
					kapitelReferenzen.add(new Tupel<>(neuesLernfach.getId(), Integer.parseInt(kapitelId)));

			}
		}

		lernfachVerwaltung.add(neuesLernfach);
		neuesLernfach = new Lernfach("", 0, 0);
	}

	public void resolveReferences()
	{
		for (final Tupel<Integer, Integer> tupel : kapitelReferenzen)
		{
			final Kapitel kapitel = KapitelVerwaltung.getInstance()
					.get(tupel.y);
			if (kapitel != null)
				lernfachVerwaltung.get(tupel.x)
						.add(kapitel);
			// else
			// throw error ask User

		}
	}
}
