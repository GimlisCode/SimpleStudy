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
		try (ResultSet rs = connection.getMetaData().getCatalogs())
		{
			int numberOfDatabases = 0;
			while (rs.next())
				numberOfDatabases++;

			if (numberOfDatabases <= 1)
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
		final String sqlarray[] = new String[]
		{ "CREATE TABLE IF NOT EXISTS Antwort (" + "	ID INT NOT NULL ," + "	fragenID INT NOT NULL," + "	text CHAR DEFAULT '',"
				+ "	correct BOOLEAN NOT NULL DEFAULT 'false'," + "	PRIMARY KEY(ID),"
				+ "	FOREIGN KEY(fragenID) REFERENCES Frage(ID)" + ")",
				"CREATE TABLE IF NOT EXISTS Dozent (" + "	ID INT NOT NULL ," + "	name CHAR DEFAULT '',"
						+ "	hochschulID INT NOT NULL," + "	PRIMARY KEY (ID)"
						+ "	FOREIGN KEY(hochschulID) REFERENCES Hochschule(ID))" };

		final String sql = "CREATE TABLE IF NOT EXISTS Antwort (" + "	ID INT NOT NULL ," + "	fragenID INT NOT NULL,"
				+ "	text CHAR DEFAULT ''," + "	correct BOOLEAN NOT NULL DEFAULT 'false'," + "	PRIMARY KEY(ID),"
				+ "	FOREIGN KEY(fragenID) REFERENCES Frage(ID)" + ");" + "CREATE TABLE IF NOT EXISTS Dozent ("
				+ "	ID INT NOT NULL ," + "	name CHAR DEFAULT ''," + "	hochschulID INT NOT NULL," + "	PRIMARY KEY (ID)"
				+ "	FOREIGN KEY(hochschulID) REFERENCES Hochschule(ID);" + "CREATE TABLE IF NOT EXISTS Frage ("
				+ "	ID INT NOT NULL ," + "	kapitelID INT DEFAULT 0 " + "	text CHAR NOT NULL DEFAULT NULL,"
				+ "	typ INT NOT NULL DEFAULT '0'," + "	PRIMARY KEY (ID)" + "	FOREIGN KEY (kapitelID);"
				+ "CREATE TABLE IF NOT EXISTS Hochschule" + "	ID INT NOT NULL ," + "	name CHAR NOT NULL DEFAULT '',"
				+ "	PRIMARY KEY (ID));" + "CREATE TABLE IF NOT EXISTS Kapitel (" + "	ID INT NOT NULL ,"
				+ "	lernfachID INT NOT NULL DEFAULT 0," + "	name CHAR NOT NULL DEFAULT ''," + "	nr INT DEFAULT '0',"
				+ "	PRIMARY KEY (ID)" + "	FOREIGN KEY (lernfachID) REFERENCES Lernfach(ID));"
				+ "CREATE TABLE IF NOT EXISTS Lernfach (" + "	ID INT NOT NULL ," + "	name CHAR DEFAULT '',"
				+ "	semester INT DEFAULT '0'," + "	credits INT DEFAULT '0'," + "	PRIMARY KEY (ID));"
				+ "CREATE TABLE IF NOT EXISTS Statistik (" + "	ID INT NOT NULL ," + "	fragenID INT NOT NULL DEFAULT '0',"
				+ "	fragenstufe INT NOT NULL DEFAULT '0'," + "	PRIMARY KEY (ID)"
				+ "	FOREIGN KEY (fragenID) REFERENCES Frage();" + "CREATE TABLE IF NOT EXISTS Studentenstatistik (" + // Hilfstabelle
																														// um m:n
																														// Beziehung
																														// zu
																														// realisieren
				"	StatistikID INT NOT NULL DEFAULT '0'," + "	StudentenID INT NOT NULL DEFAULT '0',"
				+ "	PRIMARY KEY (StatistikID,StudentenID)," + "	FOREIGN KEY(StatistikID) REFERENCES Statistik(ID),"
				+ "	FOREIGN KEY(StudentenID) REFERENCES Student(ID);" + "CREATE TABLE IF NOT EXISTS Student ("
				+ "	ID INT NOT NULL ," + "	name CHAR DEFAULT ''," + "	PRIMARY KEY (ID));";

		// executeQuery(sql);

		for (final String string : sqlarray)
			executeQuery(string);
	}
}
