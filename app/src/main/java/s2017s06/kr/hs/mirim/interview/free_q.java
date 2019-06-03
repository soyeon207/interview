package s2017s06.kr.hs.mirim.interview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
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
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class free_q extends AppCompatActivity {
    private Button next;
    private Button end;

    String qString;
    String id,pwd;
    ArrayList<String> q = new ArrayList<String>();
    int j = 0;

    //TextView question;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_q);

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
        pwd = intent.getStringExtra("passwd");
        q = intent.getStringArrayListExtra("Q");
        final TextView question=findViewById(R.id.question);

        ImageView home=findViewById(R.id.freehome);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), StartActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("passwd",pwd);
                startActivity(intent);
            }
        });

        if(question == null) {
            Toast.makeText(getApplicationContext(), "질문이 선택되지 않았습니다.", Toast.LENGTH_LONG).show();
        }

        //qString = q.get(0);
        question.setText(q.get(j));

        next=findViewById(R.id.btn_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (j == q.size() - 1) next.setVisibility(View.GONE);
                question.setText(q.get(j));
                j++;
            }
        });

        end=findViewById(R.id.btn_end);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), FreeInterview.class);
                intent.putExtra("id_free",id);
                intent.putExtra("pwd_free",pwd);
                startActivity(intent);
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
}