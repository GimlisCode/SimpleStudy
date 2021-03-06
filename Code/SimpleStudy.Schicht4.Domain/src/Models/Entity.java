package Models;

import java.util.HashMap;

public abstract class Entity
{

	protected int id;
	public static final String idText = "id";

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	abstract HashMap<String, Object> getDetails();

	public String[] getAttributeNames()
	{
		return getDetails().keySet().toArray(new String[getDetails().keySet().size()]);
	}

}
