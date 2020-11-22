package com.example.desktop.Fun.util;

public class URLContent {
//    每日一句接口
    public static String getEnglishDayURL(){
        String englishDayURL = "http://119.29.189.107:1234/meiri";
        return englishDayURL;
    }
//    翻译英文单词接口 中文单词-->英文单词   英文单词-->中文单词 两个对应的json bean数据不一样
    public static String getWordTranslateURL(String word){
        String url = "http://119.29.189.107:1234/trans?ci="+word;
        return url;
    }
//    中译英接口
    public static String getChineseToEnglish(String Chinese){
        String url = "http://119.29.189.107:1234/fanyizh?zhongwen="+Chinese;
        return url;
    }
//    英译中接口
    public static String getEnglishToChinese(String english){
        String url = "http://119.29.189.107:1234/fanyien?yingwen="+english;
        return url;
    }

//   用户登录
    public static String getUserLogin(String name,String password){
        String url = "http://119.29.189.107:1234/user?name="+name+"mima:"+password+".";
        return url;
    }

//    创建用户
    public static String getCreateUser(String name,String password){
        String url = "http://119.29.189.107:1234/createuser?name="+name+"mima:"+password+".";
        return url;
    }

//    删除用户
    public static String getDeleteUser(String name){
        String url = "http://119.29.189.107:1234/deletuser?name="+name+"mima:.";
        return url;
    }

}
