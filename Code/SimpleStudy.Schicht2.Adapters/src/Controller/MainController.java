package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import Models.Abfrage;
import Models.Abfragesystem;
import Models.Antwort;
import Models.Dozent;
import Models.Entity;
import Models.Frage;
import Models.Hochschule;
import Models.Kapitel;
import Models.Lernfach;
import Models.Modus;
import Models.Statistik;
import Models.Student;
import Modifier.AbfrageVerwaltung;
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

	public static Abfrage createAbfrage(ArrayList<HashMap<String, String>> fragenWerte)
	{
		final ArrayList<Frage> fragen = new ArrayList<Frage>();
		for (final HashMap<String, String> frage : fragenWerte)
			fragen.add(FragenVerwaltung.get(Integer.parseInt(frage.get(Entity.idText))));

		return AbfrageVerwaltung.getInstance()
				.neueAbfrage(Modus.ABFRAGE,
						Abfragesystem.LINEAR,
						fragen,
						currentUser);
	}

	public static void abfrageAuswerten(Abfrage abfrage)
	{
		AbfrageVerwaltung.getInstance()
				.abfrageAuswerten(abfrage);
	}

	private static int getNewIdFor(String className)
	{
		List<Integer> keyList = new ArrayList<Integer>();
		if (className == Antwort.class.getSimpleName())
			keyList = new ArrayList<>(AntwortVerwaltung.getInstance()
					.getAll()
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
		getInstance().benachrichtigeUis();

	}

	public static void updateAntwort(HashMap<String, String> antwortWerte)
	{
		createAntwort(antwortWerte);
		getInstance().benachrichtigeUis();
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
		getInstance().benachrichtigeUis();
	}

	public static void updateDozent(HashMap<String, String> dozentWerte)
	{
		createDozent(dozentWerte);
		DozentenFabrik.resolveReferences();
		getInstance().benachrichtigeUis();
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

		final String statistikId = studentAttribute.get(Student.statistikText);
		if (statistikId == null || statistikId.isEmpty())
		{
			final int newStatistik = getNewIdFor(Student.class.getSimpleName());
			final var newStatistikForUser = StatistikFabrik.getStatistikAttribute();
			newStatistikForUser.put(Entity.idText,
					newStatistik + "");
			newStatistikForUser.put(Statistik.statistikText,
					"1,0,0,1");
			createStatistik(newStatistikForUser);
			studentAttribute.replace(Student.statistikText,
					newStatistik + "");
		}

		StudentenFabrik.create(studentAttribute);
		getInstance().benachrichtigeUis();
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
		getInstance().benachrichtigeUis();
	}

	public static void updateHochschule(HashMap<String, String> hochschuleWerte)
	{
		createHochschule(hochschuleWerte);
		HochschulFabrik.resolveReferences();
		getInstance().benachrichtigeUis();
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
		getInstance().benachrichtigeUis();

	}

	public static void updateStatistik(HashMap<String, String> statistikWerte)
	{
		createStatistik(statistikWerte);
		getInstance().benachrichtigeUis();
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
		getInstance().benachrichtigeUis();
	}

	public static void updateLernfach(HashMap<String, String> lernfachWerte)
	{
		createLernfach(lernfachWerte);
		LernfachFabrik.resolveReferences();
		getInstance().benachrichtigeUis();
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
		getInstance().benachrichtigeUis();
	}

	public static void updateKapitel(HashMap<String, String> kapitelWerte)
	{
		createKapitel(kapitelWerte);
		KapitelFabrik.resolveReferences();
		getInstance().benachrichtigeUis();
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
		getInstance().benachrichtigeUis();
	}

	public static void updateFrage(HashMap<String, String> frageWerte)
	{
		createFrage(frageWerte);
		FragenFabrik.resolveReferences();
		getInstance().benachrichtigeUis();
	}

	public static void deleteStudent(String studentenId)
	{
		final var student = StudentenVerwaltung.get(Integer.parseInt(studentenId));
		if (student.getStatistik() != null)
			deleteStatistik(student.getStatistik()
					.getId() + "");

		StudentenVerwaltung.remove(Integer.parseInt(studentenId));
		getInstance().benachrichtigeUis();
	}

	public static void deleteStatistik(String statistikId)
	{
		StatistikVerwaltung.remove(Integer.parseInt(statistikId));
		getInstance().benachrichtigeUis();
	}

	public static void deleteHochschule(String hocschulId)
	{
		final var hochschule = HochschulVerwaltung.get(Integer.parseInt(hocschulId));
		final var dozenten = hochschule.getDozenten();
		HochschulVerwaltung.remove(Integer.parseInt(hocschulId));

		for (final Dozent dozent : dozenten)
			deleteDozent(dozent.getId() + "");

		getInstance().benachrichtigeUis();

	}

	public static void deleteDozent(String dozentId)
	{
		final var dozent = DozentenVerwaltung.get(Integer.parseInt(dozentId));
		final var kurse = dozent.getKurse();
		DozentenVerwaltung.remove(dozent);

		for (final Lernfach kurs : kurse)
			deleteLernfach(kurs.getId() + "");

		HochschulVerwaltung.getAll()
				.forEach((hochschulId, hochschule) ->
				{
					hochschule.remove(dozent);
				});

		getInstance().benachrichtigeUis();

	}

	public static void deleteLernfach(String lernfachId)
	{
		final var lernfach = LernfachVerwaltung.get(Integer.parseInt(lernfachId));
		final var kapitel = lernfach.getLernkapitel();
		LernfachVerwaltung.remove(lernfach);

		for (final Kapitel kapi : kapitel)
			deleteKapitel(kapi.getId() + "");

		DozentenVerwaltung.getAll()
				.forEach((dozentId, dozent) ->
				{
					dozent.removeKurs(lernfach);
				});

		getInstance().benachrichtigeUis();
	}

	public static void deleteKapitel(String kapitelId)
	{
		final var kapitel = KapitelVerwaltung.get(Integer.parseInt(kapitelId));
		final var fragen = kapitel.getFragen();
		KapitelVerwaltung.remove(kapitel);

		for (final Frage frage : fragen)
			deleteFrage(frage.getId() + "");

		LernfachVerwaltung.getAll()
				.forEach((lernfachId, lernfach) ->
				{
					lernfach.remove(kapitel);
				});

		getInstance().benachrichtigeUis();
	}

	public static void deleteFrage(String fragenId)
	{
		final var frage = FragenVerwaltung.get(Integer.parseInt(fragenId));
		final var antworten = frage.getAntworten();
		FragenVerwaltung.remove(frage);

		for (final Antwort antwort : antworten)
			deleteAntwort(antwort.getId() + "");

		KapitelVerwaltung.getAll()
				.forEach((kapitelId, kapitel) ->
				{
					kapitel.remove(frage);
				});

		getInstance().benachrichtigeUis();
	}

	public static void deleteAntwort(String antwortId)
	{
		final var antwort = AntwortVerwaltung.getInstance()
				.get(Integer.parseInt(antwortId));
		AntwortVerwaltung.getInstance()
				.remove(antwort);

		FragenVerwaltung.getAll()
				.forEach((fragenId, frage) ->
				{
					frage.remove(antwort);
				});

		getInstance().benachrichtigeUis();
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
		return AntwortenRenderer.getAntwortenForView(AntwortVerwaltung.getInstance()
				.getAll()
				.values());
	}

	public static void setCurrentUser(PrettyHashMap selectedItem)
	{
		currentUser = StudentenVerwaltung.get(Integer.parseInt(selectedItem.visible.get(Entity.idText)));

	}

	public static Student getCurrentUser()
	{
		return currentUser;
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
