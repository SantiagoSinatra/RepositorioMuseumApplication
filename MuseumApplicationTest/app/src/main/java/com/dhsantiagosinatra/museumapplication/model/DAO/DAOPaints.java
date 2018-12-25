package com.dhsantiagosinatra.museumapplication.model.DAO;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.dhsantiagosinatra.museumapplication.model.POJO.PaintContainer;
import com.dhsantiagosinatra.museumapplication.util.ResultListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DAOPaints extends PaintRetrofit {


    private static final String BASE_URL = "https://api.myjson.com/bins/"; //Base URL.
    private PaintService paintService;
    private FirebaseStorage storage = FirebaseStorage.getInstance();

    public DAOPaints() {
        super(BASE_URL);
        paintService = retrofit.create(PaintService.class);
    }

    public void getPaintSet1 (final ResultListener<PaintContainer> listenerDelController){
        Call<PaintContainer> call = paintService.getPaints();
        call.enqueue(new Callback<PaintContainer>() {
            @Override
            public void onResponse(Call<PaintContainer> call, Response<PaintContainer> response) {
                PaintContainer paint = response.body();
                listenerDelController.finish(paint);
            }

            @Override
            public void onFailure(Call<PaintContainer> call, Throwable t) {
                //nada.
            }
        });
    }

    public void getImageFromFirebase(final ResultListener<Uri> listener, String image){

        StorageReference reference = storage.getReference();

        StorageReference pathReference = reference.child(image);

        pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                listener.finish(uri);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                listener.finish(null);

            }
        });


    }
}
