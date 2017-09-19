package com.example.diaryproject0913;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import io.realm.Realm;
import io.realm.RealmResults;

public class ListActivity extends AppCompatActivity {

    private TextView mListTextView;
    private ListView mListView;
    private Button mmemobutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mListTextView = (TextView) findViewById(R.id.ListTextView);
        mListView = (ListView) findViewById(R.id.ListView);
        mmemobutton = (Button) findViewById(R.id.memobutton);

        //작성하기 버튼클릭하면 Activity2 화면으로 이동하여 메모를 작성한다.
        mmemobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListActivity.this, Activity2.class));
            }
        });


        //    Article[] articles = {
                //Realm사용하면서 쓰지 못하게되는부분
   //             new Article("제목", "내용"),
   //             new Article("제목2", "내용2"),
   //             new Article("제목3", "내용3"),
   //     };
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item,sample);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance().getDefaultInstance();
        final RealmResults<Article> articles = realm.where(Article.class).findAll();
        CustomAdapter adapter = new CustomAdapter(
                this,
                R.layout.list_row,
                //new ArrayList<Article>(Arrays.asList(articles))
                //위에 RealmResults로 바꿔서 넣어서 진행시킴.
                articles
        );



        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListActivity.this, Activity2.class);
                //intent.putExtra("Item", sample[i]);
                intent.putExtra("content", articles.get(i).getContent());
                intent.putExtra("title", articles.get(i).getTitle());
                startActivity(intent);
            }
        });
    }
}
