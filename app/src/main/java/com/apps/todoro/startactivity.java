package com.apps.todoro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class startactivity extends AppCompatActivity {
    TextView txtBackStart;
    TextView txtNo;
    EditText edtAddSubject;
    ImageView imgAdd;
    TextView SB;
    ImageView imgSubmenu;
    List<String> todolist;
    ListView Listsubject;
    Switch swtch;
    SubjectDBHelper subject;
    ArrayAdapter<String>  arryadabter;
    ImageView imgTips;
    AlertDialog.Builder Alertbuild;
    ViewGroup tocontanier;
    private int[] images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startactivity);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        subject = new SubjectDBHelper(getApplicationContext());

        SB = findViewById(R.id.sb);

        Button add = (Button) findViewById(R.id.btn_add);
        Button show = (Button) findViewById(R.id.btn_show);
        final EditText name = (EditText) findViewById(R.id.txt_name);

        final SubjectDBHelper newsubject = new SubjectDBHelper(this);

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                while (add.hasOnClickListeners()) {
                    String subject = name.getText().toString().trim();

                    if (!subject.isEmpty()) {
                       // newsubject.createNewsubject(name.getText().toString());
                        Toast.makeText(startactivity.this, subject + " has been added", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        SB.setVisibility(View.VISIBLE);
                        txtNo.setVisibility(View.GONE);
                        Listsubject.setVisibility(View.VISIBLE);
                        SB.setText("" + subject);
                        break;
                    } else {
                        Toast.makeText(startactivity.this, " Enter Subject First", Toast.LENGTH_SHORT).show();
                        break;
                    }

                }

            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent show_all = new Intent(startactivity.this,showall.class);
                startActivity(show_all);
            }
        });

        LinearLayout linearLayout = findViewById(R.id.start);
        imgAdd = findViewById(R.id.img_addsbjct);
        txtNo = findViewById(R.id.txt_no);
        edtAddSubject = findViewById(R.id.edt_addsbjct);
        tocontanier = findViewById(R.id.transitioncontanier);
        imgTips = findViewById(R.id.img_tps);
        Listsubject = findViewById(R.id.lstvw_sbjct);
        swtch = findViewById(R.id.s_btn);
        Alertbuild = new AlertDialog.Builder(this);

        imgAdd.setOnClickListener(new View.OnClickListener() {


            public void additem(View e) {
                if (!SB.getText().toString().trim().isEmpty() || !edtAddSubject.getText().toString().trim().isEmpty()) {
                    todolist.add(edtAddSubject.getText().toString());
                    arryadabter.notifyDataSetChanged();
                    edtAddSubject.setText(" ");
                } else {
                    Toast.makeText(startactivity.this, "Please enter subject first", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onClick(View b) {
                boolean visible = false;
                String s = edtAddSubject.getText().toString().trim();
                while (b.hasOnClickListeners()) {

                    if (s.isEmpty() || SB.getText().toString().isEmpty()) {

                        Toast.makeText(startactivity.this, "Enter Subject First", Toast.LENGTH_SHORT).show();
                        TransitionManager.beginDelayedTransition(tocontanier);
                        visible = !visible;
                        txtNo.setVisibility(visible ? View.VISIBLE : View.GONE);

                        break;

                    }

                    additem(b);
                    boolean Vis = true;
                    TransitionManager.beginDelayedTransition(tocontanier);
                    Vis = !Vis;
                    Listsubject.setVisibility(Vis ? View.VISIBLE : View.VISIBLE);
                    Vis = !Vis;
                    TransitionManager.beginDelayedTransition(tocontanier);
                    txtNo.setVisibility(Vis ? View.VISIBLE : View.GONE);
                    SB.setVisibility(View.VISIBLE);
                    break;

                }

                boolean Vis = false;
                TransitionManager.beginDelayedTransition(tocontanier);
                txtNo.setVisibility(Vis ? View.VISIBLE : View.GONE);
                return;

            }
        });

        edtAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.cancelDragAndDrop();

            }
        });

        txtBackStart = findViewById(R.id.txt_bckstrt);
        txtBackStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }

        });

        imgSubmenu = findViewById(R.id.img_sbmenu);
        imgSubmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(startactivity.this, imgSubmenu);
                popupMenu.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_disppr:

                                boolean visible = true;
                                TransitionManager.beginDelayedTransition(tocontanier);
                                visible = !visible;
                                Listsubject.setVisibility(visible ? View.VISIBLE : View.GONE);
                                TransitionManager.beginDelayedTransition(tocontanier);
                                Toast.makeText(startactivity.this, "All items disappeared", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.itm_clrall:
                                boolean vis = true;
                                txtNo.setVisibility(vis ? View.VISIBLE : View.VISIBLE);
                                SB.setVisibility(View.GONE);
                                TransitionManager.beginDelayedTransition(tocontanier);
                                vis = !vis;
                                Listsubject.setVisibility(vis ? View.VISIBLE : View.GONE);
                                arryadabter.clear();
                                arryadabter.notifyDataSetChanged();
                                Toast.makeText(startactivity.this, "All Items Cleared", Toast.LENGTH_SHORT).show();
                                SB.setText("");
                                return true;
                            case R.id.item_appr:
                                boolean Vis = true;
                                TransitionManager.beginDelayedTransition(tocontanier);
                                Vis = !Vis;
                                Listsubject.setVisibility(Vis ? View.VISIBLE : View.VISIBLE);
                                Toast.makeText(startactivity.this, "All items Appeared", Toast.LENGTH_SHORT).show();
                                TransitionManager.beginDelayedTransition(tocontanier);
                                txtNo.setVisibility(Vis ? View.VISIBLE : View.GONE);
                                SB.setVisibility(View.VISIBLE);
                                return true;
                        }
                        return false;
                    }
                });

                popupMenu.show();
            }
        });

        todolist = new ArrayList<>();
        arryadabter = new ArrayAdapter<>(this, R.layout.todo, todolist);
        Listsubject.setAdapter(arryadabter);

        imgTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Tips[] = {"ابدأ بالممكن ثُم انتقل للصعب , فجأة تجد نفسك تصنع المستحيل",
                        "الخيال أهم من المعرفة,فهو يحيط بالعالم",
                        "الذكاء هو القدرة علي التأقلم مع التغيير ",
                        "لا تكافح من أجل النجاح بل كافح من أجل أن تكون ذو قيمة",
                        "أنت لا تخسر حقا الا اذا توقفت عن المحاولة",
                        "أعمل بجد و في صمت , و أجعل نجاحك يحدث الضجيج",
                        "افعل اليوم ما سوف تشكر عليه نفسك مستقبلا",
                        "الجميع عباقرة ولكن ان حكمت علي قدرة سمكة في تسلق شجرة , ستعيش حياتها و هي تؤمن انها غبية ",
                        "لا يزال المرء عالما مادام يسعي في طلب العلم, فأذا ظن أنه علم فقد بدأ جهله ",
                        "النجاح ليس عدم فعل الأخطاء, بل النجاح هو عدم تكرار الأخطاء ",
                        "المثقفون يسعون لحل المشاكل بعد وقوعها , أما العباقرة يسعون لمنعها قبل أن تبدأ "
                };

                images = new int[12];
                images[0] = R.drawable.tesla;
                images[1] = R.drawable.isac;
                images[2] = R.drawable.billgates;
                images[3] = R.drawable.einstein;
                images[4] = R.drawable.darwin;
                images[5] = R.drawable.stephen;
                images[6] = R.drawable.stevejobs;
                images[7] = R.drawable.elonmusk;
                images[8] = R.drawable.decart;
                images[9] = R.drawable.darwin1;
                images[10] = R.drawable.stevejobs1;
                images[11] = R.drawable.einstein3;

                Random randm = new Random();
                int index = randm.nextInt(Tips.length);
                int icons = randm.nextInt(images.length);
                Alertbuild.setIcon(images[icons]).setMessage(Tips[index]).setTitle("Tip Of Today")
                        .setCancelable(true);

                AlertDialog alertDialog = Alertbuild.create();
                alertDialog.show();
            }
        });

        Listsubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView r = (TextView) view;
                r.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PopupMenu popupMenu = new PopupMenu(startactivity.this, r);
                        popupMenu.getMenuInflater().inflate(R.menu.delete_pomodoro, popupMenu.getMenu());
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @SuppressLint("ResourceType")
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                String section = r.getText().toString().trim();
                                String subject = SB.getText().toString().trim();

                                switch (menuItem.getItemId()) {
                                    case R.id.itm_cmplt_note:


                                        r.setPaintFlags(r.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                                        arryadabter.notifyDataSetChanged();
                                        return true;
                                    case R.id.itm_pmdr:

                                        Intent timer = new Intent(startactivity.this, com.apps.todoro.timer.class);
                                        timer.putExtra("subject", subject);
                                        timer.putExtra("section", section);
                                        startActivity(timer);
                                        return true;

                                }

                                return false;
                            }
                        });
                        popupMenu.show();


                    }

                });
            }
        });

        SharedPreferences checked = getSharedPreferences("checked", 0);
        boolean b = checked.getBoolean("night", true);
        if (b) {

            swtch.setChecked(true);
            linearLayout.setBackgroundColor(Color.DKGRAY);

        }

        swtch = findViewById(R.id.s_btn);

        swtch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    linearLayout.setBackgroundColor(Color.DKGRAY);
                    SharedPreferences.Editor editor = checked.edit();
                    editor.putBoolean("night", true);
                    editor.commit();

                } else {
                    swtch.setChecked(false);
                    linearLayout.setBackgroundColor(Color.LTGRAY);
                    SharedPreferences.Editor editor = checked.edit();
                    editor.putBoolean("night", false);
                    editor.commit();
                }
            }
        });
    }
}























