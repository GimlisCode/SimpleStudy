package Controller;

import java.util.HashMap;

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

		final var alleDozenten = datenVerbindung.getAllFromTable("Dozent");
		for (final HashMap<String, String> dozent : alleDozenten)
			MainController.createDozent(dozent);

	}
}
