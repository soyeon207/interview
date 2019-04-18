package s2017s06.kr.hs.mirim.interview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class post extends AppCompatActivity {

    ListView listView2;
    ArrayList<list_item> list_itemArrayList2;
    MyListAdapter myListAdapter2;
    String post_id,post_pwd;
    ImageView back_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        back_post = findViewById(R.id.back_post);



        Intent intent = getIntent();
        post_id=intent.getStringExtra("id");
        post_pwd=intent.getStringExtra("pwd");


        back_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplication(),StartActivity.class);

                intent.putExtra("id",post_id);
                intent.putExtra("pwd",post_pwd);

                startActivity(intent);
            }
        });

        listView2 = findViewById(R.id.post_list);
        list_itemArrayList2 = new ArrayList<list_item>();

        myListAdapter2 = new MyListAdapter(post.this,list_itemArrayList2);

        FirebaseDatabase mdatabase2 = FirebaseDatabase.getInstance();
        DatabaseReference mdatabaseRef2 = mdatabase2.getReference("user");

        mdatabaseRef2.child("id").child("board").child("content").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot message:dataSnapshot.getChildren()){

                    long now = System.currentTimeMillis();
                    Date date = new Date(now);
                    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd");
                    String formatDate = sdfNow.format(date);
                    list_itemArrayList2.add(new list_item(post_id,message.getValue().toString(),formatDate));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView2.setAdapter(myListAdapter2);

    }
}
