package Modifier;

import java.util.HashMap;

import Models.Student;

public class StudentenVerwaltung {

	static private HashMap<Integer, Student> studenten = new HashMap<>();
	
	public StudentenVerwaltung() {
	
	}
	
	static Student get(int id)
	{		
		return studenten.get(id);
	}
	
	static HashMap<Integer, Student> getAll()
	{
		return studenten;
	}
	
	static void add(Student student)
	{		
		studenten.put(student.getId(), student);		
	}
		
	static void remove(Student student)
	{		
		remove(student.getId());				
	}
	
	static void remove(int id)
	{		
		studenten.remove(id);				
	}
	
	static void update(Student student)
	{		
		studenten.put(student.getId(), student);		
	}
}
