package com.example.desktop.frag;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.desktop.R;



public class ShouyeFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "FragmentWord";

    private int currentBookId;

    private boolean isOnClick = true;

    private int currentRandomId;

    public static int prepareData = 0;

    private TextView titleTv,detailTv,dateTv,monthTv;
    private TextView beginTv, searchTv, wordlistTv, translateTv;

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
        init();


    }

    private void init() {
        titleTv = getActivity().findViewById(R.id.shouye_tv_title);
        detailTv = getActivity().findViewById(R.id.shouye_tv_detail);
        dateTv = getActivity().findViewById(R.id.shouye_tv_labeltext);
        monthTv = getActivity().findViewById(R.id.shouye_tv_month);
        beginTv = getActivity().findViewById(R.id.shouye_tv_begin);
        searchTv = getActivity().findViewById(R.id.shouye_tv_search);
        wordlistTv = getActivity().findViewById(R.id.shouyr_tv_wordlist);
        translateTv = getActivity().findViewById(R.id.shouye_tv_translate);

        beginTv.setOnClickListener(this);
        searchTv.setOnClickListener(this);
        wordlistTv.setOnClickListener(this);
        translateTv.setOnClickListener(this);


//        view.findViewById(R.id.fuxi_tv_listen).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), LearnwordActivity.class));
//            }
//        });
//        view.findViewById(R.id.fuxi_tv_remember).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), SearchActivity.class));
//            }
//        });



    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shouye_tv_begin:
//                Intent intent = new Intent(getActivity(),LearnwordActivity.class);
//                startActivity(intent);
                break;
            case R.id.shouye_tv_search:
//                Intent intent1 = new Intent(getActivity(),SearchActivity.class);
//                startActivity(intent1);
                break;
//            case R.id.shouye_tv_translate:
//                Intent intent2 = new Intent(getActivity(),WordclipActivity.class);
//                startActivity(intent2);
//                break;
//            case R.id.shouye_tv_wordlist:
//                Intent intent3 = new Intent(getActivity(),ChinesetoEnglishActivity.class);
//                startActivity(intent3);
//                break;
        }
    }
}