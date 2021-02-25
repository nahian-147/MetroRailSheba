package com.wc.metrorailsheba;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    private Button signIn;
    private EditText mobile, pass;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signIn = findViewById(R.id.sign_in_btn);
        mobile = findViewById(R.id.signIn_mobile);
        pass = findViewById(R.id.signIn_password);
        progressBar = findViewById(R.id.progressBar);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                final String MOBILE = mobile.getText().toString();
                final String PASS = pass.getText().toString();
                if(!MOBILE.isEmpty() && !PASS.isEmpty()){
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("USER");
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot ds : dataSnapshot.getChildren()){
                                String tempNumb = (String) ds.child("MOBILE").getValue();
                                if(tempNumb.equals(MOBILE)){
                                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                                    mAuth.signInWithEmailAndPassword((String) ds.child("EMAIL").getValue(), PASS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                Snackbar.make(v, "Authentication completed", Snackbar.LENGTH_SHORT).show();
                                                startActivity(new Intent(SignIn.this, Home.class));
                                                finish();
                                            }else{
                                                Snackbar.make(v, "Authentication failed"+task.getException(), Snackbar.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }
        });

    }
}
