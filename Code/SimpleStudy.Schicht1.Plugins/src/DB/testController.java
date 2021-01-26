package DB;

public class testController {
	public static void main(String[] args) {
		SQLite dbp = SQLite.getInstance();
		dbp.initDBConnection();
	}
}
