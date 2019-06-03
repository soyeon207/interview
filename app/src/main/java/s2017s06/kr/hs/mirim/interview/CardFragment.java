package s2017s06.kr.hs.mirim.interview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class CardFragment extends Fragment {

    private CardView cardView;

    public static Fragment getInstance(int position) {
        CardFragment f = new CardFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);

        return f;
    }

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_viewpager, container, false);
        String t[] = {"모의 면접","자유 면접"};
        String t2[] = {"직종별, 기업별로 나눠서\n모의 면접을 진행해 보세요\n기업 맞춤형 질문을 제공합니다.","본인이 직접 질문을 추가하거나\n이미 있는 질문 중 맘에 드는 질문을\n선택해 면접을 진행하세요"};
        cardView = (CardView) view.findViewById(R.id.cardView);
        cardView.setMaxCardElevation(cardView.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR);

        TextView title = (TextView) view.findViewById(R.id.title);
        Button button = (Button)view.findViewById(R.id.button);
        TextView content = view.findViewById(R.id.content);

        /*title.setText(String.format("Card %d", getArguments().getInt("position")));*/
        title.setText(t[getArguments().getInt("position")]);
        content.setText(t2[getArguments().getInt("position")]);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Button in Card " + getArguments().getInt("position")
                        + "Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public CardView getCardView() {
        return cardView;
    }
}
