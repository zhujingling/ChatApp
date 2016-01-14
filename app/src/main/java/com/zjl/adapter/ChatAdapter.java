package com.zjl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zjl.chatapp.R;
import com.zjl.entity.ChatMessage;

import java.util.List;

/**
 * Created by Administrator on 2016/1/11.
 */
public class ChatAdapter extends BaseAdapter {
    private Context mContext;
    private List<ChatMessage> mData;

    public ChatAdapter(Context context, List<ChatMessage> data) {
        this.mContext = context;
        this.mData = data;
    }

    public void Refresh() {
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView Content;
        switch(mData.get(position).getType())
        {
            case ChatMessage.MessageType_Time:
                convertView= LayoutInflater.from(mContext).inflate(R.layout.chat_time, null);
                Content=(TextView)convertView.findViewById(R.id.Time);
                Content.setText(mData.get(position).getContent());
                break;
            case ChatMessage.MessageType_From:
                convertView=LayoutInflater.from(mContext).inflate(R.layout.chat_receive, null);
                Content=(TextView)convertView.findViewById(R.id.chatlist_receive);
                Content.setText(mData.get(position).getContent());
                break;
            case ChatMessage.MessageType_To:
                convertView=LayoutInflater.from(mContext).inflate(R.layout.chat_send, null);
                Content=(TextView)convertView.findViewById(R.id.chatlist_send);
                Content.setText(mData.get(position).getContent());
                break;
        }
        return convertView;
    }
}
