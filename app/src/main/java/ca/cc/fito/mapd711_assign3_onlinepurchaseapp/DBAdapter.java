package ca.cc.fito.mapd711_assign3_onlinepurchaseapp;

/* MAPD 711 - Assignment 3 - Online Purchase App */
/* 12/17/2017                                    */
/* Fernando Ito - 300960367                      */
/* Santhosh Damodharan - 300964037               */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    static final String KEY_ROWID = "_id";
    static final String KEY_USERNAME = "userName";
    static final String KEY_PASSWORD = "password";
    static final String KEY_FIRSTNAME = "firstname";
    static final String KEY_LASTNAME = "lastname";
    static final String KEY_ADDRESS = "address";
    static final String KEY_CITY = "city";
    static final String KEY_POSTALCODE = "postalCode";
    static final String TAG = "DBAdapter";
    static final String DATABASE_NAME = "MyDB";
    static final String DATABASE_TABLE = "Customer";
    static final int DATABASE_VERSION = 5;
    static final String DATABASE_CREATE =
            "create table Customer (_id integer primary key autoincrement, "
            + "username text not null, "
            + "password text not null, "
            + "firstname text not null, "
            + "lastname text not null, "
            + "address text not null, "
            + "city text not null, "
            + "postalCode text not null);";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx){
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all ond data");
            db.execSQL("DROP TABLE IF EXISTS Customer");
            onCreate(db);
        }
    }

    //--- opens the database ---
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //--- closes the database ---
    public void close() {
        DBHelper.close();
    }

    //--- inserts a customer into the database ----
    public long insertCustomer(String userName, String password, String firstname, String lastname,
                               String address, String city, String postalCode) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USERNAME, userName);
        initialValues.put(KEY_PASSWORD, password);
        initialValues.put(KEY_FIRSTNAME, firstname);
        initialValues.put(KEY_LASTNAME, lastname);
        initialValues.put(KEY_ADDRESS, address);
        initialValues.put(KEY_CITY, city);
        initialValues.put(KEY_POSTALCODE, postalCode);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //--- deletes a particular customer ---
    public boolean deleteCustomer(long rowId){
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //--- retrieves all customers ---
    public Cursor getAllCustomers() {
        return db.query(DATABASE_TABLE, new String[] {
                KEY_ROWID, KEY_FIRSTNAME, KEY_LASTNAME, KEY_ADDRESS, KEY_CITY, KEY_POSTALCODE
        }, null, null, null, null,null);
    }

    //--- retrieves a particular customer ---
    public Cursor getCustomer(long rowId) throws SQLException {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                KEY_ROWID, KEY_FIRSTNAME, KEY_LASTNAME, KEY_ADDRESS, KEY_CITY, KEY_POSTALCODE
        }, KEY_ROWID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //--- updates a customer ---
    public boolean updateCustomer(long rowId, String userName, String password, String firstname, String lastname,
                                  String address, String city, String postalCode) {
        ContentValues args = new ContentValues();
        args.put(KEY_PASSWORD, password);
        args.put(KEY_FIRSTNAME, firstname);
        args.put(KEY_LASTNAME, lastname);
        args.put(KEY_ADDRESS, address);
        args.put(KEY_CITY, city);
        args.put(KEY_POSTALCODE, postalCode);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
