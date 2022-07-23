package com.example.cardiacrecorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.concurrent.locks.Condition;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME ="CardiacRecord.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "RecordTable";
    private static final String COLUMN_ID ="_id";
    private static final String COLUMN_Systolic="Systolic";
    private static final String COLUMN_Diastolic="Diastolic";
    private static final String COLUMN_HeartRate="HeartRate";
    private static final String COLUMN_Date="Date";
    private static final String COLUMN_Time="Time";
    private static final String COLUMN_Comment="Comment";
    private static final String COLUMN_Condition="Condition";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null  , DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_Systolic + " INTEGER, " +
                        COLUMN_Diastolic + " INTEGER, " +
                        COLUMN_HeartRate + " INTEGER, " +
                        COLUMN_Date + " TEXT, " +
                        COLUMN_Time + " TEXT, " +
                        COLUMN_Condition + " INTEGER, " +
                        COLUMN_Comment + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addRecord( int Systolic, int Diastolic, int HeartRate, String Date, String Time, int Condition, String Comment )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        
        cv.put(COLUMN_Systolic, Systolic);
        cv.put(COLUMN_Diastolic, Diastolic);
        cv.put(COLUMN_HeartRate, HeartRate);
        cv.put(COLUMN_Date, Date);
        cv.put(COLUMN_Time, Time);
        cv.put(COLUMN_Condition, Condition);
        cv.put(COLUMN_Comment, Comment);
        
        long result = db.insert(TABLE_NAME,null, cv);
        
        if( result == -1 )
        {
            Toast.makeText(context, "Add Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllDate(){
        String query = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if( db != null )
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }




}
