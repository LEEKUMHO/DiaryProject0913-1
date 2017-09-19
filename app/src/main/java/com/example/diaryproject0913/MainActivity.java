package com.example.diaryproject0913;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmObject;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private Button mButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);
        mButton = (Button) findViewById(R.id.button);


        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Activity2.class));

                if (savedInstanceState == null) {
               //     String text = getIntent().getStringExtra("Item");
               //     mTextView.setText(text);


                }
            }
        });
    }
}
