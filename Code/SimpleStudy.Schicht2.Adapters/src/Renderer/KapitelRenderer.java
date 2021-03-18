package Renderer;

import java.util.ArrayList;
import java.util.Collection;

import Models.Frage;
import Models.Kapitel;

public class KapitelRenderer
{

	public static ArrayList<PrettyHashMap> getKapitelForView(Collection<Kapitel> kapitels)
	{

		ArrayList<PrettyHashMap> gerenderteKapitel = new ArrayList<>();
		for (Kapitel kapitel : kapitels)
		{
			PrettyHashMap gerendertesKapitel = new PrettyHashMap();
			gerendertesKapitel.addVisible(Kapitel.idText,
					kapitel.getId() + "");
			gerendertesKapitel.addVisible(Kapitel.nameText,
					kapitel.getName() + "");
			gerendertesKapitel.addVisible(Kapitel.nrText,
					"(" + kapitel.getNr() + ")");
			String fragen = "";
			for (Frage frage : kapitel.getFragen())
			{
				fragen += frage.getId() + ";";
			}
			gerendertesKapitel.addUnvisible(Kapitel.fragenText,
					null);

			gerenderteKapitel.add(gerendertesKapitel);
		}

		return gerenderteKapitel;
	}

}
