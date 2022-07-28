package com.example.cardiacmonitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RecordList {

    public List<Record> records = new ArrayList<>();


    public void addRecords(Record record)
    {
        if(records.contains(record))
        {
            throw new IllegalArgumentException();
        }
        records.add(record);
    }

    public List<Record>getRecords()
    {
        List<Record>recordList = records;
        Collections.sort(recordList);
        return recordList;
    }
    public List<Record> getRecords(int x)
    {
        List<Record>recordList = records;
        return recordList;
    }
    public void delete(Record record)
    {
        List<Record>recordList = records;
        if(recordList.contains(record)){
            records.remove(record);
        }//list name is cities
        else
        {
            throw new IllegalArgumentException();
        }
    }
    public int count()
    {
        return records.size();
    }



}
