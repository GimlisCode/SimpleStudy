package Controller;

import java.io.File;

public class MainController {
	
	MainController mainControllerSingleton = new MainController();
	
	private MainController() {
		super();
	}
	
	public static void main(String[] args) {
		//DbController dbController = new DbController()
		System.out.println(new File(".").getAbsolutePath());
	}
}
