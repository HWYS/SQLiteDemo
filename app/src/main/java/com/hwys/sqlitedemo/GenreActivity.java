package com.hwys.sqlitedemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.hwys.sqlitedemo.adapter.GenreAdapter;
import com.hwys.sqlitedemo.db.MovieDb;
import com.hwys.sqlitedemo.model.GenreModel;
import com.hwys.sqlitedemo.utilities.CustomItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class GenreActivity extends AppCompatActivity {
    TextInputEditText tetGenre;
    private List<GenreModel> genreModelArrayList = new ArrayList<>();
    private GenreAdapter adapter;
    private RecyclerView rvGenre;
    private MovieDb db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);

        tetGenre = findViewById(R.id.tetGenre);

        rvGenre = findViewById(R.id.rvGenre);
        rvGenre.setLayoutManager(new LinearLayoutManager(GenreActivity.this));
        rvGenre.setHasFixedSize(true);
        rvGenre.addItemDecoration(new CustomItemDecoration());
        db = new MovieDb(GenreActivity.this);
    }

    public void saveGenre(View view) {
        if(tetGenre.getText().toString().trim().length()<=0){
            Toast.makeText(GenreActivity.this, "Fill Genre Name", Toast.LENGTH_LONG).show();
            tetGenre.requestFocus();
            return;
        }
        else{
            db.insertGenre(tetGenre.getText().toString());
            tetGenre.setText("");
            tetGenre.requestFocus();
            genreModelArrayList.clear();
            genreModelArrayList.addAll(db.getAllGenre());
            adapter.notifyDataSetChanged();

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        genreModelArrayList.addAll(db.getAllGenre());
        adapter = new GenreAdapter(genreModelArrayList);
        rvGenre.setAdapter(adapter);
    }
}
