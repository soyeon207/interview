package s2017s06.kr.hs.mirim.interview;


import android.support.v7.widget.CardView;

public interface CardAdapter {

    public final int MAX_ELEVATION_FACTOR = 2;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}
