package com.tamersarioglu.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetayActivity extends AppCompatActivity {

    private ImageView imageView_Detay;
    private TextView textView_DetayActivity_FilmAd,textView_DetayActivity_Yil, textView_DetayActivity_Yonetmen;
    private Filmler film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        init();

        film = (Filmler) getIntent().getSerializableExtra("nesne");

        textView_DetayActivity_FilmAd.setText(film.getFilm_ad());
        textView_DetayActivity_Yil.setText(String.valueOf(film.getFilm_yil()));
        textView_DetayActivity_Yonetmen.setText(film.getYonetmen_ad());
        imageView_Detay.setImageResource(getResources().getIdentifier(film.getFilm_resim(),"drawable",getPackageName()));


    }

    private void init() {
        imageView_Detay = findViewById(R.id.imageView_Detay);
        textView_DetayActivity_FilmAd = findViewById(R.id.textView_DetayActivity_FilmAd);
        textView_DetayActivity_Yil = findViewById(R.id.textView_DetayActivity_Yil);
        textView_DetayActivity_Yonetmen = findViewById(R.id.textView_DetayActivity_Yonetmen);
    }
}