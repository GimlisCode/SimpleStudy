package Controller;

import Models.Antwort;
import Modifier.AntwortFabrik;

public class MainController
{

	MainController mainControllerSingleton = new MainController();

	private MainController()
	{
		super();
	}

	public void createEntityOf(Class fabrikTyp, String Values)
	{
		switch (fabrikTyp)
		{
		case Antwort.class:
			createAntwort();
			break;

		default:
			break;
		}
	}

	private void createAntwort()
	{
		final var antwortAttribute = AntwortFabrik.getAntwortAttribute();
		for (final Entryset iterable_element : iterable)
		{

		}
		AntwortFabrik.create(antwortAttribute);

	}

}
