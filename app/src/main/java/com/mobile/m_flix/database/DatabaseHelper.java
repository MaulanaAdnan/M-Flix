package com.mobile.m_flix.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "register.db";
    public static final String TABLE_NAME = "registerUser";
    public static final String COL1 = "id";
    public static final String COL2 = "username";
    public static final String COL3 = "password";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME
                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL2 + " TEXT, " + COL3 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addUser(String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, username);
        contentValues.put(COL3, password);

        long res = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();

        return res;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String[] columns = {COL1};

        String selection = COL2 + "=?" + " and " + COL3 + "=?";
        String[] selectionArg = {username, password};

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, columns, selection, selectionArg, null, null, null);

        int count = cursor.getCount();

        sqLiteDatabase.close();

        return count > 0;
    }
}