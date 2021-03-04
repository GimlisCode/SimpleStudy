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
import Models.Antwort;
import Models.Dozent;
import Models.Frage;
import Models.Hochschule;
import Models.Kapitel;
import Models.Lernfach;
import Models.Richtigkeit;
import Models.Statistik;
import Models.Student;

public class SQLite implements DatenVerbindung
{
	private static final SQLite dbPlugin = new SQLite();
	private Connection connection;
	private final String DB_PATH = "simpleStudy.db";
	private final static String selectAllStatemant = "Select * From ";
	private String selectStatement = "";
	private ArrayList<String> joinStatement = new ArrayList<>();
	private String where = "";

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

		Runtime.getRuntime()
				.addShutdownHook(new Thread()
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

	public ArrayList<HashMap<String, String>> getResultFromQuerry(String sqlQuerry)
	{
		final ArrayList<HashMap<String, String>> resultsMapped = new ArrayList<HashMap<String, String>>();
		try
		{

			final ResultSet selectResults = executeQuery(sqlQuerry);

			final int anzColumn = selectResults.getMetaData()
					.getColumnCount() + 1;

			while (selectResults.next())
			{
				final HashMap<String, String> attributWertePaarung = new HashMap<String, String>();
				for (int i = 1; i < anzColumn; i++)
				{
					attributWertePaarung.put(selectResults.getMetaData()
							.getColumnName(i),
							selectResults.getString(i));
					System.out.println(selectResults.getString(i));
				}

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

	@Override
	public ArrayList<HashMap<String, String>> getAllFromTable(String tableName)
	{
		return getResultFromQuerry(selectAllStatemant + tableName);
	}

	@Override
	public DatenVerbindung createSelectString(String[] columns, String tableName)
	{
		return createSelectString(columns,
				tableName,
				"");
	}

	@Override
	public DatenVerbindung createSelectString(String[] columns, String tableName, String option)
	{
		if (option == null)
			option = "";

		String columnString = "";
		for (int i = 1; i < columns.length; i++)
			columnString += columns[i] + ", ";
		columnString += columns[0];
		selectStatement = "Select " + option + " " + columnString + " from " + tableName;
		return this;
	}

	public DatenVerbindung join(String[] tableName)
	{
		for (final String string : tableName)
			joinStatement.add(string);

		return this;
	}

	public DatenVerbindung where(String tableLeft, String columnLeft, String operator, String tableRight, String columnRight)
	{
		if (where != null && where.isEmpty() && where.isBlank())
			where = " where ";

		where += tableLeft + "." + columnLeft + " " + operator + " " + tableRight + "." + columnRight;
		return this;
	}

	public DatenVerbindung and()
	{
		if (where != null && !where.isEmpty() && !where.isBlank())
			where += " and ";

		return this;
	}

	public String build()
	{
		String finishedQuery = selectStatement;
		for (final String tableName : joinStatement)
			finishedQuery += " , " + tableName;

		finishedQuery += where;

		selectStatement = "";
		joinStatement = new ArrayList<String>();
		where = "";
		return finishedQuery;
	}

	public void createDB()
	{
		final String createTable[] = new String[]

			{ "CREATE TABLE IF NOT EXISTS " + Antwort.class.getSimpleName() + " (" + Antwort.idText + " INT NOT NULL, "
					+ Frage.class.getSimpleName() + Antwort.idText + " INT NOT NULL, " + Antwort.textText + " CHAR DEFAULT '', "
					+ Antwort.correctText + " BOOLEAN NOT NULL DEFAULT 'false', PRIMARY KEY(" + Antwort.idText + "), FOREIGN KEY("
					+ Frage.class.getSimpleName() + Antwort.idText + ") REFERENCES " + Frage.class.getSimpleName() + "("
					+ Frage.idText + "));",
					"CREATE TABLE IF NOT EXISTS " + Dozent.class.getSimpleName() + " (" + Dozent.idText + " INT NOT NULL, "
							+ Dozent.nameText + " CHAR DEFAULT '', " + Hochschule.class.getSimpleName() + Hochschule.idText
							+ " INT NOT NULL, PRIMARY KEY (" + Dozent.idText + "), FOREIGN KEY("
							+ Hochschule.class.getSimpleName() + Hochschule.idText + ") REFERENCES "
							+ Hochschule.class.getSimpleName() + "(" + Hochschule.idText + "));",
					"CREATE TABLE IF NOT EXISTS " + Frage.class.getSimpleName() + " (" + Frage.idText + " INT NOT NULL, "
							+ Kapitel.class.getSimpleName() + Kapitel.idText + " INT DEFAULT 0, " + Frage.textText
							+ " CHAR NOT NULL DEFAULT '', " + Frage.typText + " INT NOT NULL DEFAULT '0',PRIMARY KEY ("
							+ Frage.idText + "), FOREIGN KEY (" + Kapitel.class.getSimpleName() + Kapitel.idText + ") REFERENCES "
							+ Kapitel.class.getSimpleName() + "(" + Kapitel.idText + "));",
					"CREATE TABLE IF NOT EXISTS " + Hochschule.class.getSimpleName() + " (" + Hochschule.idText
							+ " INT NOT NULL, " + Hochschule.nameText + " CHAR NOT NULL DEFAULT '',	PRIMARY KEY ("
							+ Hochschule.idText + "));",
					"CREATE TABLE IF NOT EXISTS " + Kapitel.class.getSimpleName() + " (" + Kapitel.idText + " INT NOT NULL, "
							+ Lernfach.class.getSimpleName() + Lernfach.idText + " INT NOT NULL DEFAULT 0,	" + Kapitel.nameText
							+ " CHAR NOT NULL DEFAULT '', " + Kapitel.nrText + " INT DEFAULT '0',PRIMARY KEY (" + Kapitel.idText
							+ "), FOREIGN KEY (" + Lernfach.class.getSimpleName() + Lernfach.idText + ") REFERENCES "
							+ Lernfach.class.getSimpleName() + "(" + Lernfach.idText + "));",
					"CREATE TABLE IF NOT EXISTS " + Lernfach.class.getSimpleName() + " (" + Lernfach.idText + " INT NOT NULL ,	"
							+ Lernfach.nameText + " CHAR DEFAULT '', " + Lernfach.semesterText + " INT DEFAULT '0', "
							+ Lernfach.creditsText + " INT DEFAULT '0'," + Dozent.class.getSimpleName() + Dozent.idText
							+ " INT DEFAULT 0, PRIMARY KEY (" + Lernfach.idText + "), FOREIGN KEY ("
							+ Dozent.class.getSimpleName() + Dozent.idText + ") REFERENCES " + Dozent.class.getSimpleName() + "("
							+ Dozent.idText + "));",
					"CREATE TABLE IF NOT EXISTS " + Student.class.getSimpleName() + " (" + Student.idText + " INT NOT NULL , "
							+ Student.nameText + " CHAR DEFAULT '', PRIMARY KEY (" + Student.idText + "));",
					"CREATE TABLE IF NOT EXISTS " + Statistik.class.getSimpleName() + " (" + Statistik.idText + " INT NOT NULL, "
							+ Student.class.getSimpleName() + Student.idText + " INT DEFAULT '0', " + Frage.class.getSimpleName()
							+ Frage.idText + " INT DEFAULT '0', " + Richtigkeit.richtigText + " INT DEFAULT '0', "
							+ Richtigkeit.falschText + " INT DEFAULT '0', " + Richtigkeit.fragenstufeText
							+ " INT DEFAULT '1', PRIMARY KEY (" + Statistik.idText + ", " + Student.class.getSimpleName()
							+ Student.idText + ", " + Frage.class.getSimpleName() + Frage.idText + "), FOREIGN KEY ("
							+ Student.class.getSimpleName() + Student.idText + ") REFERENCES " + Student.class.getSimpleName()
							+ "(" + Student.idText + "),FOREIGN KEY (" + Frage.class.getSimpleName() + Frage.idText
							+ ") REFERENCES " + Frage.class.getSimpleName() + "(" + Frage.idText + "));" };

		for (final String string : createTable)
			executeQuery(string);

		final String insertFirstData[] = new String[]

			{ "INSERT INTO " + Antwort.class.getSimpleName() + " VALUES(1,1,'Bonn',false);",
					"INSERT INTO " + Antwort.class.getSimpleName() + " VALUES(2,1,'Köln',false);",
					"INSERT INTO " + Antwort.class.getSimpleName() + " VALUES(3,1,'Berlin',true);",
					"INSERT INTO " + Antwort.class.getSimpleName() + " VALUES(4,1,'Koblenz',false);",
					"INSERT INTO " + Frage.class.getSimpleName()
							+ " VALUES(1,1,'Wie lautet die Hauptstadt der Bundesrepublik Deutschland?',1);",
					"INSERT INTO " + Antwort.class.getSimpleName() + " VALUES(5,2,'Draußen',false);",
					"INSERT INTO " + Antwort.class.getSimpleName() + " VALUES(6,2,'Drinnen',true);",
					"INSERT INTO " + Antwort.class.getSimpleName() + " VALUES(7,2,'In diesem Code',true);",
					"INSERT INTO " + Antwort.class.getSimpleName() + " VALUES(8,2,'He's still alive!',false);",
					"INSERT INTO " + Frage.class.getSimpleName() + " VALUES(2,1,'Wo liegt der Hund vergraben?',1);",
					"INSERT INTO " + Hochschule.class.getSimpleName() + " VALUES(1, 'DHBW Karlsruhe');",
					"INSERT INTO " + Kapitel.class.getSimpleName() + " VALUES(1,1,'Deutschland', 1);",
					"INSERT INTO " + Dozent.class.getSimpleName() + " VALUES(1,'Freudenmann, Johannes',1);",
					"INSERT INTO " + Lernfach.class.getSimpleName() + " VALUES(1,'Erdkunde',1,8,1);",
					"INSERT INTO " + Student.class.getSimpleName() + " VALUES(1,'Maul, Johannes');",
					"INSERT INTO " + Student.class.getSimpleName() + " VALUES(2,'Lickteig, Simon');",
					"INSERT INTO " + Statistik.class.getSimpleName() + " VALUES(1,1,1,0,1,2);",
					"INSERT INTO " + Statistik.class.getSimpleName() + " VALUES(1,1,2,4,2,1);",
					"INSERT INTO " + Statistik.class.getSimpleName() + " VALUES(2,2,1,0,1,2);" };

		for (final String string : insertFirstData)
			executeQuery(string);
	}

}
