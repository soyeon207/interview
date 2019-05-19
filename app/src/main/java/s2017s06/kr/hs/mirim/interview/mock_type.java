package s2017s06.kr.hs.mirim.interview;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class mock_type extends AppCompatActivity {
    String id, pwd, type;

    private Button camera;
    private Button next;
    private Button end;

    Random rnd = new Random();
    int random = 0;
    int random2 = 0;
    char[] qChar;
    int i = 0;
    int j = 0;

    private TextToSpeech tts;
    private Button btSpeak;

    private String[] app = {"작년 방과후 시간에 무엇을 했나?", "어노테이션(@)와 주석의 차이는?", "디자인 패턴을 알고 있는가?", "안드로이드는 언제 배웠나요?", "ios를 배우고 싶은 마음은?",
            "알고 있는 단축키는?", "가장 잘하는 언어와 그 이유?", "안드로이드는 언제 배웠나요?", "ios를 배우고 싶은 마음은 있나요?"};

    private String[] con = {"진행했던 프로젝트와 했던 일을 말해주세요", "ddd", "fff", "ggg", "eee"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_type);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        pwd = intent.getStringExtra("pwd");
        type = intent.getStringExtra("type");

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

        //tts = new TextToSpeech(this, this); //첫번째는 context, 두번째는 리스너

        btSpeak = (Button) findViewById(R.id.speak);

        final TextView question=findViewById(R.id.question);

        switch (type) {
            case "app":
                random  = rnd.nextInt(app.length);
                qChar = app[random].toCharArray();
                question.setText(qChar, 0, qChar.length);
                j = i;
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

        btSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

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
                intent.putExtra("id", id);
                intent.putExtra("pwd", pwd);
                intent.putExtra("mock", "type");
                intent.putExtra("type", type);
                startActivity(intent);
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

    //앱종료시 tts를 같이 종료해 준다.
    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    //@Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int language = tts.setLanguage(Locale.KOREAN);

            if (language == TextToSpeech.LANG_MISSING_DATA || language == TextToSpeech.LANG_NOT_SUPPORTED) {
                btSpeak.setEnabled(false);
                Toast.makeText(this, "지원하지 않는 언어입니다.", Toast.LENGTH_SHORT).show();
            } else {
                btSpeak.setEnabled(true);
                speakOutNow();
            }
        } else {
            Toast.makeText(this, "TTS 실패!", Toast.LENGTH_SHORT).show();
        }
    }

    //Speak out...
    private void speakOutNow() {
        String text = app[j];
        //tts.setPitch((float) 0.1); //음량
        //tts.setSpeechRate((float) 0.5); //재생속도
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

}
