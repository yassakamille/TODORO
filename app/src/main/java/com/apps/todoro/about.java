package com.apps.todoro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class about extends AppCompatActivity {
    TextView txtAboutback;
    TextView txtAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        txtAbout = findViewById(R.id.txt_abt);
        txtAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(about.this, pdfview.class);
                startActivity(go);
            }
        });
        txtAboutback = findViewById(R.id.txt_abtback);
        txtAboutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                about.super.onBackPressed();
            }
        });
    }
}