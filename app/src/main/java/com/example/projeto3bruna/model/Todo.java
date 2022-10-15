package com.example.projeto3bruna.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.projeto3bruna.repository.UserRepository;

public class Todo implements Parcelable {

    private User user;
    private int todoId;
    private String todoTitle;
    private String todoStatus;

    public Todo() { }

    public Todo(int idUser, int todoId, String todoTitle, String todoStatus) {
        this.user = UserRepository.getInstance().getUserById(idUser);
        this.todoId = todoId;
        this.todoTitle = todoTitle;
        this.todoStatus = todoStatus;
    }

    public Todo(int todoId, String todoTitle, String todoStatus) {
        this.todoId = todoId;
        this.todoTitle = todoTitle;
        this.todoStatus = todoStatus;
    }

    protected Todo(Parcel in){
        user = in.readParcelable(User.class.getClassLoader());
        todoId = in.readInt();
        todoTitle = in.readString();
        todoStatus = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeParcelable(user, 1);
        parcel.writeInt(todoId);
        parcel.writeString(todoTitle);
        parcel.writeString(todoStatus);
    }

    public int getTodoId() {
        return todoId;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public String getTodoStatus() {
        return todoStatus;
    }

    public User getUser() { return user; }

    public void setUser(int idUser) {
        this.user = UserRepository.getInstance().getUserById(idUser);
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public void setTodoStatus(String todoStatus) {
        this.todoStatus = todoStatus;
    }

    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

}
