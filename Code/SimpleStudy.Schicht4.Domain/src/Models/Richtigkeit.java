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
		HashMap<String, Object> details = new HashMap<String, Object>();
		details.put(idText, this.id);
		details.put(richtigText, this.richtig);
		details.put(falschText, this.falsch);
		details.put(fragenstufeText, this.fragenstufe);

		return details;
	}
}
