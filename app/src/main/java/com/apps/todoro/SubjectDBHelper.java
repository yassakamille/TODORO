package com.apps.todoro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Paint;
import android.widget.TextView;

public class SubjectDBHelper extends SQLiteOpenHelper {
    private static String databaseName = "subjectDatabase";
    SQLiteDatabase subjectDatabase;

    public SubjectDBHelper(Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table subject (id integer primary key," +
                " name text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists subject");
        onCreate(db);
    }

    public void createNewsubject(String name) {
        ContentValues rows = new ContentValues();
        rows.put("name", name);

        subjectDatabase = getWritableDatabase();
        try {
            subjectDatabase.insert("subject", null, rows);
        } finally {
            subjectDatabase.close();
        }
    }

    public Cursor fetchAllsubjects() {
        subjectDatabase = getReadableDatabase();
        String[] rowDetails = {"name", "id"};
        Cursor cursor = subjectDatabase.query("subject", rowDetails, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            subjectDatabase.close();
            return cursor;
        }
        return cursor;


    }

    public void delete(String name) {

        subjectDatabase = getReadableDatabase();
        subjectDatabase.delete("subject", "name='" + name + "'", null);
        subjectDatabase.close();

    }

    public void deleteall() {

        subjectDatabase = getReadableDatabase();
        subjectDatabase.delete("subject", null, null);
        subjectDatabase.close();

    }

}


