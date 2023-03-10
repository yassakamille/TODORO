package com.apps.todoro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.security.AppUriAuthenticationPolicy;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button btnStudy;


    Button btnPlay;
    boolean pressTwiceToExit = false;


    @Override
    public void onBackPressed() {
        if (pressTwiceToExit) {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } else {
            pressTwiceToExit = true;
            Toast.makeText(this, " Press Again To Exit ", Toast.LENGTH_SHORT).show();


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    pressTwiceToExit = false;
                }
            }, 3000);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        LinearLayout Main = findViewById(R.id.main);
        AnimationDrawable Animation = (AnimationDrawable) Main.getBackground();
        Animation.setEnterFadeDuration(2500);
        Animation.setExitFadeDuration(5000);
        Animation.start();
        btnStudy = findViewById(R.id.btn_stdy);
        btnPlay = findViewById(R.id.btn_ply);


        btnStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotostudy = new Intent(MainActivity.this, homepage.class);
                startActivity(gotostudy);

            }

            ;
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gottudy = new Intent(MainActivity.this, Regisration_Activity.class);
                startActivity(gottudy);

            }

            ;
        });

    }
}