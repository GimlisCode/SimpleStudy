package Controller;

import java.util.HashMap;

import Models.Antwort;
import Models.Dozent;
import Models.Entity;
import Models.Frage;
import Models.Hochschule;
import Models.Lernfach;
import Models.Richtigkeit;
import Models.Statistik;
import Models.Student;
import Modifier.StatistikFabrik;

public class DbController
{
	private final DatenVerbindung datenVerbindung;

	public DbController(DatenVerbindung datenVerbindung)
	{
		this.datenVerbindung = datenVerbindung;
		initiliazeData();
	}

	public void initiliazeData()
	{

		// final var alleDozenten = datenVerbindung.getAllFromTable("Dozent");
		// for (final HashMap<String, String> dozent : alleDozenten)
		// MainController.createDozent(dozent);
		initializeAntwort();
		initializeStudent();
		initializeStatistik();
		initializeHochschule();
		initializeDozent();
	}

	private void initializeDozent()
	{
		final String mainTable = Dozent.class.getSimpleName();
		final String joinTable = Lernfach.class.getSimpleName();
		final String mainJoinColum = mainTable + Entity.idText;
		final String select = datenVerbindung.createSelectString(new String[]
			{ mainTable + "." + Entity.idText, mainTable + "." + Dozent.nameText,
					joinTable + "." + Entity.idText + " " + Dozent.kurseText },
				mainTable)
				.join(new String[]
				{ joinTable },
						JoinType.Left)
				.on(mainTable,
						Entity.idText,
						"=",
						joinTable,
						mainJoinColum)
				.build();

		final var alleDozenten = datenVerbindung.getResultFromQuerry(select);
		for (final HashMap<String, String> dozent : alleDozenten)
			MainController.createDozent(dozent);

	}

	private void initializeHochschule()
	{
		final String mainTable = Hochschule.class.getSimpleName();
		final String joinTable = Dozent.class.getSimpleName();
		final String mainJoinColum = mainTable + Entity.idText;
		final String select = datenVerbindung.createSelectString(new String[]
			{ mainTable + "." + Entity.idText, mainTable + "." + Hochschule.nameText,
					joinTable + "." + Entity.idText + " " + Hochschule.dozentenText },
				mainTable)
				.join(new String[]
				{ joinTable },
						JoinType.Left)
				.on(mainTable,
						Entity.idText,
						"=",
						joinTable,
						mainJoinColum)
				.build();

		final var alleHochschulen = datenVerbindung.getResultFromQuerry(select);
		for (final HashMap<String, String> hochschulen : alleHochschulen)
			MainController.createHochschule(hochschulen);

	}

	private void initializeAntwort()
	{
		final var alleAntworten = datenVerbindung.getAllFromTable(Antwort.class.getSimpleName());
		for (final HashMap<String, String> antwort : alleAntworten)
			MainController.createAntwort(antwort);

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
				.build();

		final var alleStudenten = datenVerbindung.getResultFromQuerry(studentenSelect);
		for (final HashMap<String, String> student : alleStudenten)
			MainController.createStudent(student);
	}

	private void initializeStatistik()
	{
		final var alleStatistiken = datenVerbindung.getAllFromTable(Statistik.class.getSimpleName());
		final var alleStatistikIds = datenVerbindung.getResultFromQuerry(datenVerbindung.createSelectString(new String[]
			{ Entity.idText },
				Statistik.class.getSimpleName(),
				"DISTINCT")
				.build());

		var currentNewStatistik = StatistikFabrik.getStatistikAttribute();
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
			MainController.createStatistik(currentNewStatistik);
			currentNewStatistik = StatistikFabrik.getStatistikAttribute();
		}
	}
}
