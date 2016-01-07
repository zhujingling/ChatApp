package com.zjl.chatapp;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity  extends FragmentActivity implements View.OnClickListener {

    //定义Fragment页面
    private FragmentChat fragmentChat;
    private FragmentFriend fragmentFriend;
    private FragmentPlay fragmentPlay;
    private FragmentMe fragmentMe;
    //定义布局对象
    private FrameLayout chatFl, friendFl, playFl, meFl;

    //定义突破片组件对象
    private ImageView chatIv, friendIv, playIv, meIv;

    //定义按钮图片组件
    private ImageView toggleImageView, plusImageView;


    // 定义PopupWindow
    private PopupWindow popWindow;

    // 获取手机屏幕分辨率的类
    private DisplayMetrics dm;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

        // 初始化默认为选中点击了“动态”按钮
        clickChatBtn();
    }

    /**
     * 初始化组件
     */
    private void initView() {
        // 实例化布局对象
        chatFl = (FrameLayout) findViewById(R.id.layout_chat);
        friendFl = (FrameLayout) findViewById(R.id.layout_friend);
        playFl = (FrameLayout) findViewById(R.id.layout_space);
        meFl = (FrameLayout) findViewById(R.id.layout_me);

        // 实例化图片组件对象
        chatIv = (ImageView) findViewById(R.id.image_chat);
        friendIv = (ImageView) findViewById(R.id.image_friend);
        playIv = (ImageView) findViewById(R.id.image_play);
        meIv = (ImageView) findViewById(R.id.image_me);

        // 实例化按钮图片组件
        toggleImageView = (ImageView) findViewById(R.id.toggle_btn);
        plusImageView = (ImageView) findViewById(R.id.plus_btn);

    }


    /**
     * 初始化数据
     */
    private void initData() {
        // 给布局对象设置监听
        chatFl.setOnClickListener(this);
        friendFl.setOnClickListener(this);
        playFl.setOnClickListener(this);
        meFl.setOnClickListener(this);

        // 给按钮图片设置监听
        toggleImageView.setOnClickListener(this);
    }


    /**
     * 点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 点击聊天按钮
            case R.id.layout_chat:
                clickChatBtn();
                break;
            // 点击与好友相关按钮
            case R.id.layout_friend:
                clickFriendBtn();
                break;
            // 点击我的空间按钮
            case R.id.layout_space:
                clickSpaceBtn();
                break;
            // 点击我按钮
            case R.id.layout_me:
                clickMeBtn();
                break;
            // 点击中间按钮
            case R.id.toggle_btn:
                clickToggleBtn();
                break;
        }
    }


    /**
     * 点击了“动态”按钮
     */
    private void clickChatBtn() {
        // 实例化Fragment页面
        fragmentChat = new FragmentChat();
        // 得到Fragment事务管理器
        FragmentTransaction fragmentTransaction = this
                .getSupportFragmentManager().beginTransaction();
        // 替换当前的页面
        fragmentTransaction.replace(R.id.frame_content, fragmentChat);
        // 事务管理提交
        fragmentTransaction.commit();
        // 改变选中状态
        setSelect(chatFl, chatIv, true);

        setSelect(friendFl, friendIv, false);

        setSelect(playFl, playIv, false);

        setSelect(meFl, meIv, false);

        textView=(TextView)findViewById(R.id.setting_title_txt);
        textView.setText("聊天");


    }

    /**
     * 点击了“与我相关”按钮
     */
    private void clickFriendBtn() {
        // 实例化Fragment页面
        fragmentFriend = new FragmentFriend();
        // 得到Fragment事务管理器
        FragmentTransaction fragmentTransaction = this
                .getSupportFragmentManager().beginTransaction();
        // 替换当前的页面
        fragmentTransaction.replace(R.id.frame_content, fragmentFriend);
        // 事务管理提交
        fragmentTransaction.commit();

        setSelect(chatFl, chatIv, false);

        setSelect(friendFl, friendIv, true);

        setSelect(playFl, playIv, false);

        setSelect(meFl,meIv,false);

        textView=(TextView)findViewById(R.id.setting_title_txt);
        textView.setText("好友");
    }

    /**
     * 点击了“我的空间”按钮
     */
    private void clickSpaceBtn() {
        // 实例化Fragment页面
        fragmentPlay = new FragmentPlay();
        // 得到Fragment事务管理器
        FragmentTransaction fragmentTransaction = this
                .getSupportFragmentManager().beginTransaction();
        // 替换当前的页面
        fragmentTransaction.replace(R.id.frame_content, fragmentPlay);
        // 事务管理提交
        fragmentTransaction.commit();

        setSelect(chatFl, chatIv, false);

        setSelect(friendFl, friendIv, false);

        setSelect(playFl, playIv, true);

        setSelect(meFl, meIv, false);

        textView=(TextView)findViewById(R.id.setting_title_txt);
        textView.setText("空间");

    }

    /**
     * 点击了“更多”按钮
     */
    private void clickMeBtn() {
        // 实例化Fragment页面
        fragmentMe = new FragmentMe();
        // 得到Fragment事务管理器
        FragmentTransaction fragmentTransaction = this
                .getSupportFragmentManager().beginTransaction();
        // 替换当前的页面
        fragmentTransaction.replace(R.id.frame_content, fragmentMe);
        // 事务管理提交
        fragmentTransaction.commit();

        setSelect(chatFl, chatIv, false);

        setSelect(friendFl, friendIv, false);

        setSelect(playFl, playIv, false);

        setSelect(meFl, meIv, true);

        textView=(TextView)findViewById(R.id.setting_title_txt);
        textView.setText("个人");
    }

    /**
     * 点击了中间按钮
     */
    private void clickToggleBtn() {
        showPopupWindow(toggleImageView);
        // 改变按钮显示的图片为按下时的状态
        plusImageView.setSelected(true);

        // 改变选中状态
        setSelect(chatFl, chatIv, false);

        setSelect(friendFl, friendIv, false);

        setSelect(playFl, playIv, false);

        setSelect(meFl, meIv, false);
        textView=(TextView)findViewById(R.id.setting_title_txt);
        textView.setText("功能");
    }

    /**
     * 改变显示的按钮图片为正常状态
     */
    private void changeButtonImage() {
        plusImageView.setSelected(false);
    }


    /**
     * 显示PopupWindow弹出菜单
     */
    private void showPopupWindow(View parent) {
        if (popWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.popwindow_layout, null);
            dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            // 创建一个PopuWidow对象
            popWindow = new PopupWindow(view, dm.widthPixels, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        // 使其聚集 ，要想监听菜单里控件的事件就必须要调用此方法
        popWindow.setFocusable(true);
        // 设置允许在外点击消失
        popWindow.setOutsideTouchable(true);
        // 设置背景，这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        // PopupWindow的显示及位置设置
        // popWindow.showAtLocation(parent, Gravity.FILL, 0, 0);
        popWindow.showAsDropDown(parent, 0, 0);

        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // 改变显示的按钮图片为正常状态
                changeButtonImage();
            }
        });

        // 监听触屏事件
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                // 改变显示的按钮图片为正常状态
                changeButtonImage();
                popWindow.dismiss();
                return false;
            }
        });
    }

    private void setSelect(FrameLayout fl,ImageView iv,boolean flag){
        fl.setSelected(flag);
        iv.setSelected(flag);
    }
}
