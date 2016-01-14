package com.zjl.chatapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.zjl.adapter.ChatAdapter;
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
    private ChatAdapter cAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.chat_detail);
            final ListView mListView = (ListView) findViewById(R.id.chat_list);
            mData = LoadData();
            cAdapter = new ChatAdapter(this, mData);
            mListView.setAdapter(cAdapter);
            mListView.smoothScrollToPositionFromTop(mData.size(), 0);
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
                    mListView.smoothScrollToPositionFromTop(mData.size(), 0);
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

        Message = new ChatMessage(ChatMessage.MessageType_From, "山重水复疑无路");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_To, "柳暗花明又一村");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_From, "青青子衿，悠悠我心");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_To, "但为君故，沉吟至今");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_Time, "19：25");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_From, "这是你做的Android程序吗？");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_To, "是的，这是一个仿微信的聊天界面");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_From, "为什么下面的消息发送不了呢");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_To, "呵呵，我会告诉你那是直接拿图片做的么");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_From, "哦哦，呵呵，你又在偷懒了");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_To, "因为这一部分不是今天的重点啊");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_From, "好吧，可是怎么发图片啊");
        Messages.add(Message);

        Message = new ChatMessage(ChatMessage.MessageType_To, "很简单啊，你继续定义一种布局类型，然后再写一个布局就可以了");
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
