package Models;

import java.util.HashMap;

public class Antwort extends Entity{
	
	private String text;
	private boolean correct;
	
	
	public Antwort(String text, boolean correct) {
		super();
		this.text = text;
		this.correct = correct;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public boolean isCorrect() {
		return correct;
	}


	public void setCorrect(boolean correct) {
		this.correct = correct;
	}


	@Override
	HashMap<String, Object> getDetails() {
		HashMap<String, Object> details = new HashMap<String, Object>();
		details.put("text", this.text);
		details.put("correct", this.correct);
		
		return details;
	}
	
	
	
	

}
