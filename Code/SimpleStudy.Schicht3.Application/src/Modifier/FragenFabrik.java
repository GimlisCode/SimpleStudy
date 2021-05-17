package Modifier;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Antwort;
import Models.Frage;

public class FragenFabrik
{

	private static Frage neueFrage = new Frage("", 0);
	private static FragenVerwaltung fragenVerwaltung;
	private static FragenFabrik fragenFabrikSingleton = new FragenFabrik();
	private static ArrayList<Tupel<Integer, Integer>> antwortReferenzen = new ArrayList<>();

	private FragenFabrik()
	{

		fragenVerwaltung = FragenVerwaltung.getInstance();
	}

	public static FragenFabrik getInstance()
	{
		return fragenFabrikSingleton;
	}

	public HashMap<String, String> getFragenAttribute()
	{
		final HashMap<String, String> fragenAttribute = new HashMap<>();
		final String[] attributNamen = neueFrage.getAttributeNames();
		for (final String attributName : attributNamen)
			fragenAttribute.put(attributName,
					"");

		return fragenAttribute;
	}

	public void create(HashMap<String, String> fragenAttribute)
	{
		neueFrage.setId(Integer.parseInt(fragenAttribute.get(Frage.idText)));
		neueFrage.setText(fragenAttribute.get(Frage.textText));
		neueFrage.setTyp(Integer.parseInt(fragenAttribute.get(Frage.typText)));
		final String antwortIds = fragenAttribute.get(Frage.antwortenText);
		if (antwortIds != null)
		{
			final String[] alleAntwortId = antwortIds.split(";");
			for (final String antwortenID : alleAntwortId)
			{
				final Antwort antwort = AntwortVerwaltung.getInstance()
						.get(Integer.parseInt(antwortenID));
				if (antwort != null)
					neueFrage.add(antwort);
				else
					antwortReferenzen.add(new Tupel<>(neueFrage.getId(), Integer.parseInt(antwortenID)));

			}
		}

		fragenVerwaltung.add(neueFrage);
		neueFrage = new Frage("", 0);
	}

	public void resolveReferences()
	{
		for (final Tupel<Integer, Integer> tupel : antwortReferenzen)
		{
			final Antwort antwort = AntwortVerwaltung.getInstance()
					.get(tupel.y);
			if (antwort != null)
				fragenVerwaltung.get(tupel.x)
						.add(antwort);
			// else
			// throw error ask User

		}
	}

}
