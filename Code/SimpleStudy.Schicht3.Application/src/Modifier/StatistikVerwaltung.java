package Modifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import Models.Ergebnis;
import Models.Lernfach;
import Models.Richtigkeit;
import Models.Statistik;
import Models.Student;

public class StatistikVerwaltung {

	
	
	public StatistikVerwaltung() {
	
	}

	public static void update(Student currentStudent, Ergebnis ergebnis) {		
		Statistik currentStatistik = StudentenVerwaltung.get(currentStudent.getId()).getStatistik();
		
		for (Entry<Integer, Boolean> ergebnisEinerFrage: ergebnis.getErgebnis().entrySet()) {
			Richtigkeit richtigkeit = new Richtigkeit();
			if(currentStatistik.getStatistik().containsKey(ergebnisEinerFrage.getKey()))
				richtigkeit = currentStatistik.getStatistik().get(ergebnisEinerFrage.getKey());
				
			if (ergebnisEinerFrage.getValue())
				richtigkeit.addRichtig();
			else
				richtigkeit.addFalsch();
				
			currentStatistik.update(ergebnisEinerFrage.getKey(), richtigkeit);					
		}
		
	}
	
	
	
}
	

	

