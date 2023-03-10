package com.apps.todoro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class homepage extends AppCompatActivity {
    TextView txtStart;
    TextView txtSchedule;
    TextView txtAbout;

    TextView txtExit;
    ImageView imgFacebook;
    ImageView imgTwitter;
    ImageView imgInsatagrm;
    String f = "https://www.facebook.com/Todoro-102163912617101";
    String i = "https://www.instagram.com/todoro_1/";
    String t = "https://twitter.com/TODORO20427850";

    AlertDialog.Builder Alertbuild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        txtAbout = findViewById(R.id.txt_abt);
        txtStart = findViewById(R.id.txt_strt);
        txtExit = findViewById(R.id.txt_ext);
        imgFacebook = findViewById(R.id.img_facebook);
        imgTwitter = findViewById(R.id.img_twitter);
        imgInsatagrm = findViewById(R.id.img_instagram);
        Alertbuild = new AlertDialog.Builder(this);
        txtSchedule = findViewById(R.id.txtschedule);
        txtSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoschedule = new Intent(homepage.this, schedule.class);
                startActivity(gotoschedule);
            }
        });


        Alertbuild.setIcon(R.drawable.b).setMessage("Do You Want To Exit ? ").setTitle("Exit")
                .setCancelable(false).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }

                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(0);
                    }


                });
        // Alert Create
        txtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotostart = new Intent(homepage.this, startactivity.class);
                startActivity(gotostart);
            }
        });

        txtAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoabout = new Intent(homepage.this, about.class);
                startActivity(gotoabout);

            }
        });


        txtExit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                AlertDialog alertDialog = Alertbuild.create();
                alertDialog.show();
                // Alert Calling
            }
        });


        imgFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Gotofacebook(f);

            }
        });

        imgInsatagrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Gotoinstagram(i);

            }
        });

        imgTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Gototwitter(t);

            }
        });
    }

    private void Gototwitter(String t) {
        Uri uri = Uri.parse(this.t);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    private void Gotoinstagram(String i) {
        Uri uri = Uri.parse(this.i);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    private void Gotofacebook(String f) {
        Uri uri = Uri.parse(this.f);
        startActivity(new Intent(Intent.ACTION_VIEW, uri)
        );


    }

}

