package com.example.desktop.Bean.wordbookbean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class HeightwordReviewBean {


    /**
     * num : 21
     * 8 : [{"explaination":"adj.远的；遥远的；疏远的；不亲近的","phonetic":"[ˈdistənt]","content":"distant"},{"explaination":"n.款待，请客","phonetic":"[tri:t]","content":"treat"},{"explaination":"adv.无论哪里；（用于否定、疑问等）...","phonetic":"[ˈeniˌweə(r)]","content":"anywhere"},{"explaination":"v.仿制，模仿","phonetic":"[ˈpætən]","content":"pattern"},{"explaination":"v.（over，against）取胜，...","phonetic":"[priˈveil]","content":"prevail"},{"explaination":"n.场合，时节，时刻；时机，机会","phonetic":"[əˈkeiʒən]","content":"occasion"},{"explaination":"n.一小口的量","phonetic":"[sip]","content":"sip"},{"explaination":"n.葡萄","phonetic":"[ɡreip]","content":"grape"},{"explaination":"n.观众席，听众席；会堂，礼堂","phonetic":"[ˌɔ:diˈtɔ:riəm]","content":"auditorium"},{"explaination":"v.更新，使现代化","phonetic":"[ʌpˈdeit]","content":"update"},{"explaination":"n.饮料；喝酒","phonetic":"[driŋk]","content":"drink"},{"explaination":"vt.问，询问；请求，要求；邀请，约请","phonetic":"[ɑ:sk]","content":"ask"},{"explaination":"n.（方）括号","phonetic":"[ˈbrækit]","content":"bracket"},{"explaination":"n.巧合；同时发生，共同存在；符合，一...","phonetic":"[kəuˈinsidəns]","content":"coincidence"},{"explaination":"n.投掷（距离）","phonetic":"[θrəu]","content":"throw"},{"explaination":"n.供应，供应量","phonetic":"[səˈplai]","content":"supply"},{"explaination":"adj.极度的，极坏的；威严的，可怕的","phonetic":"[ˈɔ:ful]","content":"awful"},{"explaination":"vt.浸没；使\u2026应接不暇","phonetic":"[swɔmp]","content":"swamp"},{"explaination":"v.用树篱围住","phonetic":"[hedʒ]","content":"hedge"},{"explaination":"adv.十分，非常；到极大程度","phonetic":"[mʌtʃ]","content":"much"}]
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
         * explaination : adj.远的；遥远的；疏远的；不亲近的
         * phonetic : [ˈdistənt]
         * content : distant
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
