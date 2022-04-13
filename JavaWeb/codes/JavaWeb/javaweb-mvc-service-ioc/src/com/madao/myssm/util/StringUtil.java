package com.madao.myssm.util;

public class StringUtil {
    /**
     * 判断字符串是否为空
     * @param str 输入字符串
     * @return 返回判断结果，为空返回true，否则返回false
     */
    public static boolean isEmpty(String str) {
        return str==null || "".equals(str);
    }


    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
