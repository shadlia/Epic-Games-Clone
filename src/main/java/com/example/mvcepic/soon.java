package com.example.mvcepic;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class soon extends HBox {
    Image image;
    soon(String img,String des,String title){
        HBox hb_image=new HBox();
        try {
           image = new Image(new FileInputStream(img));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Setting the image view
        ImageView iconView = new ImageView(image);
        iconView.setFitHeight(180);
        iconView.setFitWidth(200);

        hb_image.getChildren().add(iconView);

        VBox hb_des=new VBox(10);
        hb_des.setPrefWidth(220);
        Text desc=new Text(des);
        desc.setWrappingWidth(300);
        desc.maxHeight(20);
        desc.setFont(Font.font("MONOSPACED BOLD",15));
        desc.setFill(Color.WHITE);
        Label lb_titre = new Label();
        lb_titre.setText(title);
        lb_titre.setFont(Font.font("MONOSPACED BOLD",20));
        lb_titre.setTextFill(Color.WHITE);



        hb_des.getChildren().addAll(lb_titre,desc);
        hb_des.setAlignment(Pos.CENTER);

        Button btn=new Button("Read more");
        btn.setFont(Font.font("MONOSPACED BOLD",15));
        btn.setBackground(new Background(new BackgroundFill(Color.SNOW,new CornerRadii(8),null)));
        btn.setTextFill(Color.BLACK);

        HBox hb_btn=new HBox(btn);
        hb_btn.setAlignment(Pos.CENTER);





        this.setSpacing(30);
        this.getChildren().addAll(hb_image,hb_des,hb_btn);




    }
}
