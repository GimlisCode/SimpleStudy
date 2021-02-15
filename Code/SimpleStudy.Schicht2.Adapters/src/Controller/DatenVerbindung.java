package Controller;

import java.util.ArrayList;
import java.util.HashMap;

public interface DatenVerbindung
{
	ArrayList<HashMap<String, String>> getAllFromTable(String tableName);
}
