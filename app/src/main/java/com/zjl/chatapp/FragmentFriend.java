package com.zjl.chatapp;

import android.content.Context;
import android.graphics.PixelFormat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zjl.adapter.FriendListAdapter;
import com.zjl.component.LettersSideBarView;
import com.zjl.util.Sort;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/1/6.
 */
public class FragmentFriend extends Fragment implements LettersSideBarView.OnTouchingLetterChangedListener {
    private TextView txtTopBarView;

    private Sort fSort = null;

    private Context fContext = null;

    private TextView fTagIcon = null;

    private FriendListAdapter fAdapter = null;

    private ListView fListView = null;

    private LettersSideBarView lettersSideBarView;

    private ArrayList<String> newDataArrayList;


    private String[] newData;

    private String[] oldData = {"阿小毛驴", "逼小毛驴", "擦小毛驴", "傻吊", "高富帅", "百富美",
            "屌丝", "王老气", "鬼脚七", "马云", "李彦宏", "习近平", "朱镕基", "李克强",
            "孙悟空", "如来", "玉皇大帝", "唐僧", "猪八戒", "及时雨", "李白", "白居易",
            "王宝强", "周星驰", "刘备", "曹操", "孙权", "袁术", "取尔首级", "探囊取物",
            "降龙十八掌", "六脉神剑", "小相公", "秦始皇", "汉武帝", "汉高祖", "唐太宗", "don't",
            "say", "anything", "just", "kiss", "me", "you", "me",
            "hello", "java", "C#", "python", "ObjectC", "谷歌", "亚马逊",
            "NBA", "James", "kobe", "韦德", "#123","123"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            View view = inflater.inflate(R.layout.fragment_friend, container, false);
            txtTopBarView = (TextView) view.findViewById(R.id.setting_title_txt);
            txtTopBarView.setText("好友");

            fContext = getActivity();
            fTagIcon = (TextView) getActivity().getLayoutInflater().inflate(R.layout.friend_tag_icon, null);
            lettersSideBarView = (LettersSideBarView) view.findViewById(R.id.letter_sidebar);
            fListView = (ListView) view.findViewById(R.id.friendLV);

            fSort = new Sort();

            newData = fSort.autoSort(oldData);
            newDataArrayList = fSort.addChar(newData);

            fAdapter = new FriendListAdapter(getActivity(), android.R.layout.simple_list_item_1, newDataArrayList);
            fListView.setAdapter(fAdapter);

            getActivity().getWindowManager()
                    .addView(
                            fTagIcon,
                            new WindowManager.LayoutParams(
                                    ViewGroup.LayoutParams.WRAP_CONTENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT,
                                    WindowManager.LayoutParams.TYPE_APPLICATION,
                                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                            | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                    PixelFormat.TRANSLUCENT));

            fListView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    return false;
                }
            });


            lettersSideBarView.setTextView(fTagIcon);
            lettersSideBarView.setOnTouchingLetterChangedListener(this);

            return view;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void onTouchingLetterChanged(String s) {
        int position = 0;
        // 该字母首次出现的位置
        if (fAdapter != null) {
            position = fAdapter.checkSection(s);
        }
        if (position != -1) {
            fListView.setSelection(position);
        }
    }
}
