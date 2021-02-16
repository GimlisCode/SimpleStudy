package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Kapitel extends Entity
{

	private String name;
	public final static String nameText = "name";
	private int nr;
	public final static String nrText = "nr";
	private ArrayList<Frage> fragen = new ArrayList<>();
	public final static String fragenText = "fragen";

	public Kapitel(String name, int nr, ArrayList<Frage> fragen)
	{
		super();
		this.name = name;
		this.nr = nr;
		this.fragen = fragen;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getNr()
	{
		return nr;
	}

	public void setNr(int nr)
	{
		this.nr = nr;
	}

	public ArrayList<Frage> getFragen()
	{
		return fragen;
	}

	public void setFragen(ArrayList<Frage> fragen)
	{
		this.fragen = fragen;
	}

	public void add(Frage newFrage)
	{
		if (!fragen.contains(newFrage))
			fragen.add(newFrage);
	}

	public void remove(Frage removeFrage)
	{
		if (fragen.contains(removeFrage))
			fragen.remove(removeFrage);
	}

	@Override
	HashMap<String, Object> getDetails()
	{
		HashMap<String, Object> details = new HashMap<String, Object>();
		details.put(idtext, this.id);
		details.put(nrText, this.nr);
		details.put(nameText, this.name);
		details.put(fragenText, this.fragen);

		return details;
	}

}
