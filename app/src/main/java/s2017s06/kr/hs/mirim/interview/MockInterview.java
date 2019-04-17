package s2017s06.kr.hs.mirim.interview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MockInterview extends AppCompatActivity {
    String id, pwd, select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_interview);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        pwd = intent.getStringExtra("pwd");

        ImageView free_home = findViewById(R.id.free_home);
        free_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("pwd", pwd);
                startActivity(intent);
                finish();
            }
        });

        RelativeLayout type = findViewById(R.id.type);
        type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = "type";
                Intent intent = new Intent(getApplicationContext(), mock_select.class);
                intent.putExtra("id", id);
                intent.putExtra("pwd", pwd);
                intent.putExtra("mock", select);
                startActivity(intent);
                finish();
            }
        });

        RelativeLayout company = findViewById(R.id.company);
        company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = "company";
                Intent intent = new Intent(getApplicationContext(), mock_select.class);
                intent.putExtra("id", id);
                intent.putExtra("pwd", pwd);
                intent.putExtra("mock", select);
                startActivity(intent);
                finish();
            }
        });

    }
}
