package Controller;

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
		datenVerbindung.getAllFromTable("Antwort");
	}
}
