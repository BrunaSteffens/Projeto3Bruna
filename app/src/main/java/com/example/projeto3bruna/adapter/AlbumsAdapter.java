package com.example.projeto3bruna.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto3bruna.model.Album;
import com.example.projeto3bruna.R;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Album> albums;
    private final String TAG = "AlbumsAdapter";

    public AlbumsAdapter(List<Album> albums){
        this.albums = albums;
        for (Album a: albums
             ) {

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: iniciando a viewholder do albumadapter");
        View layoutVHAlbum = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_albums_vh, parent, false);
        return new AlbumsViewHolder(layoutVHAlbum);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Album objeto = albums.get(position);
        ((TextView) holder.itemView.findViewById(R.id.textViewAlbumId)).setText(objeto.getAlbumId()+"");
        ((TextView) holder.itemView.findViewById(R.id.textViewAlbumTitle)).setText(objeto.getTitle());
        //((TextView) holder.itemView.findViewById(R.id.tvAlbumsUserId)).setText(objeto.getUser().getId()+"");
        //((TextView) holder.itemView.findViewById(R.id.tvAlbumUserName)).setText(objeto.getUser().getName());
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }
}



class AlbumsViewHolder extends RecyclerView.ViewHolder{
    public View view;
    public AlbumsViewHolder(@NonNull View itemView){
        super(itemView);
        view = itemView;
    }
}