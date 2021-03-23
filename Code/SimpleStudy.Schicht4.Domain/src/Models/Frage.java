package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Frage extends Entity
{

	// private int nr;
	private String text;
	public final static String textText = "text";
	private int typ;
	public final static String typText = "typ";
	private ArrayList<Antwort> antworten = new ArrayList<>();
	public final static String antwortenText = "antworten";

	public Frage(String text, int typ, ArrayList<Antwort> antworten)
	{
		super();
		// this.nr = nr;
		this.text = text;
		this.typ = typ;
		this.antworten = antworten;
	}

	public Frage(String text, int typ)
	{
		super(); //
		this.text = text;
		this.typ = typ;
	}

//	public int getNr() {
//		return nr;
//	}
//
//
//	public void setNr(int nr) {
//		this.nr = nr;
//	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public int getTyp()
	{
		return typ;
	}

	public void setTyp(int typ)
	{
		this.typ = typ;
	}

	public ArrayList<Antwort> getAntworten()
	{
		return antworten;
	}

	public void setAntworten(ArrayList<Antwort> antworten)
	{
		this.antworten = antworten;
	}

	public void add(Antwort newAntwort)
	{
		if (!antworten.contains(newAntwort))
			antworten.add(newAntwort);
	}

	public void remove(Antwort removeAntwort)
	{
		if (antworten.contains(removeAntwort))
			antworten.remove(removeAntwort);
	}

	@Override
	public HashMap<String, Object> getDetails()
	{
		final HashMap<String, Object> details = new HashMap<String, Object>();
		// details.put("nr", this.nr);
		details.put(idText,
				id);
		details.put(textText,
				text);
		details.put(typText,
				typ);
		details.put(antwortenText,
				antworten);

		return details;
	}

}
