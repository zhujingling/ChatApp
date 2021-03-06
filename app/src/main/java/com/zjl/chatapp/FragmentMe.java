package com.zjl.chatapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/1/6.
 */
public class FragmentMe extends Fragment implements View.OnClickListener {
    private TextView tvPersonSig;//个性前面
    private TextView txtTopBarView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);

        tvPersonSig= (TextView) view.findViewById(R.id.txt_personal_signature);
        tvPersonSig.setOnClickListener(this);

        txtTopBarView= (TextView) view.findViewById(R.id.setting_title_txt);
        txtTopBarView.setText(R.string.skin_tabbar_icon_me);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_personal_signature:
                personalOnClick();
                break;
            default:
                break;
        }
    }

    private void personalOnClick(){
        Intent intent = new Intent(getActivity(), MeSignatureActivity.class);
        intent.putExtra("personalSig", (String) tvPersonSig.getText());
        startActivity(intent);
    }
}
