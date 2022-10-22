package com.example.projeto3bruna.presenter;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projeto3bruna.R;
import com.example.projeto3bruna.model.User;
import com.example.projeto3bruna.repository.UserRepository;
import com.example.projeto3bruna.repository.UserSQLRepository;
import com.example.projeto3bruna.view.MainActivity;

public class LoginPresenter implements LoginPresenterContract.presenter{

    private LoginPresenterContract.view view;


    public LoginPresenter(LoginPresenterContract.view view){
        this.view = view;
    }

    @Override
    public void checkLogin(String login, String password) {
        UserSQLRepository repo = UserSQLRepository.getInstance(view.getActivity());
        User u = repo.getUserByUserLogin(login);
        if (u== null || !u.getPassword().equals(password)){
            view.message("Usuário ou senha inválidos");
            //Testar usar dentro da mensagem: Resources.getSystem().getString(R.string.userpassinvalid)
        } else {
            validLogin(u);
        }
    }

    @Override
    public void validLogin(User user) {
        Intent intent = new Intent(view.getActivity(), MainActivity.class);
        //substituído devido ao parcelable   intent.putExtra("userId", user.getId());
        intent.putExtra("userObj", user);
        view.getActivity().startActivity(intent);
    }

}
