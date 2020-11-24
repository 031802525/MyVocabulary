package com.example.desktop.Bean.wordbookbean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Cet6learnBean {

    @SerializedName("6")
    private List<_$6Bean> _$6;

    public List<_$6Bean> get_$6() {
        return _$6;
    }

    public void set_$6(List<_$6Bean> _$6) {
        this._$6 = _$6;
    }

    public static class _$6Bean implements Serializable {
        /**
         * explaination : n.饮料；喝酒
         * phonetic : [driŋk]
         * content : drink
         */

        private String explaination;
        private String phonetic;
        private String content;

        public String getExplaination() {
            return explaination;
        }

        public void setExplaination(String explaination) {
            this.explaination = explaination;
        }

        public String getPhonetic() {
            return phonetic;
        }

        public void setPhonetic(String phonetic) {
            this.phonetic = phonetic;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
