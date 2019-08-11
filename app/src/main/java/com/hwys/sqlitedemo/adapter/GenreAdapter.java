package com.hwys.sqlitedemo.adapter;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.hwys.sqlitedemo.R;
import com.hwys.sqlitedemo.db.MovieDb;
import com.hwys.sqlitedemo.model.GenreModel;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {
    private List<GenreModel> genreModelList;
    public GenreAdapter(List<GenreModel> genreModelList) {
        this.genreModelList = genreModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvGenre.setText(genreModelList.get(position).getgName());

        holder.ibtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.ibtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                alertDialogBuilder.setMessage("Are you sure to delete?");
                        alertDialogBuilder.setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        new MovieDb(v.getContext()).deleteGenre(genreModelList.get(position).getgId());
                                        genreModelList.remove(position);
                                        notifyDataSetChanged();
                                    }
                                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return genreModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageButton ibtnEdit, ibtnDelete; TextView tvGenre;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvGenre = itemView.findViewById(R.id.tvGenre);
            ibtnDelete = itemView.findViewById(R.id.ibtnDelete);
            ibtnEdit = itemView.findViewById(R.id.ibtnEdit);
        }
    }


}
