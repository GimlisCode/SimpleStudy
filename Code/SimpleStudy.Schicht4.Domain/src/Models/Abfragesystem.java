package Models;

public enum Abfragesystem {
	
	RANDOM("Random",1),
	LINEAR("Linear",2),
	STEP("Step",3);
	
	
	private int id;
	private String name;
	
	Abfragesystem(String name, int id) {
this.id = id;
this.name = name;
}
	
	public static Abfragesystem getEnumOfId(int id) {
		for(Abfragesystem abfragesystem: values()) {
			if(abfragesystem.id == id) {
				return abfragesystem;
			}
		}
		
		return null;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
}
