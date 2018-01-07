package ca.cc.fito.mapd711_assign3_onlinepurchaseapp;

/* MAPD 711 - Final Project - Online Purchase App */
/* KIDS team - 1/06/2018                          */
/* QuickRegistryActivity.java                     */

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuickRegistryActivity extends AppCompatActivity {

    private RadioGroup rgQuickRegistry;
    private RadioButton rb;
    final DatabaseManager db = new DatabaseManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_registry);

        final EditText editText1 = (EditText) findViewById(R.id.editText1);
        final EditText editText2 = (EditText) findViewById(R.id.editText2);
        final EditText editText3 = (EditText) findViewById(R.id.editText3);
        final EditText editText4 = (EditText) findViewById(R.id.editText4);
        final EditText editText5 = (EditText) findViewById(R.id.editText5);
        final EditText editText6 = (EditText) findViewById(R.id.editText6);
        final EditText editText7 = (EditText) findViewById(R.id.editText7);

        //--- Add Customer ---
        final String fieldsCustomer[] = {"customer_id", "username", "password", "firstname", "lastname", "address", "city", "postal_code"};
        final String recordCustomer[] = new String[8];
        //--- Add Employee ---
        final String fieldsEmployee[] = {"employee_id", "username", "password", "firstname", "lastname"};
        final String recordEmployee[] = new String[5];
        //--- Add Product ---
        final String fieldsProduct[] = {"product_id", "productname", "price", "quantity", "category"};
        final String recordProduct[] = new String[5];

       // final RadioButton rb = (RadioButton) rgQuickRegistry.findViewById(rgQuickRegistry.getCheckedRadioButtonId());

        final Button btQuickAdd = (Button) findViewById(R.id.btQuickAdd);
        final Button btQuickUpdate = (Button) findViewById(R.id.btQuickUpdate);
        final Button btQuickReset = (Button) findViewById(R.id.btQuickReset);

        btQuickAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(QuickRegistryActivity.this, "Table set to: " + rb.getText().toString(), Toast.LENGTH_SHORT).show();

                switch(rb.getText().toString()){

                    case "Customer":
                        // Add Customer
                        recordCustomer[1] = editText1.getText().toString(); //username
                        recordCustomer[2] = editText2.getText().toString(); //password
                        recordCustomer[3] = editText3.getText().toString(); //firstname
                        recordCustomer[4] = editText4.getText().toString(); //lastname
                        recordCustomer[5] = editText5.getText().toString(); //address
                        recordCustomer[6] = editText6.getText().toString(); //city
                        recordCustomer[7] = editText7.getText().toString(); //postal_code
                        ContentValues valuesCustomer = new ContentValues();
                        db.addRecord(valuesCustomer, "tblCustomer", fieldsCustomer, recordCustomer);

                        Toast.makeText(QuickRegistryActivity.this,
                                "Table: " + rb.getText().toString() + "\n" +
                                        "username: " + editText1.getText().toString() + "\n" +
                                        "password: " + editText2.getText().toString() + "\n" +
                                        "firstname: " + editText3.getText().toString() + "\n" +
                                        "lastname: " + editText4.getText().toString() + "\n" +
                                        "address: " + editText5.getText().toString() + "\n" +
                                        "city: " + editText6.getText().toString() + "\n" +
                                        "postal_code: " + editText7.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                        break;

                    case "Employee":
                        // Add Employee
                        recordEmployee[1] = editText1.getText().toString(); //username
                        recordEmployee[2] = editText2.getText().toString(); //password
                        recordEmployee[3] = editText3.getText().toString(); //firstname
                        recordEmployee[4] = editText4.getText().toString(); //lastname
                        ContentValues valuesEmployee = new ContentValues();
                        db.addRecord(valuesEmployee, "tblEmployee", fieldsEmployee, recordEmployee);

                        Toast.makeText(QuickRegistryActivity.this,
                                "Table: " + rb.getText().toString() + "\n" +
                                        "username: " + editText1.getText().toString() + "\n" +
                                        "password: " + editText2.getText().toString() + "\n" +
                                        "firstname: " + editText3.getText().toString() + "\n" +
                                        "lastname: " + editText4.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                        break;

                    case "Product":
                        // Add Product
                        recordProduct[1] = editText1.getText().toString(); //productname
                        recordProduct[2] = editText2.getText().toString(); //price
                        recordProduct[3] = editText3.getText().toString(); //quantity
                        recordProduct[4] = editText4.getText().toString(); //category
                        ContentValues valuesProduct = new ContentValues();
                        db.addRecord(valuesProduct, "tblProduct", fieldsProduct, recordProduct);

                        Toast.makeText(QuickRegistryActivity.this,
                                "Table: " + rb.getText().toString() + "\n" +
                                        "productname: " + editText1.getText().toString() + "\n" +
                                        "price: " + editText2.getText().toString() + "\n" +
                                        "quantity: " + editText3.getText().toString() + "\n" +
                                        "category: " + editText4.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        Toast.makeText(QuickRegistryActivity.this,
                                "Select Customer, Employee or Product table.",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        btQuickUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(QuickRegistryActivity.this, "Table set to: " + rb.getText().toString(), Toast.LENGTH_SHORT).show();

                switch(rb.getText().toString()){

                    case "Customer":
                        // Update Customer
                        final String fieldsCustomer[] = {"customer_id", "username", "password", "firstname", "lastname", "address", "city", "postal_code"};
                        final String recordCustomer[] = new String[8];

                        List table = db.getTable("tblCustomer");

                        for (Object o : table) {
                            ArrayList row = (ArrayList) o;
                            for (int i = 0; i < row.size(); i += 8)
                            {
                                if (row.get(i+1).toString().equals(editText1.getText().toString())) {
                                    recordCustomer[0] = row.get(i).toString(); //customer_id
                                    recordCustomer[1] = editText1.getText().toString(); //username
                                    recordCustomer[2] = editText2.getText().toString(); //password
                                    recordCustomer[3] = editText3.getText().toString(); //firstname
                                    recordCustomer[4] = editText4.getText().toString(); //lastname
                                    recordCustomer[5] = editText5.getText().toString(); //address
                                    recordCustomer[6] = editText6.getText().toString(); //city
                                    recordCustomer[7] = editText7.getText().toString(); //postal_code

                                    ContentValues valuesCustomer = new ContentValues();
                                    db.updateRecord(valuesCustomer, "tblCustomer", fieldsCustomer, recordCustomer);

                                    Toast.makeText(QuickRegistryActivity.this,
                                            "Table: " + rb.getText().toString() + "\n" +
                                                    "username: " + editText1.getText().toString() + "\n" +
                                                    "password: " + editText2.getText().toString() + "\n" +
                                                    "firstname: " + editText3.getText().toString() + "\n" +
                                                    "lastname: " + editText4.getText().toString() + "\n" +
                                                    "address: " + editText5.getText().toString() + "\n" +
                                                    "city: " + editText6.getText().toString() + "\n" +
                                                    "postal_code: " + editText7.getText().toString(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        break;

                    case "Employee":
                        // Update Employee
                        final String fieldsEmployee[] = {"employee_id", "username", "password", "firstname", "lastname"};
                        final String recordEmployee[] = new String[5];

                        table = db.getTable("tblEmployee");

                        for (Object o : table) {
                            ArrayList row = (ArrayList) o;
                            for (int i = 0; i < row.size(); i += 5)
                            {
                                if (row.get(i+1).toString().equals(editText1.getText().toString())) {
                                    recordEmployee[0] = row.get(i).toString(); //employee_id
                                    recordEmployee[1] = editText1.getText().toString(); //username
                                    recordEmployee[2] = editText2.getText().toString(); //password
                                    recordEmployee[3] = editText3.getText().toString(); //firstname
                                    recordEmployee[4] = editText4.getText().toString(); //lastname

                                    ContentValues valuesEmployee = new ContentValues();
                                    db.updateRecord(valuesEmployee, "tblEmployee", fieldsEmployee, recordEmployee);

                                    Toast.makeText(QuickRegistryActivity.this,
                                            "Table: " + rb.getText().toString() + "\n" +
                                                    "username: " + editText1.getText().toString() + "\n" +
                                                    "password: " + editText2.getText().toString() + "\n" +
                                                    "firstname: " + editText3.getText().toString() + "\n" +
                                                    "lastname: " + editText4.getText().toString(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        break;

                    case "Product":
                        // Update Product
                        final String fieldsProduct[] = {"product_id", "productname", "price", "quantity", "category"};
                        final String recordProduct[] = new String[5];

                        table = db.getTable("tblProduct");

                        for (Object o : table) {
                            ArrayList row = (ArrayList) o;
                            for (int i = 0; i < row.size(); i += 5)
                            {
                                if (row.get(i+1).toString().equals(editText1.getText().toString())) {
                                    recordProduct[0] = row.get(i).toString(); //product_id
                                    recordProduct[1] = editText1.getText().toString(); //productname
                                    recordProduct[2] = editText2.getText().toString(); //price
                                    recordProduct[3] = editText3.getText().toString(); //quantity
                                    recordProduct[4] = editText4.getText().toString(); //category

                                    ContentValues valuesProduct = new ContentValues();
                                    db.updateRecord(valuesProduct, "tblProduct", fieldsProduct, recordProduct);

                                    Toast.makeText(QuickRegistryActivity.this,
                                            "Table: " + rb.getText().toString() + "\n" +
                                                    "productname: " + editText1.getText().toString() + "\n" +
                                                    "price: " + editText2.getText().toString() + "\n" +
                                                    "quantity: " + editText3.getText().toString() + "\n" +
                                                    "category: " + editText4.getText().toString(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        break;

                    default:
                        Toast.makeText(QuickRegistryActivity.this,
                                "Table not found. Update failed.",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        btQuickReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear fields
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                editText4.setText("");
                editText5.setText("");
                editText6.setText("");
                editText7.setText("");
            }
        });

        rgQuickRegistry = (RadioGroup) findViewById(R.id.rgQuickRegistry);
        rgQuickRegistry.clearCheck();
        rgQuickRegistry.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rb = (RadioButton) group.findViewById(checkedId);

                switch(rb.getText().toString()){

                    case "Customer":
                        // Set Customer Hint
                        editText1.setHint("Username");
                        editText2.setHint("Password");
                        editText3.setHint("Firstname");
                        editText4.setHint("Lastname");
                        editText5.setHint("Address");
                        editText6.setHint("City");
                        editText7.setHint("Postal Code");
                        break;

                    case "Employee":
                        // Set Employee Hint
                        editText1.setHint("Username");
                        editText2.setHint("Password");
                        editText3.setHint("Firstname");
                        editText4.setHint("Lastname");
                        editText5.setHint("");
                        editText6.setHint("");
                        editText7.setHint("");
                        break;

                    case "Product":
                        // Set Product Hint
                        editText1.setHint("Product Name (ie: Samsung Galaxy S8)");
                        editText2.setHint("Price (ie: 100.00)");
                        editText3.setHint("Stars (ie: 3 or 'no reviews')");
                        editText4.setHint("Provider name (ie: Koodo)");
                        editText5.setHint("");
                        editText6.setHint("");
                        editText7.setHint("");
                        break;

                    default:
                        Toast.makeText(QuickRegistryActivity.this,
                                "Select Customer, Employee or Product table.",
                                Toast.LENGTH_SHORT).show();
                        break;
                }

//                if (null != rb && checkedId > -1) {
//                    Toast.makeText(QuickRegistryActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }
/*
    public void onSubmit(View v) {
        RadioButton rb = (RadioButton) rgQuickRegistry.findViewById(rgQuickRegistry.getCheckedRadioButtonId());
        switch(rb.toString()){
            case "Customer":
                table_name = "tblCustomer";
                break;
            case "Employee":
                table_name = "tblEmployee";
                break;
            case "Product":
                table_name = "tblProduct";
                break;
            default:
                Toast.makeText(QuickRegistryActivity.this, "Ooops!!! Wrong table", Toast.LENGTH_SHORT).show();
                break;
        }
        Toast.makeText(QuickRegistryActivity.this, table_name, Toast.LENGTH_SHORT).show();
    }
*/
}
