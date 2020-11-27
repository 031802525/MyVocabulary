package com.example.desktop.Bean.translatebean;

import java.io.Serializable;
import java.util.List;

public class TranslationErroBean {

    /**
     * tSpeakUrl : http://openapi.youdao.com/ttsapi?q=xxxxxx&langType=zh-CHS&sign=B51404386B65BAA260B448DBF9E7F55D&salt=1606447089976&voice=4&format=mp3&appKey=0e0506d2c08a83fe&ttsVoiceStrict=false
     * requestId : 81635cfd-a197-4c40-add1-c4ce997c375b
     * query : xxxxxx
     * translation : ["xxxxxx"]
     * errorCode : 0
     * dict : {"url":"yddict://m.youdao.com/dict?le=eng&q=xxxxxx"}
     * webdict : {"url":"http://m.youdao.com/dict?le=eng&q=xxxxxx"}
     * l : id2zh-CHS
     * isWord : false
     * speakUrl : http://openapi.youdao.com/ttsapi?q=xxxxxx&langType=id&sign=B51404386B65BAA260B448DBF9E7F55D&salt=1606447089976&voice=4&format=mp3&appKey=0e0506d2c08a83fe&ttsVoiceStrict=false
     */

    private String tSpeakUrl;
    private String requestId;
    private String query;
    private String errorCode;
    private DictBean dict;
    private WebdictBean webdict;
    private String l;
    private boolean isWord;
    private String speakUrl;
    private List<String> translation;

    public String getTSpeakUrl() {
        return tSpeakUrl;
    }

    public void setTSpeakUrl(String tSpeakUrl) {
        this.tSpeakUrl = tSpeakUrl;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public DictBean getDict() {
        return dict;
    }

    public void setDict(DictBean dict) {
        this.dict = dict;
    }

    public WebdictBean getWebdict() {
        return webdict;
    }

    public void setWebdict(WebdictBean webdict) {
        this.webdict = webdict;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public boolean isIsWord() {
        return isWord;
    }

    public void setIsWord(boolean isWord) {
        this.isWord = isWord;
    }

    public String getSpeakUrl() {
        return speakUrl;
    }

    public void setSpeakUrl(String speakUrl) {
        this.speakUrl = speakUrl;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public static class DictBean implements Serializable {
        /**
         * url : yddict://m.youdao.com/dict?le=eng&q=xxxxxx
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class WebdictBean implements Serializable {
        /**
         * url : http://m.youdao.com/dict?le=eng&q=xxxxxx
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
