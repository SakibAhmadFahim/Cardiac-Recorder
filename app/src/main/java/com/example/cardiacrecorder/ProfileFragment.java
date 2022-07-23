package com.example.cardiacrecorder;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    View view;
    Spinner spinner;
    String gender;
    Button update;
    TextView age,height,weight;
    String[] gen = {"Male","Female"};

    MyDatabaseHelper myDB;
    String R_id, v_gender,v_age,v_height,v_weight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);
        spinner = view.findViewById(R.id.spinnerPro);
        age = view.findViewById(R.id.ageUP);
        height = view.findViewById(R.id.heightUP);
        weight = view.findViewById(R.id.weightUP);
        update = view.findViewById(R.id.updateButtonPro);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,gen);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        myDB = new MyDatabaseHelper(getActivity());
        R_id = new String();
        v_gender = new String();
        v_age = new String();
        v_weight = new String();
        v_height = new String();
        setData();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapterView.getItemAtPosition(i).toString();
                gender = value;
                Toast.makeText(getActivity(),value,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = myDB.readProfile();
                if( cursor.getCount() == 0 )
                {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(getActivity());

                    int Age, Weight, Height;
                    Age = Integer.valueOf(age.getText().toString().trim());
                    Weight = Integer.valueOf(weight.getText().toString().trim());
                    Height = Integer.valueOf(height.getText().toString().trim());

                    if( ( Weight < 0 || Weight > 250 ) || ( Height < 50 || Height > 250 ) || ( Age < 0 || Age > 120 ) )
                    {
                        if( Weight < 0 || Weight > 250 )
                        {
                            Toast.makeText(getActivity() , " Weight must be between 0 t0 250", Toast.LENGTH_SHORT).show();
                        }
                        else if( Height < 50 || Height > 250 )
                        {
                            Toast.makeText(getActivity() , " Height must be between 50 t0 250", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getActivity() , " Age must be between 0 t0 120", Toast.LENGTH_SHORT).show();
                        }
                        setData();
                    }
                    else
                    {
                        myDB.addProfile( gender,
                                Age,
                                Weight,
                                Height
                        );
                    }
                }
                else
                {
                    int Age, Weight, Height;
                    Age = Integer.valueOf(age.getText().toString().trim());
                    Weight = Integer.valueOf(weight.getText().toString().trim());
                    Height = Integer.valueOf(height.getText().toString().trim());

                    if( ( Weight < 0 || Weight > 250 ) || ( Height < 50 || Height > 250 ) || ( Age < 0 || Age > 120 ) )
                    {
                        if( Weight < 0 || Weight > 250 )
                        {
                            Toast.makeText(getActivity() , " Weight must be between 0 t0 250", Toast.LENGTH_SHORT).show();
                        }
                        else if( Height < 50 || Height > 250 )
                        {
                            Toast.makeText(getActivity() , " Height must be between 50 t0 250", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getActivity() , " Age must be between 0 t0 120", Toast.LENGTH_SHORT).show();
                        }
                        setData();
                    }
                    else
                    {
                        myDB.updateProfile( R_id,
                                gender,
                                Age,
                                Weight,
                                Height
                        );
                    }
                }
            }
        });



        return view;

        // Inflate the layout for this fragment
    }

    void setData()
    {
        Cursor cursor = myDB.readProfile();
        if( cursor.getCount() == 0 )
        {
            Toast.makeText(getActivity() , "No data", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while( cursor.moveToNext())
            {
                R_id= cursor.getString(0);
                v_gender= cursor.getString(1);
                v_age= cursor.getString(2);
                v_weight= cursor.getString(3);
                v_height= cursor.getString(4);
            }
            ArrayAdapter arrayAdapter = (ArrayAdapter) spinner.getAdapter();
            int spinnerPosition = arrayAdapter.getPosition(v_gender);
            spinner.setSelection(spinnerPosition);

            age.setText(v_age);
            weight.setText(v_weight);
            height.setText(v_height);

        }


    }
}