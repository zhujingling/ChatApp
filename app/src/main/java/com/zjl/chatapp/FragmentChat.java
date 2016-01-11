package com.zjl.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/1/6.
 */
public class FragmentChat extends Fragment implements View.OnClickListener {
    private TextView txtTopBarView;
    private TextView tvChatDetail;//聊天
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_chat, container, false);

        tvChatDetail= (TextView) view.findViewById(R.id.chat_detail);
        tvChatDetail.setOnClickListener(this);

        txtTopBarView= (TextView) view.findViewById(R.id.setting_title_txt);
        txtTopBarView.setText("聊天");
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chat_detail:
                chatDetailOnClick();
                break;
            default:
                break;
        }
    }


    private void chatDetailOnClick(){
       try{
           Intent intent = new Intent(getActivity(), ChatActivity.class);
           startActivity(intent);
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}
