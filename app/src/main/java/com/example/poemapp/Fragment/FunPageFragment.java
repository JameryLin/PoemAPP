package com.example.poemapp.Fragment;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.poemapp.Activity.MainActivity;
import com.example.poemapp.Adapter.FunPageDiyAdapter;
import com.example.poemapp.Database.NameDB;
import com.example.poemapp.Database.UserMakeDB;
import com.example.poemapp.R;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dell on 2019/3/19.
 */

public class FunPageFragment extends Fragment {
    //全局声明
    private static final int N = 19;
    List<NameDB> nameDBList = new ArrayList<NameDB>();
    List<UserMakeDB> userMakeDBList = new ArrayList<UserMakeDB>();
    Context context;
    Spinner spinner1,spinner2;
    ArrayAdapter<String> arrayAdapter1,arrayAdapter2;
    List<String> genderList,styleList;
    RecyclerView diyRecyclerView;
    TextView exportName;
    AssetManager mgr;   //初始数据管理对象
    Typeface tf;    //字体
    Button button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_fun,container,false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initDataBase();
        initView();

    }

    /**
     * 方法实现
     */
    private void initView() {
        //控件id
        spinner1 = getActivity().findViewById(R.id.fun_gender_spinner);
        spinner2 = getActivity().findViewById(R.id.fun_style_spinner);
        diyRecyclerView = getActivity().findViewById(R.id.rv_fun_diy);
        exportName = getActivity().findViewById(R.id.export_name);
        button = getActivity().findViewById(R.id.Name_button);

        //字体
        mgr = getActivity().getAssets();
        tf = Typeface.createFromAsset(mgr,"zhenhun.ttf");
        exportName.setTypeface(tf);
        exportName();


        //获取活动
        MainActivity activity = (MainActivity) getActivity();
        context = activity;

        //下拉菜单适配器
        initSpinner();

        //DIY横向滚动
        diyView();

    }
    //DIY横向滚动
    private void diyView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        diyRecyclerView.setLayoutManager(linearLayoutManager);
        FunPageDiyAdapter funPageDiyAdapter = new FunPageDiyAdapter(userMakeDBList,context);
        diyRecyclerView.setAdapter(funPageDiyAdapter);
    }

    //下拉菜单适配器
    private void initSpinner() {
        //列表添加
        genderList = new ArrayList<String>();
        styleList = new ArrayList<String>();
        genderList.add("女");        //性别
        genderList.add("男");
        styleList.add("优雅");        //风格
        styleList.add("大方");
        styleList.add("活泼");
        styleList.add("自强");
        //适配器
        arrayAdapter1 = new ArrayAdapter<String>(
                context,R.layout.support_simple_spinner_dropdown_item,genderList);
        spinner1.setAdapter(arrayAdapter1);
        arrayAdapter2 = new ArrayAdapter<String>(
                context,R.layout.support_simple_spinner_dropdown_item,styleList);
        spinner2.setAdapter(arrayAdapter2);

    }

    //数据初始化
    private void initDataBase(){
        nameDBList = LitePal.findAll(NameDB.class);
        userMakeDBList = LitePal.findAll(UserMakeDB.class);
    }

    //取名
    private void exportName(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                final int randNum = random.nextInt(N);
                NameDB ndb = nameDBList.get(randNum);
                exportName.setText(ndb.getName());
            }
        });

    }

}
