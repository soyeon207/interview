package s2017s06.kr.hs.mirim.interview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class mock_select extends AppCompatActivity {
    String id, pwd, mock;
    RelativeLayout mock1, mock2, mock3, mock4, mock5, mock6, mock7, mock8, mock9, mock10;
    TextView text;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_select);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        pwd = intent.getStringExtra("pwd");
        mock = intent.getStringExtra("mock");
        //Toast.makeText(getApplicationContext(), mock, Toast.LENGTH_LONG).show();

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

        View.OnClickListener changeActivity = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = "";
                String company = "";

                switch (view.getId()) {
                    case R.id.mock1:
                        if (mock.equals("type")) type = "app";
                        if(mock.equals("company")) company = "global";
                        break;

                    case R.id.mock2:
                        if (mock.equals("type")) type = "uiux";
                        if(mock.equals("company")) company = "navi";
                        break;

                    case R.id.mock3:
                        if (mock.equals("type")) type = "webP";
                        if(mock.equals("company")) company = "shin";
                        break;

                    case R.id.mock4:
                        if (mock == "type") type = "webD";
                        if(mock.equals("company")) company = "sqi";
                        break;

                    case R.id.mock5:
                        if (mock.equals("type")) type = "plat";
                        if(mock.equals("company")) company = "lg";
                        break;

                    case R.id.mock6:
                        if (mock.equals("type")) type = "manage";
                        if(mock.equals("company")) company = "covision";
                        break;

                }

                Intent intent = null;

                if (mock.equals("type"))
                    intent = new Intent(getApplicationContext(), mock_type.class);
                else if (mock.equals("company"))
                    intent = new Intent(getApplicationContext(), mock_company.class);

                intent.putExtra("id", id);
                intent.putExtra("pwd", pwd);
                intent.putExtra("type", type);
                intent.putExtra("company", company);
                startActivity(intent);
                finish();
            }
        };

        text = findViewById(R.id.text_mock1);
        text.setText(mock);

        switch (mock) {
            case "type":
                mock1 = (RelativeLayout) findViewById(R.id.mock1);
                text = findViewById(R.id.text_mock1);
                text.setText("APP 개발");
                mock1.setOnClickListener(changeActivity);

                mock2 = (RelativeLayout) findViewById(R.id.mock2);
                text = findViewById(R.id.text_mock2);
                text.setText("UI/UX 디자인");
                mock2.setOnClickListener(changeActivity);

                mock3 = (RelativeLayout) findViewById(R.id.mock3);
                text = findViewById(R.id.text_mock3);
                text.setText("WEB 개발");
                mock3.setOnClickListener(changeActivity);

                mock4 = (RelativeLayout) findViewById(R.id.mock4);
                text = findViewById(R.id.text_mock4);
                text.setText("CONTENT/WEB 디자인");
                mock4.setOnClickListener(changeActivity);

                mock5 = (RelativeLayout) findViewById(R.id.mock5);
                text = findViewById(R.id.text_mock5);
                text.setText("플랫폼 개발");
                mock5.setOnClickListener(changeActivity);

                mock6 = (RelativeLayout) findViewById(R.id.mock6);
                text = findViewById(R.id.text_mock6);
                text.setText("운영/관리");
                mock6.setOnClickListener(changeActivity);

                break;

            case "company":
                mock1 = (RelativeLayout) findViewById(R.id.mock1);
                text = findViewById(R.id.text_mock1);
                text.setText("글로벌 휴머니즘");
                mock1.setOnClickListener(changeActivity);

                mock2 = (RelativeLayout) findViewById(R.id.mock2);
                text = findViewById(R.id.text_mock2);
                text.setText("네비웍스");
                mock2.setOnClickListener(changeActivity);

                mock3 = (RelativeLayout) findViewById(R.id.mock3);
                text = findViewById(R.id.text_mock3);
                text.setText("신영증권");
                mock3.setOnClickListener(changeActivity);

                mock4 = (RelativeLayout) findViewById(R.id.mock4);
                text = findViewById(R.id.text_mock4);
                text.setText("SQI 소프트");
                mock4.setOnClickListener(changeActivity);

                mock5 = (RelativeLayout) findViewById(R.id.mock5);
                text = findViewById(R.id.text_mock5);
                text.setText("LG히다찌");
                mock5.setOnClickListener(changeActivity);

                mock6 = (RelativeLayout) findViewById(R.id.mock6);
                text = findViewById(R.id.text_mock6);
                text.setText("코비젼");
                mock6.setOnClickListener(changeActivity);

                break;
        }
    }
}