package com.lloyddsilva.mortgagecalculator.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lloyddsilva.mortgagecalculator.model.MortgageData;

/**
 * Created by lloyddsilva on 1/4/15.
 */
public class DBUtils {

    private static final String DATABASE_NAME = "MortgageQueries";
    private SQLiteDatabase database;
    private DatabaseOpenHelper databaseOpenHelper;

    public DBUtils(Context context) {
        databaseOpenHelper = new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
    }


    public void open() throws SQLException {
        database = databaseOpenHelper.getWritableDatabase();
    }


    public void close() {
        if (database != null)
            database.close();
    }


    public long insertQuery(MortgageData md) {
        ContentValues newQuery = new ContentValues();
        newQuery.put("purchase_price", md.getPurchasePrice());
        newQuery.put("down_payment", md.getDownPaymentPercent());
        newQuery.put("mortgage_term", md.getMortgageTerm());
        newQuery.put("interest_rate", md.getInterestRate());
        newQuery.put("property_tax", md.getPropertyTax());
        newQuery.put("property_insurance", md.getPropertyInsurance());
        newQuery.put("pmi", md.getPmi());
        newQuery.put("zip_code", md.getZipCode());
        newQuery.put("first_payment_month", md.getFirstPaymentMonth());
        newQuery.put("first_payment_year", md.getFirstPaymentYear());
        newQuery.put("monthly_payment", md.getMonthlyPayment());
        newQuery.put("total_payment", md.getTotalPayment());
        newQuery.put("payoff_date", md.getPayoffDate());

        open();
        long id = database.insert("queries", null, newQuery);
        close();

        return id;
    }

    public MortgageData selectQuery(long id) {
        open();
        Cursor result =  database.query("queries", null, "id=" + id, null, null,
                null, null, null);
        MortgageData md;
        try {
            result.moveToFirst();

            md = new MortgageData(
                    result.getLong(0),
                    result.getLong(1),
                    result.getDouble(2),
                    result.getInt(3),
                    result.getDouble(4),
                    result.getLong(5),
                    result.getLong(6),
                    result.getDouble(7),
                    result.getLong(8),
                    result.getString(9),
                    result.getString(10),
                    result.getDouble(11),
                    result.getDouble(12),
                    result.getString(13)
            );
        } finally {
            result.close();
            close();
        }

        return md;
    }

    private class DatabaseOpenHelper extends SQLiteOpenHelper {

        public DatabaseOpenHelper(Context context, String name,
                                  SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {

            String createQuery = "CREATE TABLE queries "
                    + "(id integer primary key autoincrement,"
                    + "purchase_price integer,"
                    + "down_payment double,"
                    + "mortgage_term integer,"
                    + "interest_rate double,"
                    + "property_tax integer,"
                    + "property_insurance integer,"
                    + "pmi double,"
                    + "zip_code integer,"
                    + "first_payment_month text,"
                    + "first_payment_year text,"
                    + "monthly_payment double,"
                    + "total_payment double,"
                    + "payoff_date text);";

            db.execSQL(createQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
