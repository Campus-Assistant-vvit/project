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

public class InternshipAdapter extends RecyclerView.Adapter<InternshipAdapter.ViewHolder> {

    private List<Internship> internshipList;
    private Context context;

    public InternshipAdapter(List<Internship> internshipList, Context context) {
        this.internshipList = internshipList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.internship_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Internship internship = internshipList.get(position);
        holder.companyTextView.setText(internship.getCompany());
        holder.positionTextView.setText(internship.getPosition());
        holder.locationTextView.setText(internship.getLocation());
        holder.durationTextView.setText(internship.getDuration());
        holder.stipendTextView.setText(internship.getStipend());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InternshipDetailActivity.class);
                intent.putExtra("company", internship.getCompany());
                intent.putExtra("position", internship.getPosition());
                intent.putExtra("location", internship.getLocation());
                intent.putExtra("duration", internship.getDuration());
                intent.putExtra("stipend", internship.getStipend());
                intent.putExtra("description", internship.getDescription());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return internshipList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView companyTextView;
        public TextView positionTextView;
        public TextView locationTextView;
        public TextView durationTextView;
        public TextView stipendTextView;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyTextView = itemView.findViewById(R.id.companyTextView);
            positionTextView = itemView.findViewById(R.id.positionTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
            durationTextView = itemView.findViewById(R.id.durationTextView);
            stipendTextView = itemView.findViewById(R.id.stipendTextView);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
