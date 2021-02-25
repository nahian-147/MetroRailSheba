package com.wc.metrorailsheba;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ComplaintFeed extends AppCompatActivity {

    private RecyclerView recycler;
    private ArrayList<Complaint> complaints;

    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_feed);

        recycler = findViewById(R.id.complaint_recycler);

        complaints = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("COMPLAINTS");
        myRef.keepSynced(true);

        initPeoples();
    }

    private void initPeoples() {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                complaints =  new ArrayList<>();
                for(DataSnapshot complaint : dataSnapshot.getChildren()){
                            String email = (String) complaint.child("user").getValue();
                            String trainNo = (String) complaint.child("train").getValue();
                            String com = (String) complaint.child("complaint").getValue();

                            Complaint complaint1 = new Complaint(email, "Train no: "+trainNo, com);
                            complaints.add(complaint1);
                }
                Collections.reverse(complaints);

                ComplaintAdapter adapter = new ComplaintAdapter(getApplicationContext(), complaints);
                recycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
