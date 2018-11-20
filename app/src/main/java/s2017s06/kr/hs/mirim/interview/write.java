package s2017s06.kr.hs.mirim.interview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class write extends AppCompatActivity {
    String id_board,pwd_board;
    EditText title,content;
    Button post_btn;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        title = findViewById(R.id.board_title);
        content = findViewById(R.id.board_content);

        post_btn = findViewById(R.id.post_btn);

        Intent intent = getIntent();
        id_board=intent.getStringExtra("id_board");
        pwd_board=intent.getStringExtra("passwd_board");


        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("user").child(id_board).child("board").child("title").push().setValue(title.getText().toString());
                databaseReference.child("user").child(id_board).child("board").child("content").push().setValue(content.getText().toString());

                Toast.makeText(getApplication(),"성공적으로 게시 되었습니다",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
