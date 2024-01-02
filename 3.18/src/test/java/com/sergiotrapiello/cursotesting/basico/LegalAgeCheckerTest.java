package com.sergiotrapiello.cursotesting.basico;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class LegalAgeCheckerTest {

	private LegalAgeChecker legalAgeChecker;

	@BeforeEach
	void setup() {
		// NOTA: la fecha que se le pasa al Instant.parse debe estar en UTC. Si para
		// España quiero fijar las 16:15 en horario de verano debo restarle 2 horas
		legalAgeChecker = new LegalAgeChecker(
				Clock.fixed(Instant.parse("2023-08-10T14:15:00.00Z"), ZoneId.systemDefault()));
	}

	@ParameterizedTest
	@MethodSource("legalAgeDates")
	void shouldBeOfLegalAge(LocalDateTime birthDate) {

		// WHEN
		boolean result = legalAgeChecker.isOfLegalAge(birthDate);

		// THEN
		assertTrue(result, "Should be of legal age. ");
	}

	private static Stream<LocalDateTime> legalAgeDates() {
		return Stream.of(LocalDateTime.of(1988, Month.JULY, 29, 19, 30),
				LocalDateTime.of(2005, Month.AUGUST, 10, 16, 14));
	}

	@ParameterizedTest
	@MethodSource("noLegalAgeDates")
	void shouldNotBeOfLegalAge(LocalDateTime birthDate) {

		// WHEN
		boolean result = legalAgeChecker.isOfLegalAge(birthDate);

		// THEN
		assertFalse(result, "Should NOT be of legal age. ");
	}

	private static Stream<LocalDateTime> noLegalAgeDates() {
		return Stream.of(LocalDateTime.of(2022, Month.JULY, 29, 19, 30),
				LocalDateTime.of(2006, Month.AUGUST, 9, 17, 30), 
				LocalDateTime.of(2005, Month.AUGUST, 10, 16, 21));
	}
}
