package com.example.poemapp.JavaClass;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.poemapp.Activity.ReadPoemActivity;
import com.example.poemapp.Database.PoemDB;
import com.example.poemapp.R;
import com.gcssloop.widget.RCRelativeLayout;

import java.util.List;
import java.util.Random;

/**
 * Created by dell on 2019/3/26.
 */

public class StudyCardWriterAdapter extends RecyclerView.Adapter<StudyCardWriterAdapter.ViewHolder> {
    //声明
    private static final int N = 19;
    private List<PoemDB> mwriterDBList;
    Context mcontext;

    //内部类ViewHolder，避免重复加载布局
    static class ViewHolder extends RecyclerView.ViewHolder {
        //声明
        ImageView writer_icon;
        //TextView writer_name;
        //TextView writer_story;

        public ViewHolder(View itemView) {
            super(itemView);
            writer_icon = (ImageView)itemView.findViewById(R.id.writer_icon);

            //writer_name = (TextView)itemView.findViewById(R.id.writer_name);
            //writer_story = (TextView)itemView.findViewById(R.id.writer_story);
        }
    }

    //构造方法
    public StudyCardWriterAdapter(List<PoemDB> writerDBList,Context context){
        mwriterDBList = writerDBList;
        mcontext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_study_tuijian_poemwriter,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Random rand = new Random();
        int randNum = rand.nextInt(N);
        PoemDB poemDB = mwriterDBList.get(randNum);
        holder.writer_icon.setImageResource(poemDB.getPoemImageID());
        //holder.writer_name.setText(poemDB.getPoemName());
        //holder.writer_story.setText(poemDB.getPoemCotent());


        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, ReadPoemActivity.class);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mwriterDBList.size();
    }


}
