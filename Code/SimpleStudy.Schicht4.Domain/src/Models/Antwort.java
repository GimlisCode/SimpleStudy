package Models;

public class Antwort extends Entity{
	
	private String text;
	private boolean correct;
	
	
	public Antwort(String text, boolean correct) {
		super();
		this.text = text;
		this.correct = correct;
	}
	
	

}
