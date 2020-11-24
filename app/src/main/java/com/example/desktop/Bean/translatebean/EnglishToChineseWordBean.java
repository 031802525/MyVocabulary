package com.example.desktop.Bean.translatebean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EnglishToChineseWordBean {

    /**
     * exam_type : ["初中","高中","CET4","CET6","考研"]
     * us-phonetic : ɡet
     * phonetic : ɡet
     * uk-phonetic : ɡet
     * uk-speech : http://openapi.youdao.com/ttsapi?q=get&langType=en&sign=F0F838776BDC6AF9235F0D8C93A4E723&salt=1605796453088&voice=5&format=mp3&appKey=0e0506d2c08a83fe&ttsVoiceStrict=false
     * explains : ["vt. 使得；获得；受到；变成","n. 生殖；幼兽","vi. 成为；变得；到达"]
     * us-speech : http://openapi.youdao.com/ttsapi?q=get&langType=en&sign=F0F838776BDC6AF9235F0D8C93A4E723&salt=1605796453088&voice=6&format=mp3&appKey=0e0506d2c08a83fe&ttsVoiceStrict=false
     */

    @SerializedName("us-phonetic")
    private String usphonetic;
    private String phonetic;
    @SerializedName("uk-phonetic")
    private String ukphonetic;
    @SerializedName("uk-speech")
    private String ukspeech;
    @SerializedName("us-speech")
    private String usspeech;
    private List<String> exam_type;
    private List<String> explains;

    public String getUsphonetic() {
        return usphonetic;
    }

    public void setUsphonetic(String usphonetic) {
        this.usphonetic = usphonetic;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public String getUkphonetic() {
        return ukphonetic;
    }

    public void setUkphonetic(String ukphonetic) {
        this.ukphonetic = ukphonetic;
    }

    public String getUkspeech() {
        return ukspeech;
    }

    public void setUkspeech(String ukspeech) {
        this.ukspeech = ukspeech;
    }

    public String getUsspeech() {
        return usspeech;
    }

    public void setUsspeech(String usspeech) {
        this.usspeech = usspeech;
    }

    public List<String> getExam_type() {
        return exam_type;
    }

    public void setExam_type(List<String> exam_type) {
        this.exam_type = exam_type;
    }

    public List<String> getExplains() {
        return explains;
    }

    public void setExplains(List<String> explains) {
        this.explains = explains;
    }
}
