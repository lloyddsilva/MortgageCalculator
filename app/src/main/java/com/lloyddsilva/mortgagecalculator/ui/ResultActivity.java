package com.lloyddsilva.mortgagecalculator.ui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.lloyddsilva.mortgagecalculator.R;


public class ResultActivity extends ActionBarActivity {

    //Parsed values to perform computations on
    private double monthlyPayment;
    private double totalPayment;
    private String payoffDate;

    //Refs to UI elements
    private TextView txtMonthlyPayment;
    private TextView txtTotalPayment;
    private TextView txtPayoffDate;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        //get passed values
        Intent intent = getIntent();
        monthlyPayment = intent.getDoubleExtra("monthlyPayment", 0);
        totalPayment = intent.getDoubleExtra("totalPayment", 0);
        payoffDate = intent.getStringExtra("payoffDate");

        //get UI elements
        txtMonthlyPayment = (TextView) findViewById(R.id.txtMonthlyPayment);
        txtTotalPayment = (TextView) findViewById(R.id.txtTotalPayment);
        txtPayoffDate = (TextView) findViewById(R.id.txtPayoffDate);
        btnBack = (Button) findViewById(R.id.btnBack);

        //Display result
        txtMonthlyPayment.setText(""+String.format("%.2f", monthlyPayment));
        txtTotalPayment.setText(""+String.format("%.2f", totalPayment));
        txtPayoffDate.setText(payoffDate);

        addBackButtonListener();

    }

    private void addBackButtonListener() {
        btnBack = (Button) findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(back);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
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
}
