package s2017s06.kr.hs.mirim.interview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FreeInterview extends AppCompatActivity {

    ArrayList<String> Items;
    ArrayAdapter<String> adapter;
    ListView listView;
    Button btnAdd;
    EditText addText;
    String id,pwd;
    Button start;
    String question[] = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_interview);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        pwd = intent.getStringExtra("pwd");

        ImageView free_home=findViewById(R.id.free_home);
        free_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), StartActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("pwd",pwd);
                startActivity(intent);
            }
        });

        Items = new ArrayList<String>();
        Items.add("좌우명이 뭔가요?");
        Items.add("마찰이 생긴다면 어떻게 대처할 건가요?");

        adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice, Items);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        addText = (EditText) findViewById(R.id.addText);
        btnAdd = (Button) findViewById(R.id.add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = addText.getText().toString();
                if(text.length() != 0){
                    Items.add(text);
                    addText.setText("");
                    adapter.notifyDataSetChanged();
                }
            }
        });

        start=findViewById(R.id.start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray booleans = listView.getCheckedItemPositions();
                int j = 0;

                for(int i = 0; i < Items.size(); i++){
                    /*if(booleans.get(i)){
                        question[j] = Items.get(i);
                        j++;
                    }*/
                    Toast.makeText(FreeInterview.this, booleans.get(i) + " ", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(getApplicationContext(), free_q.class);
                intent.putExtra("Q", question);
                intent.putExtra("id",id);
                intent.putExtra("pwd",pwd);
                startActivity(intent);


            }
        });

    }
}
