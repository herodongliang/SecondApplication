package com.example.administrator.secondapplication.bean;

/**
 * Created by Administrator on 2017/1/18 0018.
 */
public class ChoseLabelInfo {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "ChoseLabelInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public ChoseLabelInfo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
