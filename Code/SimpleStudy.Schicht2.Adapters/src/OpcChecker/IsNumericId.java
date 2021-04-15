package OpcChecker;

public class IsNumericId implements CheckedAspect
{

	@Override
	public boolean isPositiveFor(String id)
	{
		return !id.chars()
				.allMatch(Character::isDigit);
	}

}
