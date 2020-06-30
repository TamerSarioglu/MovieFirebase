package com.tamersarioglu.movies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FilmlerAdapter extends RecyclerView.Adapter<FilmlerAdapter.FilmCardTutucu> {

    private Context mContext;
    private List<Filmler> filmlerListe;

    public FilmlerAdapter(Context mContext, List<Filmler> filmlerList) {
        this.mContext = mContext;
        this.filmlerListe = filmlerList;
    }

    @NonNull
    @Override
    public FilmCardTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_card_tasarim, parent, false);
        return new FilmCardTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmCardTutucu holder, int position) {
        final Filmler film = filmlerListe.get(position);
        holder.textView_film_card_tasarim_FilmAd.setText(film.getFilm_ad());
        holder.imageView_film_card_tasarim.setImageResource(mContext.getResources()
                .getIdentifier(film.getFilm_resim(), "drawable", mContext.getPackageName()));
        holder.cardView_film_card_tasarim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetayActivity.class);
                intent.putExtra("nesne",film);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filmlerListe.size();
    }

    public static class FilmCardTutucu extends RecyclerView.ViewHolder {

        private CardView cardView_film_card_tasarim;
        private ImageView imageView_film_card_tasarim;
        private TextView textView_film_card_tasarim_FilmAd;

        public FilmCardTutucu(@NonNull View itemView) {
            super(itemView);
            cardView_film_card_tasarim = itemView.findViewById(R.id.cardView_film_card_tasarim);
            imageView_film_card_tasarim = itemView.findViewById(R.id.imageView_film_card_tasarim);
            textView_film_card_tasarim_FilmAd = itemView.findViewById(R.id.textView_film_card_tasarim_FilmAd);

        }
    }
}
