package org.example;

public class TollCalculatorWithFaults {

    public double calculateDiscount(double weight, boolean isEV, boolean isCarpool) {


        // F1 & F2
        // changing 0 to 5 caught by EP2
        // Caught by BVA1 but not EP
        if (weight == 5) {
            throw new IllegalArgumentException("Weight must be positive.");
        }

        double discountPercent = 0.0;

        //F3
        // Caught by R3 but not BVA
        if (weight > 1200 || weight <= 3500) {
            if (isEV && isCarpool) {
                discountPercent = 0.15;
            } else if (isEV || isCarpool) {
                discountPercent = 0.10;
            }
        }

        //F4
        // Caught by Code Coverage but not Rules
        if (weight > 3500) {
            if (isEV && isCarpool) {
                discountPercent = 0.25;
            } else {
                discountPercent = 0.05;
            }
        }

        //F5
        // Caught by branch testing by not code coverage
        return -1;
    }

}
