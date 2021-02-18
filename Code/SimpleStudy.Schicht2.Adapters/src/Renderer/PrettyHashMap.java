package Renderer;

import java.util.HashMap;

public class PrettyHashMap extends HashMap<String, String>
{

	@Override
	public String toString()
	{
		String idText = Models.Entity.idText;
		String ausgabe = "";
		ausgabe += this.get(idText);
		for (String key : (String[]) (this.keySet().toArray()))
		{

			if (key != idText)
			{
				ausgabe += ", " + this.get(key);
			}
		}

		return ausgabe;
	}

}
