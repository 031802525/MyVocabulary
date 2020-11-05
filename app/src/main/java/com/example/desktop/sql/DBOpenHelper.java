package com.example.desktop.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBOpenHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    public DBOpenHelper(Context context){
        super(context, "db_test", null, 1);
        db = getReadableDatabase();//数据库设置为可写入状态
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user("
                +"_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name TEXT,"+
                "password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }
    public void add(String name,String password){
        db.execSQL("INSERT INTO user (name,password) VALUES(?,?)",new Object[]{name,password});
    }
    public void delete(String name,String password){
        db.execSQL("DELETE FROM user WHERE name = AND password ="+name+password);
    }
    public void updatae(String password){
        db.execSQL("UPDATE user SET password = ?",new Object[]{password});
    }
    public ArrayList<user> getAllData(){
        ArrayList<user> list = new ArrayList<user>();
        Cursor cursor = db.query("user",null,null,null,null,null,"name DESC");
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new user(name,password));
        }
        return list;
    }
}
