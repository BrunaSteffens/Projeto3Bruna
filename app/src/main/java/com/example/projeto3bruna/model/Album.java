package com.example.projeto3bruna.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.projeto3bruna.repository.UserRepository;

public class Album implements Parcelable{

    private User user;
    private int albumId;
    private String title;

    public Album(){    }

    public Album(int userId, int albumId, String title) {
        this.user = UserRepository.getInstance().getUserById(userId);
        this.albumId = albumId;
        this.title = title;
    }

    public Album(int albumId, String title) {
        this.albumId = albumId;
        this.title = title;
    }

    public Album(int albumId) {
        this.albumId = albumId;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeParcelable(user, 1);
        parcel.writeInt(albumId);
        parcel.writeString(title);
    }

    protected Album(Parcel in){
        user = in.readParcelable(User.class.getClassLoader());
        albumId = in.readInt();
        title = in.readString();
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {

            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {

            return new Album[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public int getAlbumId() { return albumId; }

    public String getTitle() { return title;}

    public User getUser() { return user; }

    public String getUserName() {
        return getUser().getName();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
