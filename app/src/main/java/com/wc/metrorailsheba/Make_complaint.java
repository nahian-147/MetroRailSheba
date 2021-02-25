package com.wc.metrorailsheba;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class Make_complaint extends AppCompatActivity {

    Button submit;
    EditText trainNo, complaint;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_complaint);

        submit = findViewById(R.id.btn_submit_complaint);

        trainNo = findViewById(R.id.train_no);
        complaint = findViewById(R.id.complaint_text);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("COMPLAINTS");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(trainNo.getText().toString().isEmpty()){
                    trainNo.setError("Please enter train number");
                    return;
                }

                if(complaint.getText().toString().isEmpty()){
                    complaint.setError("Please enter complaint");
                    return;
                }

                Map mParent = new HashMap();
                mParent.put("user", FirebaseAuth.getInstance().getCurrentUser().getEmail());
                mParent.put("train", trainNo.getText().toString());
                mParent.put("complaint", complaint.getText().toString());

                myRef.push().setValue(mParent);

                startActivity(new Intent(Make_complaint.this, ComplaintFeed.class));
                finish();
            }
        });
    }
}
