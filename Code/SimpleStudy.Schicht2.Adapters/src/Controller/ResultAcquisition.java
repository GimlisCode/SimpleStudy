package Controller;

import java.util.ArrayList;
import java.util.HashMap;

public interface ResultAcquisition
{
	ArrayList<HashMap<String, String>> getAllFromTable(String tableName);

	ArrayList<HashMap<String, String>> getResultFromQuerry(String sqlQuerry);
}
