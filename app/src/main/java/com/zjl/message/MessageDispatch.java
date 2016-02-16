package com.zjl.message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/16.
 */
public class MessageDispatch {
    public List<MessageHandler> list = new ArrayList<MessageHandler>();

    private static MessageDispatch messageDispatch;

    public static MessageDispatch getMessageDispatch() {
        if (messageDispatch == null) {
            synchronized (MessageDispatch.class) {
                if (messageDispatch == null) {
                    messageDispatch = new MessageDispatch();
                }
            }
        }
        return messageDispatch;
    }

    public void addMessage(MessageHandler messageHandler) {
        list.add(messageHandler);
    }

    public void removeMessage(MessageHandler messageHandler) {
        list.remove(messageHandler);
    }

}
