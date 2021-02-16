package main;

import Controller.DbController;
import DB.SQLite;
import UI.LoginFrame;
import UI.MainFrame;

public class MainClass
{

	public static void main(String[] args)
	{
		SQLite db = SQLite.getInstance();

		DbController dbController = new DbController(db);

		MainFrame mframe = new MainFrame();
		LoginFrame lframe = new LoginFrame();

	}

}
