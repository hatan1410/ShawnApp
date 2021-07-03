package com.myapp.shawnapp.Model;

public class Category {
    private String cateName;
    private int point;

    public Category(String cateName, int point) {
        this.cateName = cateName;
        this.point = point;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
