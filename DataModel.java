package com.example.palm_montoring.Model;

public class DataModel {
    private String date , humidity,n;
    private int id ,temp;

    public DataModel(String date) {
        this.date = date;
    }

    public DataModel(String date, String humidity, String n, int id, int temp) {
        this.date = date;
        this.humidity = humidity;
        this.n = n;
        this.id = id;
        this.temp = temp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
