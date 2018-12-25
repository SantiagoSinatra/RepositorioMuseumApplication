package com.dhsantiagosinatra.museumapplication.model.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaintContainer {
    @SerializedName("paints")
    private List<Paint> paints;

    public PaintContainer (List<Paint> paints){
        this.paints = paints;
    }

    public List<Paint> getPaints() {
        return paints;
    }
}
