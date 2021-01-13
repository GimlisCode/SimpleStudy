package Models;

import java.util.HashMap;
import java.util.Map;



public class Statistik {
	
	
	//DOKU: Tupel ersetzt durch Wrapper
	private Map<Integer, Richtigkeit> statistik = new HashMap<>();

	public Map<Integer, Richtigkeit> getStatistik() {
		return statistik;
	}

	public void setStatistik(Map<Integer, Richtigkeit> statistik) {
		this.statistik = statistik;
	}
	

	public void add(Integer frageId, Richtigkeit richtigkeit)
	{		
		statistik.put(frageId, richtigkeit);
	}
	
	public void remove(Integer frageId, Boolean richtigkeit)
	{		
		if (statistik.containsKey(frageId)) 
			statistik.remove(frageId);
	}


}
