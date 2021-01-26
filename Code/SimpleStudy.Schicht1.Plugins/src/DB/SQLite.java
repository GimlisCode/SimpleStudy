package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite {
	 	private static final SQLite dbPlugin = new SQLite();
	    private static Connection connection;
	    private static final String DB_PATH = "D:\\DH\\5\\Advanced SWE\\SimpleStudy\\Data\\Datenbank\\simpleStudy.db";
	    
	    static {
	        try {
	            Class.forName("org.sqlite.JDBC");
	        } catch (ClassNotFoundException e) {
	            //TODO: eigener Error einführen
	        	System.err.println("Fehler beim Laden des JDBC-Treibers");
	            e.printStackTrace();
	        }
	    }
	    
	    private SQLite() {
    		super();
		}
	    
	    public static SQLite getInstance(){
	        return dbPlugin;
	    }
	    
	    public void initDBConnection() {
	        try {
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
}
