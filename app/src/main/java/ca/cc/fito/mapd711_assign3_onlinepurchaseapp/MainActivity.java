package ca.cc.fito.mapd711_assign3_onlinepurchaseapp;

/* MAPD 711 - Assignment 3 - Online Purchase App */
/* 12/16/2017                                    */
/* Fernando Ito - 300960367                      */
/* Santhosh Damodharan - 300964037               */

import android.app.Activity;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity
{

    private static final String tables[]={"tblCustomer","tblEmployee", "tblProduct", "tblOrder"};
    private static final String tableCreatorString[] =
            {
            "CREATE TABLE tblCustomer (" +
                    "customer_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username TEXT, " +
                    "password TEXT, " +
                    "firstname TEXT, " +
                    "lastname TEXT, " +
                    "address TEXT, " +
                    "city TEXT, " +
                    "postal_code TEXT);",
            "CREATE TABLE tblEmployee (" +
                    "employee_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username TEXT, " +
                    "password TEXT, " +
                    "firstname TEXT, " +
                    "lastname TEXT);",
            "CREATE TABLE tblProduct (" +
                    "product_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "productname TEXT, " +
                    "price TEXT, " +
                    "quantity TEXT, " +
                    "category TEXT);",
            "CREATE TABLE tblOrder (" +
                    "order_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "customer_id TEXT, " +
                    "product_id TEXT, " +
                    "employee_id TEXT, " +
                    "order_date TEXT, " +
                    "status TEXT);"
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseManager db = new DatabaseManager(this);
        db.dbInitialize(tables, tableCreatorString);

/*
        //--- Add customer ---
        final String fields[] = {"employee_id", "username", "password", "firstname", "lastname", "address", "city", "postal_code"};
        final String record[] = new String[5];
*/
/*
        //--- Add employee ---
        final String fields[] = {"employee_id", "username", "password", "firstname", "lastname"};
        final String record[] = new String[5];
*/
/*
        //--- Add product ---
        final String fields[] = {"product_id", "productname", "price", "quantity", "category"};
        final String record[] = new String[5];
*/

        // Handle Login button
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView display = (TextView) findViewById(R.id.tvDisplay);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

/*
                //--- Add customer fields ---
                record[1] = "fito";
                record[2] = "secret";
                record[3] = "Fernando";
                record[4] = "Ito";
                record[5] = "937, Progress Ave";
                record[6] = "Scarborough, ON";
                record[7] = "M1G 3T8";
                ContentValues values = new ContentValues();
                db.addRecord(values, "tblCustomer", fields, record);
*/
/*
                //--- Add employee fields ---
                record[1] = "300960367";
                record[2] = "secret";
                record[3] = "John";
                record[4] = "Doe";
                ContentValues values = new ContentValues();
                db.addRecord(values, "tblEmployee", fields, record);
*/
/*
                //--- Add product fields ---
                record[1] = "Broccoli"; //productname
                record[2] = "1.29";    //price
                record[3] = "1 kg";      //quantity
                record[4] = "Veggy";       //category
                ContentValues values = new ContentValues();
                db.addRecord(values, "tblProduct", fields, record);
*/
/*
                <item>Veggy: Broccoli (1 kg) - $1.29</item>
*/

                Boolean found = false;
                String outUsername = "";
                String outPassword = "";
                String outTable = "";

                //--- Validate username, password and type of user (Customer or Employee) ---
                for (int i=0;i<2;i++) {
                    List table = db.getTable(tables[i]);

                    for (Object o : table) {
                        ArrayList row = (ArrayList) o;

                        if (row.get(1).toString().equals(etUsername.getText().toString()) &&
                                row.get(2).toString().equals(etPassword.getText().toString())) {
                            outUsername = row.get(1).toString();
                            outPassword = row.get(2).toString();
                            outTable = tables[i];
                            found = true;
                        }
                    }
                }

                if (found) {
                    display.setText("Username: " + outUsername + ", Password: " + outPassword + ", Table: " + outTable);

                    // a. Main activity with two login options one for customers and other one for shipment clerk.
                    if (outTable == "tblCustomer") {
                        // b. Customers and Clerks username will be stored in Shared Preferences after successful login.
                        SharedPreferences customerPref = getSharedPreferences(
                                "ca.cc.fito.mapd711_assign3_onlinepurchaseapp_preferences", MODE_PRIVATE);
                        SharedPreferences.Editor customerEditor = customerPref.edit();
                        customerEditor.putString("usernamePref",
                                ((EditText) findViewById(R.id.etUsername)).getText().toString());
                        customerEditor.commit();

                        Intent i = new Intent( "ca.cc.fito.mapd711_assign3_onlinepurchaseapp.OrderActivity");
                        startActivity(i);
                    }

                } else {
                    display.setText(
                            "Username or Password incorrect. Please try: \n" +
                                    "CUSTOMER: Username='fito', Password='secret' \n" +
                                    "EMPLOYEE: Username='300960367', Password='secret' \n"
                    );
                }
            }
        });
    }
    public void onClickLoad(View view) {
        Intent i = new Intent("ca.cc.fito.mapd711_assign3_onlinepurchaseapp.PreferencesActivity");
        startActivity(i);
    }

    public void onClickDisplay(View view) {
        SharedPreferences appPrefs = getSharedPreferences(
                "ca.cc.fito.mapd711_assign3_onlinepurchaseapp_preferences", MODE_PRIVATE);
        DisplayText(appPrefs.getString("usernamePref", ""));
    }

    /*
    public void onClickModify(View view) {
        SharedPreferences appPrefs = getSharedPreferences(
                "ca.cc.fito.mapd711_assign3_onlinepurchaseapp_preferences", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = appPrefs.edit();
        prefsEditor.putString("editTextPref",
                ((EditText) findViewById(R.id.etUsername)).getText().toString());
        prefsEditor.commit();
    }
    */

    private void DisplayText(String str) {
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }

}

