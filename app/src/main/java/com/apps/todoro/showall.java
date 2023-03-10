package com.apps.todoro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.media.MediaPlayer;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class showall extends AppCompatActivity {
    TextView txt_BckShow;
    ListView subjectlist;
    MediaPlayer song;
    public KonfettiView celebrate;

    ImageView imgSubmenu;
    ArrayAdapter<String> subjectAdapter;
    SubjectDBHelper subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showall);
        song = MediaPlayer.create(showall.this, R.raw.success);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        celebrate = findViewById(R.id.celebrate);

        subjectlist = (ListView) findViewById(R.id.ListView1);
        subjectAdapter =new ArrayAdapter<String>(getApplicationContext(), R.layout.showall);
        subjectlist.setAdapter(subjectAdapter);
      subject = new SubjectDBHelper(getApplicationContext());
       // Cursor cursor =subject.fetchAllsubjects();
        //while (!cursor.isAfterLast()) {
          //  subjectAdapter.add(cursor.getString(0));
            //cursor.moveToNext();
       //}
        txt_BckShow = findViewById(R.id.txt_bckshow);
        txt_BckShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imgSubmenu = findViewById(R.id.img_sbmenu);
        imgSubmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(showall.this, imgSubmenu);
                popupMenu.getMenuInflater().inflate(R.menu.deleteshow, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.itm_clrall:
                                celebrate.setVisibility(View.VISIBLE);
                                subjectlist.setVisibility(View.GONE);
                                playit();
                                celebrate.build().addColors(Color.RED, Color.GREEN, Color.YELLOW, Color.MAGENTA)
                                        .setDirection(0.0, 359.0)
                                        .setSpeed(1f, 5f)
                                        .setFadeOutEnabled(true)
                                        .setTimeToLive(2000L)
                                        .addShapes(Shape.RECT, Shape.CIRCLE)
                                        .addSizes(new Size(12, 5))
                                        .setPosition(-50f, celebrate.getWidth() + 50f, -50f, -50f)
                                        .streamFor(300, 5000L);
                                subject.deleteall();
                                subjectAdapter.clear();
                                subjectAdapter.notifyDataSetChanged();
                                subject.subjectDatabase.close();
                        }
                        return false;
                    }
                });

                popupMenu.show();
            }
        });
        subjectlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView r = (TextView) view;
                r.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PopupMenu popupMenu = new PopupMenu(showall.this, r);
                        popupMenu.getMenuInflater().inflate(R.menu.popupdelete, popupMenu.getMenu());
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @SuppressLint("ResourceType")
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {

                                String s = r.getText().toString();
                                subject.delete(s);
                                subjectAdapter.remove(s);
                                subjectAdapter.notifyDataSetChanged();
                                subject.subjectDatabase.close();


                                return false;
                            }
                        });
                        popupMenu.show();
                    }
                });
            }
        });
    }

    public void playit() {

        song.start();
    }
}