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
import Controller.MainController;
import Models.Antwort;
import Modifier.AntwortFabrik;
import Modifier.AntwortVerwaltung;

public class DbControllerTest
{
	String antwortTextForTest = "gut";
	Boolean isKorrekteAntwortForTest = true;
	int antwortIdForTest = 13;
	String wrongIdNotNumeric = "wrongIdNotNumeric";
	String antwortTabelleForMock = Antwort.class.getSimpleName();

	// #Requirement: Create
	@Test
	public void properlyInitializeAnswerFromDb()
	{
		final DatenVerbindung databaseMock = EasyMock.createMock(DatenVerbindung.class);
		final var testAntwortAttributeOhneId = AntwortFabrik.getInstance()
				.getAntwortAttribute();
		testAntwortAttributeOhneId.put(Antwort.idText,
				antwortIdForTest + "");
		testAntwortAttributeOhneId.put(Antwort.textText,
				antwortTextForTest);
		testAntwortAttributeOhneId.put(Antwort.correctText,
				isKorrekteAntwortForTest.toString());
		final ArrayList<HashMap<String, String>> antwortAttributeListMock = new ArrayList<>();
		antwortAttributeListMock.add(testAntwortAttributeOhneId);
		EasyMock.expect(databaseMock.getAllFromTable(antwortTabelleForMock))
				.andReturn(antwortAttributeListMock);
		EasyMock.replay(databaseMock);

		final DbController dbController = new DbController(databaseMock);
		dbController.initializeAntwort();
		final var erzeugteAntwort = AntwortVerwaltung.getInstance()
				.get(antwortIdForTest);

		assertNotEquals(null,
				erzeugteAntwort);
		assertEquals(antwortIdForTest,
				erzeugteAntwort.getId());
		assertEquals(antwortTextForTest,
				erzeugteAntwort.getText());
		assertEquals(isKorrekteAntwortForTest,
				erzeugteAntwort.isCorrect());

	}

	// #Requirement: Create
	@Test
	public void properlyInitializeAnswerFromDbWithoutId()
	{
		final DatenVerbindung databaseMock = EasyMock.createMock(DatenVerbindung.class);
		final var testAntwortAttributeOhneId = AntwortFabrik.getInstance()
				.getAntwortAttribute();
		testAntwortAttributeOhneId.put(Antwort.textText,
				antwortTextForTest);
		testAntwortAttributeOhneId.put(Antwort.correctText,
				isKorrekteAntwortForTest.toString());
		final ArrayList<HashMap<String, String>> antwortAttributeListeMock = new ArrayList<>();
		antwortAttributeListeMock.add(testAntwortAttributeOhneId);
		EasyMock.expect(databaseMock.getAllFromTable(antwortTabelleForMock))
				.andReturn(antwortAttributeListeMock);
		EasyMock.replay(databaseMock);
		final int expectedIdForNewAntwort = MainController.getInstance()
				.getNewIdFor(Antwort.class.getSimpleName());

		final DbController zuTestendenDbController = new DbController(databaseMock);
		zuTestendenDbController.initializeAntwort();
		final var erzeugteAntwort = AntwortVerwaltung.getInstance()
				.get(expectedIdForNewAntwort);

		assertNotEquals(null,
				erzeugteAntwort);
		assertEquals(expectedIdForNewAntwort,
				erzeugteAntwort.getId());
		assertEquals(antwortTextForTest,
				erzeugteAntwort.getText());
		assertEquals(isKorrekteAntwortForTest,
				erzeugteAntwort.isCorrect());

	}

	// #Requirement: Create
	@Test
	public void properlyInitializeAnswerFromDbWithWrongNotNumericUi()
	{
		final DatenVerbindung databaseMock = EasyMock.createMock(DatenVerbindung.class);
		final var testAntwortAttributeOhneId = AntwortFabrik.getInstance()
				.getAntwortAttribute();
		testAntwortAttributeOhneId.put(Antwort.idText,
				wrongIdNotNumeric);
		testAntwortAttributeOhneId.put(Antwort.textText,
				antwortTextForTest);
		testAntwortAttributeOhneId.put(Antwort.correctText,
				isKorrekteAntwortForTest.toString());
		final ArrayList<HashMap<String, String>> antwortAttributeListeMock = new ArrayList<>();
		antwortAttributeListeMock.add(testAntwortAttributeOhneId);
		EasyMock.expect(databaseMock.getAllFromTable(antwortTabelleForMock))
				.andReturn(antwortAttributeListeMock);
		EasyMock.replay(databaseMock);
		final int expectedIdForNewAntwort = MainController.getInstance()
				.getNewIdFor(Antwort.class.getSimpleName());

		final DbController zuTestendenDbController = new DbController(databaseMock);
		zuTestendenDbController.initializeAntwort();
		final var erzeugteAntwort = AntwortVerwaltung.getInstance()
				.get(expectedIdForNewAntwort);

		assertNotEquals(null,
				erzeugteAntwort);
		assertEquals(expectedIdForNewAntwort,
				erzeugteAntwort.getId());
		assertEquals(antwortTextForTest,
				erzeugteAntwort.getText());
		assertEquals(isKorrekteAntwortForTest,
				erzeugteAntwort.isCorrect());

	}

	@After
	public void deleteCreatedAntwortForNewTest()
	{
		final var tempAntworten = AntwortVerwaltung.getInstance()
				.getAll();
		for (final var tempAntwort : tempAntworten.entrySet())
			AntwortVerwaltung.getInstance()
					.remove(tempAntwort.getValue());
	}
}
