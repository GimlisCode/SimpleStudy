package DB;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import Controller.DatenVerbindung;

public class SQLite implements DatenVerbindung
{
	private static final SQLite dbPlugin = new SQLite();
	private Connection connection;
	private final String DB_PATH = "simpleStudy.db";
	private final static String selectAllStatemant = "Select * From ";

	private SQLite()
	{
		super();
		initDBConnection();
		proofIfDbExists();

	}

	private void proofIfDbExists()
	{
		try (ResultSet rs = executeQuery("SELECT Count(*) FROM sqlite_schema WHERE type='table' ORDER BY name"))
		{
			final int numberOfTables = rs.getInt(1);
			if (numberOfTables <= 0)
				createDB();
		}
		catch (final SQLException e)
		{
			e.printStackTrace();
		}

	}

	public static SQLite getInstance()
	{
		return dbPlugin;
	}

	private void initDBConnection()
	{
		try
		{
			System.out.println(new File(".").getAbsolutePath());

			if (connection != null)
				return;
			System.out.println("Creating Connection to Database...");
			connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
			if (!connection.isClosed())
				System.out.println("...Connection established");
		}
		catch (final SQLException e)
		{
			throw new RuntimeException(e);
		}

		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			public void run()
			{
				try
				{
					if (!connection.isClosed() && connection != null)
					{
						connection.close();
						if (connection.isClosed())
							System.out.println("Connection to Database closed");
					}
				}
				catch (final SQLException e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	private ResultSet executeQuery(String sqlString)
	{
		Statement stmt = null;
		try
		{
			stmt = connection.createStatement();
		}
		catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			if (stmt.execute(sqlString))
				return stmt.getResultSet();
			else
			{
				System.out.println("Rows updated: " + stmt.getUpdateCount());
				return null;
			}
		}
		catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<HashMap<String, String>> getAllFromTable(String tableName)
	{
		final ArrayList<HashMap<String, String>> resultsMapped = new ArrayList();
		try
		{
			final ResultSet selectResults = executeQuery(selectAllStatemant + tableName);
			final int anzColumn = selectResults.getMetaData().getColumnCount() + 1;

			while (selectResults.next())
			{
				final HashMap<String, String> attributWertePaarung = new HashMap<String, String>();
				for (int i = 1; i < anzColumn; i++)
					attributWertePaarung.put(selectResults.getMetaData().getColumnName(i), selectResults.getString(i));

				resultsMapped.add(attributWertePaarung);
			}
		}
		catch (final SQLException e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultsMapped;
	}

	public void createDB()
	{
		final String createTable[] = new String[]
		{ "CREATE TABLE IF NOT EXISTS Antwort (id INT NOT NULL, fragenID INT NOT NULL, text CHAR DEFAULT '', correct BOOLEAN NOT NULL DEFAULT 'false', PRIMARY KEY(ID), FOREIGN KEY(fragenID) REFERENCES Frage(ID));",
				"CREATE TABLE IF NOT EXISTS Dozent (ID INT NOT NULL, name CHAR DEFAULT '', hochschulID INT NOT NULL, PRIMARY KEY (ID), FOREIGN KEY(hochschulID) REFERENCES Hochschule(ID));",
				"CREATE TABLE IF NOT EXISTS Frage (ID INT NOT NULL, kapitelID INT DEFAULT 0, text CHAR NOT NULL DEFAULT '', typ INT NOT NULL DEFAULT '0',PRIMARY KEY (ID), FOREIGN KEY (kapitelID) REFERENCES Kapitel(ID));",
				"CREATE TABLE IF NOT EXISTS Hochschule (ID INT NOT NULL, name CHAR NOT NULL DEFAULT '',	PRIMARY KEY (ID));",
				"CREATE TABLE IF NOT EXISTS Kapitel (ID INT NOT NULL, lernfachID INT NOT NULL DEFAULT 0,	name CHAR NOT NULL DEFAULT '', nr INT DEFAULT '0',PRIMARY KEY (ID), FOREIGN KEY (lernfachID) REFERENCES Lernfach(ID));",
				"CREATE TABLE IF NOT EXISTS Lernfach (ID INT NOT NULL ,	name CHAR DEFAULT '', semester INT DEFAULT '0', credits INT DEFAULT '0',dozentenID INT DEFAULT 0, PRIMARY KEY (ID), FOREIGN KEY (dozentenID) REFERENCES Dozent(ID));",
				"CREATE TABLE IF NOT EXISTS Student (ID INT NOT NULL , name CHAR DEFAULT '', PRIMARY KEY (ID));",
				"CREATE TABLE IF NOT EXISTS Richtigkeit (ID INT NOT NULL, StudentenID INT DEFAULT '0', FragenID INT DEFAULT '0', richtig INT DEFAULT '0', falsch INT DEFAULT '0', fragenstufe INT DEFAULT '1', PRIMARY KEY (ID, StudentenID, FragenID), FOREIGN KEY (StudentenID) REFERENCES Student(ID),FOREIGN KEY (FragenID) REFERENCES Frage(ID));" };

		for (final String string : createTable)
			executeQuery(string);

		final String insertFirstData[] = new String[]
		{ "INSERT INTO Antwort VALUES(1,1,'Bonn',false);", "INSERT INTO Antwort VALUES(2,1,'Köln',false);",
				"INSERT INTO Antwort VALUES(3,1,'Berlin',true);", "INSERT INTO Antwort VALUES(4,1,'Koblenz',false);",
				"INSERT INTO Frage VALUES(1,1,'Wie lautet die Hauptstadt der Bundesrepublik Deutschland?',1);",
				"INSERT INTO Hochschule VALUES(1, 'DHBW Karlsruhe');", "INSERT INTO Kapitel VALUES(1,1,'Deutschland', 1);",
				"INSERT INTO Dozent VALUES(1,'Freudenmann, Johannes',1);", "INSERT INTO Lernfach VALUES(1,'Erdkunde',1,8,1);",
				"INSERT INTO Student VALUES(1,'Maul, Johannes');", "INSERT INTO Richtigkeit VALUES(1,1,1,0,1,2);" };

		for (final String string : insertFirstData)
			executeQuery(string);
	}
}
