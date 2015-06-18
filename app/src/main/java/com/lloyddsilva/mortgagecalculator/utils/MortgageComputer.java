package com.lloyddsilva.mortgagecalculator.utils;

import java.util.Arrays;

/**
 * Created by lloyddsilva on 1/4/15.
 */
public class MortgageComputer {
    /**
     * Calculates the monthly payment for a specified loan
     *
     */
    public double calculateMonthlyPayment(long purchasePrice, double downPayment,
                                                 int termInYears, double interestRate, long propertyTax, long propertyInsurance, double pmi) {

        double loanAmount = purchasePrice * (1 - (downPayment/100));

        // Convert interest rate into a decimal
        // eg. 6.5% = 0.065

        interestRate /= 100.0;

        // Monthly interest rate
        // is the yearly rate divided by 12

        double monthlyRate = interestRate / 12.0;

        // The length of the term in months
        // is the number of years times 12

        int termInMonths = termInYears * 12;

        // Calculate the monthly payment
        // Typically this formula is provided so
        // we won't go into the details

        // The Math.pow() method is used
        // to calculate values raised to a power

        double monthlyPayment = (loanAmount * monthlyRate)
                / (1 - Math.pow(1 + monthlyRate, -termInMonths));

        monthlyPayment = monthlyPayment + (propertyTax/12) + (propertyInsurance/12);

        if(downPayment < 20) {
            double monthlyPMI = loanAmount * (pmi / 100) / 12 ;
            monthlyPayment += monthlyPMI;
        }

        return monthlyPayment;
    }

    public double totalPayments(double monthlyPayment, int mortgageTerm) {
        return monthlyPayment * 12 * mortgageTerm;
    }

    public String payoffDate(String firstPaymentMonth, String firstPaymentYear, int mortgageTerm) {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        int firstMonthIndex = Arrays.asList(months).indexOf(firstPaymentMonth);
        int finalMonthIndex = ( firstMonthIndex + 11 ) % 12;

        int firstPaymentYearInt = Integer.parseInt(firstPaymentYear);
        int finalPaymentYear = firstPaymentYearInt + mortgageTerm;

        if(finalMonthIndex == 11)
            finalPaymentYear -= 1;

        String finalPaymentMonth = months[finalMonthIndex];

        return finalPaymentMonth + " " + finalPaymentYear;
    }

}
