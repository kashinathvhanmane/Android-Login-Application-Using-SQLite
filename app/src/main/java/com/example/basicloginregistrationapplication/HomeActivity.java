package com.example.basicloginregistrationapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView=findViewById(R.id.bottomnavigationview);
        FragmentManager fragmentManager=getSupportFragmentManager();
        Intent intent=getIntent();
        String phoneno=intent.getStringExtra("userPhone");
        if (savedInstanceState==null)
        {
            fragmentManager.beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.home:
                        HomeFragment homeFragment=new HomeFragment();
                        fragmentManager.beginTransaction().replace(R.id.fragment_container,homeFragment).commit();
                        break;
                    case R.id.activity:
                        AddFragment addFragment=new AddFragment();
                        fragmentManager.beginTransaction().replace(R.id.fragment_container,addFragment).commit();
                        break;
                    case R.id.profile:
                        ProfileFragment profileFragment=new ProfileFragment();
                        Bundle bundle=new Bundle();
                        bundle.putString("phone",phoneno);
                        profileFragment.setArguments(bundle);
                        fragmentManager.beginTransaction().replace(R.id.fragment_container,profileFragment).commit();
                        break;

                }
                return true;
            }
        });


    }
}