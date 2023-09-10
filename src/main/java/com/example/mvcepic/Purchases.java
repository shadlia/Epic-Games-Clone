package com.example.mvcepic;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Purchases {
    int purchase_id;
    int game_id;
    int user_id;
    String date;
    String game_name;
    String game_desc;
    String game_img;

    public Purchases(int purchase_id, int game_id, int user_id, String date, String game_name, String game_desc, String game_img) {
        this.purchase_id = purchase_id;
        this.game_id = game_id;
        this.user_id = user_id;
        this.date = date;
        this.game_name = game_name;
        this.game_desc = game_desc;
        this.game_img = game_img;
    }

    public int getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(int purchase_id) {
        this.purchase_id = purchase_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGame_desc() {
        return game_desc;
    }

    public void setGame_desc(String game_desc) {
        this.game_desc = game_desc;
    }

    public String getGame_img() {
        return game_img;
    }

    public void setGame_img(String game_img) {
        this.game_img = game_img;
    }

    @Override
    public String toString() {
        return "Purchases{" +
                "purchase_id=" + purchase_id +
                ", game_id=" + game_id +
                ", user_id=" + user_id +
                ", date='" + date + '\'' +
                ", game_name='" + game_name + '\'' +
                ", game_desc='" + game_desc + '\'' +
                ", game_img='" + game_img + '\'' +
                '}';
    }
}

