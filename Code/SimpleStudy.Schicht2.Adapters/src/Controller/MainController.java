package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import Models.Entity;
import Modifier.AntwortFabrik;
import Modifier.DozentenFabrik;
import Modifier.DozentenVerwaltung;
import Modifier.HochschulFabrik;
import Modifier.HochschulVerwaltung;
import Modifier.KapitelVerwaltung;
import Modifier.LernfachVerwaltung;
import Modifier.StatistikFabrik;
import Modifier.StudentenFabrik;
import Modifier.StudentenVerwaltung;
import Renderer.DozentenRenderer;
import Renderer.HochschulRenderer;
import Renderer.KapitelRenderer;
import Renderer.LernfachRenderer;
import Renderer.PrettyHashMap;
import Renderer.StudentenRenderer;

public class MainController
{

	private static MainController mainControllerSingleton = new MainController();

	private MainController()
	{
		super();
	}

	public static MainController getInstance()
	{
		return mainControllerSingleton;
	}

	private static int getNewIdFor()
	{
		final List<Integer> keyList = new ArrayList<>(StudentenVerwaltung.getAll()
				.keySet());
		Collections.sort(keyList);
		return keyList.get(keyList.size() - 1) + 1;

	}

	public static void createAntwort(HashMap<String, String> antwortWerte)
	{
		final var antwortAttribute = AntwortFabrik.getAntwortAttribute();
		for (final Entry<String, String> antwortAttribut : antwortAttribute.entrySet())
			antwortAttribut.setValue(antwortWerte.get(antwortAttribut.getKey()));

		AntwortFabrik.create(antwortAttribute);

	}

	public static void createDozent(HashMap<String, String> dozentWerte)
	{
		final var dozentAttribute = DozentenFabrik.getDozentAttribute();
		for (final Entry<String, String> dozentAttribut : dozentAttribute.entrySet())
			dozentAttribut.setValue(dozentWerte.get(dozentAttribut.getKey()));

		DozentenFabrik.create(dozentAttribute);
	}

	public static void createStudent(HashMap<String, String> studentWerte)
	{
		final var studentAttribute = StudentenFabrik.getStudentenAttribute();
		for (final Entry<String, String> studentAttribut : studentAttribute.entrySet())
			studentAttribut.setValue(studentWerte.get(studentAttribut.getKey()));

		final String id = studentAttribute.get(Entity.idText);
		if (id == null || id.isEmpty())
			studentAttribute.replace(Entity.idText,
					getNewIdFor() + "");

		StudentenFabrik.create(studentAttribute);
	}

	public static void createHochschule(HashMap<String, String> hochschulen)
	{
		final var hochschulAttribute = HochschulFabrik.getHochschulAttribute();
		for (final Entry<String, String> hochschulAttribut : hochschulAttribute.entrySet())
			hochschulAttribut.setValue(hochschulen.get(hochschulAttribut.getKey())); // TODO: Stilbruch im Namen, durch
																						// autogenerate

		HochschulFabrik.create(hochschulAttribute);
	}

	public static void createStatistik(HashMap<String, String> currentNewStatistik)
	{
		final var statistikAttribute = StatistikFabrik.getStatistikAttribute();
		for (final Entry<String, String> statistikAttribut : statistikAttribute.entrySet())
			statistikAttribut.setValue(currentNewStatistik.get(statistikAttribut.getKey()));

		StatistikFabrik.create(statistikAttribute);

	}

	public static ArrayList<PrettyHashMap> getStudenten()
	{

		return StudentenRenderer.getStudentForView(StudentenVerwaltung.getAll()
				.values());
	}

	public static ArrayList<PrettyHashMap> getHochschulen()
	{
		return HochschulRenderer.getHochschuleForView(HochschulVerwaltung.getAll()
				.values());
	}

	public static ArrayList<PrettyHashMap> getDozenten()
	{
		return DozentenRenderer.getDozentenForView(DozentenVerwaltung.getAll()
				.values());
	}

	public static ArrayList<PrettyHashMap> getLernfaecher()
	{
		return LernfachRenderer.getLernfaecherForView(LernfachVerwaltung.getAll()
				.values());
	}

	public static ArrayList<PrettyHashMap> getKapitel()
	{
		return KapitelRenderer.getKapitelForView(KapitelVerwaltung.getAll()
				.values());
	}

}
