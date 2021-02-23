package main;

import Controller.DbController;
import DB.SQLite;
import UI.LoginFrame;

public class MainClass
{

	public static void main(String[] args)
	{
		final SQLite db = SQLite.getInstance();

		final DbController dbController = new DbController(db);

//		MainFrame mframe = new MainFrame();
		final LoginFrame lframe = new LoginFrame();

	}

}
