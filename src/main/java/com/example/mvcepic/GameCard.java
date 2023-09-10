package com.example.mvcepic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameCard extends VBox {
     Text txt_owned;
    Text txt_price;
    Button buy_btn;
    Game g;
    User u;
    ClientSide cs;
    Popup p;

    GameCard(Game g,User u,ClientSide cs){
    this.g=g;
    this.u=u;
    this.cs=cs;

        Image search_icon = null;
        try {
            Image gamepic = new Image(new FileInputStream(g.Game_image));
            ImageView GameView = new ImageView(gamepic);
            GameView.setFitWidth(180);
            GameView.setFitHeight(200);

            Label lb_name=new Label(g.Game_name);
            lb_name.setFont(Font.font("MONOSPACED BOLD",20));
            lb_name.setTextFill(Color.WHITE);


            Text txt_des=new Text("Description: "+g.Game_desc);
            txt_des.setFont(Font.font("MONOSPACED ",15));
            txt_des.setFill(Color.WHITE);
            txt_des.setWrappingWidth(200);
            txt_des.minHeight(200);



            this.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,new CornerRadii(20),new BorderWidths(4))));
            this.setAlignment(Pos.TOP_CENTER);
            this.getChildren().addAll(GameView,lb_name,txt_des);
            this.setPadding(new Insets(20,20,20,20));
            this.setSpacing(20);

            PurchasesDao pd = new PurchasesDao();
           boolean owned= pd.GameOwner(u.User_id,g.Game_id );
            if(!owned){
                buy_btn=new Button();
                buy_btn.setPrefHeight(30);
                txt_price=new Text();
                if(g.Game_price==0){
                    txt_price.setText("Free");
                    buy_btn.setText("Get "+g.Game_name+" for Free");
                }
                else{

                    txt_price.setText("Price: "+g.Game_price+ "$");
                    buy_btn.setText("Buy "+g.Game_name);}


                txt_price.setFont(Font.font("MONOSPACED ",15));
                txt_price.setFill(Color.WHITE);
                this.getChildren().addAll(txt_price,buy_btn);
                buy_btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        UserDao ud=new UserDao();
                        System.out.println((ud.get_balance(u)-g.Game_price));
                        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
                        confirmationDialog.setTitle("Confirmation");
                        confirmationDialog.setHeaderText("Are you sure you want to buy this game ?");
                        if((ud.get_balance(u)-g.Game_price)>=0) {
                            confirmationDialog.showAndWait().ifPresent(response -> {
                                if (response == ButtonType.OK) {
                                    pd.insert(g.Game_id, u.User_id);
                                    ud.updatebalance(u, g.Game_price);

                                    Double b = ud.get_balance(u);
                                    System.out.println(b);
                                    cs.lb_balance.setText("Your balance : " + b + "$");

                                    p = new Popup();
                                    VBox vb = new VBox(5);
                                    Label lb_ins = new Label("Game added to Your Library");


                                    vb.setAlignment(Pos.CENTER);
                                    lb_ins.setFont(Font.font("MONOSPACED ", 15));
                                    lb_ins.setTextFill(Color.GREEN);


                                    vb.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(8), null)));

                                    vb.getChildren().addAll(lb_ins);
                                    vb.setPadding(new Insets(5, 10, 5, 10));

                                    p.getContent().addAll(vb);
                                    p.show(cs.s);
                                    int popupDuration = 2; // in seconds

                                    // Create a Timeline to hide the popup after the duration
                                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(popupDuration), event -> p.hide()));
                                    timeline.play();


                                    cs.btn_store.fire();
                                } else {

                                }
                            });

                        }


                            else{
                                p=new Popup();
                                VBox vb=new VBox(5);
                                Label lb_ins=new Label("insufficient funds");
                                Label lb_rec=new Label("Recharge your Balance please");

                                vb.setAlignment(Pos.CENTER);
                                lb_ins.setFont(Font.font("MONOSPACED ", 15));
                                lb_ins.setTextFill(Color.RED);
                                lb_rec.setFont(Font.font("MONOSPACED ", 15));
                                lb_rec.setTextFill(Color.RED);


                                vb.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(8),null)));

                                vb.getChildren().addAll(lb_ins,lb_rec);
                                vb.setPadding(new Insets(5,10,5,10));

                                p.getContent().addAll(vb);
                                p.show(cs.s);
                                int popupDuration = 2; // in seconds

                                // Create a Timeline to hide the popup after the duration
                                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(popupDuration), event -> p.hide()));
                                timeline.play();



                            }





                    }
                });


            }
            else if(owned){
                txt_owned=new Text();
             txt_owned.setText("Owned");

                txt_owned.setFont(Font.font("MONOSPACED ",15));
                txt_owned.setFill(Color.WHITE);
            this.getChildren().addAll(txt_owned);
            };






            this.setOnMouseEntered(e->{
                this.setBackground(new Background(new BackgroundFill(Paint.valueOf("#353839"),new CornerRadii(20),null)));
            });
            this.setOnMouseExited(e->{
                this.setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(20),null)));
            });




//tests
            System.out.println(u.User_id);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }



    }

}


