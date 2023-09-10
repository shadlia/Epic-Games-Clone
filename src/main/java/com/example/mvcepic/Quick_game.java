package com.example.mvcepic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Quick_game extends HBox {


    public final Image game1;
    public final VBox vb_name;
    public final Label lb_gamename;
    public final Label lb_launch;

    public Quick_game(){

        //image
        Image back_icon = null;

        try {
            game1 = new Image(new FileInputStream("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic\\src\\img\\games\\Grand_Theft_Auto_V.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Setting the image view
        ImageView game1View = new ImageView(game1);
        game1View.setFitHeight(80);
        game1View.setFitWidth(60);

        //name+lunch button
        vb_name=new VBox(5);
        String game_name="GTA V";
        lb_gamename=new Label(game_name);
        lb_launch=new Label("Launch");
        vb_name.getChildren().addAll(lb_gamename,lb_launch);
        vb_name.setAlignment(Pos.CENTER);

        //fonts
        lb_gamename.setFont(Font.font("MONOSPACED BOLD",15));
        lb_gamename.setTextFill(Color.WHITE);

        lb_launch.setFont(Font.font("MONOSPACED BOLD",15));
        lb_launch.setTextFill(Color.WHITE);


        this.getChildren().addAll(game1View,vb_name);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.setPadding(new Insets(10,5,10,5));



}
}
