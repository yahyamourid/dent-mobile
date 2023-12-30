package com.moujib.dents_mobile.beans;


public class StudentPW {

    private String imageFront;
    private float af1;
    private float af2;
    private float bf1;
    private float bf2;
    private float cf1;
    private float cf2;
    private float noteFront;

    private String imageSide;
    private float as1;
    private float as2;
    private float bs1;
    private float bs2;
    private float cs1;
    private float cs2;
    private float noteSide;

    private String time;
    private String date ;



    public StudentPW() {
    }

    public StudentPW(String imageFront, float af1, float af2, float bf1, float bf2, float cf1, float cf2, float noteFront, String imageSide, float as1, float as2, float bs1, float bs2, float cs1, float cs2, float noteSide,String time,String date) {
        this.imageFront = imageFront;
        this.af1 = af1;
        this.af2 = af2;
        this.bf1 = bf1;
        this.bf2 = bf2;
        this.cf1 = cf1;
        this.cf2 = cf2;
        this.noteFront = noteFront;
        this.imageSide = imageSide;
        this.as1 = as1;
        this.as2 = as2;
        this.bs1 = bs1;
        this.bs2 = bs2;
        this.cs1 = cs1;
        this.cs2 = cs2;
        this.noteSide = noteSide;
        this.time=time;
        this.date=date;

    }

    public String getImageFront() {
        return imageFront;
    }

    public void setImageFront(String imageFront) {
        this.imageFront = imageFront;
    }

    public float getAf1() {
        return af1;
    }

    public void setAf1(float af1) {
        this.af1 = af1;
    }

    public float getAf2() {
        return af2;
    }

    public void setAf2(float af2) {
        this.af2 = af2;
    }

    public float getBf1() {
        return bf1;
    }

    public void setBf1(float bf1) {
        this.bf1 = bf1;
    }

    public float getBf2() {
        return bf2;
    }

    public void setBf2(float bf2) {
        this.bf2 = bf2;
    }

    public float getCf1() {
        return cf1;
    }

    public void setCf1(float cf1) {
        this.cf1 = cf1;
    }

    public float getCf2() {
        return cf2;
    }

    public void setCf2(float cf2) {
        this.cf2 = cf2;
    }

    public float getNoteFront() {
        return noteFront;
    }

    public void setNoteFront(float noteFront) {
        this.noteFront = noteFront;
    }

    public String getImageSide() {
        return imageSide;
    }

    public void setImageSide(String imageSide) {
        this.imageSide = imageSide;
    }

    public float getAs1() {
        return as1;
    }

    public void setAs1(float as1) {
        this.as1 = as1;
    }

    public float getAs2() {
        return as2;
    }

    public void setAs2(float as2) {
        this.as2 = as2;
    }

    public float getBs1() {
        return bs1;
    }

    public void setBs1(float bs1) {
        this.bs1 = bs1;
    }

    public float getBs2() {
        return bs2;
    }

    public void setBs2(float bs2) {
        this.bs2 = bs2;
    }

    public float getCs1() {
        return cs1;
    }

    public void setCs1(float cs1) {
        this.cs1 = cs1;
    }

    public float getCs2() {
        return cs2;
    }

    public void setCs2(float cs2) {
        this.cs2 = cs2;
    }

    public float getNoteSide() {
        return noteSide;
    }

    public void setNoteSide(float noteSide) {
        this.noteSide = noteSide;
    }


}

