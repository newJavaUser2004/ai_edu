package com.coder_mart_server.public_modules.util;

public class ASCIIUtil {

    //大写字母最大ascii码
    private final static Integer BIG_CODE_ASCII_MAX = 90;

    //大写字母最小ascii码
    private final static Integer BIG_CODE_ASCII_MIN = 65;

    //小写字母最大ascii码
    private final static Integer SMALL_CODE_ASCII_MAX = 122;

    //小写字母最小ascii码
    private final static Integer SMALL_CODE_ASCII_MIN = 97;

    //大小写字母之间的距离
    private final static Integer SMALL_TO_BIG = 32;

    /**
     * 首字母大写
     */
    public static String firstCodeBig(String str){
        char[] chars = str.toCharArray();
        char bigChar = smallToBig(chars[0]);
        chars[0] = bigChar;
        return new String(chars);
    }

    /**
     * 字母转大写
     */
    public static char smallToBig(char str){
        //如果不是字母，直接返回
        if(!isCode(str)){
            return str;
        }
        char newStr = (char)(str - SMALL_TO_BIG);
        return newStr;
    }

    /**
     * 字母转小写
     */
    public static char bigToSmaill(char str){
        //如果不是字母，直接返回
        if(!isCode(str)){
            return str;
        }
        char newStr = (char)(str + SMALL_TO_BIG);
        return newStr;
    }

    /**
     * 判断是否是字母
     */
    public static boolean isCode(char str){
        //判断是否是小写字母
        if(str>SMALL_CODE_ASCII_MIN && str<SMALL_CODE_ASCII_MAX){
            return true;
        }
        //是否是大写字母
        if(str>BIG_CODE_ASCII_MIN && str<BIG_CODE_ASCII_MAX){
            return true;
        }
        return false;
    }
}











