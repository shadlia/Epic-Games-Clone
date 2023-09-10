package com.example.mvcepic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class editgame extends VBox {
    TextField imgTextField;
    TextArea desTextField;
     TextField priceTextField;
    TextField nameTextField;
    Popup p;
    Game g;
    TableView tb;
    Stage s;
Popup p1;
    public ObservableList<Game> data= FXCollections.observableArrayList();
    editgame(Popup p,Game g,TableView tb,ObservableList<Game> data, Stage s){
        this.data=data;
        this.tb=tb;
        this.p=p;
        this.g=g;
        this.s=s;

        Label litle=new Label("Edit Game");
        litle.setFont(Font.font("MONOSPACED BOLD", 30));
        litle.setTextFill(Color.WHITE);

        Label nameLabel = new Label("Game name:");
        nameLabel.setFont(Font.font("MONOSPACED ", 15));
        nameLabel.setTextFill(Color.WHITE);

        nameTextField = new TextField();


        Label des = new Label("Game description:");
        des.setFont(Font.font("MONOSPACED ", 15));
        des.setTextFill(Color.WHITE);

        desTextField = new TextArea();
        desTextField.setMaxHeight(50);
        desTextField.setMaxWidth(300);
        desTextField.setWrapText(true);

        Label price = new Label("Game price:");
        price.setFont(Font.font("MONOSPACED ", 15));
        price.setTextFill(Color.WHITE);

        priceTextField = new TextField();

        Label img = new Label("Game image");
        img.setFont(Font.font("MONOSPACED ", 15));
        img.setTextFill(Color.WHITE);

        imgTextField = new TextField();
        Button save=new Button("Save Changes");
        save.setFont(Font.font("MONOSPACED BOLD",15));
        save.setBackground(new Background(new BackgroundFill(Color.SNOW,new CornerRadii(8),null)));
        save.setTextFill(Color.BLACK);


        Button cancel=new Button("Cancel");
        cancel.setFont(Font.font("MONOSPACED BOLD",15));
        cancel.setBackground(new Background(new BackgroundFill(Color.SNOW,new CornerRadii(8),null)));
        cancel.setTextFill(Color.BLACK);

        this.getChildren().addAll(litle,nameLabel,nameTextField,des,desTextField,price,priceTextField,img,imgTextField,save,cancel);
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(10),null)));
        this.setPadding(new Insets(20,50,20,50));
        this.setSpacing(20);
        load_fields();

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //edit database
               String name= nameTextField.getText();
                String des=desTextField.getText();
                String img= imgTextField.getText();
               Double price = Double.valueOf(priceTextField.getText());
                GamesDao gd=new GamesDao();
               int a= gd.edit_game(g,name,des,img,price);
               if(a>0){
                   //edit liste
                   for (Game game : data) {
                       if (g.getGame_id()==(game.getGame_id())) {
                           game.setGame_image(img);
                           game.setGame_desc(des);
                           game.setGame_price(price);
                           game.setGame_name(name);
                           break;
                       }
                   }
                    tb.refresh();

                   p1 = new Popup();
                   Label succ = new Label("Changes Saved");
                   succ.setFont(Font.font("MONOSPACED ", 15));
                   succ.setTextFill(Color.GREEN);
                   succ.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), null)));
                   succ.setPadding(new Insets(10));
                   p1.getContent().addAll(succ);
                   p1.show(s);

                   int popupDuration = 1; // in seconds

                   // Create a Timeline to hide the popup after the duration
                   Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(popupDuration), event ->{ p1.hide();p.hide();}));
                   timeline.play();


                   //refresh table
               }




            }
        });
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                p.hide();
            }
        });

    }
    public void load_fields(){
        nameTextField.setText(g.getGame_name());
        desTextField.setText(g.getGame_desc());
        imgTextField.setText(g.getGame_image());

        priceTextField.setText(String.valueOf((g.getGame_price())));

    }
}
