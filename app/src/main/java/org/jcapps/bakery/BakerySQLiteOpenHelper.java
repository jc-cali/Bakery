package org.jcapps.bakery;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * Created by JC on 7/8/16.
 */
public class BakerySQLiteOpenHelper extends SQLiteOpenHelper{
    private static final String TAG = BakerySQLiteOpenHelper.class.getCanonicalName();

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Bakery.db";
    public static final String PASTRY_LIST_TABLE_NAME = "PASTRY";
    public static final String USER_TABLE_NAME = "USER";
    public static final String ORDER_TABLE_NAME = "ORDER";

    public static final String P_COL_ID = "_id";
    public static final String P_COL_ITEM_NAME = "NAME";
    public static final String P_COL_ITEM_DESCRIPTION = "DESCRIPTION";
    public static final String P_COL_PIC_RESOURCE_ID = "PICTURE_RESOURCE_ID";
    public static final String P_COL_CALORIES = "CALORIES";
    public static final String P_COL_ITEM_PRICE = "PRICE";
    public static final String P_COL_ITEM_CATEGORY = "CATEGORY";
    public static final String P_COL_ITEM_AVAILABILITY = "IN_STOCK";

    public static final String U_COL_ID = "_id";
    public static final String U_COL_NAME = "NAME";
    public static final String U_COL_ADDR1 = "ADDRESS1";
    public static final String U_COL_ADDR2 = "ADDRESS2";
    public static final String U_COL_CITY = "CITY";
    public static final String U_COL_STATE = "STATE";
    public static final String U_COL_ZIP = "ZIP";
    public static final String U_COL_EMAIL = "EMAIL";

    public static final String O_COL_ID = "_id";
    public static final String O_COL_CUST_ID = "CUST_ID";
    public static final String O_COL_PASTRY_ID = "PASTRY_ID";
    public static final String O_COL_QUANTITY = "QUANTITY";
    public static final String O_COL_ORDER_AMT = "ORDER_AMT";
    public static final String O_COL_TOT_AMT = "TOTAL_AMT";
    public static final String O_COL_ORDER_DATE = "ORDER_DATE";
    public static final String O_COL_ORDER_FULFILLED = "ORDER_FULFILLED";

    public static final String[] PASTRY_COLUMNS = {P_COL_ID,P_COL_ITEM_NAME,P_COL_ITEM_DESCRIPTION,P_COL_PIC_RESOURCE_ID,P_COL_CALORIES,P_COL_ITEM_PRICE,P_COL_ITEM_CATEGORY,P_COL_ITEM_AVAILABILITY};

    public static final String[] USER_COLUMNS = {U_COL_ID,U_COL_NAME,U_COL_ADDR1,U_COL_ADDR2,U_COL_CITY,U_COL_STATE,U_COL_ZIP,U_COL_EMAIL};

    public static final String[] ORDER_COLUMNS = {O_COL_ID,O_COL_CUST_ID,O_COL_PASTRY_ID,O_COL_QUANTITY,O_COL_ORDER_AMT,O_COL_TOT_AMT,O_COL_ORDER_DATE,O_COL_ORDER_FULFILLED};

    private static final String CREATE_PASTRY_LIST_TABLE =
            "CREATE TABLE IF NOT EXISTS " + PASTRY_LIST_TABLE_NAME +
                    "(" +
                    P_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    P_COL_ITEM_NAME + " TEXT, " +
                    P_COL_ITEM_DESCRIPTION + " TEXT, " +
                    P_COL_PIC_RESOURCE_ID + " TEXT, " +
                    P_COL_CALORIES + " INTEGER, " +
                    P_COL_ITEM_PRICE + " TEXT, " +
                    P_COL_ITEM_CATEGORY + " TEXT, " +
                    P_COL_ITEM_AVAILABILITY + " INTEGER);";

    private static final String CREATE_USER_TABLE =
            "CREATE TABLE IF NOT EXISTS " + USER_TABLE_NAME +
                    "(" +
                    U_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    U_COL_NAME + " TEXT, " +
                    U_COL_ADDR1 + " TEXT, " +
                    U_COL_ADDR2 + " TEXT, " +
                    U_COL_STATE + " TEXT, " +
                    U_COL_ZIP + " TEXT, " +
                    U_COL_EMAIL + " TEXT);";

