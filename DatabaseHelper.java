package com.example.cmbuser.bank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ravi on 15/03/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "bank1";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Note.CREATE_TABLE);
        db.execSQL(Note.balance);

        }




    // Upgrading database
    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Note.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long inserttransaction(int id, String Credit,int amount, String tofro,int balance) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
    //onUpgrade(db,1,2);
        ContentValues values = new ContentValues();
        // `id` will be inserted automatically.
        // no need to add them
        //values.put(Note.COLUMN_MODE, mode);
       // values.put(Note.COLUMN_DESCRIPTION, description);
        //values.put(Note.COLUMN_AMOUNT, amount);
        values.put("id",id);
        values.put("creditdebit",Credit);
        values.put("amount",amount);
        values.put("receiver",tofro);
        values.put("balance",balance);


        long i = db.insert(Note.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return i;
    }
public void first() {

    String countQuery = "SELECT  * FROM balancesheet";
    SQLiteDatabase db1 = this.getReadableDatabase();
    Cursor cursor = db1.rawQuery(countQuery, null);

    int count = cursor.getCount();
    cursor.close();
    if (count != 3) {
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", 1);
        values.put("balance", 10000);
        long id = db2.insert("balancesheet", null, values);

        ContentValues values1 = new ContentValues();
        values1.put("id", 2);
        values1.put("balance", 10000);
        id = db2.insert("balancesheet", null, values1);

        ContentValues values3 = new ContentValues();
        values3.put("id", 3);
        values3.put("balance", 10000);
        id = db2.insert("balancesheet", null, values3);
        db2.close();
    }

}

    int getnumber(int k)
    {
        String countQuery = "SELECT  * FROM " + Note.TABLE_NAME+" WHERE id="+k;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        k = cursor.getCount();
        cursor.close();


        return(k);

    }

    String[] getname(int k)
    {
        String countQuery = "SELECT  * FROM " + Note.TABLE_NAME+" WHERE id="+k;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int m = cursor.getCount();
        String arra[]=new String[m];
        cursor.moveToFirst();
        while(m!=0)

        {
            String a=cursor.getString(1);
            String b=Double.toString(cursor.getDouble(2));
            String c=cursor.getString(3);
            String d=Double.toString(cursor.getDouble(4));
            arra[m-1]="    "+a+"         "+b+"    "+c+"    "+d;
            cursor.moveToNext();
            m--;
        }

        cursor.close();

        return(arra);
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Note.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();


        // return count
        return count;
    }
    public  int getbalance(int n)
    {
        int m=0;
        String countQuery = "SELECT * FROM balancesheet WHERE id="+n;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorr = db.rawQuery(countQuery, null);
    cursorr.moveToFirst();
        m = cursorr.getInt(1);
      //  Log.d("invalue",Double.toString(k));
        cursorr.close();


        return (m);
    }


    void updater(int n,int k)
    {
        SQLiteDatabase db5 = this.getWritableDatabase();

        ContentValues values5 = new ContentValues();
        values5.put("balance", n);

        // updating row
        int m= db5.update("balancesheet", values5,   " id= ?",new String[]{String.valueOf(k)});
    }

   /* public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Note.COLUMN_NOTE, note.getNote());

        // updating row
        return db.update(Note.TABLE_NAME, values, Note.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    */
}
