package com.example.poemapp.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;

import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.example.poemapp.Fragment.CommunicatePageFragment;
import com.example.poemapp.Fragment.CreatePageFinishFragment;
import com.example.poemapp.Fragment.FunPageFragment;
import com.example.poemapp.Fragment.StudyPageFragment;
import com.example.poemapp.Fragment.CreatePageFragment;

import com.example.poemapp.JavaClass.BottomNavigationViewHelper;
import com.example.poemapp.R;



import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends BaseActivity {
    //全局声明
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBar actionBar;
    NavigationView navigationView;
    CircleImageView circleImageView;
    View headLayout;
    ImageView imageView;
    TextView titleText;
    MenuItem searchMI,indexMI,finishMI,shareMI,moomMI;
    Menu drawerMenu;

    //控制量
    private Boolean mVisiable = true;
    private int mSwitch = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        replaceFragment(new StudyPageFragment());


        //调用控件
        initView();
        //左滑菜单响应
        drawerLayoutListener();
    }



    /**
     * 方法实现
     */
    //控件实现
    public void initView(){
        //获取控件id
        toolbar = findViewById(R.id.study_toolbar);
        drawerLayout = findViewById(R.id.study_drawerlayout);
        navigationView = findViewById(R.id.zuohua_menu);
        headLayout = navigationView.inflateHeaderView(R.layout.nav_header);
        titleText = findViewById(R.id.title_text);

        //顶端标题
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.nav_menu);
        }

        //底端按钮事件
        bottomNavigationEvent();

        //左滑菜单顶部头像响应
        imageView = headLayout.findViewById(R.id.icon_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UserActivity.class);
                startActivity(intent);
            }
        });



    }

    //底端按钮
    private void bottomNavigationEvent() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {   //监听器
                setmSwitch(0);
                switch (item.getItemId()){
                    case R.id.bt_study:
                        replaceFragment(new StudyPageFragment());
                        titleText.setText("学诗");
                        mVisiable = true;
                        invalidateOptionsMenu();
                        break;
                    case R.id.bt_create:
                        replaceFragment(new CreatePageFragment());
                        titleText.setText("作诗");
                        mVisiable = false;
                        invalidateOptionsMenu();
                        break;
                    case R.id.bt_shequ:
                        replaceFragment(new CommunicatePageFragment());
                        titleText.setText("交流");
                        mVisiable = false;
                        invalidateOptionsMenu();
                        break;
                    case R.id.bt_fun:
                        replaceFragment(new FunPageFragment());
                        titleText.setText("趣味");
                        mVisiable = false;
                        invalidateOptionsMenu();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        searchMI = menu.findItem(R.id.search);
        indexMI = menu.findItem(R.id.index);
        finishMI = menu.findItem(R.id.finish);
        shareMI = menu.findItem(R.id.share);

        //动态设置可见
        finishMI.setVisible(false);
        shareMI.setVisible(false);

        if (mVisiable){
            searchMI.setVisible(true);
            indexMI.setVisible(true);
        }else {
            searchMI.setVisible(false);
            indexMI.setVisible(false);
        }

        if (mSwitch == 2){
            finishMI.setVisible(true);
        }else if (mSwitch == 3){
            shareMI.setVisible(true);
        }

        return true;
    }

    //点击item
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.search:
                Intent intent1 = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent1);
                break;
            case R.id.index:
                Intent intent2 = new Intent(MainActivity.this, IndexActivity.class);
                startActivity(intent2);
            case R.id.finish:
                setmSwitch(3);
                replaceFragment(new CreatePageFinishFragment());
                break;
            case R.id.share:
                break;
            default:
                break;
        }
        return true;
    }

    //左滑菜单响应
    public void drawerLayoutListener(){
        //夜间开关
        drawerMenu = navigationView.getMenu();
        moomMI = drawerMenu.findItem(R.id.nav_moon);
        Switch moomSwitch = (Switch) moomMI.getActionView();
        moomSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Log.d(String.valueOf(MainActivity.this),"夜间模式开启");
                }else {
                    Log.d(String.valueOf(MainActivity.this),"夜间模式关闭");
                }
            }
        });

        //左滑菜单按钮监听
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_setting:
                        Intent intenta = new Intent(MainActivity.this,SettingActivity.class);
                        startActivity(intenta);
                        break;
                    case R.id.nav_friend:
                        Intent intentb = new Intent(MainActivity.this,FriendActivity.class);
                        startActivity(intentb);
                        break;
                    case R.id.nav_msg:
                        Intent intentc = new Intent(MainActivity.this,MsgActivity.class);
                        startActivity(intentc);
                        break;
                    case R.id.nav_collection:
                        Intent intentd = new Intent(MainActivity.this,MyCollectionActivity.class);
                        startActivity(intentd);
                        break;
                    case R.id.nav_langsong:
                        Intent intente = new Intent(MainActivity.this,MyIntonationActivity.class);
                        startActivity(intente);
                        break;
                    case R.id.nav_write:
                        Intent intentf = new Intent(MainActivity.this,MyWorksActivity.class);
                        startActivity(intentf);
                        break;
                    default:
                        break;
                }

                return true;
            }
        });


    }

    //fragment切换
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();   //开启转换事务
        transaction.replace(R.id.main_layout,fragment);
        transaction.commit();   //提交并结束事务
    }

    //mSwitch控制量设置，影响标题栏按钮动态显示
    public void setmSwitch(int index){
        mSwitch = index;
        invalidateOptionsMenu();
    }


}
