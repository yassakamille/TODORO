package com.apps.todoro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

public class LocalPDf extends AppCompatActivity {

    PDFView pdfviewLectures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_pdf);
//        Toast.makeText(this, "hello world", Toast.LENGTH_SHORT).show();
        pdfviewLectures = findViewById(R.id.pdfView);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("key");
            if (value.equals("lectures")) {
                pdfviewLectures.fromAsset("Mainstream_Lectures_Labs_Summer22.pdf").load();
            } else if (value.equals("rules")) {
                pdfviewLectures.fromAsset("FCISBOARD.pdf").load();
            } else if (value.equals("doc")) {
                pdfviewLectures.fromAsset("Doc.pdf").load();
            } else if (value.equals("training")) {
                pdfviewLectures.fromAsset("training.pdf").load();
            }
            //The key argument here must match that used in the other activity
        }

    }
}