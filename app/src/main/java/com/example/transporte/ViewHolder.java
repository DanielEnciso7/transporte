package com.example.transporte;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public ViewHolder(View itemView)
    {
        super(itemView);

        mView=itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view ,getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });
    }

    public void setDetails(Context ctx, String titulo, String descripcion, String imagen, String cordenada1, String cordenada2, String cordenada3, String cordenada4)
    {
        TextView mTituloView= mView.findViewById(R.id.tituloTv);
        TextView mDescripcionView= mView.findViewById(R.id.descripcionTv);
        ImageView mimagenView= mView.findViewById(R.id.imagenTv);
        TextView mCoordenada1= mView.findViewById(R.id.Tvcoordenada1);
        TextView mCoordenada2= mView.findViewById(R.id.Tvcoordenada2);
        TextView mCoordenada3= mView.findViewById(R.id.Tvcoordenada3);
        TextView mCoordenada4= mView.findViewById(R.id.Tvcoordenada4);


        mTituloView.setText(titulo);
        mDescripcionView.setText(descripcion);
        Picasso.get().load(imagen).into(mimagenView);
        mCoordenada1.setText(cordenada1);
        mCoordenada2.setText(cordenada2);
        mCoordenada3.setText(cordenada3);
        mCoordenada4.setText(cordenada4);

    }

    private ViewHolder.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener)
    {
        mClickListener= clickListener;
    }
}
