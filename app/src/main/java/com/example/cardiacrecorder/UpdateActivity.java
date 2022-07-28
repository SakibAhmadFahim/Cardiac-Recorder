package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText systolicUP, diastolicUP, heartRateUP, dateUP, timeUP, commentUP;
    Button update_button, cancel_button;
    String u_id,u_systolic, u_diastolic, u_heartRate, u_date, u_time, u_condition,u_comment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        systolicUP = findViewById(R.id.editTextSystolicED);
        diastolicUP = findViewById(R.id.editTextDiastolicED);
        heartRateUP = findViewById(R.id.editTextHeartRateED);
        dateUP = findViewById(R.id.editTextDateED);
        timeUP = findViewById(R.id.editTextTimeED);
        commentUP = findViewById(R.id.editTextCommentED);

        update_button = findViewById(R.id.Update_buttonED);
        cancel_button = findViewById(R.id.Cancel_buttonED);
        getAndSetIntentData();

        /**
         * Cancel button action defined
         */
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);

                int dist, systole, heart, status = 0;
                systole = Integer.valueOf(systolicUP.getText().toString().trim());
                dist = Integer.valueOf(diastolicUP.getText().toString().trim());
                heart = Integer.valueOf(heartRateUP.getText().toString().trim());

                if( ( systole < 0 || systole > 250 ) || ( dist < 50 || dist > 250 ) || ( heart < 0 || heart > 250 ) )
                {
                    if( systole < 50 || systole > 220 )
                    {
                        Toast.makeText(UpdateActivity.this , " Systolic pressure must be between 50 t0 220", Toast.LENGTH_SHORT).show();
                    }
                    else if( dist < 20 || dist > 130 )
                    {
                        Toast.makeText(UpdateActivity.this, " Diastolic must be between 20 t0 130", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(UpdateActivity.this , " Heart Rate must be between 0 t0 250", Toast.LENGTH_SHORT).show();
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

                    myDB.updateRecord( u_id ,systole,
                            dist,
                            heart,
                            dateUP.getText().toString().trim(),
                            timeUP.getText().toString().trim(),
                            status,
                            commentUP.getText().toString().trim()
                    );
                }
            }
        });
    }


    /**
     * Getting data from intent and setting them to textField
     */
    void getAndSetIntentData()
    {
        if( getIntent().hasExtra("id") && getIntent().hasExtra("systolic") && getIntent().hasExtra("diastolic") && getIntent().hasExtra("heartRate")
                && getIntent().hasExtra("date") && getIntent().hasExtra("time") && getIntent().hasExtra("condition") && getIntent().hasExtra("comment"))
        {
            //Getting
            u_id = getIntent().getStringExtra("id");
            u_systolic = getIntent().getStringExtra("systolic");
            u_diastolic = getIntent().getStringExtra("diastolic");
            u_heartRate = getIntent().getStringExtra("heartRate");
            u_date = getIntent().getStringExtra("date");
            u_time = getIntent().getStringExtra("time");
            u_condition = getIntent().getStringExtra("condition");
            u_comment= getIntent().getStringExtra("comment");

            //Setting
            systolicUP.setText(u_systolic);
            diastolicUP.setText(u_diastolic);
            heartRateUP.setText(u_heartRate);
            dateUP.setText(u_date);
            timeUP.setText(u_time);
            commentUP.setText(u_comment);

        }
        else
        {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();;
        }
    }
}