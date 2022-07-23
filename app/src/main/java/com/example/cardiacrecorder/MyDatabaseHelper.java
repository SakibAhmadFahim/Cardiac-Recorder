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

    //RECORD TABLE
    private static final String TABLE_NAME = "RecordTable";
    private static final String COLUMN_ID ="_id";
    private static final String COLUMN_Systolic="Systolic";
    private static final String COLUMN_Diastolic="Diastolic";
    private static final String COLUMN_HeartRate="HeartRate";
    private static final String COLUMN_Date="Date";
    private static final String COLUMN_Time="Time";
    private static final String COLUMN_Comment="Comment";
    private static final String COLUMN_Condition="Condition";

    //PROFILE TABLE
    private static final String TABLE_NAME_PRO = "ProfileTable";
    private static final String COLUMN_ID_PRO = "_id";
    private static final String COLUMN_GENDER = "Gender";
    private static final String COLUMN_AGE = "Age";
    private static final String COLUMN_WEIGHT = "Weight";
    private static final String COLUMN_HEIGHT = "Height";



    MyDatabaseHelper(@Nullable Context context) {
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


        String query1 = "CREATE TABLE " + TABLE_NAME_PRO +
                " (" + COLUMN_ID_PRO +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_GENDER + " TEXT, " +
                COLUMN_AGE + " INTEGER, " +
                COLUMN_WEIGHT + " INTEGER, " +
                COLUMN_HEIGHT + " TEXT);";

        db.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PRO);
        onCreate(db);
    }

    //ADD TO RECORD TABLE
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

    //ADD TO PROFILE TABLE
    void addProfile( String Gender , int Age, int Weight,  int Height )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_GENDER, Gender);
        cv.put(COLUMN_AGE, Age);
        cv.put(COLUMN_WEIGHT, Weight);
        cv.put(COLUMN_HEIGHT, Height);

        long result = db.insert(TABLE_NAME_PRO,null, cv);

        if( result == -1 )
        {
            Toast.makeText(context, "Profile Update Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Profile Update Successful", Toast.LENGTH_SHORT).show();
        }
    }

    //READ ALL DATA OF RECORD TABLE
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

    //READ ALL DATA OF PROFILE TABLE
    Cursor readProfile(){
        String query = "SELECT * FROM "+ TABLE_NAME_PRO;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if( db != null )
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public String averageRate()
    {
        String ans;
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT AVG(HeartRate) FROM RecordTable";

        Cursor cursor = db.rawQuery(query, null);

        if( cursor.moveToFirst() ){
            ans = String.valueOf(cursor.getInt(0));
        }
        else
        {
            ans = "0";
        }
        db.close();
        return ans;
    }


    //UPDATE ON RECORD TABLE
    void updateRecord( String row_id, int Systolic, int Diastolic, int HeartRate, String Date,
                     String Time, int Condition, String Comment)
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

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});

        if( result == -1 )
        {
            Toast.makeText(context, "Edit Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Edited Successfully", Toast.LENGTH_SHORT).show();
        }
    }


    //UPDATE ON PROFILE TABLE
    void updateProfile( String row_id, String Gender , int Age, int Weight,  int Height)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_GENDER, Gender);
        cv.put(COLUMN_AGE, Age);
        cv.put(COLUMN_WEIGHT, Weight);
        cv.put(COLUMN_HEIGHT, Height);

        long result = db.update(TABLE_NAME_PRO, cv, "_id=?", new String[]{row_id});

        if( result == -1 )
        {
            Toast.makeText(context, "Profile Update Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Profile Update Successful", Toast.LENGTH_SHORT).show();
        }
    }

    //DELETE ON RECORD
    void deleteRecord( String row_id )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if( result == -1 )
        {
            Toast.makeText(context, "Delete Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Delete Successfully", Toast.LENGTH_SHORT).show();
        }

    }

}
