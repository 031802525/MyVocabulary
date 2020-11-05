package com.example.desktop.frag;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.desktop.R;


public class ShouyeFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "FragmentWord";

    private int currentBookId;

    private boolean isOnClick = true;

    private int currentRandomId;

    public static int prepareData = 0;

    private TextView titleTv,detailTv,dateTv,monthTv,beginTv,wordnumTv,showbookTv;
    private Button wordcollectBtn;
    private EditText searchEt;
    private ImageButton searchIb;
    private RelativeLayout beginRl;
    private ImageView searchIv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_shouye, container, false);
//        init();
        return view;
    }



//    private void init() {
//        titleTv = getActivity().findViewById(R.id.shouye_tv_title);
//        detailTv = getActivity().findViewById(R.id.shouye_tv_detail);
//        dateTv = getActivity().findViewById(R.id.shouye_tv_labeltext);
//        monthTv = getActivity().findViewById(R.id.shouye_tv_month);
//        wordnumTv = getActivity().findViewById(R.id.shouye_tv_wordnum);
//        showbookTv = getActivity().findViewById(R.id.shouye_tv_showbook);
//        beginTv = getActivity().findViewById(R.id.shouye_tv_begin);
//        beginTv.setOnClickListener(this);
//        wordcollectBtn = getActivity().findViewById(R.id.shouye_btn_word);
//        wordcollectBtn.setOnClickListener(this);
//        searchEt = getActivity().findViewById(R.id.shouye_ed_searchedit);
//        searchIb = getActivity().findViewById(R.id.shouye_ib_search);
//        searchIb.setOnClickListener(this);
//        beginRl = getActivity().findViewById(R.id.shouye_rl_beginbgc);
//    }


    @Override
    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.shouye_tv_begin:
//
//                break;
//            case R.id.shouye_btn_word:
//
//                break;
//            case R.id.shouye_ib_search:
//
//                break;
//        }
    }
}
