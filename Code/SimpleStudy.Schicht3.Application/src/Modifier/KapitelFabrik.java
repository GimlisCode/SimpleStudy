package Modifier;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Frage;
import Models.Kapitel;

public class KapitelFabrik {

	private static Kapitel neuesKapitel = new Kapitel(null, 0, null);
	private static KapitelVerwaltung kapitelVerwaltung;
	private static KapitelFabrik kapitelFabrikSingleton = new KapitelFabrik();
	private static ArrayList<Tupel<Integer, Integer>> kapitelReferenzen = new ArrayList<>();

	private KapitelFabrik() {

		kapitelVerwaltung = KapitelVerwaltung.getInstance();
	}

	public static KapitelFabrik getInstance() {
		return kapitelFabrikSingleton;
	}

	public static HashMap<String, String> getKaptielAttribute() {
		HashMap<String, String> kapitelAttribute = new HashMap<>();
		String[] attributNamen = neuesKapitel.getAttributeNames();
		for (String attributName : attributNamen)
			kapitelAttribute.put(attributName, "");

		return kapitelAttribute;
	}

	public static void create(HashMap<String, String> kapitelAttribute) {
		neuesKapitel.setId(Integer.parseInt(kapitelAttribute.get("Id")));
		neuesKapitel.setName(kapitelAttribute.get("name"));
		neuesKapitel.setNr(Integer.parseInt("nr"));
		String[] alleFragenId = kapitelAttribute.get("fragen").split(";");
		for (String fragenId : alleFragenId) {
			Frage frage = FragenVerwaltung.get(Integer.parseInt(fragenId));
			if (frage != null) {
				neuesKapitel.add(frage);
			} else {
				kapitelReferenzen.add(new Tupel<>(neuesKapitel.getId(), Integer.parseInt(fragenId)));
			}

		}
		KapitelVerwaltung.add(neuesKapitel);
		neuesKapitel = new Kapitel(null, 0, null);

	}
}
