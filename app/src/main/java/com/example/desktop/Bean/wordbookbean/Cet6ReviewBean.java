package com.example.desktop.Bean.wordbookbean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Cet6ReviewBean {

    /**
     * 6 : [{"explaination":"盘旋；徘徊；犹豫；","phonetic":"ˈhɒvə(r)","content":"hover"},{"explaination":"进口；[电]引入；插入物；水湾；","phonetic":"ˈɪnlet","content":"inlet"},{"explaination":"周年纪念日；","phonetic":"ˌænɪˈvɜ:səri","content":"anniversary"},{"explaination":"部门；领域；防御地区；扇形；","phonetic":"ˈsektə(r)","content":"sector"},{"explaination":"淫秽的；下流的；猥亵的；大得惊人的；","phonetic":"əbˈsi:n","content":"obscene"},{"explaination":"特点，特性；少许；","phonetic":"treɪt","content":"trait"},{"explaination":"前提；[复数]房屋；[复数][合同、契约用语]上述各点；（逻辑学中的）大[小]前提；","phonetic":"'premɪs","content":"premise"},{"explaination":"富于想像力的，运用想像力的；想象或创造出来的；沉溺于想象的；虚假的；","phonetic":"ɪˈmædʒɪnətɪv","content":"imaginative"},{"explaination":"绷带；","phonetic":"ˈbændɪdʒ","content":"bandage"},{"explaination":"据推测；大概；可能；想来；","phonetic":"prɪˈzju:məbli","content":"presumably"}]
     * num : 21
     */

    private int num;
    @SerializedName("6")
    private List<_$6Bean> _$6;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<_$6Bean> get_$6() {
        return _$6;
    }

    public void set_$6(List<_$6Bean> _$6) {
        this._$6 = _$6;
    }

    public static class _$6Bean implements Serializable {
        /**
         * explaination : 盘旋；徘徊；犹豫；
         * phonetic : ˈhɒvə(r)
         * content : hover
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
