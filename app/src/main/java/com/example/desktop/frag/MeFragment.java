package com.example.desktop.frag;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.desktop.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {

    private Switch aSwitchNight;
    private TextView usernameTv;

    private static final String TAG = "FragmentMe";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me, container, false);
    }


    private void init() {
//        usernameTv = getActivity().findViewById(R.id.me_tv_id);
    }

}
