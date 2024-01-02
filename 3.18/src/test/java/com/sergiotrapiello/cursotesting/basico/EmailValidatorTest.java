package com.sergiotrapiello.cursotesting.basico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class EmailValidatorTest {

	private EmailValidator validator;

	@BeforeEach
	void setup() {
		validator = new EmailValidator();
	}

	@ParameterizedTest
	@ValueSource(strings = { "usuario@email.com", "a@email.com", "usuario@email.es.com", "a.b@bcd.es" })
	void shouldValidateEmail(String email) {

		// WHEN
		boolean valid = validator.isValid(email);

		// THEN
		assertValid(valid);
	}

	@ParameterizedTest
	@ValueSource(strings = { "", "   ", "usuarioemail.com", "a@b.com", "dsaf", " sdafd " })
	void shouldNotValidateEmail(String email) {

		// WHEN
		boolean valid = validator.isValid(email);

		// THEN
		assertNotValid(valid);
	}

	@ParameterizedTest
	@MethodSource("shouldValidateEmailArguments")
	void shouldValidateEmail(String email, boolean expectedResult) {

		// WHEN
		boolean result = validator.isValid(email);

		// THEN
		assertEquals(expectedResult, result);
	}

	private static Stream<Arguments> shouldValidateEmailArguments() {
		return Stream.of(Arguments.of("usuario@email.com", true), Arguments.of("a@email.com", true),
				Arguments.of("a@b.com", false), Arguments.of("   ", false));
	}

	@Test
	@Disabled // este test lo metimos en la clase "3.7 Ejercicio probar EmailValidator.
	// Solución", pero lo deshabilito para el ejercicio de lanzamiento de
	// excepciones. Ya que ambos comportamientos son incompatibles
	void shouldNotValidateEmail_null() {

		// GIVEN
		String nullEmail = null;

		// WHEN
		boolean valid = validator.isValid(nullEmail);

		// THEN
		assertNotValid(valid);
	}

	@Test
	void shouldThrowIllegalArgumentException_emailNull_tryCatchIdiom() {

		// GIVEN
		String emailNull = null;

		// WHEN
		try {
			validator.isValid(emailNull);

			// THEN
			fail("Should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertPreconditionMessage(e);
		}
	}

	@Test
	void shouldThrowIllegalArgumentException_emailNull_assertThrowsMethod() {

		// GIVEN
		String emailNull = null;

		// WHEN
		Executable executable = () -> validator.isValid(emailNull);

		// THEN
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class, executable);
		assertPreconditionMessage(e);
	}

	private void assertPreconditionMessage(IllegalArgumentException e) {
		assertEquals("The email to validate cannot be null", e.getMessage(),
				"The message of the exception is not the expected");
	}

	private void assertNotValid(boolean valid) {
		assertFalse(valid, "The email should NOT be valid");
	}

	private void assertValid(boolean valid) {
		assertTrue(valid, "The email should be valid");
	}

	// ///////////////////////////////////////////////////////////////////////
	// ============ EJEMPLOS ANTERIORES A TESTS PARAMETRIZADOS ================
	// ///////////////////////////////////////////////////////////////////////

	@Test
	void shouldValidateEmail_correctUsername() {

		// GIVEN
		String email = "usuario@email.com";

		// WHEN
		boolean valid = validator.isValid(email);

		assertValid(valid);
	}

	@Test
	void shouldValidateEmail_onlyOneCharacterUserName() {

		// GIVEN
		String email = "a@email.com";

		// WHEN
		boolean valid = validator.isValid(email);

		assertValid(valid);
	}

	@Test
	void shouldValidateEmail_domainMoreThanOneDot() {

		// GIVEN
		String email = "usuario@email.es.com";

		// WHEN
		boolean valid = validator.isValid(email);

		assertValid(valid);
	}

	@Test
	void shouldNotValidateEmail_missingAtSign() {

		// GIVEN
		String email = "usuarioemail.com";

		// WHEN
		boolean valid = validator.isValid(email);

		assertNotValid(valid);
	}
}
