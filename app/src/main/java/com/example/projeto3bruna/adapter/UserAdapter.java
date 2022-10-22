package com.example.projeto3bruna.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projeto3bruna.R;
import com.example.projeto3bruna.model.User;


import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<User> dados;
    private final String TAG = "UserAdapter";


    public UserAdapter(List<User> dados) {
        this.dados = dados;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: iniciando a viewholder do useradapter");
        View layoutVH = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_vh, parent, false);
        return new UserViewHolder(layoutVH);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: O onBindViewHolder está sendo trazido agora");
        User objeto = dados.get(position);
        ((TextView) holder.itemView.findViewById(R.id.userVHuserId)).setText(objeto.getName());
        ((TextView) holder.itemView.findViewById(R.id.userVHName)).setText(objeto.getName());
        ((TextView) holder.itemView.findViewById(R.id.userVHUserLogin)).setText(objeto.getUserLogin());
        ((TextView) holder.itemView.findViewById(R.id.userVHEmail)).setText(objeto.getEmail());
        ((TextView) holder.itemView.findViewById(R.id.userVHPhone)).setText(objeto.getPhone());

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
}

class UserViewHolder extends RecyclerView.ViewHolder  {
        public View view;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }
}