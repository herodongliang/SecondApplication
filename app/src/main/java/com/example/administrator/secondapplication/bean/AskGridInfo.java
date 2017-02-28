package com.example.administrator.secondapplication.bean;

/**
 * Created by Administrator on 2016/12/18 0018.
 */
public class AskGridInfo {
    private String word;
    private String icon;
    private int id;

    public AskGridInfo() {
    }

    public AskGridInfo(String word, String icon, int id) {
        this.word = word;
        this.icon = icon;
        this.id = id;
    }

    @Override
    public String toString() {
        return "AskGridInfo{" +
                "word='" + word + '\'' +
                ", icon='" + icon + '\'' +
                ", id=" + id +
                '}';
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
