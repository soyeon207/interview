package s2017s06.kr.hs.mirim.interview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListContent extends AppCompatActivity {

    String title, content2, nick,id_board,pwd_board;
    ImageView back_img;
    TextView con_title, con_content,con_nick;
    ListView reply_list;
    EditText reply_edit;
    Button reply_btn;
    private ArrayAdapter<String> adapter;
    List<Object> Array = new ArrayList<Object>();

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_content);

        reply_list = findViewById(R.id.reply_listview);
        reply_edit = findViewById(R.id.reply_edit);
        reply_btn = findViewById(R.id.reply_btn);

        initDatabase();

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,new ArrayList<String>());
        reply_list.setAdapter(adapter);



        Intent intent = getIntent();
        id_board=intent.getStringExtra("id_board");
        pwd_board=intent.getStringExtra("passwd_board");

        title = intent.getStringExtra("title");
        content2 = intent.getStringExtra("content2");
        nick = intent.getStringExtra("nick");

        mReference = mDatabase.getReference("board");
            mReference.child(title).child("comment").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    adapter.clear();
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()) {

                        String msg2 = snapshot.getValue().toString();
                        Array.add(msg2);
                        adapter.add(msg2);
                    }
                    adapter.notifyDataSetChanged();
                    reply_list.setSelection(adapter.getCount() - 1);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




        con_content = findViewById(R.id.content_con);
        con_nick = findViewById(R.id.content_nick);
        con_title = findViewById(R.id.content_title);

        con_content.setText(content2);
        con_nick.setText(nick);
        con_title.setText(title);

        reply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("board").child(title).child("comment").push().setValue(reply_edit.getText().toString()+" - "+nick);
                reply_edit.setText("");
            }
        });

        back_img = findViewById(R.id.back_content);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplication(),StartActivity.class);

                intent.putExtra("id",id_board);
                intent.putExtra("passwd",pwd_board);

                startActivity(intent);



            }
        });


    }

    private void initDatabase() {

        mDatabase = FirebaseDatabase.getInstance();

        mReference = mDatabase.getReference("log");
        mReference.child("log").setValue("check");

        mChild = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mReference.addChildEventListener(mChild);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mReference.removeEventListener(mChild);
    }
    }



