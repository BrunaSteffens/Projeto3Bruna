package com.example.projeto3bruna.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.projeto3bruna.R;
import com.example.projeto3bruna.presenter.LoginPresenter;
import com.example.projeto3bruna.presenter.LoginPresenterContract;
import com.example.projeto3bruna.repository.UserSQLRepository;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity implements LoginPresenterContract.view {

    private static final String TAG = "LoginActivity";
    private LoginPresenterContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserSQLRepository.getInstance(this).addUserTest();

        this.presenter = new LoginPresenter(this);
        findViewById(R.id.buttonLogin).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.checkLogin(
                                ((TextView) findViewById(R.id.editTextUser)).getText().toString(),
                                ((TextView) findViewById(R.id.editTextTextPassword)).getText().toString()
                        );
                    }
                }
        );

        findViewById(R.id.buttonLoginSignUp).setOnClickListener(
                (view) ->{
                    Intent intentSignUp = new Intent(this, SignUpActivity.class);
                    startActivity(intentSignUp);
                    Log.d(TAG, "onCreate: Partiu para activity de cadastro");
                }
        );
    }

    @Override
    public void message(String msg) {
        Snackbar.make(this, findViewById(R.id.editTextTextPassword), msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

}