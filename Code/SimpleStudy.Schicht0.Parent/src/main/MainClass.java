package main;

import Controller.DbController;
import DB.SQLite;

public class MainClass {
	
	public static void main(String[] args) {
		SQLite db = SQLite.getInstance();
		db.initDBConnection();
		
		DbController dbController = new DbController(db);
	}
	

}
