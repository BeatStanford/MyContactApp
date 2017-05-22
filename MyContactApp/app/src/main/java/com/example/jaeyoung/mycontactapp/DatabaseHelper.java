package com.example.jaeyoung.mycontactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jaeyoung on 5/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Contact.db";
    public static final String TABLE_NAME = "contact_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "Email";
    public static final String COL_4 = "Number";
    public static final String COL_5 = "Address";


    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 99); //version number
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT,EMAIL TEXT,ADDRESS TEXT,NUMBER TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String email, String phoneNumber, String address) {
        //Log.d("MyContacts", "Cant insert data1");

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        //Log.d("MyContacts", "Cant insert data2");
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, email);

        //Log.d("MyContacts", "Cant insert data3");
        contentValues.put(COL_4, phoneNumber);
        contentValues.put(COL_5, address);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;

    }
}
