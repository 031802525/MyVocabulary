package com.example.desktop.frag;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.desktop.Bean.planbean.DakaBean;
import com.example.desktop.Bean.planbean.GetDakaCountBean;
import com.example.desktop.Bean.wordbookbean.Cet4ReviewBean;
import com.example.desktop.Bean.wordbookbean.Cet6ReviewBean;
import com.example.desktop.Bean.wordbookbean.HeightwordReviewBean;
import com.example.desktop.My.activity.DakaCalendarActivity;
import com.example.desktop.My.activity.FinishActivity;
import com.example.desktop.My.util.DateUtil;
import com.example.desktop.R;
import com.example.desktop.Register.LoginActivity;
import com.example.desktop.Relearn.ChooseWordbookActivity;
import com.example.desktop.Shouye.activity.WordListActivity;
import com.example.desktop.Util.LoadDataAsyncTask;
import com.example.desktop.Util.URLContent;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
  * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment implements View.OnClickListener {

    private ImageView idIv,dakaIv;
    private Button planBtn,dakaBtn,listBtn;
    private TextView holdTv,idTv,dayTv,exitTv;

    private int idimags[] = {R.mipmap.id1,R.mipmap.id2,R.mipmap.id3,
            R.mipmap.id4,R.mipmap.id5,R.mipmap.id6,
            R.mipmap.id7,R.mipmap.id8,R.mipmap.id9,R.mipmap.id10,R.mipmap.id11};


    private static final String TAG = "FragmentMe";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        initWordCount();
//        initDakaCount();
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        初始化控件
        init();

        setIdImages();

        SharedPreferences sp = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        idTv.setText(sp.getString("USER_NAME","用户名"));
        holdTv.setText(sp.getInt("nowwordnum",10)+"");
//        SharedPreferences spc = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
//        int day = spc.getInt("count",0);
//        dayTv.setText(day+"");
        dayTv.setText(sp.getInt("DaKa",0)+"");
        holdTv.setText(sp.getInt("WordNum",0)+"");
        initWordCount();
        initDakaCount();
    }

    private void initWordCount() {
        SharedPreferences sp = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name = sp.getString("USER_NAME","");
        int type = sp.getInt("wordtype",4);
        int plan = sp.getInt("plan",10);
        String url = "";
        if(type == 4){
            url = URLContent.getCet4Review(name,plan);
        }else if(type == 6){
            url = URLContent.getCet6Review(name,plan);
        }else if(type == 8){
            url = URLContent.getCet8Review(name,plan);
        }
        LoadDataAsyncTask task = new LoadDataAsyncTask(getActivity(), new LoadDataAsyncTask.OnGetNetDataListener() {
            @Override
            public void onSuccess(String json) {
                if(!TextUtils.isEmpty(json)){
                    SharedPreferences sp = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                int type = sp.getInt("wordtype", 4);
                if (type == 4) {
                    Cet4ReviewBean bean = new Gson().fromJson(json, Cet4ReviewBean.class);
                    holdTv.setText(bean.getNum() + "");
                } else if (type == 6) {
                    Cet6ReviewBean bean = new Gson().fromJson(json, Cet6ReviewBean.class);
                    holdTv.setText(bean.getNum() + "");
                } else if (type == 8) {
                    HeightwordReviewBean bean = new Gson().fromJson(json, HeightwordReviewBean.class);
                    holdTv.setText(bean.getNum() + "");
                }
            } else {
                    holdTv.setText(0+ "");
                }
            }
        }, false);
        task.execute(url);
    }

    private void initDakaCount() {
        SharedPreferences sp = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name = sp.getString("USER_NAME","");
        String url = URLContent.getDakadayCount(name);
        LoadDataAsyncTask task = new LoadDataAsyncTask(getActivity(), new LoadDataAsyncTask.OnGetNetDataListener() {
            @Override
            public void onSuccess(String json) {
                if (!TextUtils.isEmpty(json)) {
                    GetDakaCountBean bean = new Gson().fromJson(json, GetDakaCountBean.class);
                    dayTv.setText(bean.getTime()+"");
                }else {
                    dayTv.setText(0+"");
                }
            }
        }, false);
        task.execute(url);
    }

    private void setIdImages() {
        Random r = new Random();
        int random = r.nextInt(11);  // [0,7)
        idIv.setImageResource(idimags[random]);
    }

    private void init() {
        idIv = getActivity().findViewById(R.id.me_iv_img);
        dakaIv = getActivity().findViewById(R.id.me_iv_dakabtn);
        planBtn = getActivity().findViewById(R.id.me_btn_plan);
        dakaBtn = getActivity().findViewById(R.id.me_btn_daka);
        listBtn = getActivity().findViewById(R.id.me_btn_list);
        holdTv = getActivity().findViewById(R.id.me_tv_hold);
        idTv = getActivity().findViewById(R.id.me_tv_id);
        dayTv = getActivity().findViewById(R.id.me_tv_day);
        exitTv = getActivity().findViewById(R.id.me_tv_exit);

        idTv.setOnClickListener(this);
        exitTv.setOnClickListener(this);
        dakaBtn.setOnClickListener(this);
        listBtn.setOnClickListener(this);
        planBtn.setOnClickListener(this);
        dakaIv.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.me_tv_id:
                break;
            case R.id.me_tv_exit:
                clear();
                Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.me_btn_daka:
                Intent intent3 = new Intent(getActivity(), DakaCalendarActivity.class);
                startActivity(intent3);
                break;
            case R.id.me_btn_plan:
                Intent intent5 = new Intent(getActivity(), ChooseWordbookActivity.class);
                startActivity(intent5);
                break;
            case R.id.me_btn_list:
                Intent intent4 = new Intent(getActivity(), WordListActivity.class);
                startActivity(intent4);
                break;
            case R.id.me_iv_dakabtn:
                int day = DateUtil.getCurrentday();
                SharedPreferences sp = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
                int lastDay = sp.getInt("day",0);
                if(lastDay == day) {
                    Intent intent = new Intent(getActivity(), FinishActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(),"请从学习完单词再来打卡~",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    private void clear(){
        SharedPreferences sp = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("use","");
        editor.putString("pwd","");
        editor.putBoolean("ISCHECK",false);
        editor.putBoolean("AUTO_ISCHECK",false);
        editor.commit();
    }
}
