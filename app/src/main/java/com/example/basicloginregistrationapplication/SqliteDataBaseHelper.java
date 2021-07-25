package com.example.basicloginregistrationapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class SqliteDataBaseHelper extends SQLiteOpenHelper {

    public static final  int DATA_BASE_VERSION=11;
    public static final  String DATA_BASE_NAME="USERDATABASE";
    public String TABLE_NAME="User";
    public String username="username";
    public String email="email";
    public String phone="phone";
    public String password="password";


    public SqliteDataBaseHelper(@Nullable Context context)
    {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String createTable="CREATE TABLE "+TABLE_NAME+"( "+username+" varchar(100), " +
                email+" varchar(100)," +
                phone+" varchra(100)," +
                password+" varchar(100)"+")";

        sqLiteDatabase.execSQL(createTable);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }


    public void addUser(User user)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put(username,user.getUsername());
        contentValues.put(email,user.getEmail());
        contentValues.put(password,user.getPassword());
        contentValues.put(phone,user.getPhone());

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();

    }

    public void updateStudent(User user)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put(username,user.getUsername());
        contentValues.put(email,user.getEmail());
        contentValues.put(phone,user.getPhone());

        sqLiteDatabase.update(TABLE_NAME,contentValues,
                phone+"=?",new String[]{user.getPhone()});
        sqLiteDatabase.close();

    }

    public List<User> viewStudent()
    {
        List<User> studentList=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String selectAllData="SELECT * FROM "+TABLE_NAME;
        Cursor cursor=sqLiteDatabase.rawQuery(selectAllData,null);

        if (cursor.moveToFirst())
        {
            do {
                User user=new User();
                user.setUsername(cursor.getString(0));
                user.setEmail(cursor.getString(1));
                user.setPhone(cursor.getString(3));
                studentList.add(user);
            }while (cursor.moveToNext());

        }
        return studentList;
    }

    public void deleteStudent(String name)
    {

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, username+"=?",new String[]{name});
        sqLiteDatabase.close();

    }


    public boolean loginUser(User user)
    {

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String selectAllData="SELECT * FROM "+TABLE_NAME+" WHERE "+phone+" =? AND "+password+" =?";
        Cursor cursor=sqLiteDatabase.rawQuery(selectAllData,new String[]{user.getPhone(),user.getPassword()});

        Log.d("cursor",cursor.toString());

        if (cursor.moveToFirst())
        {
            return true;
        }
        else
        {
            return false;
        }

    }


    public List<User> getUserDetails(User user)
    {
        List<User> userList=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String selectAllData="SELECT * FROM "+TABLE_NAME +" WHERE "+ phone+"=?";
        Cursor cursor=sqLiteDatabase.rawQuery(selectAllData,new String[]{user.getPhone()});

        if (cursor!=null)
        {
            cursor.moveToFirst();
            User user1=new User();
            user1.setUsername(cursor.getString(0));
            user1.setEmail(cursor.getString(1));
            user1.setPhone(cursor.getString(2));
            userList.add(user1);

        }

        return userList;

    }



}
