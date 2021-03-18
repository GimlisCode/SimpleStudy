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
			gerenderteAntwort.put(Antwort.idText,
					antwort.getId() + "");
			gerenderteAntwort.put(Antwort.textText,
					antwort.getText() + "");

			gerenderteAntworten.add(gerenderteAntwort);
		}

		return gerenderteAntworten;
	}

}
