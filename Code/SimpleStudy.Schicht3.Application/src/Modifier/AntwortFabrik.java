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
		neueAntwort.setId(Integer.parseInt(antwortAttribute.get(Antwort.idText)));
		neueAntwort.setText(antwortAttribute.get(Antwort.textText));
		neueAntwort.setCorrect(Boolean.getBoolean(antwortAttribute.get(Antwort.textText)));
		antwortenVerwaltung.add(neueAntwort);
		neueAntwort = new Antwort("", false);
	}
}
