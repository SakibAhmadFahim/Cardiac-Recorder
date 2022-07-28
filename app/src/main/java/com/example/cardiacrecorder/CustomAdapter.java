package com.example.cardiacrecorder;

import static android.view.animation.AnimationUtils.loadAnimation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.TaskExecutor;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    Context context;

    ArrayList<String> R_id;
    ArrayList<String> systolic;
    ArrayList<String>diastolic;
    ArrayList<String> heartRate;
    ArrayList<String> date;
    ArrayList<String> time;
    ArrayList<String> condition;
    ArrayList<String> comment;

    Animation translate_anim;


    /**
     * CustomAdapter Constructor
     */
    public CustomAdapter(Context context, ArrayList<String> R_id, ArrayList<String> systolic, ArrayList<String> diastolic, ArrayList<String> heartRate, ArrayList<String> date,
                         ArrayList<String> time, ArrayList<String> condition, ArrayList<String> comment , RecyclerViewInterface recyclerViewInterface)
    {
        this.context = context;
        this.R_id = R_id;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.heartRate = heartRate;
        this.date = date;
        this.time = time;
        this.condition= condition;
        this.comment = comment;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.systolicTV.setText(String.valueOf(systolic.get(position)));
        holder.diastolicTV.setText(String.valueOf(diastolic.get(position)));
        holder.heartRateTV.setText(String.valueOf(heartRate.get(position)));
        holder.dateTV.setText(String.valueOf(date.get(position)));
        holder.timeTV.setText(String.valueOf(time.get(position)));
        String status = String.valueOf( condition.get(position));

        if( status.equals("1"))
        {
            holder.conditionTV.setBackground(ContextCompat.getDrawable(context, R.drawable.red_circle));
        }
        else
        {
            holder.conditionTV.setBackground(ContextCompat.getDrawable(context, R.drawable.green_circle));
        }
    }

    @Override
    public int getItemCount() {
        return systolic.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView systolicTV, diastolicTV, heartRateTV, dateTV, timeTV, conditionTV;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface ) {
            super(itemView);
            systolicTV = itemView.findViewById(R.id.systolicTV);
            diastolicTV = itemView.findViewById(R.id.diastolicTV);
            heartRateTV = itemView.findViewById(R.id.heartRateTV);
            dateTV = itemView.findViewById(R.id.dateTV);
            timeTV = itemView.findViewById(R.id.timeTV);
            conditionTV = itemView.findViewById(R.id.conditionTV);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            //Animate RecyclerView

            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if( recyclerViewInterface != null )
                    {
                        int pos = getAdapterPosition();

                        if( pos != RecyclerView.NO_POSITION )
                        {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
