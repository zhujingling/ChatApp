package com.zjl.chatapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.netty.pkg.Pkg;
import com.zjl.message.MessageDispatch;
import com.zjl.message.MessageHandler;
import com.zjl.netty.ChannelManage;
import com.zjl.netty.WebSocketClient;
import com.zjl.netty.WebSocketClientRunner;
import com.zjl.util.StringUtil;

/**
 * Created by Administrator on 2016/2/15.
 */
public class LoginActivity extends Activity implements View.OnClickListener, MessageHandler {

    private EditText userName;
    private EditText passWord;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login_activity);

            btnLogin = (Button) findViewById(R.id.login_button);
            btnLogin.setOnClickListener(this);

            userName = (EditText) findViewById(R.id.username_edit);
            passWord = (EditText) findViewById(R.id.password_edit);

            MessageDispatch.getMessageDispatch().addMessage(this);
            WebSocketClient.connectServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //启动的时候就连接服务器
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                loginBtnClick();
                break;
        }
    }

    private void loginBtnClick() {
        if (isEmptyUserNameOrPassWord()) {
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_LONG);
            return;
        }
        Pkg pkg = Pkg.rawPkg();
        pkg.cmd = 0x00A40001;
        pkg.put(userName.getText().toString());// 用户名
        pkg.put(passWord.getText().toString());// 密码
        ChannelManage.getChannelManage().channel.writeAndFlush(pkg);
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
    }

    //用户名和密码是否为空
    private boolean isEmptyUserNameOrPassWord() {

        if (StringUtil.isEmptyString(userName.getText().toString()) || StringUtil.isEmptyString(passWord.getText().toString())) {
            return true;
        }
        return false;
    }

    @Override
    public void receiveMessage(Pkg pkg) {
        int cmd = pkg.cmd;
    }
}
