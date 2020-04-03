package com.example.calendarapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Date;

public class databaseHelp extends SQLiteOpenHelper
{
    private static final String dbName = "CalendarEvents.db";
    private static final String table = "event_table";
    private static final String col1 = "Date";
    private static final String col2 = "Event";

    public databaseHelp(@Nullable Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTable = "CREATE TABLE " + table +" (Date DATE, Event Text)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+ table);
        onCreate(db);
    }

    public boolean insertData(Date date, String event)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1, String.valueOf(date));
        contentValues.put(col2,event);
        long result = db.insert(table,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
}
