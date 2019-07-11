package com.example.cmbuser.bank;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }
 public static DatabaseAccess getInstance(Context context) {
        if (instance == null) { instance = new DatabaseAccess(context); }
        return instance; }
  public void open() {
        this.database = openHelper.getWritableDatabase();
    }
 public void close() {
        if (database != null) {
            this.database.close(); } }
    public int credentials(String a, String b) {
        String q="SELECT * FROM credentials WHERE username=\""+a+ "\"" + " AND password=\""+b+"\"";
        Cursor cursor = database.rawQuery( q,null);
        cursor.moveToFirst();
        if(cursor.isAfterLast())
        { return(-1); }
        else
        { return(cursor.getInt(2)); } }
        public int getsender(String a) {
        String q="SELECT * FROM credentials WHERE username=\""+a+"\"";
        Cursor cursor = database.rawQuery( q,null);
        cursor.moveToFirst();
        if(cursor.isAfterLast())
        { return(-1); }
        else
        { return(cursor.getInt(2));
        } }
        public int getreceiver(int k) {
        String q="SELECT * FROM credentials WHERE account="+k;
        Cursor cursor = database.rawQuery( q,null);
        cursor.moveToFirst();
        if(cursor.isAfterLast())
        { return(-1); }
        else
        {  return(cursor.getInt(2)); } }
        public String getname(int a) {
        String q="SELECT * FROM credentials WHERE id="+a;
        Cursor cursor = database.rawQuery( q,null);
        cursor.moveToFirst();
        if(cursor.isAfterLast())
        { return(" "); }
        else
        { return(cursor.getString(0));
        } }}