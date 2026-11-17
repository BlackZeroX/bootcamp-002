package com.bootcamp.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *

TDD o casos para descuentos al producto, considerando los siguientes escenarios:

Debe calcular correctamente un descuento estándar por porcentaje
Debe retornar el mismo precio si el porcentaje de descuento es 0%
Debe retornar 0 si el porcentaje de descuento es 100%
Debe calcular correctamente un porcentaje de descuento con decimales
Debe calcular correctamente un descuento estándar por monto fijo
Debe retornar el mismo precio si el descuento por monto fijo es 0
No debe permitir precios negativos, limitando el resultado mínimo a 0 si el monto supera al precio
Debe lanzar una excepción si el precio base es negativo
Debe lanzar una excepción si el descuento por porcentaje es menor a 0
Debe lanzar una excepción si el descuento por porcentaje es mayor a 100
Debe lanzar una excepción si el descuento por monto fijo es negativo

 * PriceCalculatorTest
 */
public class PriceCalculatorTest {

    private PriceCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new PriceCalculator();
    }

    // A. Casos de Descuento por Porcentaje (%)

    @Test
    @DisplayName("Debe calcular correctamente un descuento estándar por porcentaje")
    void testCalculateDiscount_StandardPercentage() {
        double result = calculator.calculateDiscount(100.0, 20.0, PriceCalculator.PRICE_TYPE.PERCENTAGE);
        assertEquals(80.0, result, 0.001);
    }

    @Test
    @DisplayName("Debe retornar el mismo precio si el porcentaje de descuento es 0%")
    void testCalculateDiscount_ZeroPercentage() {
        double result = calculator.calculateDiscount(200.0, 0.0, PriceCalculator.PRICE_TYPE.PERCENTAGE);
        assertEquals(200.0, result, 0.001);
    }

    @Test
    @DisplayName("Debe retornar 0 si el porcentaje de descuento es 100%")
    void testCalculateDiscount_FullPercentage() {
        double result = calculator.calculateDiscount(50.0, 100.0, PriceCalculator.PRICE_TYPE.PERCENTAGE);
        assertEquals(0.0, result, 0.001);
    }

    @Test
    @DisplayName("Debe calcular correctamente un porcentaje de descuento con decimales")
    void testCalculateDiscount_DecimalPercentage() {
        double result = calculator.calculateDiscount(200.0, 12.5, PriceCalculator.PRICE_TYPE.PERCENTAGE);
        assertEquals(175.0, result, 0.001);
    }

    // B. Casos de Descuento por Monto Fijo (MONEY)

    @Test
    @DisplayName("Debe calcular correctamente un descuento estándar por monto fijo")
    void testCalculateDiscount_StandardMoney() {
        double result = calculator.calculateDiscount(100.0, 15.0, PriceCalculator.PRICE_TYPE.MONEY);
        assertEquals(85.0, result, 0.001);
    }

    @Test
    @DisplayName("Debe retornar el mismo precio si el descuento por monto fijo es 0")
    void testCalculateDiscount_ZeroMoney() {
        double result = calculator.calculateDiscount(50.0, 0.0, PriceCalculator.PRICE_TYPE.MONEY);
        assertEquals(50.0, result, 0.001);
    }

    @Test
    @DisplayName("No debe permitir precios negativos, limitando el resultado mínimo a 0 si el monto supera al precio")
    void testCalculateDiscount_MoneyExceedsPrice() {
        double result = calculator.calculateDiscount(40.0, 50.0, PriceCalculator.PRICE_TYPE.MONEY);
        assertEquals(0.0, result, 0.001);
    }

    // C. Casos de Validación y Errores (Edge Cases)
    @Test
    @DisplayName("Debe lanzar una excepción si el precio base es negativo")
    void testCalculateDiscount_NegativePrice_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateDiscount(-10.0, 20.0, PriceCalculator.PRICE_TYPE.PERCENTAGE);
        });
    }

    @Test
    @DisplayName("Debe lanzar una excepción si el descuento por porcentaje es menor a 0")
    void testCalculateDiscount_NegativePercentage_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateDiscount(100.0, -5.0, PriceCalculator.PRICE_TYPE.PERCENTAGE);
        });
    }

    @Test
    @DisplayName("Debe lanzar una excepción si el descuento por porcentaje es mayor a 100")
    void testCalculateDiscount_PercentageGreaterThan100_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateDiscount(100.0, 105.0, PriceCalculator.PRICE_TYPE.PERCENTAGE);
        });
    }

    @Test
    @DisplayName("Debe lanzar una excepción si el descuento por monto fijo es negativo")
    void testCalculateDiscount_NegativeMoney_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateDiscount(100.0, -10.0, PriceCalculator.PRICE_TYPE.MONEY);
        });
    }
}
