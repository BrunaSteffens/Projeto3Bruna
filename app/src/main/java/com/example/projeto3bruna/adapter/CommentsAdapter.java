package com.example.projeto3bruna.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto3bruna.R;
import com.example.projeto3bruna.model.Comment;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "CommentsAdapter";
    private List<Comment> commentList;

    public CommentsAdapter(List<Comment> commentList){
        this.commentList = commentList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: iniciando viewHolder do commentAdapter");
        View layoutVH = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_comments_vh, parent, false);
        return new CommentsViewHolder(layoutVH);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Comment objeto = commentList.get(position);
        ((TextView) holder.itemView.findViewById(R.id.commentId)).setText(objeto.getCommentId()+"");
        ((TextView) holder.itemView.findViewById(R.id.commentEmail)).setText(objeto.getCommentEmail());
        ((TextView) holder.itemView.findViewById(R.id.commentTitle)).setText(objeto.getCommentTitle());
        ((EditText) holder.itemView.findViewById(R.id.commentBody)).setText(objeto.getCommentBody());

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }
}

class CommentsViewHolder extends RecyclerView.ViewHolder{
    public View view;


    public CommentsViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
    }
}