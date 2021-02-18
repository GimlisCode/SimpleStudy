package Modifier;

import java.util.HashMap;

import Models.Richtigkeit;
import Models.Statistik;

public class StatistikFabrik
{
	private static Statistik neueStatistik = new Statistik();
	private static StatistikVerwaltung statistikVerwaltung;
	private static StatistikFabrik statistikFabrikSingleton;

	private StatistikFabrik()
	{
		statistikVerwaltung = StatistikVerwaltung.getInstance();
	}

	public static StatistikFabrik getInstance()
	{
		return statistikFabrikSingleton;
	}

	public static HashMap<String, String> getStatistikAttribute()
	{
		HashMap<String, String> statistikAttribute = new HashMap<>();
		String[] attributNamen = neueStatistik.getAttributeNames();
		for (String attributName : attributNamen)
			statistikAttribute.put(attributName, "");

		return statistikAttribute;
	}

	public static void create(HashMap<String, String> statistikAttribute)
	{
		neueStatistik.setId(Integer.parseInt(statistikAttribute.get(Statistik.idText)));

		String[] alleRichtigkeiten = statistikAttribute.get(Statistik.statistikText).split(";");
		for (String richtigkeit : alleRichtigkeiten)
		{
			String[] richtigkeitenWerte = richtigkeit.split(",");
			neueStatistik.addToStatistik(Integer.parseInt(richtigkeitenWerte[0]),
					new Richtigkeit(Integer.parseInt(richtigkeitenWerte[1]), Integer.parseInt(richtigkeitenWerte[2]),
							Integer.parseInt(richtigkeitenWerte[3])));
		}

		statistikVerwaltung.add(neueStatistik);
		neueStatistik = new Statistik();
	}
}
