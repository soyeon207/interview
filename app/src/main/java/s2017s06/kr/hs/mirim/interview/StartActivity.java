package s2017s06.kr.hs.mirim.interview;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StartActivity extends TabActivity {
    TextView nickshow;
    Button exit_btn;
    ImageView re, write;
    String id,pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Intent intent = getIntent();
        id =intent.getStringExtra("id");
        pwd=intent.getStringExtra("passwd");

        re = findViewById(R.id.re);
        write = findViewById(R.id.write);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplication(),write.class);

                intent.putExtra("id_board",id);
                intent.putExtra("passwd_board",pwd);

                startActivity(intent);

            }
        });

        nickshow = findViewById(R.id.nickname_show);
        exit_btn = findViewById(R.id.exit_interview);

        exit_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(StartActivity.this);
                alert_confirm.setMessage("정말 탈퇴하시겠습니까?").setCancelable(false).setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplication(),MainActivity.class);
                                startActivity(intent);

                                FirebaseDatabase mdatabase_remove = FirebaseDatabase.getInstance();
                                DatabaseReference mdatabaseRef_remove = mdatabase_remove.getReference("user");

                                mdatabaseRef_remove.child(id).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for(DataSnapshot message:dataSnapshot.getChildren()){
                                          message.getRef().removeValue();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                                FirebaseDatabase mdatabase_remove2 = FirebaseDatabase.getInstance();
                                DatabaseReference mdatabaseRef_remove2 = mdatabase_remove2.getReference("id");

                                mdatabaseRef_remove2.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for(DataSnapshot remove_id:dataSnapshot.getChildren()){
                                            if(remove_id.getValue().toString().equals(id)){
                                                remove_id.getRef().removeValue();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }
                        }
                ).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            return;
                            }
                        });
                AlertDialog alert = alert_confirm.create();
                alert.show();

            }
        });

        FirebaseDatabase mdatabase2 = FirebaseDatabase.getInstance();
        DatabaseReference mdatabaseRef2 = mdatabase2.getReference("user");

        mdatabaseRef2.child(id).child("nick").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot message:dataSnapshot.getChildren()){
                    nickshow.setText(message.getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        final TabHost tabHost=getTabHost();
        ArrayList<TabHost.TabSpec> tabSpecs= new ArrayList<TabHost.TabSpec>();
        String[] texts={"면접", "Tip", "커뮤니티", "My"};
        for(int i = 0; i < 4; i++){
            tabSpecs.add(tabHost.newTabSpec("a" + (i+1)).setIndicator(texts[i]));
        }

        int j=0;
        for(TabHost.TabSpec tabspec:tabSpecs){
            tabspec.setContent(R.id.view1 + j);
            tabHost.addTab(tabspec);
            j++;
        }

        setTabColor(tabHost);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String arg0) {
                setTabColor(tabHost);
            }
        });

    }

    public static void setTabColor(TabHost tabhost) {

        for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {
            tabhost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.white);

            TextView tv = tabhost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#FF535353"));
        }


        TextView tv = tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).findViewById(android.R.id.title);
        tv.setTextColor(Color.parseColor("#FFFFFF"));

        tabhost.getTabWidget().setCurrentTab(0);
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab())
                .setBackgroundResource(R.color.colorMain);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
