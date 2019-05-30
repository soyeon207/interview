package s2017s06.kr.hs.mirim.interview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class mock_company extends AppCompatActivity {
    String id, pwd, company;

    private Button next;
    private Button end;

    Random rnd = new Random();
    int random = 0;
    int random2 = 0;
    char[] qChar;
    int i = 0;

    TextView question;

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

    private String[] global = { "웹에 관련된 프로젝트 무엇을 했는지 말해주세요.", "사용해본 웹 호스팅은 ?", "방과후 수업때 했던 내용을 간략하게 설명해주세요", "무엇을 배웠고, 무엇을 하고 싶은지에 관해 자기소개를 해보세요",
            "학교 정규수업 이외에 전공에 대해 각자 자신이 하고 싶었던 것이 무엇인가요?", "자신이 하고 싶은 전공과 관련지어 자기소개를 해주세요", "자신의 미래에 대해 자기소개하기", "어노테이션(@)와 주석의 차이",
            "자바 swing의 특징과 상속의 특징", "디자인 패턴을 아냐", "우리 회사가 어디에 위치하는지 아나요?", "php를 잘 모르는 것 같은데 회사에서 바로 php쓰라하면 어떻게 하실건가요?", "프로젝트를 진행하다 보면 친구랑 많이 싸우진 않았나요?",
            "가장 자신있는 언어", "ios를 배우고 싶은 마음은 있나요?", "마지막으로 하고 싶은 한마디는?", "팀플 해본 경험이 있나요?" };
    private String[] navi = { "간단한 자기 소개와 지원동기를 말씀해주세요", "스택과 큐의 차이점에 대해서 말해보세요", "자신이 가장 잘 하는 언어는 무엇인가요?", "공룡이라는 주제를 주고 그거에 대한 게임을 기획해보아라",
            "동아리활동뭐했냐 기억남는것은", "이메일이 왜 이런지 궁금해요", "가장 인상깊게 읽은 책이 무엇인지?", "꿈이 뭔가요", "남들과 다른 차별화된 요소", "인생에 있어서 가장 열정적으로 참여했던 것", "마지막으로 하고 싶은 말은?",
            "내가 같이 면접 본 친구들보다 잘하는 점은?", "다른 사람들과의 차별적인 프로젝트를 진행했던 경험은 무엇인가요?", "마지막으로 우리 회사가 꼭 뽑아야 하는 이유는?", "가장 힘들었던 프로젝트는 무엇인가요? 그 이유는요?" };
    private String[] shin = { "자기소개", "아르바이트 경험이나 슬펐거나 기뻤던 자신의 에피소드를 들려주세요.", "반학생이 매우 적죠? 반에서 자기 위치는 뭐라고 생각해요?", "IT분야가 아닌 다른 분야로 가게되면 어떡할 것인가?",
            "자신이 선호하는 커피 브랜드와 선호하는 이유 보완점을 말해주세요.", "IT 트렌드라고 생각하는것은?", "어떤 방법으로 자신의 스트레스를 해소하는지 말씀해주세요.", "가장 자신있는 프로그래밍 언어는 무엇인가요?",
            "학생이 화장하는 것에 대해서 찬반 의견을 말해주세요", "제일 자신있는 언어는 무엇이죠?", "스트레스를 푸는 방법과 주말에 하는 일을 말해주세요.", "자기 어필 해보세요." };
    private String[] sqi = { "사람은 왜 사는가", "왜 '면접자 자신'을 뽑아야하는가 이유를 들어 말해주세요.", "자신과 지내기 힘들다고 생각한 사람의 유형은 무엇인가?", "역사란 무엇인가",
            "영어를 왜 배워야 하는가 ?", "가치에 대한 정의를 내려보아라", "면접관들에게 궁금한 점이 있으면 물어보아라(역질문)", "어떤 사람이 되고 싶은지 짧게 얘기하시오.",
            "만약 자신이 이 일을 하지 않았으면 어떠한 일을 하고 있을 것 같은지?", "회사에 들어오면 다른 일을 하는 경우도 있는데 이럴 경우 어떻게 할 것인가" };
    private String[] lg = { "가장 기억에 남는 공모전이나 대회가 무엇인가요?", "학교에 많은 교육과정이 있는데 그중 자신의 자랑거리를 말해주세요", "일본어를 어떻게 공부했나요?", "대학 진학은 어떻게 할 것인가?",
            "학교 생활에서 가장 어려웠던 것은 무엇인가요?", "자신이 했던 프로젝트 중 가장 자신있는 프로젝트를 말해주세요.", "일본에서 5년, 10년 정도 근무하게 된다면 부모님을 어떻게 설득하실 건가요?",
            "다른 친구들이 만든 앱 중에서 좋다고 생각했던 앱이 있나요?", "나중에 자신이 어떤 사람이 되어 있을 것 같나요?", "이 회사에 입사하게 된다면 어떤 프로젝트를 진행하고 싶나요?", "마지막으로 하고 싶은 말 해보세요." };
    private String[] covision = { "자기소개하기", "개발자라는 직업을 선택한 것에 확신하나요(후회하지 않나요)?", "본인의 장단점을 말씀해보세요.", "자신이 개발과 잘 맞는다고 생각하는 기준이 무엇인가?",
            "야근 시 스트레스 받았을 때 해소법?", "휴일에는 주로 무엇을 하나요?", "프로젝트를 진행하면서 가장 인상깊었던 프로젝트에 대해 설명하라.", "개발자에게 필요한 자질은 무엇이라고 생각하는지",
            "성향이 다른 직장 상사와 만나게 된다면 어떻게 할 것 인가요?", "개발자로서 최종 목표는 무엇인가요.", "하기 싫은 일을 맡게 되었을 때 어떻게 할 것 인가?", "IT의 가장 신기술이 무엇이라고 생각하나요",
            "현재 IT트랜드는 무엇이라고 생각하는지", "좋은 회사란?", "인생에서 실패했던 경험이 있나요?", "자신의 비젼은?", "저녁 자기계발 시간이 중요하게 여겨지는 추세인데 본인은 어떻게 생각하나요?",
            "향후 50년 후에는 어떤 개발자가 되어있을 것이냐", "마지막으로 하고 싶은 말?", "숙제가 아닌 필요해서 만든 프로그램도 있나요?" };

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
        company = intent.getStringExtra("company");
        //Toast.makeText(getApplicationContext(), type, Toast.LENGTH_LONG).show();

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

        question = (TextView)findViewById(R.id.question);

        switch (company) {
            case "global":
                random = (int)(Math.random() * global.length);
                qChar = global[random].toCharArray();
                question.setText(qChar, 0, qChar.length);
                break;

            case "navi":
                random = (int)(Math.random() * navi.length);
                qChar = navi[random].toCharArray();
                question.setText(qChar, 0, qChar.length);
                break;

            case "shin":
                random = (int)(Math.random() * shin.length);
                qChar = shin[random].toCharArray();
                question.setText(qChar, 0, qChar.length);
                break;

            case "sqi":
                random = (int)(Math.random() * sqi.length);
                qChar = sqi[random].toCharArray();
                question.setText(qChar, 0, qChar.length);
                break;

            case "lg":
                random = (int)(Math.random() * lg.length);
                qChar = lg[random].toCharArray();
                question.setText(qChar, 0, qChar.length);
                break;

            case "covision":
                random = (int)(Math.random() * covision.length);
                qChar = covision[random].toCharArray();
                question.setText(qChar, 0, qChar.length);
                break;
        }

        /*btSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });*/

        next = findViewById(R.id.btn_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                switch (company) {
                    case "global":
                        random2 = rnd.nextInt(global.length);
                        if (random2 == random) if (random2++ > global.length) random2 = 0;
                        qChar = global[random2].toCharArray();
                        question.setText(qChar, 0, qChar.length);
                        random = random2;
                        break;

                    case "navi":
                        random2 = rnd.nextInt(navi.length);
                        if (random2 == random) if (random2++ > navi.length) random2 = 0;
                        qChar = navi[random2].toCharArray();
                        question.setText(qChar, 0, qChar.length);
                        random = random2;
                        break;

                    case "shin":
                        random2 = rnd.nextInt(shin.length);
                        if (random2 == random) if (random2++ > shin.length) random2 = 0;
                        qChar = shin[random2].toCharArray();
                        question.setText(qChar, 0, qChar.length);
                        random = random2;
                        break;

                    case "sqi":
                        random2 = rnd.nextInt(sqi.length);
                        if (random2 == random) if (random2++ > sqi.length) random2 = 0;
                        qChar = sqi[random2].toCharArray();
                        question.setText(qChar, 0, qChar.length);
                        random = random2;
                        break;

                    case "lg":
                        random2 = rnd.nextInt(lg.length);
                        if (random2 == random) if (random2++ > lg.length) random2 = 0;
                        qChar = lg[random2].toCharArray();
                        question.setText(qChar, 0, qChar.length);
                        random = random2;
                        break;

                    case "covision":
                        random2 = rnd.nextInt(covision.length);
                        if (random2 == random) if (random2++ > covision.length) random2 = 0;
                        qChar = covision[random2].toCharArray();
                        question.setText(qChar, 0, qChar.length);
                        random = random2;
                        break;
                }

                if (i == 5) next.setVisibility(View.GONE);
            }
        });

        end = findViewById(R.id.btn_end);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), mock_select.class);
                intent.putExtra("id", id);
                intent.putExtra("pwd", pwd);
                intent.putExtra("mock", "company");
                intent.putExtra("company", company);
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

            MediaScannerConnection.scanFile(getApplicationContext(), new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String path, Uri uri) {
                    Log.v("File scan", "file:" + path + "was scanned seccessfully");
                }
            });

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
