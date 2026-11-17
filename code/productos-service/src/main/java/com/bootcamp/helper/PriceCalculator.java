package com.bootcamp.helper;

public class PriceCalculator {

   public static enum PRICE_TYPE {
      MONEY, PERCENTAGE
   }

   public double calculateDiscount(double price, double discount, PRICE_TYPE type) {
        if (price < 0) {
            throw new IllegalArgumentException("El precio base no puede ser negativo.");
        }

        if (type == null) {
            throw new IllegalArgumentException("El tipo de descuento no puede ser nulo.");
        }

        if (type == PRICE_TYPE.PERCENTAGE) {
            if (discount < 0 || discount > 100) {
                throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 100.");
            }
            double calculatedDiscount = price * (discount / 100.0);
            return Math.max(0.0, price - calculatedDiscount);
            
        } else if (type == PRICE_TYPE.MONEY) {
            if (discount < 0) {
                throw new IllegalArgumentException("El monto de descuento no puede ser negativo.");
            }
            return Math.max(0.0, price - discount);
        }

        throw new IllegalArgumentException("Tipo de descuento no válido.");
    }
}
