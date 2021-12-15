package com.example.stars;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static DbHelper dbHelper;
    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public static DbHelper getInstance(Context context){
        if(dbHelper==null){
            dbHelper =new DbHelper(context, "Db_Stars", null, 1);
        }
        return dbHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists users" +
                "(_id integer primary key autoincrement," +
                "uname varchar(20)," +
                "upassword varchar(12))");

        db.execSQL("create table if not exists contents" +
                "(_id integer primary key autoincrement," +
                "stop varchar(20)," +
                "simg varchar(100)," +
                "sword varchar(120)," +
                "send varchar(20)," +
                "stime varchar(30)," +
                "sstar varchar(10)," +
                "sheart varchar(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}






