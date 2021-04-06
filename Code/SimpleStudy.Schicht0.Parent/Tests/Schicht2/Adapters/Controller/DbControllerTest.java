package Schicht2.Adapters.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Test;

import Controller.DatenVerbindung;
import Controller.DbController;
import Models.Antwort;
import Modifier.AntwortFabrik;
import Modifier.AntwortVerwaltung;

public class DbControllerTest
{
	String antwortText = "gut";
	Boolean isKorrekteAntwort = true;
	int antwortId = 13;
	String antwortTabelle = Antwort.class.getSimpleName();

	@Test
	public void getCorrectAntwortAttributesFromAntwortFabrik()
	{
		final DatenVerbindung database = EasyMock.createMock(DatenVerbindung.class);
		final var antwortAttribute = AntwortFabrik.getAntwortAttribute();
		antwortAttribute.put(Antwort.idText,
				antwortId + "");
		antwortAttribute.put(Antwort.textText,
				antwortText);
		antwortAttribute.put(Antwort.correctText,
				isKorrekteAntwort.toString());
		final ArrayList<HashMap<String, String>> antwortAttributeList = new ArrayList<>();
		antwortAttributeList.add(antwortAttribute);
		EasyMock.expect(database.getAllFromTable(antwortTabelle))
				.andReturn(antwortAttributeList);
		EasyMock.replay(database);

		final DbController dbController = new DbController(database);
		dbController.initializeAntwort();
		final var erzeugteAntwort = AntwortVerwaltung.getInstance()
				.get(antwortId);

		assertNotEquals(null,
				erzeugteAntwort);
		assertEquals(antwortId,
				erzeugteAntwort.getId());
		assertEquals(antwortText,
				erzeugteAntwort.getText());
		assertEquals(isKorrekteAntwort,
				erzeugteAntwort.isCorrect());

	}

	@After
	public void deleteCreatedAntwortForNewTest()
	{
		AntwortVerwaltung.getInstance()
				.remove(antwortId);
	}
}