    private static final String CREATE_ORDER_TABLE =
            "CREATE TABLE IF NOT EXISTS " + ORDER_TABLE_NAME +
                    "(" +
                    O_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    O_COL_CUST_ID + " INTEGER, " +
                    O_COL_PASTRY_ID + " INTEGER, " +
                    O_COL_QUANTITY + " INTEGER, " +
                    O_COL_ORDER_AMT + " REAL, " +
                    O_COL_TOT_AMT + " REAL, " +
                    O_COL_ORDER_DATE + " TEXT, " +
                    O_COL_ORDER_FULFILLED + " INTEGER);";

    // Default Constructor
    public BakerySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Configure DB setting.
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    // Make DB instance a singleton instance to prevent memory leaks and unnecessary reallocations.
    private static BakerySQLiteOpenHelper mInstance;

    public static synchronized BakerySQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (mInstance == null) {
            mInstance = new BakerySQLiteOpenHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PASTRY_LIST_TABLE);
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_ORDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + PASTRY_LIST_TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_TABLE_NAME + ";");
        onCreate(db);
    }

    public Cursor getMenuList(){

//        BakerySQLiteOpenHelper dbHelper = new BakerySQLiteOpenHelper(getActivity());
//        SQLiteDatabase db = dbHelper.getReadableDatabase();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(PASTRY_LIST_TABLE_NAME, // a. table
                null, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                P_COL_ITEM_NAME,  // g. order by
                null); // h. limit

        return cursor;
    }

    public Cursor searchMenuList(String query){
        SQLiteDatabase db = getReadableDatabase();

        // By using the condition, SELECT _id, ITEM_NAME, PRICE, DESCRIPTION, TYPE
        //                         WHERE NAME LIKE "%<query string>%
        // You are selecting all records where the NAME column contains the query string anywhere
        // within the field.
        //String someStringVars = "SELECT * FROM TABLE_NAME WHERE " + " _id = 1";
        //Cursor c = db.rawQuery(someStringVars, null);
        Cursor cursor = db.query(PASTRY_LIST_TABLE_NAME, // a. table
                PASTRY_COLUMNS, // b. column names
//                COL_ITEM_NAME + " LIKE ?", // c. selections
// Query both the NAME and DESCRIPTION columns.
                P_COL_ITEM_NAME + " LIKE ?" + " OR " + P_COL_ITEM_DESCRIPTION + " LIKE ?", // c. selections
//                new String[]{"%"+query+"%"}, // d. selections args
                new String[]{"%"+query+"%","%"+query+"%"}, // d. selections args
                null, // e. group by
                null, // f. having
                P_COL_ITEM_CATEGORY + "," + P_COL_ITEM_NAME, // g. order by
                null); // h. limit

        return cursor;
    }

    //Add new user record
    public long addUser(String name, String address1, String address2, String city, String state, String zip, String email){
        ContentValues values = new ContentValues();
        values.put(U_COL_NAME, name);
        values.put(U_COL_ADDR1, address1);
        values.put(U_COL_ADDR2, address2);
        values.put(U_COL_CITY, city);
        values.put(U_COL_STATE, state);
        values.put(U_COL_ZIP, zip);
        values.put(U_COL_EMAIL, email);

        SQLiteDatabase db = this.getWritableDatabase();
        long returnId = db.insert(USER_TABLE_NAME, null, values);
        db.close();
        return returnId;
    }

    //Add new order record
    public long addOrder(int cust_id, int pastry_id, int quantity, double order_total, double total_amt, String order_date, int order_fulfulled){
        ContentValues values = new ContentValues();
        values.put(O_COL_CUST_ID, cust_id);
        values.put(O_COL_PASTRY_ID, pastry_id);
        values.put(O_COL_QUANTITY, quantity);
        values.put(O_COL_ORDER_AMT, order_total);
        values.put(O_COL_TOT_AMT, total_amt);
        values.put(O_COL_ORDER_DATE, order_date);
        values.put(O_COL_ORDER_FULFILLED, order_fulfulled);

        SQLiteDatabase db = this.getWritableDatabase();
        long returnId = db.insert(ORDER_TABLE_NAME, null, values);
        db.close();
        return returnId;
    }

    public int deleteUser(int id){
        SQLiteDatabase db = getWritableDatabase();
        int deleteNum = db.delete(USER_TABLE_NAME,
                U_COL_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
        return deleteNum;
    }

    public int deleteOrder(int id){
        SQLiteDatabase db = getWritableDatabase();
        int deleteNum = db.delete(ORDER_TABLE_NAME,
                O_COL_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
        return deleteNum;
    }

}
