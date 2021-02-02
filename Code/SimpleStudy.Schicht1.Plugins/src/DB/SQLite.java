package DB;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

import Controller.DatenVerbindung;

public class SQLite implements DatenVerbindung{
	 	private static final SQLite dbPlugin = new SQLite();
	    private Connection connection;
	    private final String DB_PATH = "simpleStudy.db";
	    private final static String selectAllStatemant = "Select * From %s";
	    
	    
	    private SQLite() {
    		super();
    		initDBConnection();    		
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
		
private ResultSet executeQuery(String sqlString)
{
	Statement stmt = null;
	try {
		stmt = connection.createStatement(  );
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		if(stmt.execute(sqlString)) {
			return stmt.getResultSet(  );
		} 
		else {
			System.out.println("Rows updated: " + stmt.getUpdateCount());
			return null;
		}
	}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return null;
}

		@Override
		public ArrayList<String> getAllFromTable(String tableName) {
			try
			{
				ResultSet selectResults = executeQuery(selectAllStatemant + tableName);
				int anzColumn = selectResults.getMetaData().getColumnCount();
				ArrayList<String> resultsAsString = new ArrayList<String>();
				while (selectResults.next()) {
					String resultRow = "";
					for (int i = 0; i < anzColumn; i++) {
						resultRow += selectResults.getString(i) + ",";
					}
					resultsAsString.add(resultRow);
				}
			}catch (SQLException e) {
				// TODO: handle exception
			}
			finally {
				return new ArrayList<String>();
			}
		}


	
		public void createDB() {
			
			Statement stmt = initDBStatements();
			
			String sql ="CREATE TABLE `Antwort` (\r\n" + 
					"	`ID` INT NOT NULL AUTO_INCREMENT,\r\n" + 
					"	`text` CHAR DEFAULT '',\r\n" + 
					"	`correct` BOOLEAN NOT NULL DEFAULT 'false',\r\n" + 
					"	PRIMARY KEY (`ID`)\r\n" + 
					");";
			
			stmt.execute(sql);
			
		
			
		}
}
