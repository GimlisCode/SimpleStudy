package Renderer;

import java.util.ArrayList;
import java.util.Collection;

import Models.Lernfach;

public class LernfachRenderer
{

	public static ArrayList<PrettyHashMap> getLernfaecherForView(Collection<Lernfach> lernfaecher)
	{

		ArrayList<PrettyHashMap> gerenderteLernfaecher = new ArrayList<>();
		for (Lernfach lernfach : lernfaecher)
		{
			PrettyHashMap gerendertesLernfach = new PrettyHashMap();
			gerendertesLernfach.put(Lernfach.idText,
					lernfach.getId() + "");
			gerendertesLernfach.put(Lernfach.nameText,
					lernfach.getName() + "");

			gerenderteLernfaecher.add(gerendertesLernfach);
		}

		return gerenderteLernfaecher;
	}

}
