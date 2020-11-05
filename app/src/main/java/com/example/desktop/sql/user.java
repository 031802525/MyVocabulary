package com.example.desktop.sql;

public class user {
    private String name;
    private String password;

    public user(String name, String password){
        this.name = name;
        this.password = password;
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String toString(){
        return "User{"+ "name='"+name+'\''+",password='"+password+'\''+'}';
    }
}
