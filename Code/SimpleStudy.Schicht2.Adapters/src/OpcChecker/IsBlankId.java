package OpcChecker;

public class IsBlankId implements CheckedAspect
{

	@Override
	public boolean isPositiveFor(String id)
	{
		return id.isBlank();
	}

}
