package Renderer;

import java.util.ArrayList;
import java.util.HashMap;

public class PrettyHashMap extends HashMap<String, String>
{

	@Override
	public String toString()
	{
		String idText = Models.Entity.idText;
		String ausgabe = "";
		ausgabe += this.get(idText);

		ArrayList<String> keys = new ArrayList<>(this.keySet());

		for (String key : keys)
		{

			if (key != idText)
			{
				ausgabe += ", " + this.get(key);
			}
		}

		return ausgabe;
	}

}
