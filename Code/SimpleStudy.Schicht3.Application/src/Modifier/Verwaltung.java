package Modifier;

import Models.Entity;
import java.util.HashMap;


abstract class Verwaltung {
	
	HashMap<Integer,Entity> entities;
	
	public Verwaltung() {
		entities = new HashMap<>();
	}
	
	public void add(Entity entity) {
		entities.put(entity.getId(), entity);
	}
	
	public void update(Entity entity) {
	}
	
	abstract Entity get(int id);
	abstract void delete(int id);
	
}