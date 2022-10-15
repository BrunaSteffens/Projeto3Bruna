package com.example.projeto3bruna.view;

import androidx.appcompat.app.AppCompatActivity;
import com.example.projeto3bruna.R;
import com.example.projeto3bruna.model.User;
import com.example.projeto3bruna.repository.DataBaseHelper;
import com.example.projeto3bruna.repository.UserSQLRepository;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        findViewById(R.id.buttonSignUp).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        User user = new User();
                        user.setName(((TextView) findViewById(R.id.editTextSUName)).getText().toString());
                        user.setUserLogin(((TextView) findViewById(R.id.editTextSUUserLogin)).getText().toString());
                        user.setPassword(((TextView) findViewById(R.id.editTextSUPassword)).getText().toString());
                        user.setEmail(((TextView) findViewById(R.id.editTextSUEmail)).getText().toString());
                        user.setPhone(((TextView) findViewById(R.id.editTextSUPhone)).getText().toString());
                        UserSQLRepository.getInstance().addUser(user);

                    }
                }
        );

    }
}