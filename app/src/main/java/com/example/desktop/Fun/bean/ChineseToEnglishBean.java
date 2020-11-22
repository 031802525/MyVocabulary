package com.example.desktop.Fun.bean;

import java.io.Serializable;
import java.util.List;

public class ChineseToEnglishBean {

    /**
     * trans_result : [{"dst":"You are so beautiful","src":"你真美丽"}]
     * from : zh
     * to : en
     */

    private String from;
    private String to;
    private List<TransResultBean> trans_result;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<TransResultBean> getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(List<TransResultBean> trans_result) {
        this.trans_result = trans_result;
    }

    public static class TransResultBean implements Serializable {
        /**
         * dst : You are so beautiful
         * src : 你真美丽
         */

        private String dst;
        private String src;

        public String getDst() {
            return dst;
        }

        public void setDst(String dst) {
            this.dst = dst;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }
    }
}
