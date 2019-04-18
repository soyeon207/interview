package s2017s06.kr.hs.mirim.interview;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class signup extends AppCompatActivity {

    EditText name,nick,id,passwd,checkpasswd;
    Button checkid;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button sign = findViewById(R.id.sign);
         name = findViewById(R.id.name);
         nick = findViewById(R.id.nick);
         id = findViewById(R.id.id);
         passwd = findViewById(R.id.passwd);
        checkid = findViewById(R.id.checkid);

        checkid.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();
            DatabaseReference mdatabaseRef = mdatabase.getReference("id");

            mdatabaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot messeage : dataSnapshot.getChildren()){
                        String idcheck = messeage.getValue().toString();
                       if(idcheck.equals(id.getText().toString())){
                           id.setText("");

                           new AlertDialog.Builder(signup.this)
                                   .setTitle("알림")
                                   .setMessage("이미 있는 아이디 입니다. 다른 아이디를 입력해주세요")
                                   .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                                       @Override
                                       public void onClick(DialogInterface dialog, int which) {

                                       }
                                   }).show();
                       id.setText("");
                       }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String room = id.getText().toString();
                    databaseReference.child("id").push().setValue(room);
                    databaseReference.child("user").child(room).child("name").push().setValue(name.getText().toString());
                    databaseReference.child("user").child(room).child("nick").push().setValue(nick.getText().toString());
                    databaseReference.child("user").child(room).child("passwd").push().setValue(passwd.getText().toString());

                    Intent intent = new Intent(getApplication(),StartActivity.class);
                     intent.putExtra("id",room);
                     intent.putExtra("pwd",passwd.getText().toString());
                    startActivity(intent);

            }
        });
    }
}
