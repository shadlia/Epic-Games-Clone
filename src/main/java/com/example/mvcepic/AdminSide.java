package com.example.mvcepic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AdminSide extends BorderPane  {
     Button btn_users;
    Button btn_pur;
    Button btn_games;
    //MENU
    Stage s;


    AdminSide(Stage s) {
this.s=s;
        Background bg=new Background(new BackgroundFill(Color.LIGHTGRAY,null,null));
        this.setBackground(bg);

        VBox vb_menu = new VBox(30);
        vb_menu.setBorder(new Border(new BorderStroke(Paint.valueOf("#696969"),BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5),new Insets(10))));
        vb_menu.setAlignment(Pos.TOP_CENTER);
        vb_menu.setPadding(new Insets(50,10,50,10));

        Label lb_dash=new Label("Dashboard");
        lb_dash.setFont(Font.font("MONOSPACED BOLD", 25));
        lb_dash.setTextFill(Color.BLACK);

        btn_games = new Button("Games");
        btn_games.setFont(Font.font("MONOSPACED BOLD", 20));
        btn_games.setTextFill(Color.BLACK);
        btn_games.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(8), null)));
        btn_games.setPrefWidth(180);
        btn_games.setPrefHeight(60);


        btn_users = new Button("Users");
        btn_users.setFont(Font.font("MONOSPACED BOLD", 20));
        btn_users.setTextFill(Color.BLACK);
        btn_users.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(8), null)));
        btn_users.setPrefWidth(180);
        btn_users.setPrefHeight(60);

        btn_pur = new Button("Purchases");
        btn_pur.setFont(Font.font("MONOSPACED BOLD", 20));
        btn_pur.setTextFill(Color.BLACK);
        btn_pur.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(8), null)));
        btn_pur.setPrefWidth(180);
        btn_pur.setPrefHeight(60);


           Button logout = new Button("Logout");
            logout.setFont(Font.font("MONOSPACED BOLD", 20));
            logout.setTextFill(Color.DARKBLUE);
            logout.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(8), null)));
            logout.setPrefWidth(120);
            logout.setPrefHeight(60);
           // logout.setPadding(new Insets(400,5,20,5));

        vb_menu.getChildren().addAll(lb_dash,btn_games,btn_users,btn_pur,logout);


        //top menu

        HBox top_hb = new HBox();
        top_hb.setAlignment(Pos.CENTER);
        top_hb.setPadding(new Insets(30,0,30,0));

        Label lb_welcome=new Label("Welcome Admin");
        lb_welcome.setFont(Font.font("MONOSPACED BOLD", 25));
        lb_welcome.setTextFill(Color.BLACK);
        top_hb.getChildren().addAll(lb_welcome);

        //table view




        BorderPane bp_center=new BorderPane();

        bp_center.setTop(top_hb);
        this.setLeft(vb_menu);
        this.setCenter(bp_center);
        bp_center.setCenter(new admin_games(s,btn_games));



        //EFFECTS
        btn_games.addEventHandler(MouseEvent.MOUSE_ENTERED,new buttoneffectsadmin(this));
        btn_games.addEventHandler(MouseEvent.MOUSE_EXITED,new buttoneffectsadmin(this));
        btn_games.addEventHandler(MouseEvent.MOUSE_CLICKED,new buttoneffectsadmin(this));

        btn_users.addEventHandler(MouseEvent.MOUSE_ENTERED,new buttoneffectsadmin(this));
        btn_users.addEventHandler(MouseEvent.MOUSE_EXITED,new buttoneffectsadmin(this));
        btn_users.addEventHandler(MouseEvent.MOUSE_CLICKED,new buttoneffectsadmin(this));

        btn_pur.addEventHandler(MouseEvent.MOUSE_ENTERED,new buttoneffectsadmin(this));
        btn_pur.addEventHandler(MouseEvent.MOUSE_EXITED,new buttoneffectsadmin(this));
        btn_pur.addEventHandler(MouseEvent.MOUSE_CLICKED,new buttoneffectsadmin(this));

        //events
        btn_games.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bp_center.setCenter(new admin_games(s,btn_games));
            }
        });
        btn_users.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                        bp_center.setCenter(new admin_users(s));
                }
        });
        logout.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                        s.hide();
                        Login l=new Login();
                        try {
                                l.start(s);
                        } catch (Exception e) {
                                throw new RuntimeException(e);
                        }
                }
        });

    }
}
