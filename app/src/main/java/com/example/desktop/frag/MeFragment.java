package com.example.desktop.frag;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.desktop.My.DakaCalendarActivity;
import com.example.desktop.My.UpdatedetailActivity;
import com.example.desktop.R;
import com.example.desktop.Register.LoginActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment implements View.OnClickListener {

    private ImageView idIv,dakaIv;
    private Button planBtn,dakaBtn,listBtn;
    private TextView holdTv,idTv,dayTv,exitTv;

    private static final String TAG = "FragmentMe";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        初始化控件
        init();
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

        idIv.setOnClickListener(this);
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
//            case R.id.me_iv_img:
//                Intent intent = new Intent(getActivity(), UpdatedetailActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.me_tv_id:
//                Intent intent2 = new Intent(getActivity(), UpdatedetailActivity.class);
//                startActivity(intent2);
//                break;
            case R.id.me_tv_exit:
                Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent1);
                break;
//            case R.id.me_btn_daka:
//                Intent intent3 = new Intent(getActivity(), DakaCalendarActivity.class);
//                startActivity(intent3);
//                break;
//            case R.id.me_btn_plan:
//
//                break;
//            case R.id.me_btn_list:
//
//                break;
//            case R.id.me_iv_dakabtn:
//
//                break;
        }
    }
}
