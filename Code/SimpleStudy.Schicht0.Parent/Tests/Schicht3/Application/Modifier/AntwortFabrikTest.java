package Schicht3.Application.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.After;
import org.junit.Test;

import Models.Antwort;
import Modifier.AntwortFabrik;
import Modifier.AntwortVerwaltung;

public class AntwortFabrikTest
{
	String antwortText = "gut";
	Boolean isKorrekteAntwort = true;
	int isKorrekteAntwortDbLanguage = 1;
	int antwortId = 13;

	// #Requirement: Create
	@Test
	public void getCorrectAntwortAttributesFromAntwortFabrik()
	{
		final var antwortAttribute = AntwortFabrik.getAntwortAttribute();
		final var antwortAttributeKeys = antwortAttribute.keySet();

		assertEquals(3,
				antwortAttributeKeys.size());
		assertEquals(true,
				antwortAttributeKeys.contains(Antwort.idText));
		assertEquals(true,
				antwortAttributeKeys.contains(Antwort.correctText));
		assertEquals(true,
				antwortAttributeKeys.contains(Antwort.textText));
	}

	// #Requirement: Create
	// #Regression: f6a945f
	@Test
	public void createAntwortWithProperValues()
	{
		final var antwortAttribute = AntwortFabrik.getAntwortAttribute();
		antwortAttribute.put(Antwort.idText,
				antwortId + "");
		antwortAttribute.put(Antwort.textText,
				antwortText);
		antwortAttribute.put(Antwort.correctText,
				isKorrekteAntwort.toString());

		AntwortFabrik.create(antwortAttribute);
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

	// #Requirement: Create
	@Test
	public void createAntwortWithProperDbValues()
	{
		final var antwortAttribute = AntwortFabrik.getAntwortAttribute();
		antwortAttribute.put(Antwort.idText,
				antwortId + "");
		antwortAttribute.put(Antwort.textText,
				antwortText);
		antwortAttribute.put(Antwort.correctText,
				isKorrekteAntwortDbLanguage + "");

		AntwortFabrik.create(antwortAttribute);
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

	// #Requirement: Create
	@Test
	public void createAntwortWithMissingCorrectValue()
	{
		final var antwortAttribute = AntwortFabrik.getAntwortAttribute();
		antwortAttribute.put(Antwort.idText,
				antwortId + "");
		antwortAttribute.put(Antwort.textText,
				antwortText);
		final Boolean defaultAnswerCorrectness = false;

		AntwortFabrik.create(antwortAttribute);
		final var erzeugteAntwort = AntwortVerwaltung.getInstance()
				.get(antwortId);

		assertNotEquals(null,
				erzeugteAntwort);
		assertEquals(antwortId,
				erzeugteAntwort.getId());
		assertEquals(antwortText,
				erzeugteAntwort.getText());
		assertEquals(defaultAnswerCorrectness,
				erzeugteAntwort.isCorrect());
	}

	// #Requirement: Create
	@Test
	public void createAntwortWithMissingCorrectAndTextValue()
	{
		final var antwortAttribute = AntwortFabrik.getAntwortAttribute();
		antwortAttribute.put(Antwort.idText,
				antwortId + "");
		final String defaultAnswerText = "";
		final Boolean defaultAnswerCorrectness = false;

		AntwortFabrik.create(antwortAttribute);
		final var erzeugteAntwort = AntwortVerwaltung.getInstance()
				.get(antwortId);

		assertNotEquals(null,
				erzeugteAntwort);
		assertEquals(antwortId,
				erzeugteAntwort.getId());
		assertEquals(defaultAnswerText,
				erzeugteAntwort.getText());
		assertEquals(defaultAnswerCorrectness,
				erzeugteAntwort.isCorrect());
	}

	// #Requirement: Create
	// #Regression: 6c4480b
	@Test
	public void createAntwortWithMissingCorrectAndTextAndIdValue()
	{
		final var antwortAttribute = AntwortFabrik.getAntwortAttribute();

		AntwortFabrik.create(antwortAttribute);
		final var erzeugteAntwort = AntwortVerwaltung.getInstance()
				.get(antwortId);

		assertEquals(null,
				erzeugteAntwort);
	}

	@After
	public void deleteCreatedAntwortForNewTest()
	{
		AntwortVerwaltung.getInstance()
				.remove(antwortId);
	}

}
