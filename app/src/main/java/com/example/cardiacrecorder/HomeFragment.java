package com.example.cardiacrecorder;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment {

    View view;
    TextView heartRate;
    Button add_button;

    String v_heartRate;
    MyDatabaseHelper myDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_home, container, false);
        add_button = view.findViewById(R.id.addButtonPro);
        heartRate = view.findViewById(R.id.heartRateAVG);
        myDB = new MyDatabaseHelper(getActivity());
        setAvg();

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    void setAvg()
    {
        v_heartRate = myDB.averageRate();

        heartRate.setText(v_heartRate);

    }


}