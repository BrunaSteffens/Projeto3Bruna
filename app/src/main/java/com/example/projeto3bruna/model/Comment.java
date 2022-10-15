package com.example.projeto3bruna.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.projeto3bruna.repository.PostsRepository;

public class Comment implements Parcelable {

    private Post post;
    private int commentId;
    private String commentTitle;
    private String commentEmail;
    private String commentBody;

    public Comment() {    }

    public Comment(int idPost, int commentId, String commentTitle, String commentEmail, String commentBody) {
        this.post = PostsRepository.getInstance().getPostById(idPost);
        this.commentId = commentId;
        this.commentTitle = commentTitle;
        this.commentEmail = commentEmail;
        this.commentBody = commentBody;
    }

    public Comment(int commentId, String commentTitle, String commentEmail, String commentBody) {
        this.commentId = commentId;
        this.commentTitle = commentTitle;
        this.commentEmail = commentEmail;
        this.commentBody = commentBody;
    }

    protected Comment(Parcel in){
        post = in.readParcelable(Post.class.getClassLoader());
        commentId = in.readInt();
        commentTitle = in.readString();
        commentEmail = in.readString();
        commentBody = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeParcelable(post, 1);
        parcel.writeInt(commentId);
        parcel.writeString(commentTitle);
        parcel.writeString(commentEmail);
        parcel.writeString(commentBody);
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public int getCommentId() {
        return commentId;
    }

    public String getCommentTitle() {
        return commentTitle;
    }

    public String getCommentEmail() {
        return commentEmail;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public Post getPost() { return post; }
}
