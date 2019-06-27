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

    //app 개발	ui/ux 디자인
    //web 개발	content/web 디자인
    //플랫폼 개발  운영/관리
    private String[] app = {"작년 방과후 시간에 무엇을 했나?", "어노테이션(@)와 주석의 차이는?", "디자인 패턴을 알고 있는가?", "안드로이드는 언제 배웠나요?",
            "ios를 배우고 싶은 마음은?", "알고 있는 단축키는?", "가장 잘하는 언어와 그 이유?", "사회에서 '소프트웨어'란 무엇이라고 생각하나요?",
            "전공에 대해 각자 자신이 하고 싶었던 것은?", "팀 프로젝트에서 파트를 어떻게 나눴나요?", "sqrt와 pow가 무엇인가?", "면접자에게 소프트웨어란 어떤 것인가?",
            "포토폴리오 중에서 가장 잘 만들었다고\n생각하는 프로젝트와 이유", "모바일 앱 개발을 좋아하는 이유가 있다면?", "개발에 있어서 중요한 것은?", "다른 친구들이 만든 앱 중에서\n좋다고 생각했던 앱이 있나요?",
            "스택과 큐에 대해 설명해주세요.", "최근에 한 프로젝트는?", "왜 개발자가 되고 싶나요?", "참가한 공모전 중 가장\n기억에 남는 공모전은?"};

    private String[] uiux = {"진행했던 프로젝트와 했던 일을 말해주세요", "디자인 의견이 맞지 않을 때\n어떻게 해결할 것인가?", "잘 사용하는 디자인 프로그램은 무엇인가요?", "자신있는 Tool은 무엇인가요?",
            "롤모델로 삼거나 좋아하는 디자이너는 누구인가요?", "포토폴리오의 작업물은 본인이 다 작업한건가요?", "좋은 회사란 무엇이라고 생각하나요?", "상사가 자신의 잘못이나\n디자인에 대해 지적한다면?",
            "스트레스를 푸는 자신만의 방법은?", "협업할 때 갈등이 생긴다면\n어떤 방식으로 해결할 것인가?", "왜 지원자를 뽑아야 하죠?", "가치에 대한 정의를 내려보아라",
            "영어를 왜 배워야 하는가?", "다른 일을 배워도 괜찮나요?", "어떤 사람이 되고 싶은가?", "왜 디자인을 하는가?", "UI/UX에서 가장 중요한 것이 무엇이라 생각하는가",
            "포토폴리오 작품 중 하나만 소개해주세요.", "디자인이란 무엇이라고 생각하나요?", "가장 자신 있는 디자인은?", "개발과 친수들과 협업한 작품이 있나요?"};

    private String[] webP = {"Web과 관련된 프로젝트 무엇을 했는지 말해주세요.", "사용해 본 웹호스팅 같은 것은?", "아파치의 버전과 php의 몇 버전을 사용했나요?", "jason을 해본 경험이 있나요?",
            "프로젝트를 진행하다 보면 친구랑 많이 싸우진 않았나요?", "자신의 가치와 전공을 섞어 자기소개해주세요.", "다른 사람들이 '이것은 쓸만하고 실용적이다'라는\n프로그램이 있나요?", "IT 트렌드라고 생각하는 것은?",
            "팀 프로젝트 당시 맡았던 역할이 무엇이었나요?", "개발자에게 필요한 자질은 무엇이라고 생각하는지", "페이스북과 네이버 중 선호하는 것은 무엇인지", "다른 친구들이 만든 웹 중에서\n좋다고 생각했던 웹?",
            "팀플을 해본 경험이 있나요?", "개발 쪽에서 좋아하는 것은?", "가장 기억에 남는 프로젝트는?", "배운 것 중에 가장 잘 하는 것을 나열해보세요.",
            "야근 외에도 지방으로 근무를 하라고 회사에서 요청한다면?", "회사에서 자신을 뽑아야 하는 이유를 설명하시오.", "소스 관리는 어떻게 하고 팀프로젝트에서 소스 관리는?", "리눅스 사용 경험은?"};

    private String[] webD = {"잘 사용하는 디자인 프로그램은 무엇인가요?", "롤모델로 삼거나 좋아하는 디자이너는 누구인가요?", "어떤 사람이 되고 싶은가?", "학생들의 포트폴리오가 많이 유사한데, 자신을 대표할 수 있는 포트폴리오 하나 발표해주세요.",
            "공모전에 많이 참가하신것같은데, 이 중 소개하고 싶은 작품이 있는지", "포트폴리오 내에서 가장 재미있었던, 성향에 맞았던 작품이 어떤것인지", "왜 디자인을 하는가?", "자신의 포트폴리오를 발표해주세요",
            "20대때 하고싶은 취미는?", "마지막으로 하고 싶은 말은?", "새로운 환경에 잘 적응할 수 있는가?", "포토폴리오의 구성은 어떻게 했나요?",
            "ACA 자격증이 포토샵과 일러스트를 어느정도 할 수 있으면 취득할 수 있나?", "디자인을 시작하게 된 계기는?", "경험을 쌓기 위해서는 어떻게 해야 하는지.", "디자인을 하고 싶은 이유?",
            "이 회사에서 어떤 사람이 되고 싶은지", "지원동기가 무엇인가요?", "마지막으로 하고 싶은 말은?", "가장 애착이 가는 작품은?"};

    private String[] plat = {"자기소개와 지원동기를 60초 이내로 짧게 해주세요.", "다른 친구들과는 다른 자신만의 장점은?", "전공 관련해서 기억에 남는 프로젝트나\n힘들었던 점들을 말해주세요.", "프로그래머로써 자신이 자질이\n있는지 말해주세요.",
            "학교에서 있었던 일 중에서\n가장 기억나는 일이 무엇인가요?", "자신 있는 언어는?", "단축키 아는대로 말해주세요.", "왜 다른 애들처럼 대학에 가지 않고 취업을 우선으로 하게 되었나요?",
            "학교에 다니는 동안 했던 프로젝트는 무엇인가요?", "한타/영타가 얼마나 나오나요?", "회사에 들어오고 이미지가 어땠나요?", "어떤 업무를 맡고 싶은지",
            "회사가 학교보다 멀어질 수 있는데 괜찮겠어요?", "지금 배우고 있는 것이 전공에 맞다고 생각하나요?", "스트레스 받을 때는 어떻게 해소하나요?", "이 분야에 적성이 맞는 것 같나요?",
            "프로그래밍을 어떻게 시작하게되었나요?", "야근 할 수 있겠나요?", "프로젝트를 진행하면서 가장 인상깊었던 프로젝트에 대해 설명하라.", "개발자로서 최종 목표는 무엇인가요."};

    private String[] manage = {"선호하는 커피브랜드와 이유, 성공할 수 있었던 마케팅 기법 그리고 그 브랜드의 부족했던 점", "지원동기 말해주세요.", "자신이 있는 언어와 툴은?", "자신이 알고 있는 단축키는?",
            "가장 기억에 남는 프로젝트는 무엇인가요?", "다른 사람들이 이것은 쓸만하고\n실용적이다라는 프로그램이 있나요?", "모르는 점이 있다면 어떻게 할건가요?", "상사와 문제가 생긴다면 어떻게 해결할건가요?",
            "잘하는 외국어가 있나요?", "이 분야에 지원한 이유가 무엇인가요?", "회사에서 해야 하는 일 중에 가장 자신있는 것은?", "어떤 방법으로 자신의 스트레스를\n해소하는지 말씀해주세요.", "전공을 하면서 힘들었던 점은?",
            "아르바이트나 봉사활동 경험을 말해주세요.", "포상내용이 엄청 많은데 그 중에서 가장 의미있었고 가치있었던 경험 이야기해주세요.", "학생이 화장하는 것에 대해 어떤 의견인가요?", "야근 외에도 지방으로 근무를 하라고 회사에서 요청하면?",
            "프로젝트를 하면서 어려웠던 점이 있었나요?", "회사에서 하고 싶은 것이 있나요?", "사무실에 있는 것과 밖으로 나가는 활동 중 어떤 것을 더 많이 할 것 같나요?", "궁금하신 점 물어보세요."};

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

        switch (type) {
            case "app":
                random = (int)(Math.random() * app.length);
                qChar = app[random].toCharArray();
                question.setText(qChar, 0, qChar.length);
                break;

            case "uiux":
                random = (int)(Math.random() * uiux.length);
                qChar = uiux[random].toCharArray();
                question.setText(qChar, 0, qChar.length);
                break;

            case "webP":
                random = (int)(Math.random() * webP.length);
                qChar = webP[random].toCharArray();
                question.setText(qChar, 0, qChar.length);
                break;

            case "webD":
                random = (int)(Math.random() * webD.length);
                qChar = webD[random].toCharArray();
                question.setText(qChar, 0, qChar.length);
                break;

            case "plat":
                random = (int)(Math.random() * plat.length);
                qChar = plat[random].toCharArray();
                question.setText(qChar, 0, qChar.length);
                break;

            case "manage":
                random = (int)(Math.random() * manage.length);
                qChar = manage[random].toCharArray();
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
                switch (type) {
                    case "app":
                        random2 = rnd.nextInt(app.length);
                        if (random2 == random) if (random2++ > app.length) random2 = 0;
                        qChar = app[random2].toCharArray();
                        question.setText(qChar, 0, qChar.length);
                        random = random2;
                        break;

                    case "uiux":
                        random2 = rnd.nextInt(uiux.length);
                        if (random2 == random) if (random2++ > uiux.length) random2 = 0;
                        qChar = uiux[random2].toCharArray();
                        question.setText(qChar, 0, qChar.length);
                        random = random2;
                        break;

                    case "webP":
                        random2 = rnd.nextInt(webP.length);
                        if (random2 == random) if (random2++ > webP.length) random2 = 0;
                        qChar = webP[random2].toCharArray();
                        question.setText(qChar, 0, qChar.length);
                        random = random2;
                        break;

                    case "webD":
                        random2 = rnd.nextInt(webD.length);
                        if (random2 == random) if (random2++ > webD.length) random2 = 0;
                        qChar = webD[random2].toCharArray();
                        question.setText(qChar, 0, qChar.length);
                        random = random2;
                        break;

                    case "plat":
                        random2 = rnd.nextInt(plat.length);
                        if (random2 == random) if (random2++ > plat.length) random2 = 0;
                        qChar = plat[random2].toCharArray();
                        question.setText(qChar, 0, qChar.length);
                        random = random2;
                        break;

                    case "manage":
                        random2 = rnd.nextInt(manage.length);
                        if (random2 == random) if (random2++ > manage.length) random2 = 0;
                        qChar = manage[random2].toCharArray();
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
