package com.example.fit_buddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Databasehelper extends SQLiteOpenHelper {

    public static final String dbName="UserLogin";
    public static final Integer version=1;

    public Databasehelper(@Nullable Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table USER(USERNAME TEXT primary key,PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertData(String username,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("USERNAME",username);
        values.put("PASSWORD",password);

        long ins= db.insert("USER",null,values);
        if(ins==-1){
            return false;
        }
        else
            return true;
    }

    public boolean checkname(String username){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from USER where USERNAME=?",new String[]{username});

        if (cursor.getCount() >0){
            return true;
        }else
            return false;
    }
//    public boolean checkpassword(String username,String password){
//        SQLiteDatabase db=this.getReadableDatabase();
//        Cursor cursor=db.rawQuery("Select * from USER where USERNAME=? and PASSWORD=?",new String[]{username,password});
//
//        if (cursor.getCount() >0){
//            return true;
//        }else
//            return false;
//    }
    public boolean checkrecord(String username,String password){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from USER where USERNAME=? and PASSWORD=?",new String[]{username,password});

        if (cursor.getCount() >0){
            return true;
        }else
            return false;
    }
}
