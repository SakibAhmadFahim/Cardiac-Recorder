package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText systolic, diastolic, heartRate, date, time, comment;
    Button add_button,cancel_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        systolic = findViewById(R.id.editTextSystolic);
        diastolic = findViewById(R.id.editTextDiastolic);
        heartRate = findViewById(R.id.editTextHeartRate);
        date = findViewById(R.id.editTextDate);
        time = findViewById(R.id.editTextTime);
        comment = findViewById(R.id.editTextComment);

        add_button = findViewById(R.id.Add_button);
        cancel_button = findViewById(R.id.Cancel_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);

                int dist, systole, status = 0;
                systole = Integer.valueOf(systolic.getText().toString().trim());
                dist = Integer.valueOf(diastolic.getText().toString().trim());
                if( (systole >140 || systole<90) || (dist >90 || dist<60) ) {
                    status = 1;
                }
                else
                {
                    status = 0;
                }

                myDB.addRecord( systole,
                        dist,
                        Integer.valueOf(heartRate.getText().toString().trim()),
                        date.getText().toString().trim(),
                        time.getText().toString().trim(),
                        status,
                        comment.getText().toString().trim()
                        );
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}