package com.example.desktop.frag;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.desktop.R;
import com.example.desktop.Relearn.ChooseWordbookActivity;
import com.example.desktop.Relearn.DueToChineseActivity;
import com.example.desktop.Relearn.DueToEnglishActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class FuxiFragment extends Fragment implements View.OnClickListener {

    private LinearLayout citoyill,yitocill,updateplanll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fuxi, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {
        citoyill = getActivity().findViewById(R.id.fuxi_ll_citoyi);
        yitocill = getActivity().findViewById(R.id.fuxi_ll_yitoci);
        updateplanll = getActivity().findViewById(R.id.fuxi_ll_updateplan);

        citoyill.setOnClickListener(this);
        yitocill.setOnClickListener(this);
        updateplanll.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fuxi_ll_citoyi:
                Intent intent = new Intent(getActivity(), DueToEnglishActivity.class);
                startActivity(intent);
                break;
            case R.id.fuxi_ll_yitoci:
                Intent intent1 = new Intent(getActivity(), DueToChineseActivity.class);
                startActivity(intent1);
                break;
            case R.id.fuxi_ll_updateplan:
                Intent intent2 = new Intent(getActivity(), ChooseWordbookActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
