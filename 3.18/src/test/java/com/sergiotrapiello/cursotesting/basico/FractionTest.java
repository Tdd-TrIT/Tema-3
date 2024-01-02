package com.sergiotrapiello.cursotesting.basico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.fail;

import org.apache.commons.lang3.math.Fraction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class FractionTest {

	@Test
	void shouldGetFractionFromString() {

		// GIVEN
		String fractionAsText = "3/4";
		double expectedDoubleValue = 0.75d;

		// WHEN
		Fraction fraction = Fraction.getFraction(fractionAsText);

		// THEN
		assertEquals(expectedDoubleValue, fraction.doubleValue(), "The created fraction is not as expected. ");
	}
	
	@Test
	void shouldFailGettingFractionFromString_wrongNumberFormat_tryCatchIdiom() {

		// GIVEN
		String wrongNumber = "asdfaf";

		// WHEN
		try {
			Fraction.getFraction(wrongNumber);
			
			// THEN
			fail("An exception should be thrown");
		}catch(NumberFormatException e) {
			assertExceptionMessage(e, "For input string: \"" + wrongNumber + "\"");
		}
	}

	
	@Test
	void shouldFailGettingFractionFromString_wrongNumberFormat_assertThrowsIdiom() {

		// GIVEN
		String wrongNumber = "asdfaf";

		// WHEN
		Executable executable = () -> Fraction.getFraction(wrongNumber);

		// THEN
		NumberFormatException e = assertThrowsExactly(NumberFormatException.class, executable);
		assertExceptionMessage(e, "For input string: \"" + wrongNumber + "\"");
	}
	
	@Test
	void shouldFailGettingFraction_denominatorIsZero_tryCatchIdiom() {

		// GIVEN
		int numerator = 7;
		int denominator = 0;

		// WHEN
		try {
			Fraction.getFraction(numerator, denominator);
			
			// THEN
			fail("An exception should be thrown");
		} catch (ArithmeticException e) {
			assertExceptionMessage(e, "The denominator must not be zero");
		}
	}
	
	@Test
	void shouldFailGettingFraction_denominatorIsZero_assertThrowsIdiom() {

		// GIVEN
		int numerator = 7;
		int denominator = 0;

		// WHEN
		Executable executable = () -> Fraction.getFraction(numerator, denominator);

		// THEN
		ArithmeticException e = assertThrows(ArithmeticException.class, executable);
		assertExceptionMessage(e, "The denominator must not be zero");
	}
	
	private void assertExceptionMessage(Exception e, String expectedMsg) {
		assertEquals(expectedMsg, e.getMessage(),
				"The message of the exception is not what expected. ");
	}
	
}
