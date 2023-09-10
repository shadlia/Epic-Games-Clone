package com.example.mvcepic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;

public class GameCardLib extends VBox {
   Button Install_btn;
    Button buy_btn;

    ClientSide cs;
    Purchases p;
    GameCardLib(Purchases p){
       this.p=p;



       try {


            Image gamepic = new Image(new FileInputStream(p.game_img));
            ImageView GameView = new ImageView(gamepic);
            GameView.setFitWidth(180);
            GameView.setFitHeight(200);

            Label lb_name=new Label(p.game_name);
            lb_name.setFont(Font.font("MONOSPACED BOLD",20));
            lb_name.setTextFill(Color.WHITE);


            Text txt_des=new Text("Description: "+p.game_desc);
            txt_des.setFont(Font.font("MONOSPACED ",15));
            txt_des.setFill(Color.WHITE);
            txt_des.setWrappingWidth(200);
            txt_des.minHeight(200);

            Text txt_date=new Text("Date of purchase: "+p.date);
            txt_date.setFont(Font.font("MONOSPACED ",15));
            txt_date.setFill(Color.WHITE);
             txt_date.setWrappingWidth(200);
            txt_date.minHeight(200);

        Install_btn=new Button("Install");
        Install_btn.setPrefHeight(30);



        this.getChildren().addAll(GameView,lb_name,txt_des,txt_date);

           this.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,new CornerRadii(20),new BorderWidths(4))));
           this.setAlignment(Pos.TOP_CENTER);
           this.setPadding(new Insets(20,20,20,20));
           this.setSpacing(20);


        this.setOnMouseEntered(e->{
            this.setBackground(new Background(new BackgroundFill(Paint.valueOf("#353839"),new CornerRadii(20),null)));
        });
        this.setOnMouseExited(e->{
            this.setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(20),null)));
        });
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }




    }

}
