package Models;

public enum Modus {
	
	LERN("Lernmodus",1),
	ABFRAGE("Abfragemodus",2);
	
	
	private int id;
	private String name;
	
	Modus(String name, int id) {
		this.id = id;
		this.name = name;
	}
	
	public static Modus getEnumOfId(int id) {
		for(Modus modus: values()) {
			if(modus.id == id) {
				return modus;
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
	
