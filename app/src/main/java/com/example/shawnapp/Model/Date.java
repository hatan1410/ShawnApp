package com.example.shawnapp.Model;

public class Date {
    private String yearMonth;
    private String date;
    private Double average;
    private String note;

    public Date() {
    }

    public Date(String date, Double average,String yearMonth) {
        this.yearMonth = yearMonth;
        this.date = date;
        this.average = average;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }
}
