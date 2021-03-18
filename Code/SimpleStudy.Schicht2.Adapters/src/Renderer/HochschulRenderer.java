package Renderer;

import java.util.ArrayList;
import java.util.Collection;

import Models.Dozent;
import Models.Hochschule;

public class HochschulRenderer
{

	public static ArrayList<PrettyHashMap> getHochschuleForView(Collection<Hochschule> hochschulen)
	{

		ArrayList<PrettyHashMap> gerenderteHochschulen = new ArrayList<>();
		for (Hochschule hochschule : hochschulen)
		{
			PrettyHashMap gerenderteHochschule = new PrettyHashMap();
			gerenderteHochschule.addVisible(Hochschule.idText,
					hochschule.getId() + "");
			gerenderteHochschule.addVisible(Hochschule.nameText,
					hochschule.getName());
			String dozenten = "";
			for (Dozent dozent : hochschule.getDozenten())
			{
				dozenten += dozent.getId() + ";";
			}
			gerenderteHochschule.addUnvisible(Hochschule.dozentenText,
					dozenten);

			gerenderteHochschulen.add(gerenderteHochschule);
		}

		return gerenderteHochschulen;
	}

}
