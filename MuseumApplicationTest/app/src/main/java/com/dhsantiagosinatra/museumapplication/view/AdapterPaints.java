package com.dhsantiagosinatra.museumapplication.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dhsantiagosinatra.museumapplication.R;
import com.dhsantiagosinatra.museumapplication.model.DAO.DAOFavoritosFromDatabase;
import com.dhsantiagosinatra.museumapplication.model.POJO.Paint;

import java.util.ArrayList;
import java.util.List;

public class AdapterPaints extends RecyclerView.Adapter<AdapterPaints.PaintViewHolder> {

    private List<Paint>listaDePaints;
    private ListenerAdapterPaints listener;


    public AdapterPaints(ListenerAdapterPaints listenerAdapterPaints) {
        this.listaDePaints = new ArrayList<>();
        this.listener = listenerAdapterPaints;
        setHasStableIds(true);
    }


    public void setPaints(List<Paint> paints){
        this.listaDePaints = paints;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PaintViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View viewDeLaCelda = inflater.inflate(R.layout.celda_paint, viewGroup, false);
        PaintViewHolder paintViewHolder = new PaintViewHolder(viewDeLaCelda);
        return paintViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PaintViewHolder paintViewHolder, int i) {
        Paint paint = listaDePaints.get(i);
        paintViewHolder.bindPaint(paint);
    }

    @Override
    public int getItemCount() {
        if (listaDePaints != null){
            return listaDePaints.size();
        }else{
            return 0;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    //Interface que se comunica con la activity:
    public interface ListenerAdapterPaints{
        public void celdaPaintSeleccionada (Paint paint);
        public void botonFavoritoSeleccionado (Paint paint);
    }


    //ViewHolder:
    public class PaintViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewPaintCelda;
        private ImageView imageViewPaintCelda;
        private ImageButton botonFavoriteado;


        public PaintViewHolder(@NonNull View itemView){
            super(itemView);
            imageViewPaintCelda = itemView.findViewById(R.id.imageview_paint_celda);
            textViewPaintCelda = itemView.findViewById(R.id.textview_paint_celda);
            botonFavoriteado = itemView.findViewById(R.id.boton_favoritear);


            botonFavoriteado.setImageResource(R.drawable.favoriteado_icon);

            imageViewPaintCelda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Paint paint = listaDePaints.get(getAdapterPosition());
                    listener.celdaPaintSeleccionada(paint);
                }
            });

            botonFavoriteado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Paint paint = listaDePaints.get(getAdapterPosition());
                    listener.botonFavoritoSeleccionado(paint);
                    botonFavoriteado.setImageResource(R.drawable.favoriteado_icon);
                }
            });



        }

        //Juntar los componentes con la informacion:
        public void bindPaint(Paint paint){
            textViewPaintCelda.setText(paint.getName());
            Glide.with(itemView.getContext()).load(paint.getDownloadUrl()).into(imageViewPaintCelda);
        }
    }

}
