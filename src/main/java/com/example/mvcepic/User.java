package com.example.mvcepic;

public class User {
    int User_id;
    String User_name;
    String User_lastname;
    String User_username;
    String User_password;
    String User_role;
    double User_balance;

    public User(int id, String user_name, String user_lastname, String user_username, String user_password, String user_role, double user_balance) {
        User_id=id;
        User_name = user_name;
        User_lastname = user_lastname;
        User_username = user_username;
        User_password = user_password;
        User_role = user_role;
        User_balance = user_balance;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public String getUser_lastname() {
        return User_lastname;
    }

    public void setUser_lastname(String user_lastname) {
        User_lastname = user_lastname;
    }

    public String getUser_username() {
        return User_username;
    }

    public void setUser_username(String user_username) {
        User_username = user_username;
    }

    public String getUser_password() {
        return User_password;
    }

    public void setUser_password(String user_password) {
        User_password = user_password;
    }

    public String getUser_role() {
        return User_role;
    }

    public void setUser_role(String user_role) {
        User_role = user_role;
    }

    public double getUser_balance() {
        return User_balance;
    }

    public void setUser_balance(double user_balance) {
        User_balance = user_balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "User_id=" + User_id +
                ", User_name='" + User_name + '\'' +
                ", User_lastname='" + User_lastname + '\'' +
                ", User_username='" + User_username + '\'' +
                ", User_password='" + User_password + '\'' +
                ", User_role='" + User_role + '\'' +
                ", User_balance=" + User_balance +
                '}';
    }
}
