package com.example.shawnapp.Model;

public class Date {
    private String date;
    private Double average;
    private String note;

    public Date() {
    }

    public Date(String date, Double average) {
        this.date = date;
        this.average = average;
    }

    public Date(String date, Double average, String note) {
        this.date = date;
        this.average = average;
        this.note = note;
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
}
