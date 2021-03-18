package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Dozent extends Entity
{

	private String name;
	public static final String nameText = "name";
	private ArrayList<Lernfach> kurse = new ArrayList<>();
	public static final String kurseText = "kurse";

	public Dozent(String name, ArrayList<Lernfach> kurse)
	{
		super();
		this.name = name;
		this.kurse = kurse;
	}

	public Dozent(String name)
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

	public ArrayList<Lernfach> getKurse()
	{
		return kurse;
	}

	public void addKurs(Lernfach l)
	{
		if (kurse.contains(l) != true)
			kurse.add(l);
	}

	public void removeKurs(Lernfach l)
	{
		if (kurse.contains(l))
			kurse.remove(l);
	}

	@Override
	HashMap<String, Object> getDetails()
	{
		final HashMap<String, Object> details = new HashMap<String, Object>();
		details.put(idText,
				id);
		details.put(nameText,
				name);
		details.put(kurseText,
				kurse);
		return details;
	}

}
