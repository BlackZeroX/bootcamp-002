package com.bootcamp.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

class TaxCalculatorTest {

    private static final double IVA = 16.0;
    private static final double DELTA = 0.0001;

    private TaxCalculator taxCalculator;

    @BeforeEach
    void begin () {
        this.taxCalculator = new TaxCalculator(TaxCalculatorTest.IVA);
    }

    @Test
    void shouldCalculateIvaWhenPriceIsGreaterThanZero() {
        double valorProducto = 100.0;
        double ivaCalculado = taxCalculator.calculateIva(valorProducto);
        assertEquals(16.0, ivaCalculado, DELTA);
    }

    @Test
    @ValueSource(doubles = { -100.0, -50.0, -1.0, })
    void shouldCalculateIvaAsZeroWhenPriceIsNotPositive(double valorProducto) {
        assertEquals(0.0, taxCalculator.calculateIva(valorProducto), DELTA);
    }

    @Test
    @ValueSource(doubles = { -0.0, 0,0})
    void shouldValueIsZero(double valorProducto) {
        assertEquals(0.0, taxCalculator.calculateIva(valorProducto), DELTA);
    }

 }