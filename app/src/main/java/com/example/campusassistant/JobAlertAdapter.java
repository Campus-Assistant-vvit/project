package com.example.campusassistant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class JobAlertAdapter extends RecyclerView.Adapter<JobAlertAdapter.ViewHolder> {

    private List<JobAlert> jobAlertList;
    private Context context;

    public JobAlertAdapter(List<JobAlert> jobAlertList, Context context) {
        this.jobAlertList = jobAlertList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_alert_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JobAlert jobAlert = jobAlertList.get(position);
        holder.companyTextView.setText(jobAlert.getCompany());
        holder.positionTextView.setText(jobAlert.getPosition());
        holder.locationTextView.setText(jobAlert.getLocation());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, JobAlertDetailActivity.class);
                intent.putExtra("company", jobAlert.getCompany());
                intent.putExtra("position", jobAlert.getPosition());
                intent.putExtra("location", jobAlert.getLocation());
                intent.putExtra("description", jobAlert.getDescription());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobAlertList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView companyTextView;
        public TextView positionTextView;
        public TextView locationTextView;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyTextView = itemView.findViewById(R.id.companyTextView);
            positionTextView = itemView.findViewById(R.id.positionTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
