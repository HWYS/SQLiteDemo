package com.hwys.sqlitedemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.hwys.sqlitedemo.model.GenreModel;

import java.util.ArrayList;
import java.util.List;

public class MovieDb extends SQLiteOpenHelper {
    //Database Name
    private static final String DB_NAME ="MovieDb";
    private static final int DB_VERSION = 1;
    public MovieDb(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * All Tables must be created inside onCreate method.
     * onCreate method works only once time when Database is created
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tbl_Genre(g_id INTEGER PRIMARY KEY, g_name TEXT)");
        db.execSQL("CREATE TABLE tbl_Movie (m_id INTEGER PRIMARY KEY, g_id INTEGER, m_name TEXT, m_year TEXT, m_income REAL, m_rating REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertGenre(String gName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("g_name", gName);

        try {
            db.insert("tbl_Genre", null, cv);
            db.close();
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public List<GenreModel> getAllGenre(){
        List<GenreModel> genreModelList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_Genre", null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                genreModelList.add(new GenreModel(cursor.getInt(0), cursor.getString(1)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return genreModelList;
    }

    public boolean updateGenre(GenreModel model){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.execSQL("UPDATE tbl_Genre SET g_name '"+model.getgName()+"' WHERE g_id="+model.getgId());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateGenreByDbUpdate(GenreModel model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("g_name", model.getgName());
        try{
            db.update("tbl_Genre", cv, "g_id=?", new String[]{String.valueOf(model.getgId())});
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteGenre(int gId){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.execSQL("DELETE FROM tbl_Genre WHERE g_id="+gId);
            return true;
        }catch (Exception e){return false;}
    }
}
