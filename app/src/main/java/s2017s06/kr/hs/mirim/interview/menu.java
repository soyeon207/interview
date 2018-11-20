package s2017s06.kr.hs.mirim.interview;

import android.app.TabActivity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

import s2017s06.kr.hs.mirim.interview.R;

public class menu extends TabActivity {
    int but = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        final TabHost tabHost=getTabHost();
        ArrayList<TabHost.TabSpec> tabSpecs= new ArrayList<TabHost.TabSpec>();
        String[] texts={"면접", "Tip", "커뮤니티", "My"};
        for(int i = 0; i < 4; i++){
            tabSpecs.add(tabHost.newTabSpec("a" + (i+1)).setIndicator(texts[i]));
        }

        int j=0;
        for(TabHost.TabSpec tabspec:tabSpecs){
            tabspec.setContent(R.id.view1 + j);
            tabHost.addTab(tabspec);
            j++;
        }

        setTabColor(tabHost);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String arg0) {
                setTabColor(tabHost);
            }
        });

    }

    public static void setTabColor(TabHost tabhost) {

        for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {
            tabhost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.white);

            TextView tv = tabhost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#FF535353"));
        }


        TextView tv = tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).findViewById(android.R.id.title);
        tv.setTextColor(Color.parseColor("#FFFFFF"));

        tabhost.getTabWidget().setCurrentTab(0);
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab())
                .setBackgroundResource(R.color.colorMain);
    }

}