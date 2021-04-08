package Modifier;

import java.util.HashMap;

import Models.Antwort;

public class AntwortFabrik
{
	private static Antwort neueAntwort = new Antwort("", false);
	private static AntwortVerwaltung antwortenVerwaltung;
	private static AntwortFabrik antwortFabrikSingleton = new AntwortFabrik();

	private AntwortFabrik()
	{

		antwortenVerwaltung = AntwortVerwaltung.getInstance();
	}

	public static AntwortFabrik getInstance()
	{
		return antwortFabrikSingleton;
	}

	public static HashMap<String, String> getAntwortAttribute()
	{
		final HashMap<String, String> antwortAttribute = new HashMap<>();
		final String[] attributNamen = neueAntwort.getAttributeNames();
		for (final String attributName : attributNamen)
			antwortAttribute.put(attributName,
					"");

		return antwortAttribute;
	}

	public static void create(HashMap<String, String> antwortAttribute)
	{
		if (antwortAttribute.get(Antwort.idText) == null || antwortAttribute.get(Antwort.idText)
				.isEmpty() || antwortAttribute.get(Antwort.idText)
						.isBlank())
			return;

		neueAntwort.setId(Integer.parseInt(antwortAttribute.get(Antwort.idText)));
		neueAntwort.setText(antwortAttribute.get(Antwort.textText));
		if (antwortAttribute.get(Antwort.correctText)
				.contains("1")
				&& antwortAttribute.get(Antwort.correctText)
						.contains("1"))
			if (Integer.parseInt(antwortAttribute.get(Antwort.correctText)) != 0)
				neueAntwort.setCorrect(true);
			else
				neueAntwort.setCorrect(false);
		else
			neueAntwort.setCorrect(Boolean.parseBoolean(antwortAttribute.get(Antwort.correctText)));

		antwortenVerwaltung.add(neueAntwort);
		neueAntwort = new Antwort("", false);
	}
}
