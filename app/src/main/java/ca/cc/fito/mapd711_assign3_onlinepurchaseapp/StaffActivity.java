package ca.cc.fito.mapd711_assign3_onlinepurchaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StaffActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        final DatabaseManager db = new DatabaseManager(this);
        final EditText etOrderNumber = (EditText) findViewById(R.id.etCheckOrderNumber);
        final EditText etProductNumber = (EditText) findViewById(R.id.etProductNumber);
        final TextView tvAllOrders = (TextView) findViewById(R.id.tvAllOrders);
        final TextView tvQuickRegistry = (TextView) findViewById(R.id.tvQuickRegistry);
        Button btnCheckListOrders = (Button) findViewById(R.id.btnCheckListOrders);
        Button btnUpdateStatus = (Button) findViewById(R.id.btnUpdateStatus);

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

                        output += row.get(i + 4).toString();
                        output += " - " + row.get(i + 5).toString();
                        output += ": Order #" + row.get(i).toString();
                        output += " - Item #" + row.get(i + 2).toString() + "\n";

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
        btnCheckListOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List table = db.getTable("tblOrder");
                Toast.makeText(getBaseContext(), "Sorry, under development", Toast.LENGTH_LONG).show();
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
