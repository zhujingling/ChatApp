package com.zjl.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjl.chatapp.ChatActivity;
import com.zjl.chatapp.R;
import com.zjl.entity.ChatMsgList;

import java.util.List;

/**
 * Created by Administrator on 2016/1/16.
 */
public class ChatMsgListAdapter extends BaseAdapter {
    /**
     * 上下文对象
     */
    private Context mContext = null;
    private List<ChatMsgList> data;

    public ChatMsgListAdapter(Context ctx,List<ChatMsgList> data) {
        mContext = ctx;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.chat_msglist, parent, false);
            holder = new ViewHolder();
            holder.chat_detail = (RelativeLayout)convertView.findViewById(R.id.chat_detail);
            holder.item_right = (RelativeLayout)convertView.findViewById(R.id.item_right);

            holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.tv_title = (TextView)convertView.findViewById(R.id.tv_title);
            holder.tv_msg = (TextView)convertView.findViewById(R.id.tv_msg);
            holder.tv_time = (TextView)convertView.findViewById(R.id.tv_time);

            holder.item_right_txt = (TextView)convertView.findViewById(R.id.item_right_txt);
            convertView.setTag(holder);
        } else {// 有直接获得ViewHolder
            holder = (ViewHolder)convertView.getTag();
        }

        Log.i("ChatMsgListAdapter", "getView position=" + position);

        ChatMsgList msg = data.get(position);

        holder.tv_title.setText(msg.getTitle());
        holder.tv_msg.setText(msg.getMsg());
        holder.tv_time.setText(msg.getTime());

        holder.iv_icon.setImageResource(msg.getIcon_id());

        holder.item_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onRightItemClick(v, position);
                }
            }
        });



        return convertView;
    }


    static class ViewHolder {
        RelativeLayout chat_detail;
        RelativeLayout item_right;

        TextView tv_title;
        TextView tv_msg;
        TextView tv_time;
        ImageView iv_icon;

        TextView item_right_txt;
    }

    /**
     * 单击事件监听器
     */
    private onRightItemClickListener mListener = null;

    public void setOnRightItemClickListener(onRightItemClickListener listener){
        mListener = listener;
    }

    public interface onRightItemClickListener {
        void onRightItemClick(View v, int position);
    }


}
