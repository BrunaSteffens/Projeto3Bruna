package com.example.projeto3bruna.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto3bruna.R;
import com.example.projeto3bruna.model.Todo;

import java.util.List;

public class TodosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "TodosAdapter";
    private List<Todo> todoList;

    public TodosAdapter(List<Todo> todoList){ this.todoList = todoList;}


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: iniciando a viewHolder do todosAdapter");
        View layoutVH = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_todos_vh, parent, false);
        return new TodosViewHolder(layoutVH);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Todo objeto = todoList.get(position);
        ((TextView) holder.itemView.findViewById(R.id.vhTodoId)).setText(objeto.getTodoId() + "");
        ((TextView) holder.itemView.findViewById(R.id.vhTodoTitle)).setText(objeto.getTodoTitle());
        ((TextView) holder.itemView.findViewById(R.id.vhTodoStatus)).setText(objeto.getTodoStatus());
        //((TextView) holder.itemView.findViewById(R.id.tvTodoUserId)).setText(objeto.getUser().getId()+"");
        //((TextView) holder.itemView.findViewById(R.id.tvTodoUserName)).setText(objeto.getUser().getName());

    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
}

class TodosViewHolder extends RecyclerView.ViewHolder{
    public View view;


    public TodosViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
    }
}
