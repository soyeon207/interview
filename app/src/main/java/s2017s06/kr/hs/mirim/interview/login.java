package s2017s06.kr.hs.mirim.interview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    EditText loginid,loginpwd;
    Button loginbtn;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textView = findViewById(R.id.loginsignup);
        loginid = findViewById(R.id.loginid);
        loginpwd = findViewById(R.id.loginpwd);

        loginbtn = findViewById(R.id.loginbt);

        loginbtn.setOnClickListener(new View.OnClickListener() {

        class check{
            int idcheck=0,passwordcheck=0;
        }
            check c = new check();

            @Override
            public void onClick(View v) {



                FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();
                DatabaseReference mdatabaseRef = mdatabase.getReference("id");

                mdatabaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot messeage : dataSnapshot.getChildren()){
                            if(messeage.getValue().toString().equals(loginid.getText().toString())) {
                                c.idcheck=1;
                            }
                        }

                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                    FirebaseDatabase mdatabasepwd = FirebaseDatabase.getInstance();
                    DatabaseReference mdatabaseRefpwd = mdatabasepwd.getReference("user");

                    mdatabaseRefpwd.child(loginid.getText().toString()).child("passwd").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot messeage : dataSnapshot.getChildren()) {
                                if (messeage.getValue().toString().equals(loginpwd.getText().toString())) {
                                    if (c.idcheck == 1) {
                                        Toast.makeText(login.this, "로그인 완료!", Toast.LENGTH_SHORT).show();
                                        c.passwordcheck=1;
                                        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                                        intent.putExtra("id",loginid.getText().toString());
                                        intent.putExtra("passwd",loginpwd.getText().toString());
                                        startActivity(intent);
                                    }
                                }
                            }
                            if(!(c.idcheck==1&&c.passwordcheck==1)){
                                Toast.makeText(login.this, "아이디나 비밀번호가 잘못되었습니다. 다시 입력해주세요", Toast.LENGTH_SHORT).show();
                                loginid.setText("");
                                loginpwd.setText("");
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getApplication(),signup.class);
               startActivity(intent);
            }
        });

    }
}
