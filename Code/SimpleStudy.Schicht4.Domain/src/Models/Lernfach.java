package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Lernfach extends Entity
{

	private String name;
	public final static String nameText = "name";
	private int semester;
	public final static String semesterText = "semester";
	private int credits;
	public final static String creditsText = "credits";
	private ArrayList<Kapitel> lernkapitel = new ArrayList<>();
	public final static String kapitelText = "kapitel";

	public Lernfach(String name, int semester, int credits, ArrayList<Kapitel> lernkapitel)
	{
		super();
		this.name = name;
		this.semester = semester;
		this.credits = credits;
		this.lernkapitel = lernkapitel;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getSemester()
	{
		return semester;
	}

	public void setSemester(int semester)
	{
		this.semester = semester;
	}

	public int getCredits()
	{
		return credits;
	}

	public void setCredits(int credits)
	{
		this.credits = credits;
	}

	public ArrayList<Kapitel> getLernkapitel()
	{
		return lernkapitel;
	}

	public void setLernkapitel(ArrayList<Kapitel> lernkapitel)
	{
		this.lernkapitel = lernkapitel;
	}

	public void add(Kapitel newLernkapitel)
	{
		if (!lernkapitel.contains(newLernkapitel))
			lernkapitel.add(newLernkapitel);
	}

	public void remove(Kapitel removeLernkapitel)
	{
		if (lernkapitel.contains(removeLernkapitel))
			lernkapitel.remove(removeLernkapitel);
	}

	@Override
	HashMap<String, Object> getDetails()
	{
		HashMap<String, Object> details = new HashMap<String, Object>();
		details.put(idtext, this.id);
		details.put(nameText, this.name);
		details.put(semesterText, this.semester);
		details.put(creditsText, this.credits);
		details.put(kapitelText, this.lernkapitel);

		return details;
	}
}
