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

    EditText name,nick,id,passwd;

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
