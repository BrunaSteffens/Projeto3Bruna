package com.example.projeto3bruna.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.projeto3bruna.repository.AlbumRepository;

public class Photo implements Parcelable {

    private Albums album;
    private int photoId;
    private String photoTitle;
    private String photoUrl;
    private String photoThumbnailUrl;

    public Photo() {

    }

    public Photo(int idAlbum, int photoId, String photoTitle, String photoUrl, String photoThmbnailUrl) {
        this.album = AlbumRepository.getInstance().getAlbumById(idAlbum);
        this.photoId = photoId;
        this.photoTitle = photoTitle;
        this.photoUrl = photoUrl;
        this.photoThumbnailUrl = photoThumbnailUrl;
    }

    public Photo(int photoId, String photoTitle, String photoUrl, String photoThumbnailUrl) {
        this.photoId = photoId;
        this.photoTitle = photoTitle;
        this.photoUrl = photoUrl;
        this.photoThumbnailUrl = photoThumbnailUrl;
    }

    protected Photo(Parcel in){
        album = in.readParcelable(Albums.class.getClassLoader());
        photoId = in.readInt();
        photoTitle = in.readString();
        photoUrl = in.readString();
        photoThumbnailUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeParcelable(album, 1);
        parcel.writeInt(photoId);
        parcel.writeString(photoTitle);
        parcel.writeString(photoUrl);
        parcel.writeString(photoThumbnailUrl);
    }

    public int getPhotoId() {
        return photoId;
    }

    public String getPhotoTitle() {
        return photoTitle;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getPhotoThumbnailUrl() {
        return photoThumbnailUrl;
    }

    public Albums getAlbum() { return album; }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {

            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {

            return new Photo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
