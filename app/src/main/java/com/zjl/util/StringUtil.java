package com.zjl.util;

/**
 * Created by Administrator on 2016/2/15.
 */
public class StringUtil {
    public static boolean isEmptyString(String content) {

        if (content == null || "".equals(content) || content.length() <= 0) {
            return true;
        }
        return false;
    }
}
