package com.example.desktop.Bean.translatebean;

import java.io.Serializable;
import java.util.List;

public class ChineseToEnglishWordBean {

    /**
     * returnPhrase : ["我的"]
     * query : 我的
     * errorCode : 0
     * l : zh-CHS2en
     * tSpeakUrl : http://openapi.youdao.com/ttsapi?q=my&langType=en&sign=8337472D06329F0750B89A9835F13583&salt=1606286951233&voice=4&format=mp3&appKey=0e0506d2c08a83fe&ttsVoiceStrict=false
     * web : [{"value":["mine","my","my possessive adjective","Sognami amore mio"],"key":"我的"},{"value":["My desired happiness","Connie Francis","CD Version","The happiness I want"],"key":"我要的幸福"},{"value":["La Mujer de mi hermano"],"key":"我哥的女人"}]
     * requestId : 3078b7b2-cf56-45da-959e-bdcdbaf80357
     * translation : ["my"]
     * dict : {"url":"yddict://m.youdao.com/dict?le=eng&q=%E6%88%91%E7%9A%84"}
     * webdict : {"url":"http://m.youdao.com/dict?le=eng&q=%E6%88%91%E7%9A%84"}
     * basic : {"explains":["my","mine"]}
     * isWord : true
     * speakUrl : http://openapi.youdao.com/ttsapi?q=%E6%88%91%E7%9A%84&langType=zh-CHS&sign=44B00E0161003E345429F8F3B3198BAB&salt=1606286951233&voice=4&format=mp3&appKey=0e0506d2c08a83fe&ttsVoiceStrict=false
     */

    private String query;
    private String errorCode;
    private String l;
    private String tSpeakUrl;
    private String requestId;
    private DictBean dict;
    private WebdictBean webdict;
    private BasicBean basic;
    private boolean isWord;
    private String speakUrl;
    private List<String> returnPhrase;
    private List<WebBean> web;
    private List<String> translation;

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

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

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

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
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

    public List<String> getReturnPhrase() {
        return returnPhrase;
    }

    public void setReturnPhrase(List<String> returnPhrase) {
        this.returnPhrase = returnPhrase;
    }

    public List<WebBean> getWeb() {
        return web;
    }

    public void setWeb(List<WebBean> web) {
        this.web = web;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public static class DictBean implements Serializable {
        /**
         * url : yddict://m.youdao.com/dict?le=eng&q=%E6%88%91%E7%9A%84
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
         * url : http://m.youdao.com/dict?le=eng&q=%E6%88%91%E7%9A%84
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class BasicBean implements Serializable {
        private List<String> explains;

        public List<String> getExplains() {
            return explains;
        }

        public void setExplains(List<String> explains) {
            this.explains = explains;
        }
    }

    public static class WebBean implements Serializable {
        /**
         * value : ["mine","my","my possessive adjective","Sognami amore mio"]
         * key : 我的
         */

        private String key;
        private List<String> value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<String> getValue() {
            return value;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }
    }
}
