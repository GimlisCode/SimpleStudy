package Modifier;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Frage;
import Models.Kapitel;

public class KapitelFabrik
{

	private static Kapitel neuesKapitel = new Kapitel("", 0);
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

	public HashMap<String, String> getKapitelAttribute()
	{
		final HashMap<String, String> kapitelAttribute = new HashMap<>();
		final String[] attributNamen = neuesKapitel.getAttributeNames();
		for (final String attributName : attributNamen)
			kapitelAttribute.put(attributName,
					"");

		return kapitelAttribute;
	}

	public void create(HashMap<String, String> kapitelAttribute)
	{
		neuesKapitel.setId(Integer.parseInt(kapitelAttribute.get(Kapitel.idText)));
		neuesKapitel.setName(kapitelAttribute.get(Kapitel.nameText));
		neuesKapitel.setNr(Integer.parseInt(kapitelAttribute.get(Kapitel.nrText)));
		final String fragenIds = kapitelAttribute.get(Kapitel.fragenText);
		if (fragenIds != null)
		{
			final String[] alleFragenId = fragenIds.split(";");
			for (final String fragenId : alleFragenId)
			{
				final Frage frage = FragenVerwaltung.getInstance()
						.get(Integer.parseInt(fragenId));
				if (frage != null)
					neuesKapitel.add(frage);
				else
					fragenReferenzen.add(new Tupel<>(neuesKapitel.getId(), Integer.parseInt(fragenId)));

			}
		}

		KapitelVerwaltung.getInstance()
				.add(neuesKapitel);
		neuesKapitel = new Kapitel("", 0);
	}

	public void resolveReferences()
	{
		for (final Tupel<Integer, Integer> tupel : fragenReferenzen)
		{
			final Frage frage = FragenVerwaltung.getInstance()
					.get(tupel.y);
			if (frage != null)
				kapitelVerwaltung.get(tupel.x)
						.add(frage);

		}
	}
}
