package com.example.desktop.Bean.wordbookbean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class HeightWordBean {

    @SerializedName("8")
    private List<_$8Bean> _$8;

    public List<_$8Bean> get_$8() {
        return _$8;
    }

    public void set_$8(List<_$8Bean> _$8) {
        this._$8 = _$8;
    }

    public static class _$8Bean implements Serializable {
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
