package com.example.cardiacrecorder;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RecordFragment extends Fragment implements  RecyclerViewInterface{

    View view;
    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> R_id,systolic, diastolic, heartRate, date, time, condition,comment;
    CustomAdapter customAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_record, container, false);
        recyclerView = view.findViewById(R.id.recycleView);
        add_button = view.findViewById(R.id.add_button);

        /**
         * FloatingActionButton Add action defined
         */
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(getActivity());
        R_id = new ArrayList<>();
        systolic = new ArrayList<>();
        diastolic = new ArrayList<>();
        heartRate = new ArrayList<>();
        date = new ArrayList<>();
        time = new ArrayList<>();
        condition = new ArrayList<>();
        comment = new ArrayList<>();
        storeDataInArrays();
        customAdapter = new CustomAdapter(getActivity(),R_id,systolic,diastolic,heartRate,date, time, condition, comment, this);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    /**
     * Store Data in from Recycle View
     */
    void storeDataInArrays()
    {
        Cursor cursor = myDB.readAllDate();
        if( cursor.getCount() == 0 )
        {
            Toast.makeText(getActivity() , "No data", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while( cursor.moveToNext())
            {
                R_id.add(cursor.getString(0));
                systolic.add(cursor.getString(1));
                diastolic.add(cursor.getString(2));
                heartRate.add(cursor.getString(3));
                date.add(cursor.getString(4));
                time.add(cursor.getString(5));
                condition.add(cursor.getString(6));
                comment.add(cursor.getString(7));
            }
        }
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(),DetailsActivity.class);

        intent.putExtra("id",String.valueOf(R_id.get(position)));
        intent.putExtra("systolic",String.valueOf(systolic.get(position)));
        intent.putExtra("diastolic",String.valueOf(diastolic.get(position)));
        intent.putExtra("heartRate",String.valueOf(heartRate.get(position)));
        intent.putExtra("date",String.valueOf(date.get(position)));
        intent.putExtra("time",String.valueOf(time.get(position)));
        intent.putExtra("condition",String.valueOf(condition.get(position)));
        intent.putExtra("comment",String.valueOf(comment.get(position)));

        startActivity(intent);

    }
}