package com.apps.todoro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class pdfview extends AppCompatActivity {
    TextView txt_backPDF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        txt_backPDF = findViewById(R.id.txt_bckpdf);
        txt_backPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    onBackPressed();
                }
            }
        });
    }
}