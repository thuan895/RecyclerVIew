package com.listview;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c){
        context = c;
    }
    public  DBManager open() throws SQLException{
        Log.e("895","open");
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }
    public void insert(account acc){
        ContentValues contentValues =new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT,acc.getName());
        database.insert(DatabaseHelper.TABLE_NAME, null,contentValues);
    }
    public ArrayList<account> read() {
        Cursor cursorCourses = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME, null);
        ArrayList<account> courseModalArrayList = new ArrayList<>();
        if (cursorCourses.moveToFirst()) {
            do {
                courseModalArrayList.add(new account(
                        cursorCourses.getString(1)));
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return courseModalArrayList;
    }
    public int update (long _id, String name, String desc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT, name);
        int i =database.update(DatabaseHelper.TABLE_NAME,
                contentValues, DatabaseHelper._ID
                        + " = " + _id, null);
        return i;
    }
    public void delete(long _id){
        database.delete(DatabaseHelper.TABLE_NAME,
                DatabaseHelper._ID + " = " + _id,
                null);
    }

}
