package Controller;

public enum JoinType
{

	Right("Right Join", 1), Left("Left Join", 2), Natural("Join", 3);

	private int id;
	private String name;

	JoinType(String name, int id)
	{
		this.id = id;
		this.name = name;
	}

	public static JoinType getEnumOfId(int id)
	{
		for (final JoinType abfragesystem : values())
			if (abfragesystem.id == id)
				return abfragesystem;

		return null;
	}

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

}