package Controller;

import java.util.HashMap;

import Models.Antwort;
import Models.Dozent;
import Models.Entity;
import Models.Frage;
import Models.Hochschule;
import Models.Kapitel;
import Models.Lernfach;
import Models.Richtigkeit;
import Models.Statistik;
import Models.Student;
import Modifier.DozentenFabrik;
import Modifier.FragenFabrik;
import Modifier.HochschulFabrik;
import Modifier.KapitelFabrik;
import Modifier.LernfachFabrik;
import Modifier.StatistikFabrik;
import Modifier.StudentenFabrik;

public class DbController
{
	private final DatenVerbindung datenVerbindung;

	public DbController(DatenVerbindung datenVerbindung)
	{
		this.datenVerbindung = datenVerbindung;
	}

	public void resolveAll()
	{
		StudentenFabrik.resolveReferences();
		HochschulFabrik.getInstance()
				.resolveReferences();
		DozentenFabrik.getInstance()
				.resolveReferences();
		LernfachFabrik.getInstance()
				.resolveReferences();
		KapitelFabrik.getInstance()
				.resolveReferences();
		FragenFabrik.getInstance()
				.resolveReferences();
	}

	public void initilizeData()
	{

		initializeAntwort();
		initializeStudent();
		initializeStatistik();
		initializeHochschule();
		initializeDozent();
		initializeLernfach();
		initializeKapitel();
		initializeFrage();
	}

	private void initializeFrage()
	{
		final String mainTable = Frage.class.getSimpleName();
		final String joinTable = Antwort.class.getSimpleName();
		final String mainJoinColum = mainTable + Entity.idText;
		final String select = datenVerbindung.createSelectString(new String[]
			{ mainTable + "." + Entity.idText, mainTable + "." + Frage.textText, mainTable + "." + Frage.typText,
					"group_concat(" + joinTable + "." + Entity.idText + ", ';') " + Frage.antwortenText },
				mainTable)
				.join(new String[]
				{ joinTable },
						JoinType.Left)
				.on(mainTable,
						Entity.idText,
						"=",
						joinTable,
						mainJoinColum)
				.groupBy(new String[]
				{ mainTable + "." + Entity.idText })
				.build();

		final var alleFragen = datenVerbindung.getResultFromQuerry(select);
		for (final HashMap<String, String> frage : alleFragen)
			MainController.getInstance()
					.createFrage(frage);

	}

	private void initializeKapitel()
	{
		final String mainTable = Kapitel.class.getSimpleName();
		final String joinTable = Frage.class.getSimpleName();
		final String mainJoinColum = mainTable + Entity.idText;
		final String select = datenVerbindung.createSelectString(new String[]
			{ mainTable + "." + Entity.idText, mainTable + "." + Kapitel.nameText, mainTable + "." + Kapitel.nrText,
					"group_concat(" + joinTable + "." + Entity.idText + ", ';') " + Kapitel.fragenText },
				mainTable)
				.join(new String[]
				{ joinTable },
						JoinType.Left)
				.on(mainTable,
						Entity.idText,
						"=",
						joinTable,
						mainJoinColum)
				.groupBy(new String[]
				{ mainTable + "." + Entity.idText })
				.build();

		final var alleKapitel = datenVerbindung.getResultFromQuerry(select);
		for (final HashMap<String, String> kapitel : alleKapitel)
			MainController.getInstance()
					.createKapitel(kapitel);

	}

	private void initializeLernfach()
	{
		final String mainTable = Lernfach.class.getSimpleName();
		final String joinTable = Kapitel.class.getSimpleName();
		final String mainJoinColum = mainTable + Entity.idText;
		final String select = datenVerbindung.createSelectString(new String[]
			{ mainTable + "." + Entity.idText, mainTable + "." + Lernfach.nameText, mainTable + "." + Lernfach.semesterText,
					mainTable + "." + Entity.idText, mainTable + "." + Lernfach.creditsText,
					"group_concat(" + joinTable + "." + Entity.idText + ", ';') " + Lernfach.kapitelText },
				mainTable)
				.join(new String[]
				{ joinTable },
						JoinType.Left)
				.on(mainTable,
						Entity.idText,
						"=",
						joinTable,
						mainJoinColum)
				.groupBy(new String[]
				{ mainTable + "." + Entity.idText })
				.build();

		final var alleLernfaecher = datenVerbindung.getResultFromQuerry(select);
		for (final HashMap<String, String> lernfach : alleLernfaecher)
			MainController.getInstance()
					.createLernfach(lernfach);

	}

