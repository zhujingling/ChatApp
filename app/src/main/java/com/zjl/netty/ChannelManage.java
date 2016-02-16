package com.zjl.netty;

import io.netty.channel.Channel;

/**
 * Created by Administrator on 2016/2/16.
 */
public class ChannelManage {
    private static ChannelManage channelManage;

    public static ChannelManage getChannelManage() {
        if (channelManage == null) {
            synchronized (ChannelManage.class) {
                if (channelManage == null) {
                    channelManage = new ChannelManage();
                }
            }
        }
        return channelManage;
    }

    public Channel channel;

}
