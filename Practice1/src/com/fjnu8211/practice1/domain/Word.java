package com.fjnu8211.practice1.domain;

/**
 * 功能描述:扫描得到的词类
 *
 * @ClassName: Word
 * @Author: Yu Xiaogai
 * @Date: 2020/2/22 1:48
 */
public class Word {

    private int typenum;                    //字符种别码
    private String word;                    //扫描得到的词

    public int getTypenum() {
        return typenum;
    }

    public void setTypenum(int typenum) {
        this.typenum = typenum;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

}
