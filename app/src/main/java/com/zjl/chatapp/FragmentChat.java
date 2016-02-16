package com.zjl.chatapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.zjl.adapter.ChatMsgListAdapter;
import com.zjl.component.ChatMsgListView;
import com.zjl.entity.ChatMsgList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/6.
 */
public class FragmentChat extends Fragment implements View.OnClickListener {
    private TextView txtTopBarView;

    private List<ChatMsgList> data = new ArrayList<ChatMsgList>();
    private ChatMsgListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_chat, container, false);

        initData();

        mListView = (ChatMsgListView) view.findViewById(R.id.mListView);
        findView(view);



        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"默认样式",position).show();
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                FragmentChat.this.startActivity(intent);
            }
        });

        txtTopBarView= (TextView) view.findViewById(R.id.setting_title_txt);
        txtTopBarView.setText(R.string.skin_tabbar_icon_chat);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    private void findView(View view) {

        ChatMsgListAdapter chatMsgListAdapter = new ChatMsgListAdapter(getActivity(),data);
        chatMsgListAdapter.setOnRightItemClickListener(new ChatMsgListAdapter.onRightItemClickListener() {

            @Override
            public void onRightItemClick(View v, int position) {
            }
        });

        mListView.setAdapter(chatMsgListAdapter);
    }

    private void initData() {

        for(int i=0;i<50;i++){
            ChatMsgList msg = null;
            if(i%3==0){
                msg = new ChatMsgList("小毛驴", "小毛驴留图不留种", "早上8:44");
                msg.setIcon_id(R.drawable.qq_icon);
            }else if(i%3==1){
                msg = new ChatMsgList("大煞笔", "来一发？","早上8:49");
                msg.setIcon_id(R.drawable.wechat_icon);
            }else{
                msg = new ChatMsgList("靓妹", "约？约就来","昨天晚上");
                msg.setIcon_id(R.drawable.qq_icon);
            }

            data.add(msg);
        }
    }


}
