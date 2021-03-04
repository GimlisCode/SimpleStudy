package Renderer;

import java.util.ArrayList;
import java.util.Collection;

import Models.Hochschule;

public class HochschulRenderer
{

	public static ArrayList<PrettyHashMap> getHochschuleForView(Collection<Hochschule> hochschulen)
	{

		ArrayList<PrettyHashMap> gerenderteHochschulen = new ArrayList<>();
		for (Hochschule hochschule : hochschulen)
		{
			PrettyHashMap gerenderteHochschule = new PrettyHashMap();
			gerenderteHochschule.put(Hochschule.idText,
					hochschule.getId() + "");
			gerenderteHochschule.put(Hochschule.nameText,
					hochschule.getName());

			gerenderteHochschulen.add(gerenderteHochschule);
		}

		return gerenderteHochschulen;
	}

}
