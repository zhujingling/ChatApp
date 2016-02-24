package com.zjl.message;

/**
 * Created by Administrator on 2016/2/17.
 */

import com.zjl.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 *登录成功之后保存用户信息
 */
public class MessageFriend {
    private static MessageFriend messageFriend;
    public static MessageFriend getMessageFriend(){
        if (messageFriend==null){
            synchronized (MessageFriend.class){
                if (messageFriend==null){
                    messageFriend=new MessageFriend();
                }
            }
        }
        return  messageFriend;
    }

    public List<User> userFriendList=new ArrayList<User>();
}
