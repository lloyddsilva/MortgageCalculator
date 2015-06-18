package com.lloyddsilva.mortgagecalculator.model;

/**
 * Created by lloyddsilva on 1/4/15.
 */
public class MortgageData {
    private long id;
    private long purchasePrice;
    private double downPaymentPercent;
    private int mortgageTerm;
    private double interestRate;
    private long propertyTax;
    private long propertyInsurance;
    private double pmi;
    private long zipCode;
    private String firstPaymentMonth;
    private String firstPaymentYear;
    private double monthlyPayment;
    private double totalPayment;
    private String payoffDate;

    public MortgageData(long id, long purchasePrice, double downPaymentPercent, int mortgageTerm, double interestRate, long propertyTax, long propertyInsurance, double pmi, long zipCode, String firstPaymentMonth, String firstPaymentYear, double monthlyPayment, double totalPayment, String payoffDate) {
        this.id = id;
        this.purchasePrice = purchasePrice;
        this.downPaymentPercent = downPaymentPercent;
        this.mortgageTerm = mortgageTerm;
        this.interestRate = interestRate;
        this.propertyTax = propertyTax;
        this.propertyInsurance = propertyInsurance;
        this.pmi = pmi;
        this.zipCode = zipCode;
        this.firstPaymentMonth = firstPaymentMonth;
        this.firstPaymentYear = firstPaymentYear;
        this.monthlyPayment = monthlyPayment;
        this.totalPayment = totalPayment;
        this.payoffDate = payoffDate;
    }

    public MortgageData(long purchasePrice, double downPaymentPercent, int mortgageTerm, double interestRate, long propertyTax, long propertyInsurance, double pmi, long zipCode, String firstPaymentMonth, String firstPaymentYear, double monthlyPayment, double totalPayment, String payoffDate) {
        this.purchasePrice = purchasePrice;
        this.downPaymentPercent = downPaymentPercent;
        this.mortgageTerm = mortgageTerm;
        this.interestRate = interestRate;
        this.propertyTax = propertyTax;
        this.propertyInsurance = propertyInsurance;
        this.pmi = pmi;
        this.zipCode = zipCode;
        this.firstPaymentMonth = firstPaymentMonth;
        this.firstPaymentYear = firstPaymentYear;
        this.monthlyPayment = monthlyPayment;
        this.totalPayment = totalPayment;
        this.payoffDate = payoffDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(long purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getDownPaymentPercent() {
        return downPaymentPercent;
    }

    public void setDownPaymentPercent(double downPaymentPercent) {
        this.downPaymentPercent = downPaymentPercent;
    }

    public int getMortgageTerm() {
        return mortgageTerm;
    }

    public void setMortgageTerm(int mortgageTerm) {
        this.mortgageTerm = mortgageTerm;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public long getPropertyTax() {
        return propertyTax;
    }

    public void setPropertyTax(long propertyTax) {
        this.propertyTax = propertyTax;
    }

    public long getPropertyInsurance() {
        return propertyInsurance;
    }

    public void setPropertyInsurance(long propertyInsurance) {
        this.propertyInsurance = propertyInsurance;
    }

    public double getPmi() {
        return pmi;
    }

    public void setPmi(double pmi) {
        this.pmi = pmi;
    }

    public long getZipCode() {
        return zipCode;
    }

    public void setZipCode(long zipCode) {
        this.zipCode = zipCode;
    }

    public String getFirstPaymentMonth() {
        return firstPaymentMonth;
    }

    public void setFirstPaymentMonth(String firstPaymentMonth) {
        this.firstPaymentMonth = firstPaymentMonth;
    }

    public String getFirstPaymentYear() {
        return firstPaymentYear;
    }

    public void setFirstPaymentYear(String firstPaymentYear) {
        this.firstPaymentYear = firstPaymentYear;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getPayoffDate() {
        return payoffDate;
    }

    public void setPayoffDate(String payoffDate) {
        this.payoffDate = payoffDate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MortgageData{");
        sb.append("id=").append(id);
        sb.append(", purchasePrice=").append(purchasePrice);
        sb.append(", downPaymentPercent=").append(downPaymentPercent);
        sb.append(", mortgageTerm=").append(mortgageTerm);
        sb.append(", interestRate=").append(interestRate);
        sb.append(", propertyTax=").append(propertyTax);
        sb.append(", propertyInsurance=").append(propertyInsurance);
        sb.append(", pmi=").append(pmi);
        sb.append(", zipCode=").append(zipCode);
        sb.append(", firstPaymentMonth='").append(firstPaymentMonth).append('\'');
        sb.append(", firstPaymentYear='").append(firstPaymentYear).append('\'');
        sb.append(", monthlyPayment=").append(monthlyPayment);
        sb.append(", totalPayment=").append(totalPayment);
        sb.append(", payoffDate='").append(payoffDate).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
