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

                switch (view.getId()){
                    case R.id.mock1:
                        if(mock == "type") type = "app";
                        break;

                    case R.id.mock2:
                        if(mock == "type") type = "con";
                        break;

                    case R.id.mock3:
                        if(mock == "type") type = "webD";
                        break;

                    case R.id.mock4:
                        if(mock == "type") type = "web";
                        break;

                    case R.id.mock5:
                        if(mock == "type") type = "data";
                        break;

                    case R.id.mock6:
                        if(mock == "type") type = "UiUx";
                        break;

                    case R.id.mock7:
                        if(mock == "type") type = "webP";
                        break;

                    case R.id.mock8:
                        if(mock == "type") type = "DB";
                        break;

                    case R.id.mock9:
                        if(mock == "type") type = "etc";
                        break;

                    case R.id.mock10:
                        if(mock == "type") type = "ofice";
                        break;
                }

                Intent intent = null;

                if(mock.equals("type"))
                     intent = new Intent(getApplicationContext(), mock_type.class);
                else if(mock.equals("company"))
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

        //if절 안으로 들어가지 않는 듯함. 위의 setText는 적용이 되지만 아래부터는 안되고 있을 가능성이 다분.
        if(mock == "type"){
            mock1 = (RelativeLayout)findViewById(R.id.mock1);
            text = findViewById(R.id.text_mock1);
            text.setText("APP 개발");
            mock1.setOnClickListener(changeActivity);

            mock2 = (RelativeLayout)findViewById(R.id.mock2);
            text = findViewById(R.id.text_mock2);
            text.setText("CONTENT 디자인");
            mock2.setOnClickListener(changeActivity);

            mock3 = (RelativeLayout)findViewById(R.id.mock3);
            text = findViewById(R.id.text_mock3);
            text.setText("WEB 디자인");
            mock3.setOnClickListener(changeActivity);

            mock4 = (RelativeLayout)findViewById(R.id.mock4);
            text = findViewById(R.id.text_mock4);
            text.setText("WEB 개발");
            mock4.setOnClickListener(changeActivity);

            mock5 = (RelativeLayout)findViewById(R.id.mock5);
            text = findViewById(R.id.text_mock5);
            text.setText("정보 시스템 / 보안");
            mock5.setOnClickListener(changeActivity);

            mock6 = (RelativeLayout)findViewById(R.id.mock6);
            text = findViewById(R.id.text_mock6);
            text.setText("UI/UX 디자인");
            mock6.setOnClickListener(changeActivity);

            mock7 = (RelativeLayout)findViewById(R.id.mock7);
            text = findViewById(R.id.text_mock7);
            text.setText("WEB PUBLISHING");
            mock7.setOnClickListener(changeActivity);

            mock8 = (RelativeLayout)findViewById(R.id.mock8);
            text = findViewById(R.id.text_mock8);
            text.setText("DATEBASE");
            mock8.setOnClickListener(changeActivity);

            mock9 = (RelativeLayout)findViewById(R.id.mock9);
            text = findViewById(R.id.text_mock9);
            text.setText("기획 / 테스팅");
            mock9.setOnClickListener(changeActivity);

            mock10 = (RelativeLayout)findViewById(R.id.mock10);
            text = findViewById(R.id.text_mock10);
            text.setText("사무직");
            mock10.setOnClickListener(changeActivity);
        }
    }
}
