package com.example.administrator.secondapplication.info;

/**
 * Created by Administrator on 2017/1/9 0009.
 */
public class PostGivefeedback {

    /**
     * id : 1
     * comment : well done
     * star : 3
     */

    private int id;
    private String comment;
    private int star;

    public PostGivefeedback(int id, String comment, int star) {
        this.id = id;
        this.comment = comment;
        this.star = star;
    }

    @Override
    public String toString() {
        return "PostGivefeedback{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", star=" + star +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
