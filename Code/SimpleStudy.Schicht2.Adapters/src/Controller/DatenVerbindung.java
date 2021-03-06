package Controller;

import java.util.ArrayList;
import java.util.HashMap;

public interface DatenVerbindung
{
	ArrayList<HashMap<String, String>> getAllFromTable(String tableName);

	ArrayList<HashMap<String, String>> getResultFromQuerry(String sqlQuerry);

	DatenVerbindung createSelectString(String[] columns, String tableName, String option);

	DatenVerbindung createSelectString(String[] columns, String tableName);

	DatenVerbindung join(String[] tableName, JoinType joinyType);

	DatenVerbindung where(String tableLeft, String columnLeft, String operator, String tableRight, String columnRight);

	DatenVerbindung on(String tableLeft, String columnLeft, String operator, String tableRight, String columnRight);

	DatenVerbindung groupBy(String[] columnName);

	DatenVerbindung and();

	String build();
}
