package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import Modifier.AntwortFabrik;
import Modifier.DozentenFabrik;
import Modifier.StudentenFabrik;
import Modifier.StudentenVerwaltung;
import Renderer.PrettyHashMap;
import Renderer.StudentenRenderer;

public class MainController
{

	private static MainController mainControllerSingleton = new MainController();

	private MainController()
	{
		super();
	}

	public static MainController getInstance()
	{
		return mainControllerSingleton;
	}

//	public static void createEntityOf(Class fabrikTyp, HashMap<String, String> classValues)
//	{
//		// TODO: herausfinden wie richtiges Switch-Case geht.
//		createAntwort(classValues);
//		createDozent(classValues);
//	}

	public static void createAntwort(HashMap<String, String> antwortWerte)
	{
		final var antwortAttribute = AntwortFabrik.getAntwortAttribute();
		for (final Entry<String, String> antwortAttribut : antwortAttribute.entrySet())
			antwortAttribut.setValue(antwortWerte.get(antwortAttribut.getKey()));

		AntwortFabrik.create(antwortAttribute);

	}

	public static void createDozent(HashMap<String, String> dozentWerte)
	{
		final var dozentAttribute = DozentenFabrik.getDozentAttribute();
		for (final Entry<String, String> dozentAttribut : dozentAttribute.entrySet())
			dozentAttribut.setValue(dozentWerte.get(dozentAttribut.getKey()));

		DozentenFabrik.create(dozentAttribute);
	}

	public static void createStudent(HashMap<String, String> studentWerte)
	{
		final var studentAttribute = StudentenFabrik.getStudentenAttribute();
		for (final Entry<String, String> studentAttribut : studentAttribute.entrySet())
			studentAttribut.setValue(studentWerte.get(studentAttribut.getKey()));

		StudentenFabrik.create(studentAttribute);
	}

	public static ArrayList<PrettyHashMap> getStudenten()
	{

		return StudentenRenderer.getStudentForView(StudentenVerwaltung.getAll().values());
	}

}
