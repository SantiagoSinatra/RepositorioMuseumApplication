package com.dhsantiagosinatra.museumapplication.model.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistContainer {
    @SerializedName("artists")
    private List<Artist> artists;

    public ArtistContainer() {
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}
