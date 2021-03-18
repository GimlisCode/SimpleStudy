package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import Models.Antwort;
import Models.Dozent;
import Models.Entity;
import Models.Frage;
import Models.Hochschule;
import Models.Kapitel;
import Models.Lernfach;
import Models.Statistik;
import Models.Student;
import Modifier.AntwortFabrik;
import Modifier.AntwortVerwaltung;
import Modifier.DozentenFabrik;
import Modifier.DozentenVerwaltung;
import Modifier.FragenFabrik;
import Modifier.FragenVerwaltung;
import Modifier.HochschulFabrik;
import Modifier.HochschulVerwaltung;
import Modifier.KapitelFabrik;
import Modifier.KapitelVerwaltung;
import Modifier.LernfachFabrik;
import Modifier.LernfachVerwaltung;
import Modifier.StatistikFabrik;
import Modifier.StatistikVerwaltung;
import Modifier.StudentenFabrik;
import Modifier.StudentenVerwaltung;
import Renderer.AntwortenRenderer;
import Renderer.DozentenRenderer;
import Renderer.FragenRenderer;
import Renderer.HochschulRenderer;
import Renderer.KapitelRenderer;
import Renderer.LernfachRenderer;
import Renderer.PrettyHashMap;
import Renderer.StudentenRenderer;

public class MainController implements UiBeobachtete
{

	private static MainController mainControllerSingleton = new MainController();
	private static Student currentUser = null;
	private static ArrayList<UiBeobachter> registrierteUiBeobachter = new ArrayList<UiBeobachter>();

	private MainController()
	{
		super();
	}

	public static MainController getInstance()
	{
		return mainControllerSingleton;
	}

	private static int getNewIdFor(String className)
	{
		List<Integer> keyList = new ArrayList<Integer>();
		if (className == Antwort.class.getSimpleName())
			keyList = new ArrayList<>(AntwortVerwaltung.getAll()
					.keySet());

		else if (className == Dozent.class.getSimpleName())
			keyList = new ArrayList<>(DozentenVerwaltung.getAll()
					.keySet());

		else if (className == Student.class.getSimpleName())
			keyList = new ArrayList<>(StudentenVerwaltung.getAll()
					.keySet());

		else if (className == Hochschule.class.getSimpleName())
			keyList = new ArrayList<>(HochschulVerwaltung.getAll()
					.keySet());

		else if (className == Lernfach.class.getSimpleName())
			keyList = new ArrayList<>(LernfachVerwaltung.getAll()
					.keySet());

		else if (className == Kapitel.class.getSimpleName())
			keyList = new ArrayList<>(KapitelVerwaltung.getAll()
					.keySet());

		else if (className == Frage.class.getSimpleName())
			keyList = new ArrayList<>(FragenVerwaltung.getAll()
					.keySet());

		else if (className == Statistik.class.getSimpleName())
			keyList = new ArrayList<>(StatistikVerwaltung.getAll()
					.keySet());

		if (keyList.size() <= 0)
			return 1;

		Collections.sort(keyList);
		return keyList.get(keyList.size() - 1) + 1;

	}

	public static void createAntwort(HashMap<String, String> antwortWerte)
	{
		final var antwortAttribute = AntwortFabrik.getAntwortAttribute();
		for (final Entry<String, String> antwortAttribut : antwortAttribute.entrySet())
			antwortAttribut.setValue(antwortWerte.get(antwortAttribut.getKey()));

		final String id = antwortAttribute.get(Entity.idText);
		if (id == null || id.isEmpty())
			antwortAttribute.replace(Entity.idText,
					getNewIdFor(Antwort.class.getSimpleName()) + "");

		AntwortFabrik.create(antwortAttribute);

	}

	public static void createDozent(HashMap<String, String> dozentWerte)
	{
		final var dozentAttribute = DozentenFabrik.getDozentAttribute();
		for (final Entry<String, String> dozentAttribut : dozentAttribute.entrySet())
			dozentAttribut.setValue(dozentWerte.get(dozentAttribut.getKey()));

		final String id = dozentAttribute.get(Entity.idText);
		if (id == null || id.isEmpty())
			dozentAttribute.replace(Entity.idText,
					getNewIdFor(Dozent.class.getSimpleName()) + "");

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
					getNewIdFor(Student.class.getSimpleName()) + "");

