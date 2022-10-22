package com.example.projeto3bruna.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto3bruna.R;
import com.example.projeto3bruna.adapter.PhotosAdapter;
import com.example.projeto3bruna.repository.PhotoSQLRepository;
import com.example.projeto3bruna.repository.PhotosRepository;

import android.os.Bundle;

public class PhotoActivity extends AppCompatActivity {

    private static final String TAG = "PhotosActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        PhotoSQLRepository.getInstance(this).addPhotoTest();

        RecyclerView rv = findViewById(R.id.recyclerPhotos);
        PhotosAdapter adapter = new PhotosAdapter(PhotoSQLRepository.getInstance().getPhotos());
        rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
    }
}