package com.wc.metrorailsheba;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Comment extends AppCompatActivity {

    Button make_complaint, facebook, see;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);


        make_complaint = findViewById(R.id.make_complaint_btn);
        see = findViewById(R.id.see_complaint_btn);
        facebook = findViewById(R.id.facebook_btn);

        make_complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Comment.this, Make_complaint.class);
                startActivity(intent);
            }
        });

        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Comment.this, ComplaintFeed.class));
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/groups/2729655537086658/"));
                startActivity(intent);;
            }
        });
    }
}
