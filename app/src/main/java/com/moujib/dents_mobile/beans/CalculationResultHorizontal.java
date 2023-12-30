package com.moujib.dents_mobile.beans;


import android.graphics.Bitmap;

public class CalculationResultHorizontal {
    private static int idCounter = 1;

    private int id;


    private String issymetrical;

    private double angleGaucheHorizontal;
    private double angleDroitHorizontal;
    private double intersectionAngleDegHorizontal;
    private float note;

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    private Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public CalculationResultHorizontal (double angleGaucheHorizontal, double angleDroitHorizontal) {
        this.id = idCounter++;
        this.angleGaucheHorizontal = angleGaucheHorizontal;
        this.angleDroitHorizontal = angleDroitHorizontal;
        //this.intersectionAngleDegHorizontal = intersectionAngleDegHorizontal;

    }

    public CalculationResultHorizontal(double angleGaucheHorizontal, double angleDroitHorizontal, Bitmap bitmap) {
        this.id = idCounter++;
        this.angleGaucheHorizontal = angleGaucheHorizontal;
        this.angleDroitHorizontal = angleDroitHorizontal;
        this.bitmap = bitmap;
    }

    // Getter pour l'ID
    public int getId() {
        return id;
    }

    public String getIssymetrical() {
        return issymetrical;
    }

    public double getAngleGaucheHorizontal() {
        return angleGaucheHorizontal;
    }

    public double getAngleDroitHorizontal() {
        return angleDroitHorizontal;
    }

    public double getIntersectionAngleDegHorizontal() {
        return intersectionAngleDegHorizontal;
    }
}
