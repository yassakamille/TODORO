package com.apps.todoro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;

public class Regisration_Activity extends AppCompatActivity {
    TextView txtBackregestir;
    MediaPlayer ox;
    int player = 0; //0=O 1=X
    boolean gameIsOver = true;
    boolean gameisactive = true, somewonwon = false;
    int WinPosition[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}, {1, 4, 7}};
    int gamestate[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisration);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        txtBackregestir = findViewById(R.id.txtbckrgstr);
        ox = MediaPlayer.create(Regisration_Activity.this, R.raw.ox);
        txtBackregestir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void drop(View view) {
        ImageView xoImage = (ImageView) view;
        int tapped = Integer.parseInt(xoImage.getTag().toString());
        if (gamestate[tapped] == 2) {
            gamestate[tapped] = player;
            playox();
            xoImage.setTranslationY(-1000f);
            if (player == 0) {
                xoImage.setImageResource(R.drawable.o);
                player = 1;
            } else {
                xoImage.setImageResource(R.drawable.x);
                player = 0;
            }
            xoImage.animate().translationYBy(1000f).rotation(360).setDuration(600);

        }
        for (int[] WinPositions : WinPosition) {
            if (gamestate[WinPositions[0]] != 2 &&
                    gamestate[WinPositions[0]] == gamestate[WinPositions[1]] &&
                    gamestate[WinPositions[1]] == gamestate[WinPositions[2]]) {
                gameisactive = false;
                somewonwon = true;
                String Winner = "X";
                if (gamestate[WinPositions[0]] == 0) {
                    Winner = "O";
                }
                LinearLayout linearwinner = findViewById(R.id.winner);
                linearwinner.setVisibility(View.VISIBLE);
                TextView txWinner = findViewById(R.id.txwinners);
                txWinner.setText("The Winner is " + Winner);

            } else {
                gameIsOver = true;
                for (int state : gamestate) {
                    if (state == 2) {
                        gameIsOver = false;
                    }
                }

                if (gameIsOver && !somewonwon) {
                    TextView txWinner = findViewById(R.id.txwinners);
                    txWinner.setText(" Game Over It's Draw");
                    LinearLayout linearwinner = findViewById(R.id.winner);
                    linearwinner.setVisibility(View.VISIBLE);

                }
            }
        }
    }

    public void play(View view) {
        gameisactive = true;
        somewonwon = false;
        LinearLayout Winner = findViewById(R.id.winner);
        Winner.setVisibility(View.INVISIBLE);
        player = 0;
        for (int i = 0; i < gamestate.length; i++) {
            gamestate[i] = 2;
        }
        GridLayout Grid = findViewById(R.id.gridlayout);
        for (int i = 0; i < Grid.getChildCount(); i++) {
            ((ImageView) Grid.getChildAt(i)).setImageResource(0);

        }
    }

    public void playox() {

        ox.start();
    }
}
