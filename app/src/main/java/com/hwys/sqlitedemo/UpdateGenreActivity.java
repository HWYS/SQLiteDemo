package com.hwys.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.hwys.sqlitedemo.db.MovieDb;
import com.hwys.sqlitedemo.model.GenreModel;

public class UpdateGenreActivity extends AppCompatActivity {
    private TextInputEditText tetGenre;
    private Button btnUpdate;
    private GenreModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_genre);

        tetGenre = findViewById(R.id.tetGenre);
        btnUpdate = findViewById(R.id.btnUpdate);

        model = getIntent().getParcelableExtra("Update_Genre");
        tetGenre.setText(model.getgName());
    }

    public void updateGenre(View view) {
        GenreModel updateModel = new GenreModel(model.getgId(), tetGenre.getText().toString());
        new MovieDb(UpdateGenreActivity.this).updateGenreByDbUpdate(updateModel);
        finish();
    }
}
