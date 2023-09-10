package com.example.mvcepic;

public class Game {
    int Game_id;
    String Game_name;
    String Game_desc;
    double Game_price;
    String Game_image;

    public Game(int game_id, String game_name, String game_desc, double game_price, String game_image) {
        Game_id = game_id;
        Game_name = game_name;
        Game_desc = game_desc;
        Game_price = game_price;
        Game_image = game_image;
    }
    public Game( String game_name, String game_desc, double game_price, String game_image) {

        Game_name = game_name;
        Game_desc = game_desc;
        Game_price = game_price;
        Game_image = game_image;
    }


    public int getGame_id() {
        return Game_id;
    }

    public void setGame_id(int game_id) {
        Game_id = game_id;
    }

    public String getGame_name() {
        return Game_name;
    }

    public void setGame_name(String game_name) {
        Game_name = game_name;
    }

    public String getGame_desc() {
        return Game_desc;
    }

    public void setGame_desc(String game_desc) {
        Game_desc = game_desc;
    }

    public double getGame_price() {
        return Game_price;
    }

    public void setGame_price(double game_price) {
        Game_price = game_price;
    }

    public String getGame_image() {
        return Game_image;
    }

    public void setGame_image(String game_image) {
        Game_image = game_image;
    }

    @Override
    public String toString() {
        return "Game{" +
                "Game_id=" + Game_id +
                ", Game_name='" + Game_name + '\'' +
                ", Game_desc='" + Game_desc + '\'' +
                ", Game_price=" + Game_price +
                ", Game_image='" + Game_image + '\'' +
                '}';
    }
}