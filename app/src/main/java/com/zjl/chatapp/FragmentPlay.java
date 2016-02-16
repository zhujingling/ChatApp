package com.zjl.chatapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/1/6.
 */
public class FragmentPlay extends Fragment {
    private TextView txtTopBarView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view= inflater.inflate(R.layout.fragment_play, container, false);
        txtTopBarView= (TextView) view.findViewById(R.id.setting_title_txt);
        txtTopBarView.setText(R.string.skin_tabbar_icon_play);
        return view;
    }

}
