package com.bootcamp.helper;

public class TaxCalculator {

   private double ivaRate;

   public TaxCalculator(double ivaRate) {
      this.ivaRate = ivaRate;
   }

   public double calculateIva(double price) {
      double ret = 0.0;
      if (price > 0) {
         ret = price * ivaRate / 100;
      }
      return ret;
   }

   public double getIva() {
      return ivaRate;
   }
}
