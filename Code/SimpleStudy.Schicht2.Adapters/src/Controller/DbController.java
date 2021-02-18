package Controller;

import java.util.HashMap;

import Models.Statistik;
import Models.Student;

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
		final var alleAntworten = datenVerbindung.getAllFromTable("Antwort");
		for (final HashMap<String, String> antwort : alleAntworten)
			MainController.createAntwort(antwort);

		// final var alleDozenten = datenVerbindung.getAllFromTable("Dozent");
		// for (final HashMap<String, String> dozent : alleDozenten)
		// MainController.createDozent(dozent);

		initializeStudent();

	}

	private void initializeStudent()
	{
		final String mainTable = Student.class.getSimpleName();
		final String joinTable = Statistik.class.getSimpleName();
		final String mainJoinColum = mainTable + Student.idText;
		final String studentenSelect = datenVerbindung.createSelectString(new String[]
			{ mainTable + "." + Student.idText, Student.nameText, Statistik.class.getSimpleName() + Statistik.idText },
				Student.class.getSimpleName())
				.join(new String[]
				{ Statistik.class.getSimpleName() })
				.where(mainTable,
						Student.idText,
						"=",
						joinTable,
						mainJoinColum)
				.build();
		datenVerbindung.getResultFromQuerry(studentenSelect);

		final var alleStudenten = datenVerbindung.getResultFromQuerry(studentenSelect);
		for (final HashMap<String, String> student : alleStudenten)
			MainController.createAntwort(student);
	}
}
