package s2017s06.kr.hs.mirim.interview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyListAdapter extends BaseAdapter{
    Context context;
    ArrayList<list_item> list_itemArrayList;

    TextView nickname_textView;
    TextView title_textView;
    TextView date_textView;

    public MyListAdapter(Context context, ArrayList<list_item> list_itemArrayList) {
        this.context = context;
        this.list_itemArrayList = list_itemArrayList;
    }



    @Override
    public int getCount() {
       return this.list_itemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return list_itemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item,null);

        nickname_textView = convertView.findViewById(R.id.nickname_textview);
        title_textView = convertView.findViewById(R.id.title_textview);
        date_textView = convertView.findViewById(R.id.date_textview);
    }

    nickname_textView.setText(list_itemArrayList.get(position).getNickname());
        title_textView.setText(list_itemArrayList.get(position).getTitle());
        date_textView.setText(list_itemArrayList.get(position).getWrite_date());

    return convertView;

}
}
