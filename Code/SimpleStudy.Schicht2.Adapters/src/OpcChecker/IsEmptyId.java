package OpcChecker;

public class IsEmptyId implements CheckedAspect
{

	@Override
	public boolean isPositiveFor(String id)
	{
		return id.isEmpty();
	}

}
