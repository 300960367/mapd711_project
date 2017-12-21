package ca.cc.fito.mapd711_assign3_onlinepurchaseapp;

/* MAPD 711 - Assignment 3 - Online Purchase App */
/* 12/16/2017                                    */
/* Fernando Ito - 300960367                      */
/* Santhosh Damodharan - 300964037               */

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBAdapter db = new DBAdapter(this);

/*
        //--- add a customer ---
        db.open();
        long id = db.insertCustomer("fito", "password", "Fernando", "Ito",
                "937, Progress Ave, Room 229", "Scarborough, ON", "M1G 3T8");
        id = db.insertCustomer("sdamadharan", "password", "Santhosh", "Damadharan",
                "937, Progress Ave, Room 729", "Scarborough, ON", "M1G 3T8");
        db.close();
*/


        //--- retrieve all customers ---
        db.open();
        Cursor c = db.getAllCustomers();
        if (c.moveToFirst())
        {
            do {
                DisplayCustomer(c);
            } while (c.moveToNext());
        }
        db.close();


/*
        //--- retrieve all customers ---
        db.open();
        Cursor c = db.getAllCustomers();
*/

/*
        //--- get a customer ---
        db.open();
        Cursor c = db.getCustomer(1);
        if (c.moveToFirst())
            DisplayCustomer(c);
        else
            Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
        db.close();
*/

/*
        //--- update a customer ---
        db.open();
        if (db.updateCustomer(1, "fito", "password", "Fernando",
                "Ito", "19 - 5535, Glen Erin Dr", "Mississauga, ON", "L43 L2N"))
            Toast.makeText(this, "Update successful.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Update failed.", Toast.LENGTH_LONG).show();
        db.close();
*/

/*
        //--- delete a customer ---
        db.open();
        if (db.deleteCustomer(2))
            Toast.makeText(this, "Delete successful.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Delete failed.", Toast.LENGTH_LONG).show();
        db.close();
*/
    }

    public void DisplayCustomer(Cursor c) {
        Toast.makeText(this,
                "Customer ID: " + c.getString(0) + "\n" +
                        "First name: " + c.getString(1) + "\n" +
                        "Last name: " + c.getString(2) + "\n" +
                        "Address: " + c.getString(3) + "\n" +
                        "City: " + c.getString(4) + "\n" +
                        "Postal Code: " + c.getString(5),
                Toast.LENGTH_LONG).show();
    }

    public void onClickLoad(View view) {
        Intent i = new Intent("ca.cc.fito.mapd711_assign3_onlinepurchaseapp.CustomerActivity");
        startActivity(i);
    }

    public void onClickDisplay(View view) {
        SharedPreferences appPrefs = getSharedPreferences(
                "ca.cc.fito.mapd711_assign3_onlinepurchaseapp_preferences", MODE_PRIVATE);
        DisplayText(appPrefs.getString("editTextPref", ""));
    }

    public void onClickModify(View view) {
        SharedPreferences appPrefs = getSharedPreferences(
                "ca.cc.fito.mapd711_assign3_onlinepurchaseapp_preferences", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = appPrefs.edit();
        prefsEditor.putString("editTextPref",
                ((EditText) findViewById(R.id.editText)).getText().toString());
        prefsEditor.commit();
    }

    private void DisplayText(String str) {
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }

}
