package com.example.desktop.Util;

import android.widget.EditText;

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
    public static String getUserLogin(String name, String password){
        String url = "http://119.29.189.107:1234/user?name=id:"+name+"mima:"+password+".";
        return url;
    }

//    创建用户
    public static String getCreateUser(String name,String password){
        String url = "http://119.29.189.107:1234/createuser?name=id:"+name+"mima:"+password+".";
        return url;
    }

//    删除用户
    public static String getDeleteUser(String name){
        String url = "http://119.29.189.107:1234/deletuser?name=id:"+name+"mima:.";
        return url;
    }

//    修改密码
    public static String getChangePwd(String name,String repassword){
        String url = "http://119.29.189.107:1234/changepassword?name=id:"+name+"mima:"+repassword+".";
        return url;
    }

//    返回四级学习词包
    public static String getCet4Word(String name){
        String url = "http://119.29.189.107:1234/study4?name=id:"+name+".";
        return url;
    }

    //    返回六级学习词包
    public static String getCet6Word(String name){
        String url = "http://119.29.189.107:1234/study6?name=id:"+name+".";
        return url;
    }

    //    返回考研学习词包
    public static String getHighWord(String name){
        String url = "http://119.29.189.107:1234/study8?name=id:"+name+".";
        return url;
    }

//    返回四级复习词包
    public static String getCet4Review(String name,int num){
        String url = "http://119.29.189.107:1234/review4?name=id:"+name+"num="+num+".";
        return url;
    }

    //    返回六级复习词包
    public static String getCet6Review(String name,int num){
        String url = "http://119.29.189.107:1234/review6?name=id:"+name+"num="+num+".";
        return url;
    }

    //    返回考研复习词包
    public static String getCet8Review(String name,int num){
        String url = "http://119.29.189.107:1234/review8?name=id:"+name+"num="+num+".";
        return url;
    }

//    根据中文意思返回三个相近的英文单词（从四级词包中获取）
    public static String getNear4Word(String word){
        String url = "http://119.29.189.107:1234/s4?word="+word;
        return url;
    }

//    根据中文意思返回三个相近的英文单词（从六级词包中获取）
    public static String getNear6Word(String word){
        String url = "http://119.29.189.107:1234/s6?word="+word;
        return url;
    }

//    根据中文意思返回三个相近的英文单词（从考研词包中获取）
    public static String getNear8Word(String word){
        String url = "http://119.29.189.107:1234/s8?word="+word;
        return url;
    }

//      返回计划数
    public static String getPlanCount(String name){
        String url = "http://119.29.189.107:1234/getplan?name=id:"+name+".";
        return url;
    }

//    修改计划数
    public static String getUpdatePlanCount(String name,int plan){
        String url = "http://119.29.189.107:1234/changeplan?name=id:"+name+"plan:"+plan+".";
        return url;
    }

//    获得打卡天数
    public static String getDakadayCount(String name){
        String url = "http://119.29.189.107:1234/getsign?name=id:"+name+".";
        return url;
    }

//    打卡
    public static String getDaka(String name){
        String url = "http://119.29.189.107:1234/changesign?name=id:"+name+".";
        return url;
    }
//    获取英音
    public static String getEngPronuns(String word){
        String url = "http://dict.youdao.com/dictvoice?type=1&audio=+"+word;
        return url;
    }
//    获取美音
    public static String getAmrPronuns(String word){
        String url = "http://dict.youdao.com/dictvoice?type=2&audio="+word;
        return url;
    }

}
