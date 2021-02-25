package com.wc.metrorailsheba;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    private Button signUp;
    private EditText name, email, mobile, pass, confirmPass;
    private ProgressBar progressBar;

    ImageView ProfileImage;
    private static int PRequestCode = 1;
    private static int Gallery_pick = 1;
    private Uri ImageUri ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

        signUp = findViewById(R.id.sign_up_btn);
        name = findViewById(R.id.signUp_name);
        email = findViewById(R.id.signUp_email);
        mobile = findViewById(R.id.signUp_mobile);
        pass = findViewById(R.id.signUp_password);
        confirmPass = findViewById(R.id.signUp_confirmPass);
        progressBar = findViewById(R.id.progressBar);
        ProfileImage = findViewById(R.id.regUserPhoto);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                progressBar.setVisibility(View.INVISIBLE);

                final String NAME = name.getText().toString();
                final String EMAIL = email.getText().toString();
                final String MOBILE = mobile.getText().toString();
                String PASS = pass.getText().toString();
                String CPASS = confirmPass.getText().toString();

                if (!NAME.isEmpty() && !EMAIL.isEmpty() && !MOBILE.isEmpty() && !PASS.isEmpty() && !CPASS.isEmpty()) {
                    if (PASS.equals(CPASS)) {
                        mAuth.createUserWithEmailAndPassword(EMAIL, PASS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("USER");
                                    ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("NAME").setValue(NAME);
                                    ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("MOBILE").setValue(MOBILE);
                                    ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("EMAIL").setValue(EMAIL);
                                    Snackbar.make(v, "Sign Up success", Snackbar.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                    startActivity(new Intent(SignUp.this, Home.class));
                                    finish();
                                } else {
                                    progressBar.setVisibility(View.GONE);

                                    Snackbar.make(v, "Sign Up failed", Snackbar.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        progressBar.setVisibility(View.GONE);

                        confirmPass.setError("Password not matched");
                    }
                } else {
                    progressBar.setVisibility(View.GONE);

                    Snackbar.make(v, "Please fill all the fields", Snackbar.LENGTH_SHORT).show();
                }

            }

                private void checkAndRequestForPermission() {

                    if (ContextCompat.checkSelfPermission(SignUp.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(SignUp.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            Toast.makeText(SignUp.this, "Please accept for required permission", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            ActivityCompat.requestPermissions(SignUp.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PRequestCode);
                        }

                    }

                }

        });
    }
}
