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
import com.zjl.util.Sort;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/1/6.
 */
public class FragmentFriend extends Fragment implements View.OnTouchListener,GestureDetector.OnGestureListener {
    private TextView txtTopBarView;

    private String searchStr = null;

    private Sort fSort = null;

    private Context fContext = null;

    private TextView fTagIcon = null;

    private FriendListAdapter fAdapter = null;

    private ListView fListView = null;

    private EditText fSearchText = null;

    private ImageView fImageView = null;

    private GestureDetector fGesture = null;

    private ArrayList<String> newDataArrayList;

    private ArrayList<String> checkArrayList;

    private String[] newData;

    private String[] english = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    private String[] oldData = {"一败如水", "胆小如鼠", "引狼入室", "风驰电掣", "刀山火海", "一贫如洗",
            "料事如神", "视死如归", "对答如流", "挥金如土", "铁证如山", "度日如年", "心急如焚", "巧舌如簧",
            "如雷贯耳", "如履薄冰", "如日中天", "势如破竹", "稳如泰山", "骨瘦如柴", "爱财如命", "暴跳如雷",
            "门庭若市", "恩重如山", "从善如流", "观者如云", "浩如烟海", "弃暗投明", "取长补短", "厚今薄古",
            "同甘共苦", "因小失大", "优胜劣败", "自生自灭", "评头论足", "远交近攻", "求同存异", "well",
            "hello", "one", "goodtime", "running", "java", "android", "jsp",
            "html", "struts", "Charles", "Mark", "Bill", "Vincent", "William",
            "Joseph", "James", "Henry", "Gary", "Martin"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.fragment_friend, container, false);
        txtTopBarView= (TextView) view.findViewById(R.id.setting_title_txt);
        txtTopBarView.setText("好友");

        fContext=getActivity();
        fTagIcon=(TextView)getActivity().getLayoutInflater().inflate(R.layout.friend_tag_icon, null);
        fImageView=(ImageView)view.findViewById(R.id.friendIV);
        fListView=(ListView)view.findViewById(R.id.friendLV);
        fSearchText=(EditText)getActivity().getLayoutInflater().inflate(R.layout.friend_search, null);

        fGesture=new GestureDetector(this);

        fSort=new Sort();

        newData=fSort.autoSort(oldData);
        newDataArrayList=fSort.addChar(newData);

        fAdapter=new FriendListAdapter(getActivity(),android.R.layout.simple_list_item_1,newDataArrayList);
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
                if (fSearchText.hasFocus())
                    fSearchText.clearFocus();
                return false;
            }
        });

        fSearchText.addTextChangedListener(new FriendWatchToSearch());

        fImageView.setOnTouchListener(this);
        fImageView.setLongClickable(true);

        return view;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        util(e);
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // TODO Auto-generated method stub
        fImageView.setImageResource(R.drawable.contact_list_scroll_normal);
        fTagIcon.setVisibility(View.INVISIBLE);
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        util(e2);
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        fImageView.setImageResource(R.drawable.contact_list_scroll_normal);
        fTagIcon.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        fImageView.setImageResource(R.drawable.contact_list_scroll_normal);
        fTagIcon.setVisibility(View.INVISIBLE);
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(fSearchText.hasFocus())
            fSearchText.clearFocus();
        return fGesture.onTouchEvent(event);
    }

    public void util(MotionEvent e2)
    {
        //获取触摸点在导航图片中的位置
        int i = (int) (e2.getY() - fImageView.getTop());
        fImageView.setImageResource(R.drawable.contact_list_scroll_pressed);

        int pos;
        int count;
        if (0 <= i && i <= 30)
        {
            fTagIcon.setText("搜");
            fTagIcon.setVisibility(View.VISIBLE);
            fListView.setSelectionFromTop(0, 0);
        }
        else
        {
            count = (int) ((i - 31) / ((float) (fImageView.getHeight() - 62) / 26f));

            if (0 <= count && count < 26)
            {
                fTagIcon.setText(english[count]);
                fTagIcon.setVisibility(View.VISIBLE);
                pos = fAdapter.checkSection(english[count]) + 1;
                if (pos >= 0) fListView.setSelectionFromTop(pos, 0);
            }
            else
            {
                fTagIcon.setVisibility(View.VISIBLE);
                fTagIcon.setText("#");
            }
        }

    }


    class FriendWatchToSearch implements TextWatcher
    {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after)
        {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count)
        {
            // TODO Auto-generated method stub
            //搜索功能只针对ListView中的内容数据，不针对标题数据。
            //使用没添加的分组字母的数据创建新的ArrayList
            checkArrayList = fSort.toArrayList(newData);
            searchStr = fSearchText.getText().toString();

            if (searchStr.length() != 0)
            {
                checkSearchStr(searchStr);
                fAdapter = new FriendListAdapter(fContext,
                        android.R.layout.simple_list_item_1, checkArrayList);
                fListView.setAdapter(fAdapter);
            }
        }

        @Override
        public void afterTextChanged(Editable s)
        {
            // TODO Auto-generated method stub
            if (searchStr.length() == 0)
            {
                fAdapter = new FriendListAdapter(fContext,
                        android.R.layout.simple_list_item_1, newDataArrayList);
                fListView.setAdapter(fAdapter);
            }
            fSearchText.requestFocus();
        }

        /**
         *
         */
        public void checkSearchStr(String search)
        {

            String tempSearch;
            String tempList;
            String newDataChar = null;
            String checkArrayListItem = null;
            //当输入的搜索字符为字母时
            if (search.matches("[a-zA-Z]+"))
            {
                for (int i = 0; i < search.length(); i++)
                {
                    for (int j = 0; j < checkArrayList.size(); j++)
                    {
                        checkArrayListItem = checkArrayList.get(j);
                        //如果联系人名称不为字母，则得到联系人名称的所有首字母
                        if (!checkArrayListItem.matches("[a-zA-Z]+"))
                        {
                            newDataChar = fSort
                                    .getAllPinYinHeadChar(checkArrayListItem);
                        }
                        else
                        {
                            newDataChar = checkArrayListItem;
                        }
                        //取出输入的字符串的第i个字母，并转换为大写
                        tempSearch = String.valueOf(search.charAt(i))
                                .toUpperCase();
                        //取出得到的联系人名称所有首字母的第i个字母，并转换为大写
                        tempList = String.valueOf(newDataChar.charAt(i))
                                .toUpperCase();

                        if (!(tempSearch.equals(tempList)))
                        {
                            checkArrayList.remove(j);
                            newDataChar = null;
                            j--;
                        }
                    }
                }
            }
            //当输入的搜索字符为汉字时
            else if (search.matches("[\u4e00-\u9fa5]+"))
            {

                for (int j = 0; j < checkArrayList.size(); j++)
                {
                    if (!checkArrayList.get(j).contains(search))
                    {
                        checkArrayList.remove(j);
                        j--;
                    }
                }
            }
            else
            {
                search = String.valueOf(search.charAt(0));
                for (int j = 0; j < checkArrayList.size(); j++)
                {
                    if (!checkArrayList.get(j).contains(search))
                    {
                        checkArrayList.remove(j);
                        j--;
                    }
                }
            }
        }
    }
}
