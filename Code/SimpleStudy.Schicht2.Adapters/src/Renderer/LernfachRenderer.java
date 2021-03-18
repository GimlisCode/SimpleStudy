package Renderer;

import java.util.ArrayList;
import java.util.Collection;

import Models.Kapitel;
import Models.Lernfach;

public class LernfachRenderer
{

	public static ArrayList<PrettyHashMap> getLernfaecherForView(Collection<Lernfach> lernfaecher)
	{

		ArrayList<PrettyHashMap> gerenderteLernfaecher = new ArrayList<>();
		for (Lernfach lernfach : lernfaecher)
		{
			PrettyHashMap gerendertesLernfach = new PrettyHashMap();
			gerendertesLernfach.addVisible(Lernfach.idText,
					lernfach.getId() + "");
			gerendertesLernfach.addVisible(Lernfach.nameText,
					lernfach.getName() + "");
			gerendertesLernfach.addUnvisible(Lernfach.creditsText,
					lernfach.getCredits() + "");
			gerendertesLernfach.addUnvisible(Lernfach.semesterText,
					lernfach.getSemester() + "");
			String kapitels = "";
			for (Kapitel kapitel : lernfach.getLernkapitel())
			{
				kapitels += kapitel.getId() + ";";
			}

			gerendertesLernfach.addUnvisible(Lernfach.kapitelText,
					kapitels);

			gerenderteLernfaecher.add(gerendertesLernfach);
		}

		return gerenderteLernfaecher;
	}

}
