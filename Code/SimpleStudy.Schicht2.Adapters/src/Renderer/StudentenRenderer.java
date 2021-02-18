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
			gerenderterStudent.put(Student.idText, student.getId() + "");
			gerenderterStudent.put(Student.nameText, student.getName());

			gerenderteStudenten.add(gerenderterStudent);

//			for ( var attribute : student.getDetails().entrySet())
//			{
//				gerenderterStudent.put(attribute.getKey().toString(), attribute.getValue().toString())
//			}
		}

		return gerenderteStudenten;
	}

}
