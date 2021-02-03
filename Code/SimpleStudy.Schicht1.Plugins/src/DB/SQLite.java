package DB;

import java.io.File;
import java.sql.*;

import Controller.DatenVerbindung;

public class SQLite implements DatenVerbindung{
	 	private static final SQLite dbPlugin = new SQLite();
	    private Connection connection;
	    private final String DB_PATH = "simpleStudy.db";
	    private final static String selectAllStatemant = "Select * From %s";
	    
	    
	    private SQLite() {
    		super();
    		initDBConnection();
    		initDBStatements();
		}
	    
	    private Statement initDBStatements() {
	    	try {
	    		return connection.createStatement();
	    	}  catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
			
		}

		public static SQLite getInstance(){
	        return dbPlugin;
	    }
	    
	    public void initDBConnection() {
	        try {
	        	System.out.println(new File(".").getAbsolutePath());
		        
	            if (connection != null)
	                return;
	            System.out.println("Creating Connection to Database...");
	            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
	            if (!connection.isClosed())
	                System.out.println("...Connection established");
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }

	        Runtime.getRuntime().addShutdownHook(new Thread() {
	            public void run() {
	                try {
	                    if (!connection.isClosed() && connection != null) {
	                        connection.close();
	                        if (connection.isClosed())
	                            System.out.println("Connection to Database closed");
	                    }
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }

		@Override
		public void getAllFromTable(String tableName) {
			Statement sqlStatemant;
			
		}
		
		public void createDB() {
			
			Statement stmt = initDBStatements();
			
			String sql ="CREATE TABLE IF NOT EXISTS Antwort (" + 
					"	ID INT NOT NULL AUTO_INCREMENT," + 
					"	fragenID INT NOT NULL," +	
					"	text CHAR DEFAULT ''," + 
					"	correct BOOLEAN NOT NULL DEFAULT 'false'," + 
					"	FOREIGN KEY(fragenID) REFERENCES Frage(ID)"+
					"	PRIMARY KEY (ID));" + 
					"CREATE TABLE IF NOT EXISTS Dozent (" + 
					"	ID INT NOT NULL AUTO_INCREMENT," + 
					"	name CHAR DEFAULT ''," +
					"	hoschulID INT NOT NULL," +
					"	PRIMARY KEY (ID)" + 
					"	FOREIGN KEY(hochschulID) REFERENCES Hochschule(ID);" +
					"CREATE TABLE IF NOT EXISTS Frage (" + 
					"	ID INT NOT NULL AUTO_INCREMENT," + 
					"	kapitelID INT DEFAULT 0 " +
					"	text CHAR NOT NULL DEFAULT NULL," + 
					"	typ INT NOT NULL DEFAULT '0'," + 
					"	PRIMARY KEY (ID)" +
					"	FOREIGN KEY (kapitelID);" +
					"CREATE TABLE IF NOT EXISTS Hochschule" + 
					"	ID INT NOT NULL AUTO_INCREMENT," + 
					"	name CHAR NOT NULL DEFAULT ''," + 
					"	PRIMARY KEY (ID));" +  
					"CREATE TABLE IF NOT EXISTS Kapitel (" + 
					"	ID INT NOT NULL AUTO_INCREMENT," + 
					"	lernfachID INT NOT NULL DEFAULT 0,"+
					"	name CHAR NOT NULL DEFAULT ''," + 
					"	nr INT DEFAULT '0'," + 
					"	PRIMARY KEY (ID)" + 
					"	FOREIGN KEY (lernfachID) REFERENCES Lernfach(ID));"+
					"CREATE TABLE IF NOT EXISTS Lernfach (" + 
					"	ID INT NOT NULL AUTO_INCREMENT," + 
					"	name CHAR DEFAULT ''," + 
					"	semester INT DEFAULT '0'," + 
					"	credits INT DEFAULT '0'," + 
					"	PRIMARY KEY (ID));" + 
					"CREATE TABLE IF NOT EXISTS Statistik (" + 
					"	ID INT NOT NULL AUTO_INCREMENT," + 
					"	fragenID INT NOT NULL DEFAULT '0'," + 
					"	fragenstufe INT NOT NULL DEFAULT '0'," + 
					"	PRIMARY KEY (ID)" + 
					"	FOREIGN KEY (fragenID) REFERENCES Frage();"+
					"CREATE TABLE IF NOT EXISTS Studentenstatistik (" + 		//Hilfstabelle um m:n Beziehung zu realisieren
					"	StatistikID INT NOT NULL DEFAULT '0'," + 
					"	StudentenID INT NOT NULL DEFAULT '0'," + 
					"	PRIMARY KEY (StatistikID,StudentenID)," +
					"	FOREIGN KEY(StatistikID) REFERENCES Statistik(ID)," +
					"	FOREIGN KEY(StudentenID) REFERENCES Student(ID);" + 
					"CREATE TABLE IF NOT EXISTS Student (" + 
					"	ID INT NOT NULL AUTO_INCREMENT," + 
					"	name CHAR DEFAULT ''," + 
					"	PRIMARY KEY (ID));"					
					;
			
			try {
				stmt.execute(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			
		}
}
