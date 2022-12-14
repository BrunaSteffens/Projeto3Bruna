package com.example.projeto3bruna.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projeto3bruna.R;
import com.example.projeto3bruna.adapter.AlbumsAdapter;
import com.example.projeto3bruna.repository.AlbumRepository;
import com.example.projeto3bruna.repository.AlbumSQLRepository;

import android.os.Bundle;
import android.util.Log;


public class AlbumActivity extends AppCompatActivity  {
    private final String TAG = "AlbumActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Iniciando a activity dos álbuns");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        AlbumSQLRepository.getInstance(this).addAlbumTest();

        Log.d(TAG, "onCreate: Iniciando o recycler dos albuns do usuário");
        RecyclerView rv = findViewById(R.id.recyclerAlbums);
        AlbumsAdapter adapter = new AlbumsAdapter(AlbumSQLRepository.getInstance().getAlbums());
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);
    }
}