		StudentenFabrik.create(studentAttribute);
	}

	public static void updateStudent(HashMap<String, String> studentWerte)
	{
		createStudent(studentWerte);
		StudentenFabrik.resolveReferences();
		getInstance().benachrichtigeUis();
	}

	public static void createHochschule(HashMap<String, String> hochschulen)
	{
		final var hochschulAttribute = HochschulFabrik.getHochschulAttribute();
		for (final Entry<String, String> hochschulAttribut : hochschulAttribute.entrySet())
			hochschulAttribut.setValue(hochschulen.get(hochschulAttribut.getKey())); // TODO: Stilbruch im Namen, durch
																						// autogenerate
		final String id = hochschulAttribute.get(Entity.idText);
		if (id == null || id.isEmpty())
			hochschulAttribute.replace(Entity.idText,
					getNewIdFor(Hochschule.class.getSimpleName()) + "");

		HochschulFabrik.create(hochschulAttribute);
	}

	public static void createStatistik(HashMap<String, String> currentNewStatistik)
	{
		final var statistikAttribute = StatistikFabrik.getStatistikAttribute();
		for (final Entry<String, String> statistikAttribut : statistikAttribute.entrySet())
			statistikAttribut.setValue(currentNewStatistik.get(statistikAttribut.getKey()));

		final String id = statistikAttribute.get(Entity.idText);
		if (id == null || id.isEmpty())
			statistikAttribute.replace(Entity.idText,
					getNewIdFor(Statistik.class.getSimpleName()) + "");

		StatistikFabrik.create(statistikAttribute);

	}

	public static void createLernfach(HashMap<String, String> lernfach)
	{
		final var lernfachAttribute = LernfachFabrik.getLernfachAttribute();
		for (final Entry<String, String> lernfachAttribut : lernfachAttribute.entrySet())
			lernfachAttribut.setValue(lernfach.get(lernfachAttribut.getKey()));

		final String id = lernfachAttribute.get(Entity.idText);
		if (id == null || id.isEmpty())
			lernfachAttribute.replace(Entity.idText,
					getNewIdFor(Lernfach.class.getSimpleName()) + "");

		LernfachFabrik.create(lernfachAttribute);
	}

	public static void createKapitel(HashMap<String, String> kapitel)
	{
		final var kapitelAttribute = KapitelFabrik.getKaptielAttribute();
		for (final Entry<String, String> kapitelAttribut : kapitelAttribute.entrySet())
			kapitelAttribut.setValue(kapitel.get(kapitelAttribut.getKey()));

		final String id = kapitelAttribute.get(Entity.idText);
		if (id == null || id.isEmpty())
			kapitelAttribute.replace(Entity.idText,
					getNewIdFor(Kapitel.class.getSimpleName()) + "");

		KapitelFabrik.create(kapitelAttribute);
	}

	public static void createFrage(HashMap<String, String> frage)
	{
		final var fragenAttribute = FragenFabrik.getFragenAttribute();
		for (final Entry<String, String> frageAttribut : fragenAttribute.entrySet())
			frageAttribut.setValue(frage.get(frageAttribut.getKey()));

		final String id = fragenAttribute.get(Entity.idText);
		if (id == null || id.isEmpty())
			fragenAttribute.replace(Entity.idText,
					getNewIdFor(Frage.class.getSimpleName()) + "");

		FragenFabrik.create(fragenAttribute);

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

	public static ArrayList<PrettyHashMap> getFragen()
	{
		return FragenRenderer.getFragenForView(FragenVerwaltung.getAll()
				.values());
	}

	public static ArrayList<PrettyHashMap> getAntworten()
	{
		return AntwortenRenderer.getAntwortenForView(AntwortVerwaltung.getAll()
				.values());
	}

	public static void setCurrentUser(PrettyHashMap selectedItem)
	{
		currentUser = StudentenVerwaltung.get(Integer.parseInt(selectedItem.visible.get(Entity.idText)));

	}

	@Override
	public void registriere(UiBeobachter uiBeobachter)
	{
		registrierteUiBeobachter.add(uiBeobachter);

	}

	@Override
	public void entferne(UiBeobachter uiBeobachter)
	{
		registrierteUiBeobachter.remove(uiBeobachter);

	}

	@Override
	public void benachrichtigeUis()
	{
		for (final UiBeobachter uiBeobachter : registrierteUiBeobachter)
			uiBeobachter.aktualisiere();
	}

}
