package com.lloyddsilva.mortgagecalculator.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.lloyddsilva.mortgagecalculator.db.DBUtils;
import com.lloyddsilva.mortgagecalculator.model.MortgageData;
import com.lloyddsilva.mortgagecalculator.utils.MortgageComputer;
import com.lloyddsilva.mortgagecalculator.R;


public class MainActivity extends ActionBarActivity {

    // constants used when saving/restoring state
    private static final String PURCHASE_PRICE_AMOUNT = "PURCHASE_PRICE_AMOUNT";
    private static final String DOWN_PAYMENT_PERCENT = "DOWN_PAYMENT_PERCENT";
    private static final String MORTGAGE_TERM_YEARS = "MORTGAGE_TERM_YEARS";
    private static final String INTEREST_RATE_PERCENT = "INTEREST_RATE_PERCENT";
    private static final String PROPERTY_TAX_AMOUNT = "PROPERTY_TAX_AMOUNT";
    private static final String PROPERTY_INSURANCE_AMOUNT = "PROPERTY_INSURANCE_AMOUNT";
    private static final String PMI_PERCENT = "PMI_PERCENT";
    private static final String ZIP_CODE_NUMBER = "ZIP_CODE_NUMBER";
    private static final String FIRST_PAYMENT_MONTH_TEXT = "FIRST_PAYMENT_MONTH_TEXT";
    private static final String FIRST_PAYMENT_YEAR_TEXT = "FIRST_PAYMENT_YEAR_TEXT";

    //Parsed values to perform computations on
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

    //Refs to UI elements
    private EditText txtPurchasePrice;
    private EditText txtDownPayment;
    private EditText txtMortgageTerm;
    private EditText txtInterestRate;
    private EditText txtPropertyTax;
    private EditText txtPropertyInsurance;
    private EditText txtPMI;
    private EditText txtZipCode;
    private Spinner spnFirstPaymentMonth;
    private Spinner spnFirstPaymentYear;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // check if app just started or is being restored from memory
        if ( savedInstanceState == null ) // the app just started running
        {
            purchasePrice = 300000;
            downPaymentPercent = 20;
            mortgageTerm = 30;
            interestRate = 4.5;
            propertyTax = 3000;
            propertyInsurance = 1500;
            pmi = 0.52;
            zipCode = 94043;
            firstPaymentMonth = "Apr";
            firstPaymentYear = "2015";

        } // end if
        else // app is being restored from memory, not executed from scratch
        {
            purchasePrice = savedInstanceState.getLong(PURCHASE_PRICE_AMOUNT);
            downPaymentPercent = savedInstanceState.getDouble(DOWN_PAYMENT_PERCENT);
            mortgageTerm = savedInstanceState.getInt(MORTGAGE_TERM_YEARS);
            interestRate = savedInstanceState.getDouble(INTEREST_RATE_PERCENT);
            propertyTax = savedInstanceState.getLong(PROPERTY_TAX_AMOUNT);
            propertyInsurance = savedInstanceState.getLong(PROPERTY_INSURANCE_AMOUNT);
            pmi = savedInstanceState.getDouble(PMI_PERCENT);
            zipCode = savedInstanceState.getLong(ZIP_CODE_NUMBER);
            firstPaymentMonth = savedInstanceState.getString(FIRST_PAYMENT_MONTH_TEXT);
            firstPaymentYear = savedInstanceState.getString(FIRST_PAYMENT_YEAR_TEXT);

        } // end else

        // get references UI elements
        txtPurchasePrice = (EditText) findViewById(R.id.txtPurchasePrice);
        txtDownPayment = (EditText) findViewById(R.id.txtDownPayment);
        txtMortgageTerm = (EditText) findViewById(R.id.txtMortgageTerm);
        txtInterestRate = (EditText) findViewById(R.id.txtInterestRate);
        txtPropertyTax = (EditText) findViewById(R.id.txtPropertyTax);
        txtPropertyInsurance = (EditText) findViewById(R.id.txtPropertyInsurance);
        txtPMI = (EditText) findViewById(R.id.txtPMI);
        txtZipCode = (EditText) findViewById(R.id.txtZipCode);
        spnFirstPaymentMonth = (Spinner) findViewById(R.id.spnFirstPaymentMonth);
        spnFirstPaymentYear = (Spinner) findViewById(R.id.spnFirstPaymentYear);

