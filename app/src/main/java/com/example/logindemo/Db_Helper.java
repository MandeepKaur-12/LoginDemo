package com.example.logindemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Db_Helper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "LoginDatabase.db";
    public static final int Version = 1;

    public static final String TABLE_NAME = "Login_Data";
    public static final String COL_1 = "NAME";
    public static final String COL_2 = "EMAIL";
    public static final String COL_3 = "PASSWORD";
    public static final String COL_4 = "PHONE";

    private String Create_table = "CREATE TABLE " + TABLE_NAME + "( "+
            COL_1 + " TEXT,"+
            COL_2 + " TEXT PRIMARY KEY,"+
            COL_3 + " TEXT,"+
            COL_4 + " TEXT)";


    public Db_Helper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
//        onCreate(db);
    }

    public boolean insertData(String name, String email, String password, String phone){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, name);
        contentValues.put(COL_2, email);
        contentValues.put(COL_3, password);
        contentValues.put(COL_4, phone);

        long ins = database.insert(TABLE_NAME, null, contentValues);
        if(ins == -1) return false;
        else return true;
    }

    public boolean check_email(String email){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE "+COL_2+"=?", new String[]{email});
        if(cursor.getCount()>0)
        { return false; }
        else
        { return true;   }
    }

    public boolean email_password(String email, String password){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE "+COL_2+"=? AND "+COL_3+"=?", new String[]{email,password});
        if(cursor.getCount()>0)
        {   return true;   }
        else
        {    return false;   }
    }



}
