package s2017s06.kr.hs.mirim.interview;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class StartActivity extends TabActivity {
    TextView nickshow,interview;
    LinearLayout Mock,Free;
    TextView exit_btn,watch_btn;
    ImageView write;
    String pwd2,id2;
    int positions;
    ListView listView;
    MyListAdapter myListAdapter;
    ArrayList<list_item> list_itemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Intent intent = getIntent();
        id2 =intent.getStringExtra("id");
        pwd2=intent.getStringExtra("pwd");

        listView = findViewById(R.id.start_listview);
        list_itemArrayList = new ArrayList<list_item>();


        myListAdapter = new MyListAdapter(StartActivity.this,list_itemArrayList);

        FirebaseDatabase mdatabase2 = FirebaseDatabase.getInstance();
        DatabaseReference mdatabaseRef2 = mdatabase2.getReference("board_value");

        mdatabaseRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot message:dataSnapshot.getChildren()){

                    long now = System.currentTimeMillis();
                    Date date = new Date(now);
                    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd");
                    String formatDate = sdfNow.format(date);
                    list_itemArrayList.add(new list_item(message.getValue().toString(),message.getKey(),formatDate));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setAdapter(myListAdapter);

        Mock = findViewById(R.id.Mock);
        Free = findViewById(R.id.Free);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {

                positions = position;

                FirebaseDatabase lion = FirebaseDatabase.getInstance();
                DatabaseReference lion2 = lion.getReference("board");

                lion2.child(list_itemArrayList.get(position).getTitle()).child("content").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot message:dataSnapshot.getChildren()){
                            Intent intent = new Intent(getApplication(),ListContent.class);
                            intent.putExtra("content2",message.getValue().toString());
                            intent.putExtra("title",list_itemArrayList.get(positions).getTitle());
                            intent.putExtra("nick",list_itemArrayList.get(positions).getNickname());
                            intent.putExtra("id_board",id2);
                            intent.putExtra("passwd_board",pwd2);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        write = findViewById(R.id.write);

        Mock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),MockInterview.class);
                intent.putExtra("id",id2);
                intent.putExtra("pwd",pwd2);
                startActivity(intent);
            }
        });

        Free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),FreeInterview.class);
                intent.putExtra("id",id2);
                intent.putExtra("pwd",pwd2);
                startActivity(intent);
            }
        });

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplication(),write.class);

                intent.putExtra("id",id2);
                intent.putExtra("pwd",pwd2);

                startActivity(intent);

            }
        });

        nickshow = findViewById(R.id.nickname_show);
        exit_btn = findViewById(R.id.exit_interview);
        watch_btn = findViewById(R.id.watch);

        watch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));        //저장되어있는 스토리지에 미디어폴더로 접근하여 겔러리를 보여준다.
                startActivity((mIntent));

            }
        });

        exit_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(StartActivity.this);
                alert_confirm.setMessage("정말 탈퇴하시겠습니까?").setCancelable(false).setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplication(),login.class);
                                startActivity(intent);

                                FirebaseDatabase mdatabase_remove = FirebaseDatabase.getInstance();
                                DatabaseReference mdatabaseRef_remove = mdatabase_remove.getReference("user");

                                mdatabaseRef_remove.child(id2).addValueEventListener(new ValueEventListener() {
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
                                            if(remove_id.getValue().toString().equals(id2)){
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

        FirebaseDatabase list = FirebaseDatabase.getInstance();
        DatabaseReference listRef = list.getReference("user");

        listRef.child(id2).child("nick").addValueEventListener(new ValueEventListener() {
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

    public void mOnPopupClick(View v){
        //데이터 담아서 팝업(액티비티) 호출
        Intent intent = new Intent(this, Popup.class);
        intent.putExtra("data", "Test Popup");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                String result = data.getStringExtra("result");

            }
        }
    }

    public static void setTabColor(TabHost tabhost) {

        for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {
            tabhost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.white);

            TextView tv = tabhost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#404040"));
        }


        TextView tv = tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).findViewById(android.R.id.title);
        tv.setTextColor(Color.parseColor("#FFFFFF"));

        tabhost.getTabWidget().setCurrentTab(0);
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab())
                .setBackgroundResource(R.color.m);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    @Override public void onBackPressed() { }
}



