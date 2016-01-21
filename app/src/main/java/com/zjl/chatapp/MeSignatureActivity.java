package com.zjl.chatapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/1/7.
 */
public class MeSignatureActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_signature);
        String personalSig=getIntent().getStringExtra("personalSig");
        Toast.makeText(MeSignatureActivity.this, personalSig, Toast.LENGTH_LONG).show();
    }
}
