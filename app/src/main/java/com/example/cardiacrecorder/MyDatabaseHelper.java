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
    public long addRecord( int Systolic, int Diastolic, int HeartRate, String Date, String Time, int Condition, String Comment )
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
        return result;
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

    public boolean checkDataExistsOrNot(Long id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String Query = "Select * from " + TABLE_NAME + " where " + COLUMN_ID + " = " + Long.toString(id);
        Cursor cursor = sqLiteDatabase.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public boolean checkContent(String id, String sys, String dias, String hr , String date, String time, String condition, String comments) {
        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();
        String[] columns = {MyDatabaseHelper.COLUMN_Systolic, MyDatabaseHelper.COLUMN_Diastolic, MyDatabaseHelper.COLUMN_HeartRate, MyDatabaseHelper.COLUMN_Date, MyDatabaseHelper.COLUMN_Time, MyDatabaseHelper.COLUMN_Condition, MyDatabaseHelper.COLUMN_Comment};
        Cursor cursor = sqLiteDatabase.query(MyDatabaseHelper.TABLE_NAME, columns, MyDatabaseHelper.COLUMN_ID+" = '"+id+"'", null, null, null, null);
        while (cursor.moveToNext()) {
            int i1 = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_Systolic);
            int i2 = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_Diastolic);
            int i3 = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_HeartRate);
            int i4 = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_Date);
            int i5 = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_Time);
            int i6 = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_Condition);
            int i7 = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_Comment);

            String sys1 = cursor.getString(i1);
            String dia1 = cursor.getString(i2);
            String hr1 = cursor.getString(i3);
            String date1 = cursor.getString(i4);
            String time1 = cursor.getString(i5);
            String condition1 = cursor.getString(i6);
            String comment1 = cursor.getString(i7);


            if (sys != sys1 || dias != dia1 ||  hr != hr1 || date != date1 || time != time1 || condition != condition1 || comments != comment1 ) {
                cursor.close();
                return false;
            }
        }
        cursor.close();
        return true;
    }

}
