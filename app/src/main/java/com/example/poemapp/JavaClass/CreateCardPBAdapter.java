package com.example.poemapp.JavaClass;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.poemapp.Database.CreateDB;
import com.example.poemapp.R;

import java.util.List;

/**
 * Created by dell on 2019/4/29.
 */

public class CreateCardPBAdapter extends RecyclerView.Adapter<CreateCardPBAdapter.ViewHolder> {
    //声明
    private List<CreateDB> createDBS;
    Context mcontext;
    TextView textView;

    //内部类ViewHolder，避免重复加载布局
    static class ViewHolder extends RecyclerView.ViewHolder {
        //声明
        ImageView pb_image;

        public ViewHolder(View itemView) {
            super(itemView);
            pb_image = (ImageView)itemView.findViewById(R.id.create_pb);
        }
    }

    //构造方法
    public CreateCardPBAdapter(List<CreateDB> createDBList, Context context, TextView yangshiText){
        createDBS = createDBList;
        mcontext = context;
        textView = yangshiText;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.rv_paiban,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CreateDB createDB = createDBS.get(position);
        holder.pb_image.setImageResource(createDB.getCreatePBImageID());

        //点击事件
        holder.pb_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position){
                    case 0:
                        textView.setGravity(Gravity.RIGHT);
                        Log.i("CreateCardPBAdapter","右上");
                        break;
                    case 1:
                        Log.i("CreateCardPBAdapter","左下");
                        break;
                    case 2:
                        Log.i("CreateCardPBAdapter","中");
                        break;
                    case 3:
                        Log.i("CreateCardPBAdapter","默认横");
                        break;
                        default:
                            break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return createDBS.size();
    }


}
