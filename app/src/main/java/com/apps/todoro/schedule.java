package com.apps.todoro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class schedule extends AppCompatActivity {
    TextView txtBackSchedule, lecturesTV, rulesTV, trainingTV, newsTv;

    String u = "https://ums.asu.edu.eg/";

    ImageView UMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        UMS = findViewById(R.id.ums);
        lecturesTV = findViewById(R.id.txt_mohadrat);
        UMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoums(u);
            }
        });
        txtBackSchedule = findViewById(R.id.txt_bckschudle);
        txtBackSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        lecturesTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GotoPDF = new Intent(getApplicationContext(), LocalPDf.class);
                GotoPDF.putExtra("key", "lectures");
                startActivity(GotoPDF);

            }
        });
        trainingTV = findViewById(R.id.training);
        trainingTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GotoPDF = new Intent(getApplicationContext(), LocalPDf.class);
                GotoPDF.putExtra("key", "training");
                startActivity(GotoPDF);

            }
        });
        newsTv = findViewById(R.id.news);
        newsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GotoPDF = new Intent(getApplicationContext(), LocalPDf.class);
                GotoPDF.putExtra("key", "doc");
                startActivity(GotoPDF);

            }
        });

        rulesTV = findViewById(R.id.rules);
        rulesTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GotoPDF = new Intent(getApplicationContext(), LocalPDf.class);
                GotoPDF.putExtra("key", "rules");
                startActivity(GotoPDF);

            }
        });
    }

    private void gotoums(String u) {
        Uri uri = Uri.parse(this.u);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));

    }


}
