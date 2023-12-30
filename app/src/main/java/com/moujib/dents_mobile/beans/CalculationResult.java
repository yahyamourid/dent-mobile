package com.moujib.dents_mobile.beans;


import android.graphics.Bitmap;

public class CalculationResult {
    private static int idCounter = 1;

    private int id;
    private double angleGauche;
    private double angleDroit;
    private double intersectionAngleDeg;
    private String issymetrical;

    private Bitmap bitmap;
    private float note;

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public CalculationResult(double angleGauche, double angleDroit, double intersectionAngleDeg, String issymetrical,float note) {
        this.id = idCounter++;
        this.angleGauche = angleGauche;
        this.angleDroit = angleDroit;
        this.intersectionAngleDeg = intersectionAngleDeg;
        this.issymetrical = issymetrical;
        this.note=note;

    }

    public CalculationResult(int id, double angleGauche, double angleDroit, double intersectionAngleDeg, String issymetrical, Bitmap bitmap) {
        this.id = id;
        this.angleGauche = angleGauche;
        this.angleDroit = angleDroit;
        this.intersectionAngleDeg = intersectionAngleDeg;
        this.issymetrical = issymetrical;
        this.bitmap = bitmap;
    }

    // Getters pour accéder aux valeurs des angles
    public double getAngleGauche() {
        return angleGauche;
    }

    public double getAngleDroit() {
        return angleDroit;
    }

    public double getIntersectionAngleDeg() {
        return intersectionAngleDeg;
    }

    // Getter pour l'ID
    public int getId() {
        return id;
    }

    public String getIssymetrical() {
        return issymetrical;

    }



}
