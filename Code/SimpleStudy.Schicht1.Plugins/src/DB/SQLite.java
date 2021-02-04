package DB;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Controller.DatenVerbindung;

public class SQLite implements DatenVerbindung
{
	private static final SQLite dbPlugin = new SQLite();
	private Connection connection;
	private final String DB_PATH = "simpleStudy.db";
	private final static String selectAllStatemant = "Select * From %s";

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
	public ArrayList<String> getAllFromTable(String tableName)
	{
		final ArrayList<String> resultsAsString = new ArrayList<String>();
		try
		{
			final ResultSet selectResults = executeQuery(selectAllStatemant + tableName);
			final int anzColumn = selectResults.getMetaData().getColumnCount();
			while (selectResults.next())
			{
				String resultRow = "";
				for (int i = 0; i < anzColumn; i++)
					resultRow += selectResults.getString(i) + ",";
				resultsAsString.add(resultRow);
			}
		}
		catch (final SQLException e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultsAsString;
	}

	public void createDB()
	{
		final String createTable[] = new String[]
		{ "CREATE TABLE IF NOT EXISTS Antwort (ID INT NOT NULL, fragenID INT NOT NULL, text CHAR DEFAULT '', correct BOOLEAN NOT NULL DEFAULT 'false', PRIMARY KEY(ID), FOREIGN KEY(fragenID) REFERENCES Frage(ID));",
				"CREATE TABLE IF NOT EXISTS Dozent (ID INT NOT NULL, name CHAR DEFAULT '', hochschulID INT NOT NULL, PRIMARY KEY (ID), FOREIGN KEY(hochschulID) REFERENCES Hochschule(ID));",
				"CREATE TABLE IF NOT EXISTS Frage (ID INT NOT NULL, kapitelID INT DEFAULT 0, text CHAR NOT NULL DEFAULT '', typ INT NOT NULL DEFAULT '0',PRIMARY KEY (ID), FOREIGN KEY (kapitelID) REFERENCES Kapitel(ID));",
				"CREATE TABLE IF NOT EXISTS Hochschule (ID INT NOT NULL, name CHAR NOT NULL DEFAULT '',	PRIMARY KEY (ID));",
				"CREATE TABLE IF NOT EXISTS Kapitel (ID INT NOT NULL, lernfachID INT NOT NULL DEFAULT 0,	name CHAR NOT NULL DEFAULT '', nr INT DEFAULT '0',PRIMARY KEY (ID), FOREIGN KEY (lernfachID) REFERENCES Lernfach(ID));",
				"CREATE TABLE IF NOT EXISTS Lernfach (ID INT NOT NULL ,	name CHAR DEFAULT '', semester INT DEFAULT '0', credits INT DEFAULT '0', PRIMARY KEY (ID));",
				"CREATE TABLE IF NOT EXISTS Student (ID INT NOT NULL , name CHAR DEFAULT '',S , PRIMARY KEY (ID));",
				"CREATE TABLE IF NOT EXISTS Richtigkeit (ID INT NOT NULL, StudentenID INT DEFAULT '0', FragenID INT DEFAULT '0', richtig INT DEFAULT '0', falsch INT DEFAULT '0', fragenstufe INT DEFAULT '1', PRIMARY KEY (ID, StudentenID, FragenID), FOREIGN KEY (StudentenID) REFERENCES Student(ID),FOREIGN KEY (FragenID) REFERENCES Frage(ID));" };

		for (final String string : createTable)
			executeQuery(string);

		final String insertFirstData[] = new String[]
		{ "INSERT INTO Antwort VALUES(0,1,'test',false);" };

		for (String string : insertFirstData)
		{
			executeQuery(string);
		}
	}
}
