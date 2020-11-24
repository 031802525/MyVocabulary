package com.example.desktop.Bean.wordbookbean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class HeightwordReviewBean {

    /**
     * num : 21
     * 8 : [{"explaination":"n.款待，请客","phonetic":"[tri:t]","content":"treat"},{"explaination":"adv.无论哪里；（用于否定、疑问等）...","phonetic":"[ˈeniˌweə(r)]","content":"anywhere"},{"explaination":"n.场合，时节，时刻；时机，机会","phonetic":"[əˈkeiʒən]","content":"occasion"},{"explaination":"n.一小口的量","phonetic":"[sip]","content":"sip"},{"explaination":"n.观众席，听众席；会堂，礼堂","phonetic":"[ˌɔ:diˈtɔ:riəm]","content":"auditorium"},{"explaination":"n.饮料；喝酒","phonetic":"[driŋk]","content":"drink"},{"explaination":"n.巧合；同时发生，共同存在；符合，一...","phonetic":"[kəuˈinsidəns]","content":"coincidence"},{"explaination":"n.投掷（距离）","phonetic":"[θrəu]","content":"throw"},{"explaination":"adj.极度的，极坏的；威严的，可怕的","phonetic":"[ˈɔ:ful]","content":"awful"},{"explaination":"adv.十分，非常；到极大程度","phonetic":"[mʌtʃ]","content":"much"}]
     */

    private int num;
    @SerializedName("8")
    private List<_$8Bean> _$8;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<_$8Bean> get_$8() {
        return _$8;
    }

    public void set_$8(List<_$8Bean> _$8) {
        this._$8 = _$8;
    }

    public static class _$8Bean implements Serializable {
        /**
         * explaination : n.款待，请客
         * phonetic : [tri:t]
         * content : treat
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
