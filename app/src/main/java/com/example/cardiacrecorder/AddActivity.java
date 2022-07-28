package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        /**
         * Add button action defined
         */
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);

                int dist, systole, heart, status = 0;
                systole = Integer.valueOf(systolic.getText().toString().trim());
                dist = Integer.valueOf(diastolic.getText().toString().trim());
                heart = Integer.valueOf(heartRate.getText().toString().trim());

                if( ( systole < 0 || systole > 250 ) || ( dist < 50 || dist > 250 ) || ( heart < 0 || heart > 250 ) )
                {
                    if( systole < 50 || systole > 220 )
                    {
                        Toast.makeText(AddActivity.this , " Systolic pressure must be between 50 t0 220", Toast.LENGTH_SHORT).show();
                    }
                    else if( dist < 20 || dist > 130 )
                    {
                        Toast.makeText(AddActivity.this, " Diastolic must be between 20 t0 130", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(AddActivity.this , " Heart Rate must be between 0 t0 250", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    if( (systole >140 || systole<90) || (dist >90 || dist<60) ) {
                        status = 1;
                    }
                    else
                    {
                        status = 0;
                    }

                    long i = myDB.addRecord( systole,
                            dist,
                            heart,
                            date.getText().toString().trim(),
                            time.getText().toString().trim(),
                            status,
                            comment.getText().toString().trim()
                    );
                }
            }
        });

        /**
         * Cancel button action defined
         */
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}