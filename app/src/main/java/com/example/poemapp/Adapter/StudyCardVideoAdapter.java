package com.example.poemapp.Adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.poemapp.Database.MiniClassDB;
import com.example.poemapp.R;

import java.util.List;

/**
 * Created by dell on 2019/4/24.
 */

public class StudyCardVideoAdapter extends RecyclerView.Adapter<StudyCardVideoAdapter.ViewHolder>{
    //声明
    private List<MiniClassDB> miniClassDBS;
    Context mcontext;
    String mp4FilePath;
    //内部类ViewHolder，避免重复加载布局
    static class ViewHolder extends RecyclerView.ViewHolder {
        //声明
        //ImageView video_image;
        TextView video_title;
        VideoView videoView;


        public ViewHolder(View itemView) {
            super(itemView);
            //video_image = (ImageView)itemView.findViewById(R.id.minivideo);
            video_title = (TextView)itemView.findViewById(R.id.minivideo_title);
            videoView = itemView.findViewById(R.id.video_view);
        }
    }

    //构造方法
    public StudyCardVideoAdapter(List<MiniClassDB> miniClassDBList,Context context){
        miniClassDBS = miniClassDBList;
        mcontext = context;

        mp4FilePath = "android.resource://" + mcontext.getPackageName() + "/" + R.raw.jx3;
    }

    @Override
    public StudyCardVideoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_study_minvideo,parent,false);
        StudyCardVideoAdapter.ViewHolder holder = new StudyCardVideoAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MiniClassDB miniClassDB = miniClassDBS.get(position);
        //holder.video_image.setImageResource(miniClassDB.getImageID());
        holder.video_title.setText(miniClassDB.getVideoTitle());
        //holder.videoView.setVideoURI(Uri.parse(mp4FilePath));
        //holder.videoView.start();

      }

    @Override
    public int getItemCount() {
        return miniClassDBS.size();
    }


}
