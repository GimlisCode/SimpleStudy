package OpcChecker;

import java.util.ArrayList;
import java.util.List;

public class IdChecker
{
	private List<CheckedAspect> checks = null;

	public IdChecker()
	{
		super();
		checks = new ArrayList<CheckedAspect>();
	}

	public boolean isValid(String id)
	{
		for (final CheckedAspect check : checks)
			if (check.isPositiveFor(id))
				return false;

		return true;
	}

	public void register(CheckedAspect checkAspect)
	{
		checks.add(checkAspect);
	}

}
