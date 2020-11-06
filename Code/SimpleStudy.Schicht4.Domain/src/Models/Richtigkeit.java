package Models;

public class Richtigkeit {
	
	private int richtig = 0;
	private int falsch = 0;
	
	
	public Richtigkeit() {
	
	}


	public int getRichtig() {
		return richtig;
	}


	public int getFalsch() {
		return falsch;
	}

	public void addFalsch() {
		falsch++;
	}
	
	public void addRichtig() {
		richtig++;
	}
}
