package Renderer;

import java.util.ArrayList;
import java.util.HashMap;

public class PrettyHashMap
{

	public HashMap<String, String> visible = new HashMap<>();
	public HashMap<String, String> unvisible = new HashMap<>();

	@Override
	public String toString()
	{
		String idText = Models.Entity.idText;
		String ausgabe = "";
		ausgabe += this.visible.get(idText);

		ArrayList<String> keys = new ArrayList<>(this.visible.keySet());

		for (String key : keys)
		{

			if (key != idText)
			{
				ausgabe += ", " + this.visible.get(key);
			}
		}

		return ausgabe;
	}

	public void addVisible(String key, String value)
	{
		this.visible.put(key,
				value);
	}

	public void removeVisible(String key)
	{
		this.visible.remove(key);
	}

	public void addUnvisible(String key, String value)
	{
		this.unvisible.put(key,
				value);
	}

	public void removeUnvisible(String key)
	{
		this.unvisible.remove(key);
	}

	public HashMap<String, String> getNormalHashMap()
	{
		HashMap<String, String> hashMap = new HashMap<>();
		for (var element : visible.entrySet())
		{
			hashMap.put(element.getKey(),
					element.getValue());
		}
		for (var element : unvisible.entrySet())
		{
			hashMap.put(element.getKey(),
					element.getValue());
		}

		return hashMap;
	}

}
