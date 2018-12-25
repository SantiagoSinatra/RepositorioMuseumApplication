package com.dhsantiagosinatra.museumapplication.model.DAO;


import com.dhsantiagosinatra.museumapplication.model.POJO.Paint;
import com.dhsantiagosinatra.museumapplication.model.POJO.PaintContainer;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PaintService {

    @GET("x858r")
    Call<PaintContainer> getPaints();


}
