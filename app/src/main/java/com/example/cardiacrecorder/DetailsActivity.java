package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    TextView systolic, diastolic, heartRate, date, time, condition,comment;
    Button edit_button, delete_button, cancel_button;

    String v_id,v_systolic, v_diastolic, v_heartRate, v_date, v_time, v_condition,v_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        systolic = findViewById(R.id.systolicTVD);
        diastolic = findViewById(R.id.diastolicTVD);
        heartRate = findViewById(R.id.heartRateTVD);
        date = findViewById(R.id.dateTVD);
        time = findViewById(R.id.timeTVD);
        condition = findViewById(R.id.conditionTVD);
        comment = findViewById(R.id.commentTVD);
        edit_button = findViewById(R.id.Edit_buttonD);
        delete_button  = findViewById(R.id.Delete_buttonD);
        cancel_button = findViewById(R.id.Cancel_buttonTVD);

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this,UpdateActivity.class);
                startActivity(intent);
                finish();
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getAndSetIntentData();
    }

    void getAndSetIntentData()
    {
        if( getIntent().hasExtra("id") && getIntent().hasExtra("systolic") && getIntent().hasExtra("diastolic") && getIntent().hasExtra("heartRate")
                && getIntent().hasExtra("date") && getIntent().hasExtra("time") && getIntent().hasExtra("condition") && getIntent().hasExtra("comment"))
        {
            //Getting
            v_id = getIntent().getStringExtra("id");
            v_systolic = getIntent().getStringExtra("systolic");
            v_diastolic = getIntent().getStringExtra("diastolic");
            v_heartRate = getIntent().getStringExtra("heartRate");
            v_date = getIntent().getStringExtra("date");
            v_time = getIntent().getStringExtra("time");
            v_condition = getIntent().getStringExtra("condition");
            v_comment= getIntent().getStringExtra("comment");

            //Setting
            systolic.setText(v_systolic);
            diastolic.setText(v_diastolic);
            heartRate.setText(v_heartRate);
            date.setText(v_date);
            time.setText(v_time);
            comment.setText(v_comment);

            if( v_condition.equals("1"))
            {
                condition.setBackground(getResources().getDrawable(R.drawable.red_circle));
            }
            else
            {
                condition.setBackground(getResources().getDrawable(R.drawable.green_circle));
            }
        }
        else
        {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();;
        }
    }
}