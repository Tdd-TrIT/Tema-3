package com.sergiotrapiello.cursotesting.basico;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class InvoiceLineTest {

	@ParameterizedTest
	@MethodSource
	void shouldCreateFromProduct(Product product, int units, double expectedTotalPrice) {

		// WHEN
		InvoiceLine createdLine = new InvoiceLine(product, units);

		// THEN
		assertEquals(expectedTotalPrice, createdLine.getTotalPrice(), 0.05d);
	}

	private static Stream<Arguments> shouldCreateFromProduct() {
		return Stream.of(Arguments.of(new Product("Product 1", 82.64d, 21), 2, 199.99d),
				Arguments.of(new Product("P2", 0.65, 10), 1, 0.72d),
				Arguments.of(new Product("P3", 0.65, 10), 3, 2.15d),
				Arguments.of(new Product("P3", 1.55, 0), 1, 1.55d));
	}

}
