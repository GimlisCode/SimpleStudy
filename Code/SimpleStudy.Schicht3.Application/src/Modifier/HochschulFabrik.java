package Modifier;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Dozent;
import Models.Hochschule;

public class HochschulFabrik
{

	private static Hochschule neueHochschule = new Hochschule("");
	private static HochschulVerwaltung hochschulVerwaltung;
	private static HochschulFabrik hochschulFabrikSingleton = new HochschulFabrik();
	private static ArrayList<Tupel<Integer, Integer>> dozentenReferenzen = new ArrayList<>();

	private HochschulFabrik()
	{

		hochschulVerwaltung = HochschulVerwaltung.getInstance();
	}

	public static HochschulFabrik getInstance()
	{
		return hochschulFabrikSingleton;
	}

	public static HashMap<String, String> getHochschulAttribute()
	{
		final HashMap<String, String> hochschulAttribute = new HashMap<>();
		final String[] attributNamen = neueHochschule.getAttributeNames();
		for (final String attributName : attributNamen)
			hochschulAttribute.put(attributName,
					"");

		return hochschulAttribute;
	}

	public static void create(HashMap<String, String> hochschulAttribute)
	{
		neueHochschule.setId(Integer.parseInt(hochschulAttribute.get(Hochschule.idText)));
		neueHochschule.setName(hochschulAttribute.get(Hochschule.nameText));
		final String dozentenIds = hochschulAttribute.get(Hochschule.dozentenText);
		if (dozentenIds != null)
		{
			final String[] alleDozentenId = dozentenIds.split(";");
			for (final String dozentenId : alleDozentenId)
			{
				final Dozent dozent = DozentenVerwaltung.get(Integer.parseInt(dozentenId));
				if (dozent != null)
					neueHochschule.add(dozent);
				else
					dozentenReferenzen.add(new Tupel<>(neueHochschule.getId(), Integer.parseInt(dozentenId)));

			}
		}

		hochschulVerwaltung.add(neueHochschule);
		neueHochschule = new Hochschule(null, null);
	}

	public static void resolveReferences()
	{
		for (final Tupel<Integer, Integer> tupel : dozentenReferenzen)
		{
			final Dozent dozent = DozentenVerwaltung.get(tupel.y);
			if (dozent != null)
				hochschulVerwaltung.get(tupel.x)
						.add(dozent);
			// else
			// throw error ask User

		}
	}

}
