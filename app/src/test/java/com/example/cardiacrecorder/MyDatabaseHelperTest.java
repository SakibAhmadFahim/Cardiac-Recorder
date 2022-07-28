package com.example.cardiacrecorder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
public class MyDatabaseHelperTest {

    /**
     * this method test if a value is inserted in database properly
     */
    @Test
    public void testAddRecord()
    {
        MyDatabaseHelper mydb = new MyDatabaseHelper((RuntimeEnvironment.application));

        int sys = 120;
        int dias = 90;
        int hr = 75;
        String date = "12-12-20";
        String time = "16:20";
        int condi = 0;
        String cmnt = "Ok";

        long i = mydb.addRecord(sys,dias,hr,date,time,condi,cmnt);
        assertTrue(mydb.checkDataExistsOrNot(i));

        mydb.close();
    }

    /**
     * this method is to test to test the if the record is updated properly
     */
    @Test
    public void testUpdateData() {
        MyDatabaseHelper mydb = new MyDatabaseHelper((RuntimeEnvironment.application));

        int sys = 120;
        int dias = 90;
        int hr = 75;
        String date = "12-12-20";
        String time = "16:25";
        int condi = 0;
        String cmnt = "Ok";

        long i = mydb.addRecord(sys,dias,hr,date,time,condi,cmnt);


        mydb.updateRecord(Long.toString(mydb.addRecord(sys,dias,hr,date,time,condi,cmnt)),100,65,65,"15-12-21","17:15",0,"OK");



        assertFalse(mydb.checkContent(Long.toString(mydb.addRecord(sys,dias,hr,date,time,condi,cmnt)),"100","65","65","15-12-21","17:15","0","OK"));

        mydb.close();
    }

    /**
     * this method is to test to test the if the record is deleted properly from database
     */
    @Test
    public void DeleteData()
    {
        MyDatabaseHelper mydb = new MyDatabaseHelper((RuntimeEnvironment.application));

        int sys = 110;
        int dias = 80;
        int hr = 75;
        String date = "12-12-20";
        String time = "16:20";
        int condi = 0;
        String cmnt = "Ok";

        long i = mydb.addRecord(sys,dias,hr,date,time,condi,cmnt);

        mydb.deleteRecord(Long.toString(i));
        assertFalse(mydb.checkDataExistsOrNot(i));
        mydb.close();
    }
}