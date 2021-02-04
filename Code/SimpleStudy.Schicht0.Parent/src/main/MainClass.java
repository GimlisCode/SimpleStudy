package main;

import Controller.DbController;
import DB.SQLite;

public class MainClass {
	
	public static void main(String[] args) {
		SQLite db = SQLite.getInstance();
		
		DbController dbController = new DbController(db);
	}
	

}
