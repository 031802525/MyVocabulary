package com.example.desktop.Bean.wordbookbean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Cet4ReviewBean {

    /**
     * 4 : [{"explaination":"n.千米，公里","phonetic":"/\u2018kiləmi:tə/","content":"kilometer"},{"explaination":"v. 蒸馏, 提取....的精华","phonetic":"/dis\u2019til/","content":"distil"},{"explaination":"vt.允许n.执照","phonetic":"/pə:\u2019mit,\u2018pə:mit/","content":"permit"},{"explaination":"vi.吹，吹动；吹响","phonetic":"/bləu/","content":"blow"},{"explaination":"n.居住；驻扎；住处","phonetic":"/\u2018rezidəns/","content":"residence"},{"explaination":"adv. 尽管如此,然而","phonetic":"/\u2018nΛnðә\u2019les/","content":"nonetheless"},{"explaination":"a.沉思的；体贴的","phonetic":"/\u2018θɔ:tful/","content":"thoughtful"},{"explaination":"n.山羊","phonetic":"/gəut/","content":"goat"},{"explaination":"vi.波动 vt.使波动","phonetic":"/\u2018flΛktjueit/","content":"fluctuate"},{"explaination":"n.礼貌，谦恭，请安","phonetic":"/\u2018kә:tisi/","content":"courtesy"}]
     * num : 21
     */

    private int num;
    @SerializedName("4")
    private List<_$4Bean> _$4;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<_$4Bean> get_$4() {
        return _$4;
    }

    public void set_$4(List<_$4Bean> _$4) {
        this._$4 = _$4;
    }

    public static class _$4Bean implements Serializable {
        /**
         * explaination : n.千米，公里
         * phonetic : /‘kiləmi:tə/
         * content : kilometer
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
