package DB;

import java.io.File;
import java.sql.*;

import Controller.DatenVerbindung;

public class SQLite implements DatenVerbindung{
	 	private static final SQLite dbPlugin = new SQLite();
	    private static Connection connection;
	    private static final String DB_PATH = "simpleStudy.db";
	    
	    private SQLite() {
    		super();
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
		public void getAll() {
			//Select * From etc
			
		}
}
