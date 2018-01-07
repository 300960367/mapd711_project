package ca.cc.fito.mapd711_assign3_onlinepurchaseapp;

/* MAPD 711 - Final Project - Online Purchase App */
/* KIDS team - 1/06/2018                          */
/* StaffActivity.java                             */

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StaffActivity extends AppCompatActivity {

    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String strDate;
    String firstnamePref = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        final SharedPreferences appPrefs = getSharedPreferences(
                "ca.cc.fito.mapd711_assign3_onlinepurchaseapp_preferences", MODE_PRIVATE);
        firstnamePref = appPrefs.getString("firstnamePref", "");

        final DatabaseManager db = new DatabaseManager(this);
        final EditText etCheckOrderNumber = (EditText) findViewById(R.id.etCheckOrderNumber);
        final EditText etProductNumber = (EditText) findViewById(R.id.etProductNumber);
        final TextView tvGreetings = (TextView) findViewById(R.id.tvGreetings);
        final TextView tvAllOrders = (TextView) findViewById(R.id.tvAllOrders);
        final TextView tvQuickRegistry = (TextView) findViewById(R.id.tvQuickRegistry);
        Button btnCheckListOrders = (Button) findViewById(R.id.btnCheckListOrders);
        Button btnUpdateStatus = (Button) findViewById(R.id.btnUpdateStatus);

        tvGreetings.setText("Hello " + firstnamePref + "!");

        btnCheckListOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List table = db.getTable("tblOrder");

                for (Object o : table) {
                    ArrayList row = (ArrayList) o;
                    // Writing table to log
                    String output = "";
                    for (int i = 0; i < row.size(); i += 6)
//                    for (int i=0;i<row.size();i++)
                    {
//                        output += row.get(i).toString();
                        output += "Order #" + row.get(i).toString();
                        output += " - Customer ID: " + row.get(i + 1).toString ();
                        output += " - Item #" + row.get(i + 2).toString();
                        output += " - " + row.get(i + 5).toString() + "\n";

/*
                        output += "Order ID: " + row.get(i).toString() + "\n";
                        output += "Customer ID: " + row.get(i+1).toString() + "\n";
                        output += "Product ID: " + row.get(i+2).toString() + "\n";
                        output += "Employee ID: " + row.get(i+3).toString() + "\n";
                        output += "Order Date: " + row.get(i+4).toString() + "\n";
                        output += "Status: " + row.get(i+5).toString() + "\n";
*/
                    }
                    tvAllOrders.setText(output);
                }

            }

        });

        btnUpdateStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //--- Update order ---
                final String fields[] = {"order_id", "customer_id", "product_id", "employee_id", "order_date", "status"};
                final String record[] = new String[6];

                List table = db.getTable("tblOrder");

                for (Object o : table) {
                    ArrayList row = (ArrayList) o;
                    for (int i = 0; i < row.size(); i += 6)
                    {
                        if (row.get(i).toString().equals(etCheckOrderNumber.getText().toString())) {
                            record[0] = row.get(i).toString();
                            record[1] = row.get(i+1).toString();
                            record[2] = row.get(i+2).toString();
                            record[3] = row.get(i+3).toString();
                            record[4] = row.get(i+4).toString();
                            record[5] = "Delivered";

                            ContentValues values = new ContentValues();
                            db.updateRecord(values, "tblOrder", fields, record);

                            String  str = "Order Updated successfully: ";
                            str += "\n Order ID: " + record[0];
                            str += "\n Customer ID: " + record[1];
                            str += "\n Product ID: " + record[2];
                            str += "\n Employee ID: " + record[3];
                            str += "\n Order Date: " + record[4];
                            str += "\n Status: " + record[5];

                            Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

        tvQuickRegistry.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent("ca.cc.fito.mapd711_assign3_onlinepurchaseapp.QuickRegistryActivity");
                startActivity(i);
            }
        });
    }
}
