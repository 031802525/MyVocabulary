package com.example.desktop.Bean.wordbookbean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Cet4learnBean {

    @SerializedName("4")
    private List<_$4Bean> _$4;

    public List<_$4Bean> get_$4() {
        return _$4;
    }

    public void set_$4(List<_$4Bean> _$4) {
        this._$4 = _$4;
    }

    public static class _$4Bean implements Serializable {
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
