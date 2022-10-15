package com.example.projeto3bruna.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.example.projeto3bruna.R;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Iniciando a MainActivity");
        setContentView(R.layout.activity_main);


        findViewById(R.id.buttonUsers).setOnClickListener(
                (view)->{
                    Intent intentUsers = new Intent(this, UserActivity.class);
                    startActivity(intentUsers);
                    Log.d(TAG, "onCreate: Partiu para activity dos usuários");
                }
        );

        findViewById(R.id.buttonAlbums).setOnClickListener(
                (view) ->{
                    Intent intentAlbum = new Intent(this, AlbumActivity.class);
                    startActivity(intentAlbum);
                    Log.d(TAG, "onCreate: Partiu para activity dos Album");
                }
        );

        findViewById(R.id.buttonPosts).setOnClickListener(
                (view) ->{
                    Intent intentPosts = new Intent(this, PostActivity.class);
                    startActivity(intentPosts);
                    Log.d(TAG, "onCreate: Partiu para activity dos Posts");
                }
        );

        findViewById(R.id.buttonTodos).setOnClickListener(
                (view) ->{
                    Intent intentTodos = new Intent(this, TodoActivity.class);
                    startActivity(intentTodos);
                    Log.d(TAG, "onCreate: Partiu para activity das Tarefas");
                }
        );

        findViewById(R.id.buttonComments).setOnClickListener(
                (view) ->{
                    Intent intentComments = new Intent(this, CommentActivity.class);
                    startActivity(intentComments);
                    Log.d(TAG, "onCreate: Partiu para activity dos Comentarios");
                }
        );


        findViewById(R.id.buttonPhotos).setOnClickListener(
                (view) ->{
                    Intent intentPhotos = new Intent(this, PhotoActivity.class);
                    startActivity(intentPhotos);
                    Log.d(TAG, "onCreate: Partiu para activity das Fotos");
                }
        );

    }
}
