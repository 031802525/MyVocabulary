package com.example.desktop;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.desktop.frag.FuxiFragment;
import com.example.desktop.frag.QuweiFragment;
import com.example.desktop.frag.MeFragment;
import com.example.desktop.frag.ShouyeFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    RadioGroup mainRg;
    Fragment ShouyeFrag, QuweiFrag,FuxiFrag,MeFrag;
    private FragmentManager manager;
    public static boolean needRefresh = true;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainRg = findViewById(R.id.main_rg);
        mainRg.setOnCheckedChangeListener(this);

//        创建Fragment对象
        ShouyeFrag = new ShouyeFragment();
        QuweiFrag = new QuweiFragment();
        FuxiFrag = new FuxiFragment();
        MeFrag = new MeFragment();
        addFragmentPage();
    }

//    加入所有的Fragment页面，一个显示，其他三个隐藏
    private void addFragmentPage() {
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_layout_center,ShouyeFrag);
        transaction.add(R.id.main_layout_center, QuweiFrag);
        transaction.add(R.id.main_layout_center,FuxiFrag);
        transaction.add(R.id.main_layout_center,MeFrag);
        transaction.hide(QuweiFrag);
        transaction.hide(FuxiFrag);
        transaction.hide(MeFrag);
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (i){
            case R.id.main_rb_quwei:
                transaction.hide(ShouyeFrag);
                transaction.hide(FuxiFrag);
                transaction.hide(MeFrag);
                transaction.show(QuweiFrag);
                break;
            case R.id.main_rb_fuxi:
                transaction.hide(ShouyeFrag);
                transaction.hide(MeFrag);
                transaction.hide(QuweiFrag);
                transaction.show(FuxiFrag);
                break;
            case R.id.main_rb_me:
                transaction.hide(ShouyeFrag);
                transaction.hide(QuweiFrag);
                transaction.hide(FuxiFrag);
                transaction.show(MeFrag);
                break;
            case R.id.main_rb_shouye:
                transaction.hide(QuweiFrag);
                transaction.hide(FuxiFrag);
                transaction.hide(MeFrag);
                transaction.show(ShouyeFrag);
                break;
        }
        transaction.commit();
    }
}