	private void initializeDozent()
	{
		final String mainTable = Dozent.class.getSimpleName();
		final String joinTable = Lernfach.class.getSimpleName();
		final String mainJoinColum = mainTable + Entity.idText;
		final String select = datenVerbindung.createSelectString(new String[]
			{ mainTable + "." + Entity.idText, mainTable + "." + Dozent.nameText,
					"group_concat(" + joinTable + "." + Entity.idText + ", ';') " + Dozent.kurseText },
				mainTable)
				.join(new String[]
				{ joinTable },
						JoinType.Left)
				.on(mainTable,
						Entity.idText,
						"=",
						joinTable,
						mainJoinColum)
				.groupBy(new String[]
				{ mainTable + "." + Entity.idText })
				.build();

		final var alleDozenten = datenVerbindung.getResultFromQuerry(select);
		for (final HashMap<String, String> dozent : alleDozenten)
			MainController.getInstance()
					.createDozent(dozent);

	}

	private void initializeHochschule()
	{
		final String mainTable = Hochschule.class.getSimpleName();
		final String joinTable = Dozent.class.getSimpleName();
		final String mainJoinColum = mainTable + Entity.idText;
		final String select = datenVerbindung.createSelectString(new String[]
			{ mainTable + "." + Entity.idText, mainTable + "." + Hochschule.nameText,
					"group_concat(" + joinTable + "." + Entity.idText + ", ';')" + Hochschule.dozentenText },
				mainTable)
				.join(new String[]
				{ joinTable },
						JoinType.Left)
				.on(mainTable,
						Entity.idText,
						"=",
						joinTable,
						mainJoinColum)
				.groupBy(new String[]
				{ mainTable + "." + Entity.idText })
				.build();

		final var alleHochschulen = datenVerbindung.getResultFromQuerry(select);
		for (final HashMap<String, String> hochschulen : alleHochschulen)
			MainController.getInstance()
					.createHochschule(hochschulen);

	}

	public void initializeAntwort()
	{
		final var alleAntworten = datenVerbindung.getAllFromTable(Antwort.class.getSimpleName());
		for (final HashMap<String, String> antwort : alleAntworten)
			MainController.getInstance()
					.createAntwort(antwort);

	}

	private void initializeStudent()
	{
		final String mainTable = Student.class.getSimpleName();
		final String joinTable = Statistik.class.getSimpleName();
		final String mainJoinColum = mainTable + Student.idText;
		final String studentenSelect = datenVerbindung.createSelectString(new String[]
			{ mainTable + "." + Student.idText, Student.nameText,
					Statistik.class.getSimpleName() + "." + Statistik.idText + " " + Student.statistikText },
				Student.class.getSimpleName(),
				"DISTINCT")
				.join(new String[]
				{ Statistik.class.getSimpleName() },
						JoinType.Left)
				.on(mainTable,
						Student.idText,
						"=",
						joinTable,
						mainJoinColum)
				.groupBy(new String[]
				{ mainTable + "." + Entity.idText })
				.build();

		final var alleStudenten = datenVerbindung.getResultFromQuerry(studentenSelect);
		for (final HashMap<String, String> student : alleStudenten)
			MainController.getInstance()
					.createStudent(student);
	}

	private void initializeStatistik()
	{
		final var alleStatistiken = datenVerbindung.getAllFromTable(Statistik.class.getSimpleName());
		final var alleStatistikIds = datenVerbindung.getResultFromQuerry(datenVerbindung.createSelectString(new String[]
			{ Entity.idText },
				Statistik.class.getSimpleName(),
				"DISTINCT")
				.build());

		var currentNewStatistik = StatistikFabrik.getInstance()
				.getStatistikAttribute();
		for (final HashMap<String, String> statistikId : alleStatistikIds)
		{
			final String currentId = statistikId.get(Entity.idText);
			currentNewStatistik.put(Entity.idText,
					currentId);

			String richtigkeitText = "";
			for (final HashMap<String, String> statistik : alleStatistiken)
				if (statistik.get(Entity.idText)
						.equals(currentId))
				{
					richtigkeitText += statistik.get(Frage.class.getSimpleName() + Entity.idText) + ",";
					richtigkeitText += statistik.get(Richtigkeit.richtigText) + ",";
					richtigkeitText += statistik.get(Richtigkeit.falschText) + ",";
					richtigkeitText += statistik.get(Richtigkeit.fragenstufeText) + ";";
				}

			currentNewStatistik.put(Statistik.statistikText,
					richtigkeitText);
			MainController.getInstance()
					.createStatistik(currentNewStatistik);
			currentNewStatistik = StatistikFabrik.getInstance()
					.getStatistikAttribute();
		}
	}
}
