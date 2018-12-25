package com.dhsantiagosinatra.museumapplication.model.POJO;

import java.io.Serializable;

public class Artist implements Serializable {
    private String Influenced_by;
    private String artistId;
    private String name;
    private String nationality;

    public Artist() {
    }

    public Artist(String influenced_by, String artistId, String name, String nationality) {
        this.Influenced_by = influenced_by;
        this.artistId = artistId;
        this.name = name;
        this.nationality = nationality;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getInfluenced_by() {
        return Influenced_by;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public void setInfluenced_by(String influenced_by) {
        Influenced_by = influenced_by;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
