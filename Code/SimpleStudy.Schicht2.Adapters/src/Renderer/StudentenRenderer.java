package Renderer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import Models.Student;

public class StudentenRenderer
{

	public static ArrayList<HashMap<String, String>> getStudentForView(Collection<Student> studenten)
	{

		ArrayList<HashMap<String, String>> gerenderteStudenten = new ArrayList<>();
		for (Student student : studenten)
		{
			HashMap<String, String> gerenderterStudent = new HashMap<>();
			gerenderterStudent.put(Student.idtext, student.getId() + "");
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
