package com.moujib.dents_mobile.beans;

import java.io.Serializable;

public class PW implements Serializable {
    private long id;
    private String title;
    private String objectif;
    private String docs;

    public PW( String title, String objectif, String docs) {
        this.title = title;
        this.objectif = objectif;
        this.docs = docs;
    }

    public PW() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getDocs() {
        return docs;
    }

    public void setDocs(String docs) {
        this.docs = docs;
    }

    @Override
    public String toString() {
        return "PW{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", objectif='" + objectif + '\'' +
                ", docs='" + docs + '\'' +
                '}';
    }
}
