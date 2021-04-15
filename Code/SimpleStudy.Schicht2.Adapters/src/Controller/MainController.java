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
import OpcChecker.IdChecker;
import OpcChecker.IsBlankId;
import OpcChecker.IsEmptyId;
import OpcChecker.IsNullId;
import OpcChecker.IsNumericId;
import Renderer.AntwortenRenderer;
import Renderer.DozentenRenderer;
import Renderer.FragenRenderer;
import Renderer.HochschulRenderer;
import Renderer.KapitelRenderer;
import Renderer.LernfachRenderer;
import Renderer.PrettyHashMap;
import Renderer.StudentenRenderer;

public final class MainController implements UiBeobachtete
{

	private static MainController mainControllerSingleton = new MainController();
	private Student currentUser = null;
	private final ArrayList<UiBeobachter> registrierteUiBeobachter = new ArrayList<UiBeobachter>();
	private IdChecker idChecker = null;

	private MainController()
	{
		super();
		idChecker = new IdChecker();
		idChecker.register(new IsNullId());
		idChecker.register(new IsEmptyId());
		idChecker.register(new IsBlankId());
		idChecker.register(new IsNumericId());
	}

	public static MainController getInstance()
	{
		return mainControllerSingleton;
	}

	public Abfrage createAbfrage(ArrayList<HashMap<String, String>> fragenWerte)
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

	public void abfrageAuswerten(Abfrage abfrage)
	{
		AbfrageVerwaltung.getInstance()
				.abfrageAuswerten(abfrage);
	}

	public int getNewIdFor(String className)
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

	public void createAntwort(HashMap<String, String> antwortWerte)
	{
		final var antwortAttribute = AntwortFabrik.getAntwortAttribute();
		for (final Entry<String, String> antwortAttribut : antwortAttribute.entrySet())
			antwortAttribut.setValue(antwortWerte.get(antwortAttribut.getKey()));

		final String id = antwortAttribute.get(Entity.idText);
		if (!idChecker.isValid(id))
			antwortAttribute.replace(Entity.idText,
					getNewIdFor(Antwort.class.getSimpleName()) + "");

		AntwortFabrik.create(antwortAttribute);
		getInstance().benachrichtigeUis();

	}

	public void updateAntwort(HashMap<String, String> antwortWerte)
	{
		createAntwort(antwortWerte);
		getInstance().benachrichtigeUis();
	}

	public void createDozent(HashMap<String, String> dozentWerte)
	{
		final var dozentAttribute = DozentenFabrik.getDozentAttribute();
		for (final Entry<String, String> dozentAttribut : dozentAttribute.entrySet())
			dozentAttribut.setValue(dozentWerte.get(dozentAttribut.getKey()));

		final String id = dozentAttribute.get(Entity.idText);
		if (!idChecker.isValid(id))
			dozentAttribute.replace(Entity.idText,
					getNewIdFor(Dozent.class.getSimpleName()) + "");

		DozentenFabrik.create(dozentAttribute);
		getInstance().benachrichtigeUis();
	}

	public void updateDozent(HashMap<String, String> dozentWerte)
	{
		createDozent(dozentWerte);
		DozentenFabrik.resolveReferences();
		getInstance().benachrichtigeUis();
	}

