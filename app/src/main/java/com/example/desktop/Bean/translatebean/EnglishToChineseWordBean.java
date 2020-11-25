package com.example.desktop.Bean.translatebean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class EnglishToChineseWordBean {

    /**
     * returnPhrase : ["word"]
     * query : word
     * errorCode : 0
     * l : en2zh-CHS
     * tSpeakUrl : http://openapi.youdao.com/ttsapi?q=%E8%AF%8D&langType=zh-CHS&sign=32356D7276EFF49276AD02AB9AAD5D09&salt=1606286148542&voice=4&format=mp3&appKey=0e0506d2c08a83fe&ttsVoiceStrict=false
     * web : [{"value":["字","单词","及答案","词"],"key":"Word"},{"value":["混成词","紧缩词","行囊词","并合词"],"key":"portmanteau word"},{"value":["构词法","词性转换","单词构成","构词"],"key":"Word Formation"}]
     * requestId : 769946d3-3a3a-4102-9a9c-5d9e66d7a783
     * translation : ["词"]
     * dict : {"url":"yddict://m.youdao.com/dict?le=eng&q=word"}
     * webdict : {"url":"http://m.youdao.com/dict?le=eng&q=word"}
     * basic : {"exam_type":["初中","高中","CET4","CET6","考研"],"us-phonetic":"wɜːrd","phonetic":"wɜːd","uk-phonetic":"wɜːd","wfs":[{"wf":{"name":"复数","value":"words"}},{"wf":{"name":"过去式","value":"worded"}},{"wf":{"name":"过去分词","value":"worded"}},{"wf":{"name":"现在分词","value":"wording"}},{"wf":{"name":"第三人称单数","value":"words"}}],"uk-speech":"http://openapi.youdao.com/ttsapi?q=word&langType=en&sign=114CAC06623CF1723FACF38999F6AD98&salt=1606286148542&voice=5&format=mp3&appKey=0e0506d2c08a83fe&ttsVoiceStrict=false","explains":["n. [语] 单词；话语；消息；诺言；命令","vt. 用言辞表达","n. (Word)人名；(英)沃德"],"us-speech":"http://openapi.youdao.com/ttsapi?q=word&langType=en&sign=114CAC06623CF1723FACF38999F6AD98&salt=1606286148542&voice=6&format=mp3&appKey=0e0506d2c08a83fe&ttsVoiceStrict=false"}
     * isWord : true
     * speakUrl : http://openapi.youdao.com/ttsapi?q=word&langType=en&sign=114CAC06623CF1723FACF38999F6AD98&salt=1606286148542&voice=4&format=mp3&appKey=0e0506d2c08a83fe&ttsVoiceStrict=false
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
         * url : yddict://m.youdao.com/dict?le=eng&q=word
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
         * url : http://m.youdao.com/dict?le=eng&q=word
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
        /**
         * exam_type : ["初中","高中","CET4","CET6","考研"]
         * us-phonetic : wɜːrd
         * phonetic : wɜːd
         * uk-phonetic : wɜːd
         * wfs : [{"wf":{"name":"复数","value":"words"}},{"wf":{"name":"过去式","value":"worded"}},{"wf":{"name":"过去分词","value":"worded"}},{"wf":{"name":"现在分词","value":"wording"}},{"wf":{"name":"第三人称单数","value":"words"}}]
         * uk-speech : http://openapi.youdao.com/ttsapi?q=word&langType=en&sign=114CAC06623CF1723FACF38999F6AD98&salt=1606286148542&voice=5&format=mp3&appKey=0e0506d2c08a83fe&ttsVoiceStrict=false
         * explains : ["n. [语] 单词；话语；消息；诺言；命令","vt. 用言辞表达","n. (Word)人名；(英)沃德"]
         * us-speech : http://openapi.youdao.com/ttsapi?q=word&langType=en&sign=114CAC06623CF1723FACF38999F6AD98&salt=1606286148542&voice=6&format=mp3&appKey=0e0506d2c08a83fe&ttsVoiceStrict=false
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
        private List<WfsBean> wfs;
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

        public List<WfsBean> getWfs() {
            return wfs;
        }

        public void setWfs(List<WfsBean> wfs) {
            this.wfs = wfs;
        }

        public List<String> getExplains() {
            return explains;
        }

        public void setExplains(List<String> explains) {
            this.explains = explains;
        }

        public static class WfsBean implements Serializable {
            /**
             * wf : {"name":"复数","value":"words"}
             */

            private WfBean wf;

            public WfBean getWf() {
                return wf;
            }

            public void setWf(WfBean wf) {
                this.wf = wf;
            }

            public static class WfBean implements Serializable {
                /**
                 * name : 复数
                 * value : words
                 */

                private String name;
                private String value;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }
    }

    public static class WebBean implements Serializable {
        /**
         * value : ["字","单词","及答案","词"]
         * key : Word
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
