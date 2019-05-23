package s2017s06.kr.hs.mirim.interview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class mock_type extends AppCompatActivity {
    String id, pwd, type;

    private Button next;
    private Button end;

    Random rnd = new Random();
    int random = 0;
    int random2 = 0;
    char[] qChar;
    int i = 0;

    TextView question;
    String q;

    /*private TextToSpeech tts;
    private Button btSpeak;*/

    //카메라

    private final int PERMISSIONS_ALL = 5;

    SurfaceView surfaceView;
    SurfaceHolder holder;
    MediaRecorder recorder;

    Camera camera;

    SimpleDateFormat format = new SimpleDateFormat("yyMMdd_HHmmss");

    long now;
    Date time;
    String time_name;
    String path;

    private String getTime() {
        now = System.currentTimeMillis();
        time = new Date(now);
        return format.format(time);
    }

    //카메라

    private String[] app = {"작년 방과후 시간에 무엇을 했나?", "어노테이션(@)와 주석의 차이는?", "디자인 패턴을 알고 있는가?", "안드로이드는 언제 배웠나요?", "ios를 배우고 싶은 마음은?",
            "알고 있는 단축키는?", "가장 잘하는 언어와 그 이유?", "안드로이드는 언제 배웠나요?", "ios를 배우고 싶은 마음은 있나요?"};

    private String[] con = {"진행했던 프로젝트와 했던 일을 말해주세요", "ddd", "fff", "ggg", "eee"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_type);

        //권한 및 카메라

        String str = Environment.getExternalStorageState();
        if (str.equals(Environment.MEDIA_MOUNTED)) {

            String dirPath = "/sdcard/DCIM/모면";
            File file = new File(dirPath);
            if (!file.exists())  // 원하는 경로에 폴더가 있는지 확인
                file.mkdirs();
        } else
            Toast.makeText(this, "SD Card 인식 실패", Toast.LENGTH_SHORT).show();

        int permssionCamera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int permssionRecord = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        int permssionRead = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permssionWrite = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if ((permssionCamera != PackageManager.PERMISSION_GRANTED) && (permssionRecord != PackageManager.PERMISSION_GRANTED) && (permssionRead != PackageManager.PERMISSION_GRANTED) && (permssionWrite != PackageManager.PERMISSION_GRANTED)) {
            Toast.makeText(this, "권한 승인이 필요합니다.", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO) && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "면접을 녹화하기 위해 카메라, 오디오 녹음 등의 권한이 필요합니다.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_ALL);
                Toast.makeText(this, "면접을 녹화하기 위해 카메라 권한이 필요합니다.", Toast.LENGTH_LONG).show();
            }
        }

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);

        holder = surfaceView.getHolder();

        //권한 및 카메라

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

        //btSpeak = (Button) findViewById(R.id.speak);

        question=findViewById(R.id.question);

        switch (type) {
            case "app":
                random  = (int) (Math.random() * app.length);
                question.setText(random);
                qChar = app[random].toCharArray();
                q = app[random];
                question.setText(q);
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

        /*btSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });*/

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

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_ALL: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "승인이 허가되어 있습니다.", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this, "아직 승인받지 않았습니다.", Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }

    public void BtnStart(View v) {
        time_name = getTime();

        try {
            if (recorder != null) {
                recorder.stop();
                recorder.release();
                recorder = null;
            }

            recorder = new MediaRecorder();

            camera = Camera.open(1);
            camera.setDisplayOrientation(90);
            camera.unlock();

            recorder.setCamera(camera);

            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            recorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);

            path = "/sdcard/DCIM/모면/" + time_name + ".mp4";

            recorder.setOutputFile(path);
            recorder.setPreviewDisplay(holder.getSurface());
            recorder.prepare();
            recorder.start();

            Toast.makeText(getApplicationContext(), "녹화를 시작합니다.", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void BtnStop(View v) {
        if (recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;

            camera.stopPreview();
            camera.release();
        }

        Toast.makeText(getApplicationContext(), "녹화를 중지합니다.", Toast.LENGTH_LONG).show();
    }

    //앱종료시 tts를 같이 종료해 준다.
    //@Override
    /*protected void onDestroy() {
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
    }*/

}
