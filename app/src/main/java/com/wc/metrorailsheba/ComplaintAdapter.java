package com.wc.metrorailsheba;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ComplaintAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Complaint> complaints;

    public ComplaintAdapter(Context context, ArrayList<Complaint> complaints) {
        this.context = context;
        this.complaints = complaints;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_complaint_item, parent, false);

        return new ComplaintAdapter.VHItem(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ComplaintAdapter.VHItem vhItem = (ComplaintAdapter.VHItem) holder;

        vhItem.email.setText(complaints.get(position).getEmail());
        vhItem.complaint.setText(complaints.get(position).getComplaint());
        vhItem.trainNo.setText(complaints.get(position).getTrainNo());
    }

    @Override
    public int getItemCount() {
        return complaints.size();
    }

    class VHItem extends RecyclerView.ViewHolder{
        TextView email, complaint, trainNo;

        public VHItem(@NonNull View itemView) {
            super(itemView);
            this.complaint = itemView.findViewById(R.id.complaint_complaint);
            this.email = itemView.findViewById(R.id.email_complaint);
            this.trainNo = itemView.findViewById(R.id.train_complaint);
        }
    }
}
