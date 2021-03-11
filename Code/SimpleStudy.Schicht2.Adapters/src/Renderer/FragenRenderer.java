package Renderer;

import java.util.ArrayList;
import java.util.Collection;

import Models.Frage;

public class FragenRenderer
{

	public static ArrayList<PrettyHashMap> getFragenForView(Collection<Frage> fragen)
	{

		ArrayList<PrettyHashMap> gerenderteFragen = new ArrayList<>();
		for (Frage frage : fragen)
		{
			PrettyHashMap gerenderteFrage = new PrettyHashMap();
			gerenderteFrage.put(Frage.idText,
					frage.getId() + "");
			gerenderteFrage.put(Frage.textText,
					frage.getText() + "");

			gerenderteFragen.add(gerenderteFrage);
		}

		return gerenderteFragen;
	}

}
