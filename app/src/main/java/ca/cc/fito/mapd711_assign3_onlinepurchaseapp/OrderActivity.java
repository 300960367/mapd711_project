package ca.cc.fito.mapd711_assign3_onlinepurchaseapp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> userItems = new ArrayList<>();
    Button btnOrder;

    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        final DatabaseManager db = new DatabaseManager(this);
        final EditText etOrderNumber = (EditText) findViewById(R.id.etOrderNumber);
        final EditText etProductNumber = (EditText) findViewById(R.id.etProductNumber);
        final TextView tvOrder = (TextView) findViewById(R.id.tvOrder);
        btnOrder = (Button) findViewById(R.id.btnOrder);
        Button btnAddOrder = (Button) findViewById(R.id.btnAddOrder);
        Button btnViewOrder = (Button) findViewById(R.id.btnViewOrder);
        Button btnUpdateOrder = (Button) findViewById(R.id.btnUpdateOrder);
        Button btnListProducts = (Button) findViewById(R.id.btnListProducts);

        listItems = getResources().getStringArray(R.array.shoppingItem);
        checkedItems = new boolean[listItems.length];

        btnListProducts.setOnClickListener(new View.OnClickListener() {
                                            @Override
            public void onClick(View view)
            {
                List table = db.getTable("tblProduct");

                for (Object o : table) {
                    ArrayList row = (ArrayList)o;
                    // Writing table to log
                    String output = "";
                    for (int i=0;i<row.size();i+=5)
                    {
                        output += row.get(i).toString() + ": ";
                        output += row.get(i+1).toString() + " (";
                        output += row.get(i+3).toString() + ") - $";
                        output += row.get(i+2).toString();
                        output += "\n";
                    }
                    tvOrder.setText(output);
                }
            }
        });

        btnAddOrder.setOnClickListener(new View.OnClickListener() {
           public void onClick(View view){
               //--- Add order ---
               final String fields[] = {"order_id", "customer_id", "product_id", "employee_id", "order_date", "status"};
               final String record[] = new String[6];

               //--- Get current date and time ---
               calendar = Calendar.getInstance();
               simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
               Date = simpleDateFormat.format(calendar.getTime());

               //--- Add order fields ---
               record[1] = "1"; //customer_id
               record[2] = etProductNumber.getText().toString(); //product_id
               record[3] = "1"; //employee_id
               record[4] = Date; //order_date
               record[5] = "In-Process"; //status
               ContentValues values = new ContentValues();
               db.addRecord(values, "tblOrder", fields, record);
           }
        });


        btnViewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List table = db.getTable("tblOrder");

                for (Object o : table) {
                    ArrayList row = (ArrayList)o;
                    // Writing table to log
                    String output = "";
                    //for (int i=0;i<row.size();i+=6)
                    for (int i=0;i<row.size();i++)
                    {
                        output += row.get(i).toString();
/*
                        output += row.get(i+4).toString();
                        output += " - " + row.get(i+5).toString();
                        output += ": Order #" + row.get(i).toString();
                        output += " - Item #" + row.get(i+2).toString() + "\n";
*/
/*
                        output += "Order ID: " + row.get(i).toString() + "\n";
                        output += "Customer ID: " + row.get(i+1).toString() + "\n";
                        output += "Product ID: " + row.get(i+2).toString() + "\n";
                        output += "Employee ID: " + row.get(i+3).toString() + "\n";
                        output += "Order Date: " + row.get(i+4).toString() + "\n";
                        output += "Status: " + row.get(i+5).toString() + "\n";
*/
                    }
                    tvOrder.setText(output);
                }

            }
        });

        btnUpdateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //--- Add order ---
                final String fields[] = {"order_id", "customer_id", "product_id", "employee_id", "order_date", "status"};
                final String record[] = new String[6];

                List table = db.getTable("tblOrder");
                record[0] = etOrderNumber.getText().toString(); // order_id
                record[1] = "1"; //customer_id
                record[2] = etProductNumber.getText().toString(); //product_id
                record[3] = "1"; //employee_id
                record[4] = Date; //order_date
                record[5] = "In-Process"; //status
                ContentValues values = new ContentValues();
                db.updateRecord(values, "tblOrder", fields, record);
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(OrderActivity.this);
                mBuilder.setTitle(R.string.dialog_title);
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked) {
                            if (!userItems.contains(position)) {
                                userItems.add(position);
                            }
                        } else if (userItems.contains(position)) {
                            userItems.remove(position);
                        }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String item = "" ;
                        for (int i = 0; i < userItems.size(); i++) {
                            item = item + listItems[userItems.get(i)];
                            if (i != userItems.size() - 1) {
                                item = item + "\n";
                            }
                        }
                        tvOrder.setText(item);
                    }
                });

                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i=0; i < checkedItems.length;  i++) {
                            checkedItems[i] = false;
                            userItems.clear();
                                tvOrder.setText("");
                            }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

    }
}
