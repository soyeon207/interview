package s2017s06.kr.hs.mirim.interview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class mock_select extends AppCompatActivity {
    String id, pwd, mock;
    LinearLayout type, company;
    RelativeLayout app, con, webD, web, data, UiUx, webP, DB, etc, office;

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

        type = findViewById(R.id.type);
        company = findViewById(R.id.company);

        if(mock == "type"){
            type.setVisibility(View.VISIBLE);
            company.setVisibility(View.GONE);
        } else if(mock == "company"){
            company.setVisibility(View.VISIBLE);
            type.setVisibility(View.GONE);
        }

        View.OnClickListener changeActivity = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = "";

                switch (view.getId()){
                    case R.id.mock_app:
                        type = "app";
                        break;

                    case R.id.mock_content:
                        type = "con";
                        break;

                    case R.id.mock_webD:
                        type = "webD";
                        break;

                    case R.id.mock_web:
                        type = "web";
                        break;

                    case R.id.mock_data:
                        type = "data";
                        break;

                    case R.id.mock_UiUx:
                        type = "UiUx";
                        break;

                    case R.id.mock_webP:
                        type = "webP";
                        break;

                    case R.id.mock_DB:
                        type = "DB";
                        break;

                    case R.id.mock_etc:
                        type = "etc";
                        break;

                    case R.id.mock_office:
                        type = "ofice";
                        break;
                }

                Intent intent = null;

                if(mock == "type")
                     intent = new Intent(getApplicationContext(), mock_type.class);
                else if(mock == "company")
                    intent = new Intent(getApplicationContext(), mock_type.class);

                intent.putExtra("id", id);
                intent.putExtra("pwd", pwd);
                intent.putExtra("mock", mock);
                intent.putExtra("type", type);
                startActivity(intent);
                finish();
            }
        };

        if(mock == "type"){
            app = (RelativeLayout)findViewById(R.id.mock_app);
            app.setOnClickListener(changeActivity);

            con = (RelativeLayout)findViewById(R.id.mock_content);
            app.setOnClickListener(changeActivity);

            webD = (RelativeLayout)findViewById(R.id.mock_webD);
            webD.setOnClickListener(changeActivity);

            web = (RelativeLayout)findViewById(R.id.mock_web);
            web.setOnClickListener(changeActivity);

            data = (RelativeLayout)findViewById(R.id.mock_data);
            data.setOnClickListener(changeActivity);

            UiUx = (RelativeLayout)findViewById(R.id.mock_UiUx);
            UiUx.setOnClickListener(changeActivity);

            webP = (RelativeLayout)findViewById(R.id.mock_webP);
            webP.setOnClickListener(changeActivity);

            DB = (RelativeLayout)findViewById(R.id.mock_DB);
            DB.setOnClickListener(changeActivity);

            etc = (RelativeLayout)findViewById(R.id.mock_etc);
            etc.setOnClickListener(changeActivity);

            office  = (RelativeLayout)findViewById(R.id.mock_office);
            office.setOnClickListener(changeActivity);
        }
    }
}
