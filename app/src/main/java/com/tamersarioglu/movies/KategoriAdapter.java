package com.tamersarioglu.movies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.KategoriKartTutucu> {

    private Context mContext;
    private List<Kategoriler> kategoriListe;

    public KategoriAdapter(Context mContext, List<Kategoriler> kategoriListe) {
        this.mContext = mContext;
        this.kategoriListe = kategoriListe;
    }

    @NonNull
    @Override
    public KategoriKartTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_kategori_tasarim, parent, false);
        return new KategoriKartTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KategoriKartTutucu holder, final int position) {

        final Kategoriler k = kategoriListe.get(position);
        holder.textViewKategoriAd.setText(k.getKategori_ad());
        holder.cardViewKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, FilmlerActivity.class);

                intent.putExtra("kategoriNesne",k);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kategoriListe.size();
    }

    public static class KategoriKartTutucu extends RecyclerView.ViewHolder {

        private CardView cardViewKategori;
        private TextView textViewKategoriAd;

        public KategoriKartTutucu(@NonNull View itemView) {
            super(itemView);

            cardViewKategori = itemView.findViewById(R.id.cardView_Kategori);
            textViewKategoriAd = itemView.findViewById(R.id.textViewKategoriAd);
        }
    }
}
