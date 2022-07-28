package com.example.cardiacmonitor;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="cardiac.db";
    private static final String TABLE_NAME ="cardiac_details";
    private static final String  ID ="_id";
    private static final String SYSTOLIC ="systolic";
    private static final String DIASTOLIC ="diastolic";
    private static final String PRESURE_STATUS ="pressure_status";
    private static final String PULSE ="pulse";
    private static final String PULSE_STATUS ="pulse_status";
    private static final String DATE ="date";
    private static final String TIME ="time";
    private static final String COMMENTS ="comments";
    private static final String CREATE_TABLE ="CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+SYSTOLIC+" varchar,"+DIASTOLIC+" varchar,"+PRESURE_STATUS+" varchar,"+PULSE+" varchar,"+PULSE_STATUS+","+DATE+" varchar,"+TIME+" varchar,"+COMMENTS+" varchar(255));";
    private static final String SELECT_ALL = "SELECT * FROM cardiac_details WHERE _id = ?";
    private static final String UPDATE_DATA = "SELECT * FROM "+TABLE_NAME;
    private static final int VERSION_NUMBER = 1;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;


    private final Context context;


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }catch (Exception e)
        {
            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try{
            Toast.makeText(context,"onUpgrade is Called",Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);

        }catch (Exception e)
        {
            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_SHORT).show();
        }

    }

    public long insertData(String systolic,String diastolic,String pre_stat,String pulse,String pul_stat,String date,String time,String comments)
    {
        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(SYSTOLIC,systolic);
        contentValues.put(DIASTOLIC,diastolic);
        contentValues.put(PRESURE_STATUS,pre_stat);
        contentValues.put(PULSE,pulse);
        contentValues.put(PULSE_STATUS,pul_stat);
        contentValues.put(DATE,"Date: "+date);
        contentValues.put(TIME,"Time: "+time);
        contentValues.put(COMMENTS,"Comments: "+comments);

        return sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }

    public Boolean updateData(String id,String systolic,String diastolic,String pre_stat,String pulse,String pul_stat,String date,String time,String comments)
    {

       // Toast.makeText(context,"id: "+id,Toast.LENGTH_SHORT).show();
        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(SYSTOLIC,systolic);
        contentValues.put(DIASTOLIC,diastolic);
        contentValues.put(PRESURE_STATUS,pre_stat);
        contentValues.put(PULSE,pulse);
        contentValues.put(PULSE_STATUS,pul_stat);
        contentValues.put(DATE,"Date: "+date);
        contentValues.put(TIME,"Time: "+time);
        contentValues.put(COMMENTS,"Comments: "+comments);

        sqLiteDatabase.update(TABLE_NAME, contentValues, "_id = ?", new String[]{id});

       return true;
    }

    public long deleteData(String id)
    {
        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();
        return  sqLiteDatabase.delete(TABLE_NAME,ID+" = ?",new String[]{id});
    }


    public SimpleCursorAdapter populateListViewFromDB() {

        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();
        String[] columns = {MyDatabaseHelper.ID,MyDatabaseHelper.SYSTOLIC,MyDatabaseHelper.DIASTOLIC,MyDatabaseHelper.PRESURE_STATUS,MyDatabaseHelper.PULSE,MyDatabaseHelper.PULSE_STATUS,MyDatabaseHelper.DATE,MyDatabaseHelper.TIME,MyDatabaseHelper.COMMENTS};

        Cursor cursor = sqLiteDatabase.query(MyDatabaseHelper.TABLE_NAME,columns,null,null,null,null,null);

        String[] fromFieldNames = new String[]{
                MyDatabaseHelper.ID,MyDatabaseHelper.SYSTOLIC,MyDatabaseHelper.DIASTOLIC,MyDatabaseHelper.PRESURE_STATUS,MyDatabaseHelper.PULSE,MyDatabaseHelper.PULSE_STATUS,MyDatabaseHelper.DATE,MyDatabaseHelper.TIME,MyDatabaseHelper.COMMENTS
        };

        int[] toViewId = new int[]{
               R.id.item_id, R.id.systol, R.id.diastol, R.id.pressure_stat, R.id.pulse, R.id.pulse_status, R.id.date, R.id.time, R.id.comments
        };
        return new SimpleCursorAdapter(context, R.layout.sample_list,cursor,fromFieldNames,toViewId);

    }
}
