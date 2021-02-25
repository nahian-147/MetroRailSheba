package com.wc.metrorailsheba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Settings extends AppCompatActivity {

    private Spinner gender, identify;
    private Button btn1, btn_submit1, btn2, btn_submit2, btn3, btn_submit3, btn4, btn_submit4;
    private EditText name, dob, idNo, password, confirmPassword, mobile, newMobile, oldPassword, email, updateEmail, ePassword;
    private boolean toggle1,toggle2, toggle3, toggle4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        gender = findViewById(R.id.spinner);
        identify = findViewById(R.id.spinner2);
        btn1 = findViewById(R.id.btn1);
        btn_submit1 = findViewById(R.id.byn_submit1);
        name = findViewById(R.id.name);
        dob = findViewById(R.id.dob);
        idNo = findViewById(R.id.id_no);
        btn2 = findViewById(R.id.btn2);
        btn_submit2 = findViewById(R.id.byn_submit2);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.cPassword);
        mobile= findViewById(R.id.MobileNumber);
        newMobile= findViewById(R.id.uMoblieNumber);
        oldPassword= findViewById(R.id.oPassword);
        btn3= findViewById(R.id.btn3);
        btn_submit3 = findViewById(R.id.byn_submit3);
        email= findViewById(R.id.Email);
        updateEmail= findViewById(R.id.uEmail);
        ePassword= findViewById(R.id.ePassword);
        btn4= findViewById(R.id.btn4);
        btn_submit4= findViewById(R.id.byn_submit4);

        gender.setVisibility(View.GONE);
        identify.setVisibility(View.GONE);
        btn_submit1.setVisibility(View.GONE);
        name.setVisibility(View.GONE);
        dob.setVisibility(View.GONE);
        idNo.setVisibility(View.GONE);

        password.setVisibility(View.GONE);
        confirmPassword.setVisibility(View.GONE);
        btn_submit2.setVisibility(View.GONE);

        mobile.setVisibility(View.GONE);
        newMobile.setVisibility(View.GONE);
        oldPassword.setVisibility(View.GONE);
        btn_submit3.setVisibility(View.GONE);

        email.setVisibility(View.GONE);
        updateEmail.setVisibility(View.GONE);
        ePassword.setVisibility(View.GONE);
        btn_submit4.setVisibility(View.GONE);

        toggle1 =false;
        toggle2 =false;
        toggle3 =false;
        toggle4 =false;

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggle1){
                    gender.setVisibility(View.GONE);
                    identify.setVisibility(View.GONE);
                    btn_submit1.setVisibility(View.GONE);
                    name.setVisibility(View.GONE);
                    dob.setVisibility(View.GONE);
                    idNo.setVisibility(View.GONE);
                    toggle1 = false;
                }else{
                    gender.setVisibility(View.VISIBLE);
                    identify.setVisibility(View.VISIBLE);
                    btn_submit1.setVisibility(View.VISIBLE);
                    name.setVisibility(View.VISIBLE);
                    dob.setVisibility(View.VISIBLE);
                    idNo.setVisibility(View.VISIBLE);
                    toggle1 = true;
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggle2){
                    password.setVisibility(View.GONE);
                    confirmPassword.setVisibility(View.GONE);
                    btn_submit2.setVisibility(View.GONE);
                    toggle2=false;
                }else{
                    password.setVisibility(View.VISIBLE);
                    confirmPassword.setVisibility(View.VISIBLE);
                    btn_submit2.setVisibility(View.VISIBLE);
                    toggle2=true;
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggle3) {
                    mobile.setVisibility(View.GONE);
                    newMobile.setVisibility(View.GONE);
                    oldPassword.setVisibility(View.GONE);
                    btn_submit3.setVisibility(View.GONE);
                    toggle3=false;
                }else {
                    mobile.setVisibility(View.VISIBLE);
                    newMobile.setVisibility(View.VISIBLE);
                    oldPassword.setVisibility(View.VISIBLE);
                    btn_submit3.setVisibility(View.VISIBLE);
                    toggle3=true;
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggle4){
                    email.setVisibility(View.GONE);
                    updateEmail.setVisibility(View.GONE);
                    ePassword.setVisibility(View.GONE);
                    btn_submit4.setVisibility(View.GONE);
                    toggle4=false;
                }else {
                    email.setVisibility(View.VISIBLE);
                    updateEmail.setVisibility(View.VISIBLE);
                    ePassword.setVisibility(View.VISIBLE);
                    btn_submit4.setVisibility(View.VISIBLE);
                    toggle4=true;
                }
            }
        });


        List<String> genders = new ArrayList<String>();
        genders.add("Male");
        genders.add("Female");

        List<String> ids = new ArrayList<String>();
        genders.add("NID Number");
        genders.add("Birth Certificate Number");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.id_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        identify.setAdapter(adapter2);

        identify.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(identify.getSelectedItem().toString().equals("NID Number")){
                    idNo.setHint("NID Number");
                }else if(identify.getSelectedItem().toString().equals("Birth Certificate Number")){
                    idNo.setHint("Birth Certificate Number");
                }else{
                    idNo.setHint("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
