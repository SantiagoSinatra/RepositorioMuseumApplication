package com.dhsantiagosinatra.museumapplication.view;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dhsantiagosinatra.museumapplication.R;
import com.dhsantiagosinatra.museumapplication.controller.PaintController;
import com.dhsantiagosinatra.museumapplication.model.DAO.DAOPaints;
import com.dhsantiagosinatra.museumapplication.model.POJO.Artist;
import com.dhsantiagosinatra.museumapplication.model.POJO.Paint;
import com.dhsantiagosinatra.museumapplication.util.ResultListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetallePaints extends Fragment {

    public static final String KEY_PAINT = "paints";
    private AdapterPaints adapterPaints;

    public FragmentDetallePaints() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_detalle_paints, container, false);
        Bundle bundle = getArguments();

        Paint paint = (Paint) bundle.getSerializable(KEY_PAINT);

        final ImageView imageView = view.findViewById(R.id.imageview_detalle);
        final TextView textViewName = view.findViewById(R.id.textview_detalle_name);
        final TextView textViewInfluencer = view.findViewById(R.id.textview_detalle_influencer);
        final TextView textViewNationality = view.findViewById(R.id.textview_detalle_nationality);

        PaintController paintController = new PaintController();

        paintController.getArtista(new ResultListener<Artist>() {
            @Override
            public void finish(Artist result) {
                textViewInfluencer.setText(result.getInfluenced_by());
                textViewNationality.setText(result.getNationality());
                textViewName.setText(result.getName());
            }
        }, paint.getArtistId());


        DAOPaints daoPaints = new DAOPaints();
        daoPaints.getImageFromFirebase(new ResultListener<Uri>() {
            @Override
            public void finish(Uri result) {
                Glide.with(FragmentDetallePaints.this).load(result).into(imageView);
            }
        }, paint.getImage());

        return view;
    }


}
