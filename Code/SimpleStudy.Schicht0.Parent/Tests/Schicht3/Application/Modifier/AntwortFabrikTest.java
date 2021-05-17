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
	Boolean isKorrekteAntwortNegiert = false;
	int isKorrekteAntwortDbLanguage = 1;
	int antwortId = 13;

	// #Requirement: Create
	@Test
	public void getCorrectAntwortAttributesFromAntwortFabrik()
	{
		final var antwortAttribute = AntwortFabrik.getInstance()
				.getAntwortAttribute();
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
		final var antwortAttribute = AntwortFabrik.getInstance()
				.getAntwortAttribute();
		antwortAttribute.put(Antwort.idText,
				antwortId + "");
		antwortAttribute.put(Antwort.textText,
				antwortText);
		antwortAttribute.put(Antwort.correctText,
				isKorrekteAntwort.toString());

		AntwortFabrik.getInstance()
				.create(antwortAttribute);
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

	// #Bugfix: 665c9a4
	@Test
	public void createAntwortWithProperDbValues()
	{
		final var antwortAttribute = AntwortFabrik.getInstance()
				.getAntwortAttribute();
		antwortAttribute.put(Antwort.idText,
				antwortId + "");
		antwortAttribute.put(Antwort.textText,
				antwortText);
		antwortAttribute.put(Antwort.correctText,
				isKorrekteAntwortDbLanguage + "");

		AntwortFabrik.getInstance()
				.create(antwortAttribute);
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

	// #Bugfix: 665c9a4
	@Test
	public void createAntwortWithProperDbValuesNegierteKorrektheit()
	{
		final var antwortAttribute = AntwortFabrik.getInstance()
				.getAntwortAttribute();
		antwortAttribute.put(Antwort.idText,
				antwortId + "");
		antwortAttribute.put(Antwort.textText,
				antwortText);
		antwortAttribute.put(Antwort.correctText,
				isKorrekteAntwortDbLanguage - 1 + "");

		AntwortFabrik.getInstance()
				.create(antwortAttribute);
		final var erzeugteAntwort = AntwortVerwaltung.getInstance()
				.get(antwortId);

		assertNotEquals(null,
				erzeugteAntwort);
		assertEquals(antwortId,
				erzeugteAntwort.getId());
		assertEquals(antwortText,
				erzeugteAntwort.getText());
		assertEquals(isKorrekteAntwortNegiert,
				erzeugteAntwort.isCorrect());
	}

	// #Requirement: Create
	@Test
	public void createAntwortWithMissingCorrectValue()
	{
		final var antwortAttribute = AntwortFabrik.getInstance()
				.getAntwortAttribute();
		antwortAttribute.put(Antwort.idText,
				antwortId + "");
		antwortAttribute.put(Antwort.textText,
				antwortText);
		final Boolean defaultAnswerCorrectness = false;

		AntwortFabrik.getInstance()
				.create(antwortAttribute);
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
		final var antwortAttribute = AntwortFabrik.getInstance()
				.getAntwortAttribute();
		antwortAttribute.put(Antwort.idText,
				antwortId + "");
		final String defaultAnswerText = "";
		final Boolean defaultAnswerCorrectness = false;

		AntwortFabrik.getInstance()
				.create(antwortAttribute);
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
		final var antwortAttribute = AntwortFabrik.getInstance()
				.getAntwortAttribute();

		AntwortFabrik.getInstance()
				.create(antwortAttribute);
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
