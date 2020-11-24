package com.example.desktop.Bean.translatebean;

import java.util.List;

public class ChineseToEnglishWordBean {

    /**
     * phonetic : wǒ
     * explains : ["I","me","myself"]
     */

    private String phonetic;
    private List<String> explains;

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public List<String> getExplains() {
        return explains;
    }

    public void setExplains(List<String> explains) {
        this.explains = explains;
    }
}
