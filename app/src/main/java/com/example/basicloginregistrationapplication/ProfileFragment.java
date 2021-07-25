package com.example.basicloginregistrationapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    TextView nametxt,emailtxt,phonetext;
    List<User> userList;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.profilefragment,container,false);

        nametxt=view.findViewById(R.id.profileusername);
        emailtxt=view.findViewById(R.id.profileuseremail);
        phonetext=view.findViewById(R.id.profileuserphone);
        Bundle bundle=this.getArguments();
        String phone = null;
        if(getArguments()!=null)
        {
         phone=getArguments().getString("phone");

        }
        SqliteDataBaseHelper sqliteDataBaseHelper=new SqliteDataBaseHelper(getContext());
        User user=new User();
        user.setPhone(phone);
        userList=sqliteDataBaseHelper.getUserDetails(user);

        for (User user1:userList)
        {
            nametxt.setText(user1.getUsername());
            emailtxt.setText(user1.getEmail());
            phonetext.setText(user1.getPhone());

        }





        return view;
    }
}
