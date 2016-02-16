package com.zjl.message;

import com.netty.pkg.Pkg;

/**
 * Created by Administrator on 2016/2/16.
 */
public interface MessageHandler {
    void receiveMessage(Pkg pkg);
}
