package com.example.projeto3bruna.repository;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projeto3bruna.model.Photo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PhotosRepository {
    private static final String TAG = "PhotosRepository";
    private final List<Photo> photos;
    private static PhotosRepository instance;
    private Context context;

    private PhotosRepository(Context context){
        super();
        photos = new ArrayList<>();
        this.context = context;

        RequestQueue photosQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/photos",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, "onResponse: " + response.length());

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject json = response.getJSONObject(i);
                                Log.d(TAG, "onResponse: " + json.toString());
                                photos.add(new Photo( json.getInt("albumId"),
                                        json.getInt("id"), json.getString("title"), json.getString("url"), json.getString("thumbnailUrl")));
                                //int idAlbum, int photoId, String photoTitle, String photoUrl, String photoThmbnailUrl

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        Log.e(TAG, "onResponse: inclus??o das fotos finalizada" );
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: " + error.getMessage() );
                    }
                });

        photosQueue.add(jsonArrayRequest);
        Log.e(TAG, "PhotosRepository: reposit??rio de fotos lan??ado");

    }

    public static PhotosRepository getInstance(Context context){
        if (instance == null){
            instance = new PhotosRepository(context);
        }
        return instance;
    }

    public List<Photo> getPhotos(){ return photos; }


    public Photo getPhotoById(int photoId){
        Photo ret = null;
        Log.d(TAG, "getPhotoById: "+photos.size());
        for(Photo p : photos){
            Log.d(TAG, "getPhotoById: " + photoId+ " ->" +p.getPhotoId());
            if(p.getPhotoId()==photoId){
                ret = p;
            }
        }
        return ret;
    }

}
