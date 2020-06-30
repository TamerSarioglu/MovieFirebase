package com.tamersarioglu.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FilmlerActivity extends AppCompatActivity {

    private Toolbar toolbar_FilmlerActivity;
    private RecyclerView recyclerView_FilmlerActivity;
    private ArrayList<Filmler> filmlerArrayList;
    private FilmlerAdapter filmlerAdapter;
    private Kategoriler kategori;

    private DatabaseReference myRefFilmler;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmler);
        init();

        database = FirebaseDatabase.getInstance();
        myRefFilmler = database.getReference("filmler");

        kategori = (Kategoriler) getIntent().getSerializableExtra("kategoriNesne");

        assert kategori != null;
        toolbar_FilmlerActivity.setTitle(kategori.getKategori_ad());
        setSupportActionBar(toolbar_FilmlerActivity);

        recyclerView_FilmlerActivity.setHasFixedSize(true);
        recyclerView_FilmlerActivity.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        filmlerArrayList = new ArrayList<>();
        filmlerAdapter = new FilmlerAdapter(this, filmlerArrayList);
        recyclerView_FilmlerActivity.setAdapter(filmlerAdapter);

        adaGoreKategoriGetir();

    }

    public void adaGoreKategoriGetir() {
        Query sorgu = myRefFilmler.orderByChild("kategori_ad").equalTo(kategori.getKategori_ad());
        sorgu.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                filmlerArrayList.clear();

                for (DataSnapshot d : snapshot.getChildren()) {
                    Filmler film = d.getValue(Filmler.class);
                    film.setFilm_id(d.getKey());

                    filmlerArrayList.add(film);
                }
                filmlerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void init() {
        toolbar_FilmlerActivity = findViewById(R.id.toolbar_FilmlerActivity);
        recyclerView_FilmlerActivity = findViewById(R.id.recyclerView_FilmlerActivity);
    }
}