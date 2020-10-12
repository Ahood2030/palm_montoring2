package com.example.palm_montoring.Model;

import java.util.List;

public class ResponseModel {
    private int Kode;
    private String pesan;
    private List<DataModel> data;

    public int getKode() {
        return Kode;
    }

    public void setKode(int kode) {
        Kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<DataModel> getData() {
        return data;
    }

    public void setData(List<DataModel> data) {
        this.data = data;
    }
}
