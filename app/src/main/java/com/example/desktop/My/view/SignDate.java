package com.example.desktop.My.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.desktop.My.OnSignedSuccess;
import com.example.desktop.My.adapter.AdapterDate;
import com.example.desktop.My.adapter.AdapterWeek;
import com.example.desktop.My.util.DateUtil;
import com.example.desktop.My.view.InnerGridView;
import com.example.desktop.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SignDate extends LinearLayout {

    private TextView tvYear;
    private InnerGridView gvWeek;
    private InnerGridView gvDate;
    private AdapterDate adapterDate;
    private int day = 30;
    SharedPreferences sp;
    private List<Boolean> checkboolean = new ArrayList<>();


    public SignDate(Context context) {
        super(context);
        init();
    }

    public SignDate(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SignDate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        View view = View.inflate(getContext(),R.layout.layout_signdate,this);
        tvYear = view.findViewById(R.id.tvYear);
        gvWeek = view.findViewById(R.id.gvWeek);
        gvDate = view.findViewById(R.id.gvDate);
        tvYear.setText(DateUtil.getCurrentYearAndMonth());
        gvWeek.setAdapter(new AdapterWeek(getContext()));

        day = DateUtil.getCurrentMonthLastDay();
        for (int i = 0; i < day; i++) {
            checkboolean.add(false);
        }
        List<Integer> check = new ArrayList<>();
        SharedPreferences sp=getContext().getSharedPreferences("data",Context.MODE_PRIVATE);
        int size=sp.getInt("size",0);
        for(int i=0;i<size;i++) {
            check.add (sp.getInt("type"+i,0));
        }
//        改变签到天数
        for (int i = 0; i < check.size(); i++) {
            checkboolean.set(check.get(i)-1,true);
        }

        adapterDate = new AdapterDate(getContext(),checkboolean);
        gvDate.setAdapter(adapterDate);
    }

    /**
     * 签到成功的回调
     * @param onSignedSuccess
     */
    public void setOnSignedSuccess(OnSignedSuccess onSignedSuccess){
        adapterDate.setOnSignedSuccess(onSignedSuccess);
    }
}
