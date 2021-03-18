package Renderer;

import java.util.ArrayList;
import java.util.Collection;

import Models.Antwort;
import Models.Frage;

public class FragenRenderer
{

	public static ArrayList<PrettyHashMap> getFragenForView(Collection<Frage> fragen)
	{

		ArrayList<PrettyHashMap> gerenderteFragen = new ArrayList<>();
		for (Frage frage : fragen)
		{
			PrettyHashMap gerenderteFrage = new PrettyHashMap();
			gerenderteFrage.addVisible(Frage.idText,
					frage.getId() + "");
			gerenderteFrage.addVisible(Frage.textText,
					frage.getText() + "");
			gerenderteFrage.addUnvisible(Frage.typText,
					frage.getTyp() + "");
			String antworten = "";
			for (Antwort antwort : frage.getAntworten())
			{
				antworten += antwort.getId() + ";";
			}
			gerenderteFrage.addUnvisible(Frage.antwortenText,
					antworten);
			gerenderteFragen.add(gerenderteFrage);
		}

		return gerenderteFragen;
	}

}
