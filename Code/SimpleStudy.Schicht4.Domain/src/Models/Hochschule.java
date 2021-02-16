package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Hochschule extends Entity
{

	private String name;
	public final static String nameText = "name";
	private ArrayList<Dozent> dozenten = new ArrayList<>();
	public final static String dozentenText = "dozenten";

	public Hochschule(String name, ArrayList<Dozent> dozenten)
	{
		super();
		this.name = name;
		this.dozenten = dozenten;
	}

	public Hochschule(String name)
	{
		super();
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public ArrayList<Dozent> getDozenten()
	{
		return dozenten;
	}

	public void setDozenten(ArrayList<Dozent> dozenten)
	{
		this.dozenten = dozenten;
	}

	public void add(Dozent newDozent)
	{
		if (!dozenten.contains(newDozent))
			dozenten.add(newDozent);
	}

	public void remove(Dozent removeDozent)
	{
		if (dozenten.contains(removeDozent))
			dozenten.remove(removeDozent);
	}

	@Override
	HashMap<String, Object> getDetails()
	{
		HashMap<String, Object> details = new HashMap<String, Object>();
		details.put(idtext, this.id);
		details.put(nameText, this.name);
		details.put(dozentenText, this.dozenten);

		return details;
	}

}
