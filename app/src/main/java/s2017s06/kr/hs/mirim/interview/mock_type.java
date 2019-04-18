package s2017s06.kr.hs.mirim.interview;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class mock_type extends AppCompatActivity {
    String id, pwd, type;

    private Button camera;
    private Button next;
    private Button end;

    private String[] app = {"작년 방과후 시간에 무엇을 했나?", "어노테이션(@)와 주석의 차이는?", "디자인 패턴을 알고 있는가?", "안드로이드는 언제 배웠나요?", "ios를 배우고 싶은 마음은?",
            "알고 있는 단축키는?", "가장 잘하는 언어와 그 이유?"};

    Random rnd = new Random();
    int random = 0;
    int random2 = 0;
    char[] qChar;
    int i = 0;

    //TextView explain = findViewById(R.id.explain);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_type);

        Intent intent = getIntent();
        id = intent.getStringExtra("id_mock");
        pwd = intent.getStringExtra("pwd_mock");
        type = intent.getStringExtra("type");

        ImageView free_home = findViewById(R.id.free_home);
        free_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("passwd", pwd);
                startActivity(intent);
                finish();
            }
        });

        final TextView question=findViewById(R.id.question);

        switch (type) {
            case "app":
                random  = rnd.nextInt(app.length);
                qChar = app[random].toCharArray();
                question.setText(qChar, 0, qChar.length);
                i++;
                break;

            case "con":
                break;

            case "webD":
                break;

            case "web":
                break;

            case "data":
                break;

            case "UiUx":
                break;

            case "webP":
                break;

            case "DB":
                break;

            case "etc":
                break;

            case "ofice":
                break;
        }

        camera=(Button)findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isExistsCameraApplication()){
                    Intent cameraApp = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraApp, 10000);
                }
            }
        });

        next=findViewById(R.id.btn_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                switch (type){
                    case "app":
                        random2 = rnd.nextInt(app.length);
                        if(random2 == random) if(random2++ > app.length) random2 = 0;
                        qChar = app[random2].toCharArray();
                        question.setText(qChar, 0, qChar.length);
                        random = random2;
                        break;

                    case "con":
                        break;

                    case "webD":
                        break;

                    case "web":
                        break;

                    case "data":
                        break;

                    case "UiUx":
                        break;

                    case "webP":
                        break;

                    case "DB":
                        break;

                    case "etc":
                        break;

                    case "ofice":
                        break;

                    case "company":
                        break;
                }

                if(i == 5) next.setVisibility(View.GONE);
            }
        });

        end=findViewById(R.id.btn_end);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), mock_select.class);
                startActivity(intent);
                intent.putExtra("id", id);
                intent.putExtra("passwd", pwd);
                intent.putExtra("mock", "type");
                finish();
            }
        });
    }

    private boolean isExistsCameraApplication(){
        PackageManager packageManager = getPackageManager();

        Intent cameraApp = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        List<ResolveInfo> cameraApps = packageManager.queryIntentActivities(cameraApp, PackageManager.MATCH_DEFAULT_ONLY);

        return cameraApps.size() > 0;
    }

}
