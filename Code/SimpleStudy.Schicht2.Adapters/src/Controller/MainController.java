package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import Modifier.AntwortFabrik;
import Modifier.DozentenFabrik;
import Modifier.StudentenVerwaltung;
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

	public static ArrayList<HashMap<String, String>> getStudenten()
	{

		return StudentenRenderer.getStudentForView(StudentenVerwaltung.getAll().values());
	}

}