	public void createStudent(HashMap<String, String> studentWerte)
	{
		final var studentAttribute = StudentenFabrik.getStudentenAttribute();
		for (final Entry<String, String> studentAttribut : studentAttribute.entrySet())
			studentAttribut.setValue(studentWerte.get(studentAttribut.getKey()));

		final String id = studentAttribute.get(Entity.idText);
		if (!idChecker.isValid(id))
			studentAttribute.replace(Entity.idText,
					getNewIdFor(Student.class.getSimpleName()) + "");

		final String statistikId = studentAttribute.get(Student.statistikText);
		if (!idChecker.isValid(statistikId))
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

	public void updateStudent(HashMap<String, String> studentWerte)
	{
		createStudent(studentWerte);
		StudentenFabrik.resolveReferences();
		getInstance().benachrichtigeUis();
	}

	public void createHochschule(HashMap<String, String> hochschulen)
	{
		final var hochschulAttribute = HochschulFabrik.getHochschulAttribute();
		for (final Entry<String, String> hochschulAttribut : hochschulAttribute.entrySet())
			hochschulAttribut.setValue(hochschulen.get(hochschulAttribut.getKey())); // TODO: Stilbruch im Namen, durch
																						// autogenerate
		final String id = hochschulAttribute.get(Entity.idText);
		if (!idChecker.isValid(id))
			hochschulAttribute.replace(Entity.idText,
					getNewIdFor(Hochschule.class.getSimpleName()) + "");

		HochschulFabrik.create(hochschulAttribute);
		getInstance().benachrichtigeUis();
	}

	public void updateHochschule(HashMap<String, String> hochschuleWerte)
	{
		createHochschule(hochschuleWerte);
		HochschulFabrik.resolveReferences();
		getInstance().benachrichtigeUis();
	}

	public void createStatistik(HashMap<String, String> currentNewStatistik)
	{
		final var statistikAttribute = StatistikFabrik.getStatistikAttribute();
		for (final Entry<String, String> statistikAttribut : statistikAttribute.entrySet())
			statistikAttribut.setValue(currentNewStatistik.get(statistikAttribut.getKey()));

		final String id = statistikAttribute.get(Entity.idText);
		if (!idChecker.isValid(id))
			statistikAttribute.replace(Entity.idText,
					getNewIdFor(Statistik.class.getSimpleName()) + "");

		StatistikFabrik.create(statistikAttribute);
		getInstance().benachrichtigeUis();

	}

	public void updateStatistik(HashMap<String, String> statistikWerte)
	{
		createStatistik(statistikWerte);
		getInstance().benachrichtigeUis();
	}

	public void createLernfach(HashMap<String, String> lernfach)
	{
		final var lernfachAttribute = LernfachFabrik.getLernfachAttribute();
		for (final Entry<String, String> lernfachAttribut : lernfachAttribute.entrySet())
			lernfachAttribut.setValue(lernfach.get(lernfachAttribut.getKey()));

		final String id = lernfachAttribute.get(Entity.idText);
		if (!idChecker.isValid(id))
			lernfachAttribute.replace(Entity.idText,
					getNewIdFor(Lernfach.class.getSimpleName()) + "");

		LernfachFabrik.create(lernfachAttribute);
		getInstance().benachrichtigeUis();
	}

	public void updateLernfach(HashMap<String, String> lernfachWerte)
	{
		createLernfach(lernfachWerte);
		LernfachFabrik.resolveReferences();
		getInstance().benachrichtigeUis();
	}

	public void createKapitel(HashMap<String, String> kapitel)
	{
		final var kapitelAttribute = KapitelFabrik.getKaptielAttribute();
		for (final Entry<String, String> kapitelAttribut : kapitelAttribute.entrySet())
			kapitelAttribut.setValue(kapitel.get(kapitelAttribut.getKey()));

		final String id = kapitelAttribute.get(Entity.idText);
		if (!idChecker.isValid(id))
			kapitelAttribute.replace(Entity.idText,
					getNewIdFor(Kapitel.class.getSimpleName()) + "");

		KapitelFabrik.create(kapitelAttribute);
		getInstance().benachrichtigeUis();
	}

	public void updateKapitel(HashMap<String, String> kapitelWerte)
	{
		createKapitel(kapitelWerte);
		KapitelFabrik.resolveReferences();
		getInstance().benachrichtigeUis();
	}

	public void createFrage(HashMap<String, String> frage)
	{
		final var fragenAttribute = FragenFabrik.getFragenAttribute();
		for (final Entry<String, String> frageAttribut : fragenAttribute.entrySet())
			frageAttribut.setValue(frage.get(frageAttribut.getKey()));

		final String id = fragenAttribute.get(Entity.idText);
		if (!idChecker.isValid(id))
			fragenAttribute.replace(Entity.idText,
					getNewIdFor(Frage.class.getSimpleName()) + "");

		FragenFabrik.create(fragenAttribute);
		getInstance().benachrichtigeUis();
	}

	public void updateFrage(HashMap<String, String> frageWerte)
	{
		createFrage(frageWerte);
		FragenFabrik.resolveReferences();
		getInstance().benachrichtigeUis();
	}

	public void deleteStudent(String studentenId)
	{
		final var student = StudentenVerwaltung.get(Integer.parseInt(studentenId));
		if (student.getStatistik() != null)
			deleteStatistik(student.getStatistik()
					.getId() + "");

		StudentenVerwaltung.remove(Integer.parseInt(studentenId));
		getInstance().benachrichtigeUis();
	}

	public void deleteStatistik(String statistikId)
	{
		StatistikVerwaltung.remove(Integer.parseInt(statistikId));
		getInstance().benachrichtigeUis();
	}

	public void deleteHochschule(String hocschulId)
	{
		final var hochschule = HochschulVerwaltung.get(Integer.parseInt(hocschulId));
		final var dozenten = hochschule.getDozenten();
		HochschulVerwaltung.remove(Integer.parseInt(hocschulId));

		for (final Dozent dozent : dozenten)
			deleteDozent(dozent.getId() + "");

		getInstance().benachrichtigeUis();

	}

	public void deleteDozent(String dozentId)
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

	public void deleteLernfach(String lernfachId)
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

	public void deleteKapitel(String kapitelId)
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

	public void deleteFrage(String fragenId)
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

	public void deleteAntwort(String antwortId)
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

	public ArrayList<PrettyHashMap> getStudenten()
	{
		return StudentenRenderer.getStudentForView(StudentenVerwaltung.getAll()
				.values());
	}

	public ArrayList<PrettyHashMap> getHochschulen()
	{
		return HochschulRenderer.getHochschuleForView(HochschulVerwaltung.getAll()
				.values());
	}

	public ArrayList<PrettyHashMap> getDozenten()
	{
		return DozentenRenderer.getDozentenForView(DozentenVerwaltung.getAll()
				.values());
	}

	public ArrayList<PrettyHashMap> getLernfaecher()
	{
		return LernfachRenderer.getLernfaecherForView(LernfachVerwaltung.getAll()
				.values());
	}

	public ArrayList<PrettyHashMap> getKapitel()
	{
		return KapitelRenderer.getKapitelForView(KapitelVerwaltung.getAll()
				.values());
	}

	public ArrayList<PrettyHashMap> getFragen()
	{
		return FragenRenderer.getFragenForView(FragenVerwaltung.getAll()
				.values());
	}

	public ArrayList<PrettyHashMap> getAntworten()
	{
		return AntwortenRenderer.getAntwortenForView(AntwortVerwaltung.getInstance()
				.getAll()
				.values());
	}

	public void setCurrentUser(PrettyHashMap selectedItem)
	{
		currentUser = StudentenVerwaltung.get(Integer.parseInt(selectedItem.visible.get(Entity.idText)));

	}

	public Student getCurrentUser()
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
