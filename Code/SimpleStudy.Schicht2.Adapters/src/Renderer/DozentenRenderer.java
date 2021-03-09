package Renderer;

import java.util.ArrayList;
import java.util.Collection;

import Models.Dozent;

public class DozentenRenderer
{

	public static ArrayList<PrettyHashMap> getDozentenForView(Collection<Dozent> dozenten)
	{

		ArrayList<PrettyHashMap> gerenderteDozenten = new ArrayList<>();
		for (Dozent dozent : dozenten)
		{
			PrettyHashMap gerenderterDozent = new PrettyHashMap();
			gerenderterDozent.put(Dozent.idText,
					dozent.getId() + "");
			gerenderterDozent.put(Dozent.nameText,
					dozent.getName() + "");

			gerenderteDozenten.add(gerenderterDozent);
		}

		return gerenderteDozenten;
	}

}
