package Modifier;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Antwort;
import Models.Frage;

public class FragenFabrik
{

	private static Frage neueFrage = new Frage(null, 0, null);
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

	public static HashMap<String, String> getFragenAttribute()
	{
		HashMap<String, String> antwortAttribute = new HashMap<>();
		String[] attributNamen = neueFrage.getAttributeNames();
		for (String attributName : attributNamen)
			antwortAttribute.put(attributName, "");

		return antwortAttribute;
	}

	public static void create(HashMap<String, String> fragenAttribute)
	{
		neueFrage.setId(Integer.parseInt(fragenAttribute.get(Frage.idText)));
		neueFrage.setText(fragenAttribute.get(Frage.textText));
		neueFrage.setTyp(Integer.parseInt(fragenAttribute.get(Frage.typText)));
		String[] alleAntwortId = fragenAttribute.get(Frage.antwortenText).split(";");
		for (String antwortenID : alleAntwortId)
		{
			Antwort antwort = AntwortVerwaltung.get(Integer.parseInt(antwortenID));
			if (antwort != null)
			{
				neueFrage.add(antwort);
			}
			else
			{
				antwortReferenzen.add(new Tupel<>(neueFrage.getId(), Integer.parseInt(antwortenID)));
			}

		}
		fragenVerwaltung.add(neueFrage);
		neueFrage = new Frage(null, 0, null);
	}

	public static void resolveReferences()
	{
		for (Tupel<Integer, Integer> tupel : antwortReferenzen)
		{
			Antwort antwort = AntwortVerwaltung.get(tupel.y);
			if (antwort != null)
				fragenVerwaltung.get(tupel.x).add(antwort);
			// else
			// throw error ask User

		}
	}

}
