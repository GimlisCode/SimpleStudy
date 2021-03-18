package Renderer;

import java.util.ArrayList;
import java.util.Collection;

import Models.Dozent;
import Models.Lernfach;

public class DozentenRenderer
{

	public static ArrayList<PrettyHashMap> getDozentenForView(Collection<Dozent> dozenten)
	{

		ArrayList<PrettyHashMap> gerenderteDozenten = new ArrayList<>();
		for (Dozent dozent : dozenten)
		{
			PrettyHashMap gerenderterDozent = new PrettyHashMap();
			gerenderterDozent.addVisible(Dozent.idText,
					dozent.getId() + "");
			gerenderterDozent.addVisible(Dozent.nameText,
					dozent.getName() + "");
			String kurse = "";
			for (Lernfach lernfach : dozent.getKurse())
			{
				kurse += lernfach.getId() + ";";
			}

			gerenderterDozent.addUnvisible(Dozent.kurseText,
					kurse);

			gerenderteDozenten.add(gerenderterDozent);
		}

		return gerenderteDozenten;
	}

}
