package com.moujib.dents_mobile.beans;

import java.io.Serializable;
import java.util.List;

public class Groupe implements Serializable {

    private long id;
    private String code ;
    private String year;

    private List<PW> pws;

    public Groupe() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<PW> getPws() {
        return pws;
    }

    @Override
    public String toString() {
        return "Groupe{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", year='" + year + '\'' +
                ", pws=" + pws +
                '}';
    }

    public void setPws(List<PW> pws) {
        this.pws = pws;
    }
}
