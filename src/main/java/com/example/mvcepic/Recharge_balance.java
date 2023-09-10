package com.example.mvcepic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.util.Duration;

public class Recharge_balance extends VBox {
User u;
    Popup p;
    ClientSide c;
    Label lb_balance;
    Recharge_balance(User u, Popup p, Label lb_balance){
        this.u=u;
        this.p=p;
        this.lb_balance=lb_balance;

        Label lb_title=new Label("HOW MUCH YOU WANT TO RECHARGE ?");
        TextField tf_amount=new TextField();

        HBox btns=new HBox(10);
        Button btn_valide=new Button("Recharge");
        Button  btn_annuler=new Button("Cancel");
        btns.setAlignment(Pos.CENTER);

        Label lb_info=new Label("");
        lb_info.setFont(Font.font("MONOSPACED ",15));
        lb_info.setTextFill(Color.RED);

        btns.getChildren().addAll(btn_annuler,btn_valide);

        this.getChildren().addAll(lb_title ,lb_info,tf_amount,btns);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.setWidth(600);
         this.setHeight(400);
         this.setPadding(new Insets(30,50,20,50));
        Background bg=new Background(new BackgroundFill(Color.WHITE,new CornerRadii(20),null));
        this.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(20),new BorderWidths(4),null)));
        this.setBackground(bg);

        btn_annuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                p.hide();

            }
        });
        btn_valide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if((!tf_amount.getText().equals(""))&isNumeric(tf_amount.getText())) {
                    UserDao us = new UserDao();
                    us.Recharge_balance(u, Double.parseDouble(tf_amount.getText()));

                    Double b = us.get_balance(u);
                    System.out.println(b);
                    lb_balance.setText("Your balance : " + b + "$");


                    int popupDuration = 30; // in seconds
                    // Create a Timeline to hide the popup after the duration
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(popupDuration), event -> p.hide()));
                    timeline.play();


                }
                else {
                    lb_info.setText("Invalide Amount");
                }

            }
        });



    }
    public static boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
