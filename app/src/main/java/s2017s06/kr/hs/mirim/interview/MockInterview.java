package s2017s06.kr.hs.mirim.interview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MockInterview extends AppCompatActivity {
    String id,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_interview);

        Intent intent = getIntent();
         id = intent.getStringExtra("id_mock");
         pwd = intent.getStringExtra("pwd_mock");

        ImageView free_home=findViewById(R.id.free_home);
        free_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), StartActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("passwd",pwd);
                startActivity(intent);
            }
        });

    }
}