        //Set values for spinners
        ArrayAdapter<CharSequence> months_adapter = ArrayAdapter.createFromResource(
                this, R.array.months_array, android.R.layout.simple_spinner_item);
        months_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnFirstPaymentMonth.setAdapter(months_adapter);

        ArrayAdapter<CharSequence> years_adapter = ArrayAdapter.createFromResource(
                this, R.array.years_array, android.R.layout.simple_spinner_item);
        years_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnFirstPaymentYear.setAdapter(years_adapter);

        //Set all the above values on the UI
        txtPurchasePrice.setText("" + purchasePrice);
        txtDownPayment.setText("" + downPaymentPercent);
        txtMortgageTerm.setText("" + mortgageTerm);
        txtInterestRate.setText("" + interestRate);
        txtPropertyTax.setText("" + propertyTax);
        txtPropertyInsurance.setText("" + propertyInsurance);
        txtPMI.setText("" + pmi);
        txtZipCode.setText("" + zipCode);

        spnFirstPaymentMonth.setSelection(4);
        spnFirstPaymentYear.setSelection(15);

        addCalculateButtonListener();
    }

    private void addCalculateButtonListener() {
        btnCalculate = (Button) findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchasePrice = Long.parseLong(txtPurchasePrice.getText().toString());
                downPaymentPercent = Double.parseDouble(txtDownPayment.getText().toString());
                mortgageTerm = Integer.parseInt(txtMortgageTerm.getText().toString());
                interestRate = Double.parseDouble(txtInterestRate.getText().toString());
                propertyTax = Long.parseLong(txtPropertyTax.getText().toString());
                propertyInsurance = Long.parseLong(txtPropertyInsurance.getText().toString());
                pmi = Double.parseDouble(txtPMI.getText().toString());
                zipCode = Long.parseLong(txtZipCode.getText().toString());
                firstPaymentMonth = spnFirstPaymentMonth.getSelectedItem().toString();
                firstPaymentYear = spnFirstPaymentYear.getSelectedItem().toString();

                MortgageComputer computer = new MortgageComputer();
                double monthlyPayment = computer.calculateMonthlyPayment(purchasePrice, downPaymentPercent, mortgageTerm, interestRate, propertyTax, propertyInsurance, pmi);
                double totalPayment = computer.totalPayments(monthlyPayment, mortgageTerm);
                String payoffDate = computer.payoffDate(firstPaymentMonth, firstPaymentYear, mortgageTerm);

                //Save values to SQLite
                MortgageData md = new MortgageData(purchasePrice, downPaymentPercent, mortgageTerm, interestRate, propertyTax, propertyInsurance, pmi, zipCode, firstPaymentMonth, firstPaymentYear, monthlyPayment, totalPayment, payoffDate);
                DBUtils dbu = new DBUtils(MainActivity.this);
                long id = dbu.insertQuery(md);

                MortgageData result = dbu.selectQuery(id);

                //Retrieve values from SQLite
                Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class);
                resultIntent.putExtra("monthlyPayment", result.getMonthlyPayment());
                resultIntent.putExtra("totalPayment", result.getTotalPayment());
                resultIntent.putExtra("payoffDate", result.getPayoffDate());

                startActivity(resultIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // save values
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

        outState.putLong(PURCHASE_PRICE_AMOUNT, purchasePrice);
        outState.putDouble(DOWN_PAYMENT_PERCENT, downPaymentPercent);
        outState.putInt(MORTGAGE_TERM_YEARS, mortgageTerm);
        outState.putDouble(INTEREST_RATE_PERCENT, interestRate);
        outState.putLong(PROPERTY_TAX_AMOUNT, propertyTax);
        outState.putLong(PROPERTY_INSURANCE_AMOUNT, propertyInsurance);
        outState.putDouble(PMI_PERCENT, pmi);
        outState.putLong(ZIP_CODE_NUMBER, zipCode);
        outState.putString(FIRST_PAYMENT_MONTH_TEXT, firstPaymentMonth);
        outState.putString(FIRST_PAYMENT_YEAR_TEXT, firstPaymentYear);

    } // end method onSaveInstanceState
}
