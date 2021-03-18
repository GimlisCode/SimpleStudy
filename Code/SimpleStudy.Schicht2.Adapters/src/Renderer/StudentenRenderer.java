package Renderer;

import java.util.ArrayList;
import java.util.Collection;

import Models.Student;

public class StudentenRenderer
{

	public static ArrayList<PrettyHashMap> getStudentForView(Collection<Student> studenten)
	{

		ArrayList<PrettyHashMap> gerenderteStudenten = new ArrayList<>();
		for (Student student : studenten)
		{
			PrettyHashMap gerenderterStudent = new PrettyHashMap();
			gerenderterStudent.addVisible(Student.idText,
					student.getId() + "");
			gerenderterStudent.addVisible(Student.nameText,
					student.getName());

			if (student.getStatistik() != null)
			{
				gerenderterStudent.addUnvisible(Student.statistikText,
						student.getStatistik()
								.getId() + "");
			}

			gerenderteStudenten.add(gerenderterStudent);

//			for ( var attribute : student.getDetails().entrySet())
//			{
//				gerenderterStudent.put(attribute.getKey().toString(), attribute.getValue().toString())
//			}
		}

		return gerenderteStudenten;
	}

}
