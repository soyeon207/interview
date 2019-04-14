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
import android.widget.Toast;

import java.util.List;

public class free_q extends AppCompatActivity {
    private Button camera;
    // private Button next;
    private Button end;
    char[] qChar;
    String id,pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_q);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        pwd = intent.getStringExtra("passwd");
        final String Q = intent.getStringExtra("Q");
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

        if(Q == null) {
            Toast.makeText(getApplicationContext(), "질문이 선택되지 않았습니다.", Toast.LENGTH_LONG).show();
        }
        qChar = Q.toCharArray();
        question.setText(qChar, 0, qChar.length);

        /*next=findViewById(R.id.btn_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next.setVisibility(View.GONE);
            }
        });*/

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

    private boolean isExistsCameraApplication(){
        PackageManager packageManager = getPackageManager();

        Intent cameraApp = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        List<ResolveInfo> cameraApps = packageManager.queryIntentActivities(cameraApp, PackageManager.MATCH_DEFAULT_ONLY);

        return cameraApps.size() > 0;
    }
}