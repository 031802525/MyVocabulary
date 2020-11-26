package com.example.desktop.frag;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.desktop.My.util.DateUtil;
import com.example.desktop.R;
import com.example.desktop.Shouye.activity.ChineseToEnglishActivity;
import com.example.desktop.Shouye.activity.LearnWordActivity;
import com.example.desktop.Shouye.activity.SearchWordActivity;
import com.example.desktop.Shouye.activity.WordListActivity;


public class ShouyeFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "FragmentWord";
    private TextView titleTv,detailTv,dateTv,monthTv;
    private LinearLayout beginll, searchll, wordlistll;
    private RelativeLayout  translateRl;

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_shouye, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        初始化控件
        init();
        SharedPreferences sp = getActivity().getSharedPreferences("everysentence", Context.MODE_PRIVATE);
        if(!TextUtils.isEmpty(sp.getString("sentence",""))){
            titleTv.setText("     "+sp.getString("sentence",""));
            detailTv.setText(sp.getString("translation",""));
            dateTv.setText(DateUtil.getCurrentday()+"");
            monthTv.setText(DateUtil.getCurrentMonth()+"");
        }

    }

    private void init() {
        titleTv = getActivity().findViewById(R.id.shouye_tv_title);
        detailTv = getActivity().findViewById(R.id.shouye_tv_detail);
        dateTv = getActivity().findViewById(R.id.shouye_tv_labeltext);
        monthTv = getActivity().findViewById(R.id.shouye_tv_month);

        beginll = getActivity().findViewById(R.id.shouye_ll_begin);
        searchll = getActivity().findViewById(R.id.shouye_ll_search);
        wordlistll = getActivity().findViewById(R.id.shouye_ll_wordlist);
        translateRl = getActivity().findViewById(R.id.shouye_rl_translate);

        beginll.setOnClickListener(this);
        searchll.setOnClickListener(this);
        wordlistll.setOnClickListener(this);
        translateRl.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shouye_ll_begin:
                Intent intent = new Intent(getActivity(), LearnWordActivity.class);
                startActivity(intent);
                break;
            case R.id.shouye_ll_search:
                Intent intent1 = new Intent(getActivity(), SearchWordActivity.class);
                startActivity(intent1);
                break;
            case R.id.shouye_rl_translate:
                Intent intent2 = new Intent(getActivity(), ChineseToEnglishActivity.class);
                startActivity(intent2);
                break;
            case R.id.shouye_ll_wordlist:
                Intent intent3 = new Intent(getActivity(), WordListActivity.class);
                startActivity(intent3);
                break;
        }
    }
}