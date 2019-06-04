package s2017s06.kr.hs.mirim.interview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class Popup extends AppCompatActivity {

    TextView sqi,covison,content,sin,lg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.content_popup);

        sqi = findViewById(R.id.sqi);
        covison = findViewById(R.id.covison);
        content = findViewById(R.id.content);
        sin = findViewById(R.id.sin);
        lg = findViewById(R.id.lg);

        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.setText("머리를 최대한 깔끔하게 하는게 좋을 것 같습니다\n전공에 대한 심도있는 질문 보단 면접자가 어떤 사람인지에 대해 궁금해 하는 질문을 많이 합니다");
            }
        });

        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.setText("일본어 관련 능력을 키우면 도움이 될 것이고 인성 관련된 면접 준비를 하는것이 도움이 될 것 같습니다.");
            }
        });
        sqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.setText("자신의 가치관이나 직업관이 확고하면 좋을 것 같습니다.\n자기소개서나 포트폴리오를 중점으로 보기 보다는 개인의 가치관이나 직업관을 상당히 중요하게 여기시는 것 같았습니다.");
            }
        });

        covison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.setText("면접을 보면서 느낀 것은 정확하게 조곤조곤 하게 말하는 습관을 길러야 할 것 같고 두괄식을 얘기하는 것을 방법을 길러야 할 것 같습니다");
            }
        });

    }

    //확인 버튼 클릭
    public void mOnClose(View v){
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }

}
