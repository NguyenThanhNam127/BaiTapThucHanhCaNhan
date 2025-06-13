package com.example.baithuchanh24;

import android.graphics.Bitmap;

public class Tygia {
    private String type;
    private String imageurl;
    private Bitmap bitmap;
    private String muatienmat;
    private String muack;
    private String bantienmat;
    private String banck;

    public Tygia() {
    }

    public Tygia(String type, String imageurl, Bitmap bitmap, String muatienmat, String muack, String bantienmat, String banck) {
        this.type = type;
        this.imageurl = imageurl;
        this.bitmap = bitmap;
        this.muatienmat = muatienmat;
        this.muack = muack;
        this.bantienmat = bantienmat;
        this.banck = banck;
    }

    public String getType() {
        return type;
    }

    public String getImageurl() {
        return imageurl;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getMuatienmat() {
        return muatienmat;
    }

    public String getMuack() {
        return muack;
    }

    public String getBantienmat() {
        return bantienmat;
    }

    public String getBanck() {
        return banck;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setMuatienmat(String muatienmat) {
        this.muatienmat = muatienmat;
    }

    public void setMuack(String muack) {
        this.muack = muack;
    }

    public void setBantienmat(String bantienmat) {
        this.bantienmat = bantienmat;
    }

    public void setBanck(String banck) {
        this.banck = banck;
    }
}

