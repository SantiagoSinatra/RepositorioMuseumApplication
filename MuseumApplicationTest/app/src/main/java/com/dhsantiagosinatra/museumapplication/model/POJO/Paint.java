package com.dhsantiagosinatra.museumapplication.model.POJO;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;


@Entity(tableName = "tabla_de_paints")
public class Paint implements Serializable {

    @NonNull
    @PrimaryKey()
    private String name;
    private String image;
    private String artistId;
    private String URL = "";

    public Paint() {
    }

    @Ignore
    public Paint(String image, String name, String artistId) {
        this.image = image;
        this.name = name;
        this.artistId = artistId;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getDownloadUrl() {
        return URL;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.URL = downloadUrl;
    }

    public String getImage() {
        return image;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public String getArtistId() {
        return artistId;
    }
}
