package com.example.basicloginregistrationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText userNameedt,passwordedt;
    SqliteDataBaseHelper sqliteDataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sqliteDataBaseHelper=new SqliteDataBaseHelper(this);
        userNameedt=findViewById(R.id.username);
        passwordedt=findViewById(R.id.password);
    }

    public void gotoSignIn(View view) {
        startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
    }

    public void gotoHome(View view) {

        User user=new User();
        user.setPhone(userNameedt.getText().toString());
        user.setPassword(passwordedt.getText().toString());

        boolean status=sqliteDataBaseHelper.loginUser(user);
        Log.d("Status",String.valueOf(status));
        if(status)
        {
            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
            intent.putExtra("userPhone",userNameedt.getText().toString());
            startActivity(intent);
        }
        else
        {
            Toast.makeText(LoginActivity.this,"please enter valid username or password",Toast.LENGTH_LONG).show();
        }


    }
}