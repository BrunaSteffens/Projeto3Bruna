package com.example.projeto3bruna.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.projeto3bruna.repository.UserRepository;

public class Post implements Parcelable {

    private User user;
    private int postId;
    private String postTitle;
    private String postBody;

    public Post(){    }


    public Post(int idUser, int postId, String postTitle, String postBody) {
        this.user = UserRepository.getInstance().getUserById(idUser);
        this.postId = postId;
        this.postTitle = postTitle;
        this.postBody = postBody;
    }

    public Post(int postId, String postTitle, String postBody) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postBody = postBody;
    }

    protected Post(Parcel in){
        user = in.readParcelable(User.class.getClassLoader());
        postId = in.readInt();
        postTitle = in.readString();
        postBody = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeParcelable(user, 1);
        parcel.writeInt(postId);
        parcel.writeString(postTitle);
        parcel.writeString(postBody);
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public Post(int postId) {
        this.postId = postId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getPostId() {
        return postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostBody() {
        return postBody;
    }

    public User getUser() {return user; }
}
