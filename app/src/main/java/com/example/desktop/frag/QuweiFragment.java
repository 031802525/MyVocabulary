package com.example.desktop.frag;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.desktop.Fun.activity.AlarmActivity;
import com.example.desktop.Fun.activity.EveryDayActivity;
import com.example.desktop.Fun.activity.LinkGameActivity;
import com.example.desktop.Fun.activity.LockScreenActivity;
import com.example.desktop.Fun.service.AlarmService;
import com.example.desktop.Fun.service.LockScreenService;
import com.example.desktop.Fun.util.ServiceUtil;
import com.example.desktop.MainActivity;
import com.example.desktop.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuweiFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout linkgameRL,lockRL,alarmRL;

    public QuweiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_quwei, container, false);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();

    }

    private void init() {
       linkgameRL = getActivity().findViewById(R.id.quwei_rl_linkgame);
       alarmRL = getActivity().findViewById(R.id.quwei_rl_alarm);
       lockRL = getActivity().findViewById(R.id.quwei_rl_bottom);
       linkgameRL.setOnClickListener(this);
       alarmRL.setOnClickListener(this);
       lockRL.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.quwei_rl_linkgame:
                Intent intent = new Intent(getActivity(), LinkGameActivity.class);
                startActivity(intent);
                break;
            case R.id.quwei_rl_alarm:
                Intent intent1 = new Intent(getActivity(), AlarmActivity.class);
                startActivity(intent1);
                break;
            case R.id.quwei_rl_bottom:
//                Intent intent2 = new Intent(getActivity(), LockScreenService.class);
//                Toast.makeText(getContext(),"开启成功",Toast.LENGTH_SHORT).show();
//                getActivity().startService(intent2);
                Intent intent2 = new Intent(getActivity(), EveryDayActivity.class);
                startActivity(intent2);
//                Intent intent2 = new Intent(getActivity(), LockScreenActivity.class);
//                startActivity(intent2);
                break;
        }
    }
}
