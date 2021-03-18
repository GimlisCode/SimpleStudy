package Renderer;

import java.util.ArrayList;
import java.util.Collection;

import Models.Antwort;

public class AntwortenRenderer
{

	public static ArrayList<PrettyHashMap> getAntwortenForView(Collection<Antwort> antworten)
	{

		ArrayList<PrettyHashMap> gerenderteAntworten = new ArrayList<>();
		for (Antwort antwort : antworten)
		{
			PrettyHashMap gerenderteAntwort = new PrettyHashMap();
			gerenderteAntwort.addVisible(Antwort.idText,
					antwort.getId() + "");
			gerenderteAntwort.addVisible(Antwort.textText,
					antwort.getText() + "");
			gerenderteAntwort.addUnvisible(Antwort.correctText,
					antwort.isCorrect() + "");

			gerenderteAntworten.add(gerenderteAntwort);
		}

		return gerenderteAntworten;
	}

}
