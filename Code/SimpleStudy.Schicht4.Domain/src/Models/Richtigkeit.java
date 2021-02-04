package Models;

import java.util.HashMap;

public class Richtigkeit extends Entity
{

	private int richtig = 0;
	private int falsch = 0;
	private int fragenstufe = 1;

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
		details.put("id", this.id);
		details.put("richtig", this.richtig);
		details.put("falsch", this.falsch);
		details.put("fragenstufe", this.fragenstufe);

		return details;
	}
}
