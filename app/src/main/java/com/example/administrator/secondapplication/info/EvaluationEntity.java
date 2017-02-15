package com.example.administrator.secondapplication.info;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
public class EvaluationEntity {
    private String word;
    private String icon;
    private int id;
    private boolean ischecked;
    private boolean ischanged;
    public EvaluationEntity() {
    }

    public EvaluationEntity(String word, String icon,int id,boolean ischecked,boolean ischanged) {
        this.word = word;
        this.icon = icon;
        this.id=id;
        this.ischecked=ischecked;
        this.ischanged=ischanged;
    }

    public EvaluationEntity(boolean ischecked,boolean ischanged) {
        this.ischecked = ischecked;
        this.ischanged=ischanged;
    }

    @Override
    public String toString() {
        return "EvaluationEntity{" +
                "word='" + word + '\'' +
                ", icon='" + icon + '\'' +
                ", id='" + id + '\'' +
                ", ischecked='" + ischecked + '\'' +
                '}';
    }

    public boolean ischanged() {
        return ischanged;
    }

    public void setIschanged(boolean ischanged) {
        this.ischanged = ischanged;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean ischecked() {
        return ischecked;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }
}
