package com.moujib.dents_mobile.beans;

import java.io.Serializable;

public class Student implements Serializable  {

    private int id ;

    private String firstName;
    private String lastName;
    private String email;

    private String number;
    private String password;

    private String photo ;

    private Groupe group;

    public Groupe getGroupe() {
        return group;
    }

    public void setGroupe(Groupe groupe) {
        this.group = groupe;
    }

    public Student() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                ", password='" + password + '\'' +
                ", groupe=" + group +
                '}';
    }

    public Student(String lastname, String firstname, String email, String number, String photo ) {
        this.lastName = lastname;
        this.firstName = firstname;
        this.email = email;

        this.number = number;
        this.photo=photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLast_name() {
        return lastName;
    }

    public void setLast_name(String last_name) {
        this.lastName = last_name;
    }

    public String getFirst_name() {
        return firstName;
    }

    public void setFirst_name(String first_name) {
        this.firstName = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
