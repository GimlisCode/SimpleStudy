package OpcChecker;

public class IsNullId implements CheckedAspect
{

	@Override
	public boolean isPositiveFor(String id)
	{
		if (id == null)
			return true;

		return false;
	}

}
