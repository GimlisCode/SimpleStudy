package Schicht4.Domain.Models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Models.Antwort;

public class AntwortTest
{
	@Test
	public void antwortChangeCorrectnessFromFalseToTrue()
	{
		final String antowrtText = "TestText";
		final Antwort originalAntwort = new Antwort(antowrtText, false);

		originalAntwort.setCorrect(true);

		assertEquals(originalAntwort.isCorrect(),
				true);
		assertEquals(originalAntwort.getText(),
				antowrtText);
	}

	@Test
	public void antwortChangeCorrectnessFromTrueToTrue()
	{
		final String antowrtText = "TestText";
		final Antwort originalAntwort = new Antwort(antowrtText, true);

		originalAntwort.setCorrect(true);

		assertEquals(originalAntwort.isCorrect(),
				true);
		assertEquals(originalAntwort.getText(),
				antowrtText);
	}

	@Test
	public void antwortChangeCorrectnessFromTrueToFalse()
	{
		final String antowrtText = "TestText";
		final Antwort originalAntwort = new Antwort(antowrtText, true);

		originalAntwort.setCorrect(false);

		assertEquals(originalAntwort.isCorrect(),
				false);
		assertEquals(originalAntwort.getText(),
				antowrtText);
	}

}
