package Modifier;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Statistik;
import Models.Student;

public class StudentenFabrik
{
	private static Student neuerStudent = new Student(null, null);
	private static StudentenVerwaltung studentenVerwaltung;
	private static StudentenFabrik studentenFabrikSingleton;
	private static ArrayList<Tupel<Integer, Integer>> statistikReferenzen = new ArrayList<>();

	private StudentenFabrik()
	{
		studentenVerwaltung = StudentenVerwaltung.getInstance();
	}

	public static StudentenFabrik getInstance()
	{
		return studentenFabrikSingleton;
	}

	public static HashMap<String, String> getStudentenAttribute()
	{
		final HashMap<String, String> studentenAttribute = new HashMap<>();
		final String[] attributNamen = neuerStudent.getAttributeNames();
		for (final String attributName : attributNamen)
			studentenAttribute.put(attributName,
					"");

		return studentenAttribute;
	}

	public static void create(HashMap<String, String> studentenAttribute)
	{
		neuerStudent.setId(Integer.parseInt(studentenAttribute.get(Student.idText)));
		neuerStudent.setName(studentenAttribute.get(Student.nameText));

		final String statistikId = studentenAttribute.get(Student.statistikText);
		if (statistikId != null)

		{
			final Statistik statistik = StatistikVerwaltung.get(Integer.parseInt(statistikId));
			if (statistik != null)
				neuerStudent.setStatistik(statistik);
			else
				statistikReferenzen.add(new Tupel<>(neuerStudent.getId(), Integer.parseInt(statistikId)));
		}

		studentenVerwaltung.add(neuerStudent);
		neuerStudent = new Student("", null);
	}

	public static void resolveReferences()
	{
		for (final Tupel<Integer, Integer> tupel : statistikReferenzen)
		{
			final Statistik statistik = StatistikVerwaltung.get(tupel.y);
			if (statistik != null)
				studentenVerwaltung.get(tupel.x)
						.setStatistik(statistik);
			// else
			// throw error ask User

		}
	}
}
