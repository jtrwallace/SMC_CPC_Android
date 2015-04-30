package com.example.nighthawk.take2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jer20084 on 4/29/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "professor.db";
    public static final String TABLE_NAME = "professor_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "SUBJECT";
    public static final String COL_3 = "COURSE";
    public static final String COL_4 = "SECTION";
    public static final String COL_5 = "INSTRUCTOR";
    public static final String COL_6 = "A";
    public static final String COL_7 = "B";
    public static final String COL_8 = "C";
    public static final String COL_9 = "D";
    public static final String COL_10 = "F";
    public static final String COL_11 = "W";
    public static final String COL_12 = "TOTAL";

    //basic constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
      }
    //crate the basic database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, SUBJECT TEXT, COURSE TEXT, SECTION INTEGER, INSTRUCTOR TEXT, A INTEGER, B INTEGER, C INTEGER, D INTEGER, F INTEGER, W INTEGER, TOTAL INTEGER)");
    }
    //used for upgrading the database later
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITSTS "+ TABLE_NAME);
        onCreate(db);
    }

    //inserts data into the database
    public boolean insertData(String subject, String course, int section, String instructor, int gradeA, int gradeB,
                              int gradeC, int gradeD, int gradeF, int gradeW, int gradeTotal)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, subject);
        contentValues.put(COL_3, course);
        contentValues.put(COL_4, section);
        contentValues.put(COL_5, instructor);
        contentValues.put(COL_6, gradeA);
        contentValues.put(COL_7, gradeB);
        contentValues.put(COL_8, gradeC);
        contentValues.put(COL_9, gradeD);
        contentValues.put(COL_10, gradeF);
        contentValues.put(COL_11, gradeW);
        contentValues.put(COL_12, gradeTotal);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == 1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor dbResult = db.rawQuery("select * from " + TABLE_NAME, null);
        return dbResult;
    }

}
