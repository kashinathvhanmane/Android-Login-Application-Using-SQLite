package com.example.basicloginregistrationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    EditText usernameedt,emailedt,mobileedt,passwrodedt,repasswordedt;
    Button signinbtn;

    SqliteDataBaseHelper sqliteDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        sqliteDataBaseHelper=new SqliteDataBaseHelper(this);

        usernameedt=findViewById(R.id.regiUser);
        emailedt=findViewById(R.id.regiEmail);
        mobileedt=findViewById(R.id.regiMobile);
        passwrodedt=findViewById(R.id.regiPassword);
        repasswordedt=findViewById(R.id.regiRepass);

        signinbtn=findViewById(R.id.regiSignin);



        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                int Flag = 0;
                if (TextUtils.isEmpty(emailedt.getText().toString())) {
                    emailedt.requestFocus();
                    emailedt.setError("Please enter email");
                    Flag = 1;
                } else if (!isValidEmail(emailedt.getText().toString())) {
                    emailedt.requestFocus();
                    emailedt.setError("Please enter valid email");
                    Flag = 1;
                }
                if (TextUtils.isEmpty(usernameedt.getText().toString())) {
                    usernameedt.requestFocus();
                    usernameedt.setError("Please enter username");
                    Flag = 1;
                }

                if (TextUtils.isEmpty(passwrodedt.getText().toString())) {
                    passwrodedt.requestFocus();
                    passwrodedt.setError("Please enter password");
                    Flag = 1;
                }
                if (passwrodedt.getText().toString().trim().length() < 8) {
                    passwrodedt.requestFocus();
                    passwrodedt.setError("password should be 8 length long");
                    Flag = 1;
                }
                if(!passwrodedt.getText().toString().trim().matches("(.*[0-9].*)"))
                {
                    passwrodedt.requestFocus();
                    passwrodedt.setError("password should contain at least one digit");
                    Flag = 1;

                }
                if(!passwrodedt.getText().toString().trim().matches("(.*[A-Z].*)"))
                {
                    passwrodedt.requestFocus();
                    passwrodedt.setError("password should contain at least one upper alphabet");
                    Flag = 1;

                }

                if(!passwrodedt.getText().toString().trim().matches("(.*[a-z].*)"))
                {
                    passwrodedt.requestFocus();
                    passwrodedt.setError("password should contain at least one lower alphabet");
                    Flag = 1;

                }
                if(!passwrodedt.getText().toString().trim().matches("^(?=.*[_.()$&@]).*$"))
                {
                    passwrodedt.requestFocus();
                    passwrodedt.setError("password should contains at least one special symbol ");
                    Flag = 1;

                }
                if (TextUtils.isEmpty(repasswordedt.getText().toString())) {
                    repasswordedt.requestFocus();
                    repasswordedt.setError("Please enter conformpassword");
                    Flag = 1;
                }/*else if (conformpassword.getText().toString().trim().length() < 8) {
                    conformpassword.requestFocus();
                    conformpassword.setError("Please enter 8 conform password");
                    Flag = 1;
                }*/
                if (TextUtils.isEmpty(mobileedt.getText().toString())) {
                    mobileedt.requestFocus();
                    mobileedt.setError("Please enter mobile");
                    Flag = 1;
                } else if (mobileedt.getText().toString().trim().length() < 10) {
                    mobileedt.requestFocus();
                    mobileedt.setError("Please enter 10 digit mobile number");
                    Flag = 1;
                }

                if (Flag == 0) {
                    signup();
                }



            }
        });


    }

    public void signup()
    {
        User user=new User();
        user.setUsername(usernameedt.getText().toString());
        user.setEmail(emailedt.getText().toString());
        user.setPhone(mobileedt.getText().toString());
        user.setPassword(passwrodedt.getText().toString());
        sqliteDataBaseHelper.addUser(user);

        Toast.makeText(this,"user Registered sucessfully..",Toast.LENGTH_LONG).show();
        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
    }

    private boolean isValidEmail(String email) {
        if (!TextUtils.isEmpty(email)) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        return false;
    }

}