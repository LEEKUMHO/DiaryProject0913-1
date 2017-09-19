package com.example.diaryproject0913;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

import io.realm.Realm;

import static com.example.diaryproject0913.R.id.PhotoImage;

public class Activity2 extends AppCompatActivity {
    private Button mimage_button;
    private ImageView mPhotoImage;
    private Button mSaveButton;
    private EditText mEditText;
    private final int IMEGE_RESULT =1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        mimage_button = (Button) findViewById(R.id.image_button);
        //이미지 가져오기 버튼
        mSaveButton = (Button) findViewById(R.id.SaveButton);
        //저장하는 버튼
        mEditText = (EditText) findViewById(R.id.editText);
        mPhotoImage = (ImageView) findViewById(PhotoImage);


        //저장버튼 클릭하면 내용이 EditText내용이 사라지게 되고 이미지의 경우 getImageMatrix를 사용하면 똑같은 효과를 줄 수 있다.
        final View.OnClickListener first = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleText = mEditText.getText().toString();
                String Photo = mPhotoImage.getImageMatrix().toShortString();
                //이미지를 저장하고 불러와야함
                mEditText.setText("");
                mPhotoImage.setImageBitmap(null);

                Realm.init(getApplicationContext());
                Realm realm = Realm.getDefaultInstance();

                realm.beginTransaction();
                Article article = realm.createObject(Article.class);
                article.setTitle(titleText);
                article.setContent(Photo);
                realm.commitTransaction();
            }
        };

        mSaveButton.setOnClickListener(first);
        mPhotoImage.setOnClickListener(first);
        if (savedInstanceState == null) {
            String title = getIntent().getStringExtra("title");
            String content = getIntent().getStringExtra("content");
            mPhotoImage.setImageBitmap(null);
            mEditText.setText(content);



            //이미지 가져오기
            mimage_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, IMEGE_RESULT);
                }
            });
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMEGE_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    mPhotoImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

