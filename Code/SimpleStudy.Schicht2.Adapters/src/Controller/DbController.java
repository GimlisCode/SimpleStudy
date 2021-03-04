package Controller;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Antwort;
import Models.Entity;
import Models.Hochschule;
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
		initializeHochschule();
		initializeStatistik();
	}

	private void initializeHochschule()
	{
		final var alleHochschulen = datenVerbindung.getAllFromTable(Hochschule.class.getSimpleName());
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
				Student.class.getSimpleName())
				.join(new String[]
				{ Statistik.class.getSimpleName() })
				.where(mainTable,
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
		final var formatierteStatistiken = new ArrayList<HashMap<String, String>>();

		final var alleStatistikIds = datenVerbindung.getResultFromQuerry(datenVerbindung.createSelectString(new String[]
			{ Entity.idText },
				Statistik.class.getSimpleName(),
				"DISTINCT")
				.build());
		final var currentNewStatistik = StatistikFabrik.getStatistikAttribute();
		for (final HashMap<String, String> statistikId : alleStatistikIds)
		{
			final String currentId = statistikId.get(Entity.idText);
			currentNewStatistik.put(Entity.idText,
					currentId);

			final var allRichtigkeitenForStatistik = new ArrayList<HashMap<String, String>>();
			for (final HashMap<String, String> statistik : alleStatistiken)
				if (statistik.get(Entity.idText)
						.equals(currentId))

					currentNewStatistik.put(Statistik.statistikText,
							richtigkeitText);

		}
		MainController.createStatistik(currentNewStatistik);

	}
}
