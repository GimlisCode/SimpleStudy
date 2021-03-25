package Models;

import java.util.HashMap;

public class Richtigkeit extends Entity
{

	private int richtig = 0;
	public final static String richtigText = "richtig";
	private int falsch = 0;
	public final static String falschText = "falsch";
	private int fragenstufe = 1;
	public final static String fragenstufeText = "fragenstufe";

	public Richtigkeit()
	{

	}

	public Richtigkeit(int richtige, int falsche, int fragenstufe)
	{
		richtig = richtige;
		falsch = falsche;
		this.fragenstufe = fragenstufe;
	}

	public int getRichtig()
	{
		return richtig;
	}

	public int getFalsch()
	{
		return falsch;
	}

	public void addFalsch()
	{
		falsch++;
	}

	public void addRichtig()
	{
		richtig++;
	}

	@Override
	HashMap<String, Object> getDetails()
	{
		final HashMap<String, Object> details = new HashMap<String, Object>();
		details.put(idText,
				id);
		details.put(richtigText,
				richtig);
		details.put(falschText,
				falsch);
		details.put(fragenstufeText,
				fragenstufe);

		return details;
	}

	@Override
	public String toString()
	{
		return "Richtige: " + richtig + ",\n\r" + " Falsche: " + falsch + ",\n\r" + " Fragenstufe: " + fragenstufe + "\n\r";
	}
}
