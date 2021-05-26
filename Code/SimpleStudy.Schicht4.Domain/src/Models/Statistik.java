package Models;

import java.util.HashMap;
import java.util.Map;

public class Statistik extends Entity
{

	//Tupel ersetzt durch Wrapper
	private Map<Integer, Richtigkeit> statistik = new HashMap<>();
	public final static String statistikText = "richtigkeiten";

	public Map<Integer, Richtigkeit> getStatistik()
	{
		return statistik;
	}

	public void setStatistik(Map<Integer, Richtigkeit> statistik)
	{
		this.statistik = statistik;
	}

	public void addToStatistik(Integer frageId, Richtigkeit richtigkeit)
	{
		statistik.put(frageId, richtigkeit);
	}

	public void removeFromStatistik(Integer frageId)
	{
		if (statistik.containsKey(frageId))
			statistik.remove(frageId);
	}

	public void updateStatistik(Integer frageId, Richtigkeit richtigkeit)
	{
		statistik.put(frageId, richtigkeit);
	}

	@Override
	HashMap<String, Object> getDetails()
	{
		HashMap<String, Object> details = new HashMap<String, Object>();
		details.put(idText, this.id);
		details.put(statistikText, this.statistik);

		return details;
	}

}
