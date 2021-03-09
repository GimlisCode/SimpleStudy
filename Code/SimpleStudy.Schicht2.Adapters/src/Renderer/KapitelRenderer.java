package Renderer;

import java.util.ArrayList;
import java.util.Collection;

import Models.Kapitel;

public class KapitelRenderer
{

	public static ArrayList<PrettyHashMap> getKapitelForView(Collection<Kapitel> kapitels)
	{

		ArrayList<PrettyHashMap> gerenderteKapitel = new ArrayList<>();
		for (Kapitel kapitel : kapitels)
		{
			PrettyHashMap gerendertesKapitel = new PrettyHashMap();
			gerendertesKapitel.put(Kapitel.idText,
					kapitel.getId() + "");
			gerendertesKapitel.put(Kapitel.nameText,
					kapitel.getName() + "");
			gerendertesKapitel.put(Kapitel.nrText,
					"(" + kapitel.getNr() + ")");

			gerenderteKapitel.add(gerendertesKapitel);
		}

		return gerenderteKapitel;
	}

}
