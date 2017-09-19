package com.example.diaryproject0913;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by 이금호 on 2017-09-17.
 */

public class CustomAdapter extends ArrayAdapter<Article> {
    private Context mContext;
    private RealmResults<Article> mArticles;
    //위에 커스텀 CustomAdapter class를 만들어서 ArrayAdapter를 상속받고 제네릭을 이용하여 Article로 지정

    public CustomAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Article> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mArticles = (RealmResults<Article>) objects;
        //ArrayList에서 RealmResults로 바꿔서 진행... 그전에 Error발생

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.list_row, parent, false);

        TextView contentView = (TextView) row.findViewById(R.id.contentView);
        TextView titleView = (TextView) row.findViewById(R.id.titleView); //CustomAdapter에서 Aticle을 받고 있는데 TextView말고 Image를 받으려고 수정함.


        contentView.setText(mArticles.get(position).getContent());
        titleView.setText(mArticles.get(position).getTitle());



        return row;
    }
}
