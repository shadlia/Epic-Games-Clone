package com.example.mvcepic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class signup extends VBox {
     GridPane gp;
    Stage s;
    Popup p;
    signup(Stage s) {
        this.s=s;



        Label litle=new Label("Sign Up");
        litle.setFont(Font.font("MONOSPACED BOLD", 35));
        litle.setTextFill(Color.WHITE);

        Label nameLabel = new Label("Name:");
        nameLabel.setFont(Font.font("MONOSPACED ", 20));
        nameLabel.setTextFill(Color.WHITE);

        TextField nameTextField = new TextField();


        Label lastnameLabel = new Label("Last name:");
        lastnameLabel.setFont(Font.font("MONOSPACED ", 20));
        lastnameLabel.setTextFill(Color.WHITE);

        TextField lastnameTextField = new TextField();

        Label usernameLabel = new Label("UserName:");
        usernameLabel.setFont(Font.font("MONOSPACED ", 20));
        usernameLabel.setTextFill(Color.WHITE);

        TextField usernameTextField = new TextField();

        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("MONOSPACED ", 20));
        passwordLabel.setTextFill(Color.WHITE);

        PasswordField passwordField = new PasswordField();

        gp=new GridPane();
        gp.setVgap(30);
        gp.setHgap(20);
        gp.add(nameLabel,0,0);
        gp.add(nameTextField,1,0);

        gp.add(lastnameLabel,0,1);
        gp.add(lastnameTextField,1,1);

        gp.add(usernameLabel,0,2);
        gp.add(usernameTextField,1,2);

        gp.add(passwordLabel,0,3);
        gp.add(passwordField,1,3);

        gp.setAlignment(Pos.CENTER);

        HBox btns=new HBox(100);
        Button btn=new Button("Create");
        Button btn_clear=new Button("Clear");

        btns.getChildren().addAll(btn_clear,btn);
        btns.setAlignment(Pos.BOTTOM_CENTER);



        BackgroundImage backgroundImage = new BackgroundImage(new Image("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic2\\mvcepic\\src\\img\\background_login.JPG"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        this.setBackground(new Background(backgroundImage));
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(30,0,30,0));
        this.setSpacing(50);


        this.getChildren().addAll(litle,gp,btns);


        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                UserDao us=new UserDao();
                String name=nameTextField.getText();
                String last_name=lastnameTextField.getText();
                String username=usernameTextField.getText();
                String password=passwordField.getText();
                if(nameTextField.getText().isEmpty()||lastnameTextField.getText().isEmpty()||usernameTextField.getText().isEmpty()||passwordField.getText().isEmpty()) {
                    p = new Popup();
                    Label succ = new Label("fill all your information please");
                    succ.setFont(Font.font("MONOSPACED ", 15));
                    succ.setTextFill(Color.RED);
                    succ.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), null)));
                    succ.setPadding(new Insets(10));
                    p.getContent().addAll(succ);
                    p.show(s);

                    int popupDuration = 2; // in seconds

                    // Create a Timeline to hide the popup after the duration
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(popupDuration), event -> p.hide()));
                    timeline.play();


                }
                else {
                    us.add(name, last_name, username, password);

                    p = new Popup();
                    Label succ = new Label("Account created");
                    succ.setFont(Font.font("MONOSPACED ", 15));
                    succ.setTextFill(Color.GREEN);
                    succ.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), null)));
                    succ.setPadding(new Insets(10));
                    p.getContent().addAll(succ);
                    p.show(s);

                    int popupDuration = 2; // in seconds

                    // Create a Timeline to hide the popup after the duration
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(popupDuration), event -> s.hide()));
                    timeline.play();
                }




            }
        });
        btn_clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lastnameTextField.clear();
                nameTextField.clear();
                passwordField.clear();
                usernameTextField.clear();
                nameTextField.clear();
            }
        });
    }
}
