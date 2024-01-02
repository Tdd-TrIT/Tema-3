package com.sergiotrapiello.cursotesting.basico;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class TextNormalizerTest {

	private TextNormalizer normalizer;

	@BeforeEach
	void setup() {
		normalizer = new TextNormalizer();
	}

	@ParameterizedTest
	@CsvSource({ "patatá, PATATA", "AQUello está allí . áéíóú, AQUELLO ESTA ALLI . AEIOU",
			"Sergio García Trapiello, SERGIO GARCIA TRAPIELLO", "'Sergio  , García', 'SERGIO  , GARCIA'", "'   ', '',",
			"' á  ', 'A'", "null, NULL" })
	void shouldNormalizeText(String textToNormalize, String expectedResult) {

		// WHEN
		String actualResult = normalizer.normalizeUppercase(textToNormalize);

		// THEN
		assertEquals(expectedResult, actualResult);
	}

	@ParameterizedTest
	@NullAndEmptySource
	void shouldNormalizeText_nullAndEmpty(String text) {

		// GIVEN

		// WHEN
		String actualResult = normalizer.normalizeUppercase(text);

		// THEN
		assertEquals("", actualResult);
	}

	// ///////////////////////////////////////////////////////////////////////
	// ============ EJEMPLOS ANTERIORES A TESTS PARAMETRIZADOS ================
	// ///////////////////////////////////////////////////////////////////////

	@Test
	void shouldNormalizeText() {

		// GIVEN
		String textToNormalize = "  AQUello está allí . áéíóú  ";

		// WHEN
		String actualResult = normalizer.normalizeUppercase(textToNormalize);

		// THEN
		assertEquals("AQUELLO ESTA ALLI . AEIOU", actualResult);
	}

	@Test
	void shouldNormalizeText_empty() {

		// GIVEN
		String emptyText = "";

		// WHEN
		String actualResult = normalizer.normalizeUppercase(emptyText);

		// THEN
		assertEquals("", actualResult);
	}

	@Test
	void shouldNormalizeText_null() {

		// GIVEN
		String nullText = null;

		// WHEN
		String actualResult = normalizer.normalizeUppercase(nullText);

		// THEN
		assertEquals("", actualResult);
	}
}
