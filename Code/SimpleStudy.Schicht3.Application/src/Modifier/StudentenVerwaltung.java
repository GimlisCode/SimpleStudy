package Modifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import Models.Student;

public class StudentenVerwaltung
{

	static private HashMap<Integer, Student> studenten = new HashMap<>();
	static private StudentenVerwaltung studentenVerwaltungSingleton = new StudentenVerwaltung();

	private StudentenVerwaltung()
	{
		super();
	}

	public static StudentenVerwaltung getInstance()
	{
		return studentenVerwaltungSingleton;
	}

	public static Student get(int id)
	{
		return studenten.get(id);
	}

	public static HashMap<Integer, Student> getAll()
	{
		return studenten;
	}

	static void add(Student student)
	{
		studenten.put(student.getId(),
				student);
	}

	public static void remove(Student student)
	{
		remove(student.getId());
	}

	public static void remove(int id)
	{
		studenten.remove(id);
	}

	static void update(Student student)
	{
		studenten.put(student.getId(),
				student);
	}

	static ArrayList<Student> suche(String suchstring)
	{
		final ArrayList<Student> passendeStudentenZumSuchstring = new ArrayList<>();
		for (final Entry<Integer, Student> student : studenten.entrySet())
			if (student.getValue()
					.toString()
					.contains(suchstring))
				passendeStudentenZumSuchstring.add(student.getValue());

		if (passendeStudentenZumSuchstring.size() > 0)
			return passendeStudentenZumSuchstring;

		return null;
	}

}
