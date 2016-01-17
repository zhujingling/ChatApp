package com.zjl.chatapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.zjl.adapter.ChatDetailAdapter;
import com.zjl.entity.ChatMessage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2016/1/11.
 */
public class ChatActivity extends Activity {
    private Button btn_chat_send;
    private EditText chatInputBox;
    private List<ChatMessage> mData;
    private ChatDetailAdapter cAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.chat_detail);
            final ListView mListView = (ListView) findViewById(R.id.chat_list);
            mData = LoadData();
            cAdapter = new ChatDetailAdapter(this, mData);
            mListView.setAdapter(cAdapter);
            mListView.smoothScrollToPosition(mData.size(), 0);
            chatInputBox = (EditText) findViewById(R.id.chat_edittext);
            btn_chat_send = (Button) findViewById(R.id.btn_chat_send);

            btn_chat_send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    if (chatInputBox.getText().toString() != "") {
                        //获取时间
                        Calendar c = Calendar.getInstance();
                        StringBuilder mBuilder = new StringBuilder();
                        mBuilder.append(Integer.toString(c.get(Calendar.YEAR)) + "年");
                        mBuilder.append(Integer.toString(c.get(Calendar.MONTH)) + "月");
                        mBuilder.append(Integer.toString(c.get(Calendar.DATE)) + "日");
                        mBuilder.append(Integer.toString(c.get(Calendar.HOUR_OF_DAY)) + ":");
                        mBuilder.append(Integer.toString(c.get(Calendar.MINUTE)));
                        //构造时间消息
                        ChatMessage Message = new ChatMessage(ChatMessage.MessageType_Time, mBuilder.toString());
                        mData.add(Message);
                        //构造输入消息
                        Message = new ChatMessage(ChatMessage.MessageType_To, chatInputBox.getText().toString());
                        mData.add(Message);
                        //构造返回消息，如果这里加入网络的功能，那么这里将变成一个网络机器人
                        Message = new ChatMessage(ChatMessage.MessageType_From, "收到！");
                        mData.add(Message);
                        //更新数据
                        cAdapter.Refresh();
                    }
                    //清空输入框
                    chatInputBox.setText("");
                    //关闭输入法
                    imm.hideSoftInputFromWindow(null, InputMethodManager.HIDE_IMPLICIT_ONLY);
                    //滚动列表到当前消息
                    mListView.smoothScrollToPosition(mData.size(), 0);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<ChatMessage> LoadData() {
        List<ChatMessage> Messages = new ArrayList<ChatMessage>();

        ChatMessage Message = new ChatMessage(ChatMessage.MessageType_Time, "2015年12月27日");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_From, "速度带头");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_To, "带毛，每次都是我");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_From, "麻痹，1024需要翻墙？");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_To, "毛，发种子来看看");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_Time, "19：25");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_From, "傻吊？");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_To, "弄死吗");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_To, "毛的傻吊");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_From, "听说教主的工具都没了？");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_To, "毛的没");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_From, "真真的");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_To, "毛的真真的，教主怎么可能会没有工具，你有脑子吗");
        Messages.add(Message);
        return Messages;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
