package Models;

import java.util.HashMap;
import java.util.Map;

public class Ergebnis {
	
	private Map<Integer, Boolean> ergebnis = new HashMap<>();

	public Map<Integer, Boolean> getErgebnis() {
		return ergebnis;
	}

	public void setErgebnis(Map<Integer, Boolean> ergebnis) {
		this.ergebnis = ergebnis;
	}

	public void add(Integer frageId, Boolean richtigkeit)
	{		
		ergebnis.put(frageId, richtigkeit);
	}
	
	public void remove(Integer frageId, Boolean richtigkeit)
	{		
		if (ergebnis.containsKey(frageId)) 
			ergebnis.remove(frageId);
	}
	
	
}
