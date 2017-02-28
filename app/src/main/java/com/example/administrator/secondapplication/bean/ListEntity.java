package com.example.administrator.secondapplication.bean;

import java.io.Serializable;

/**
 * Created by mj
 * on 2016/10/28.
 */
public class ListEntity implements Serializable {

    public String avatarUrl;
    public String name;
    public String date;
    public String content;
    public String descUrl;
    public int layoutType=0;

    public String url;
    //高清图
    public String hdurl;
    public float width;
    public float height;
}
