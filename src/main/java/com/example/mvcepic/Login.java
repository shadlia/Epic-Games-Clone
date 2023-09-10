package com.example.mvcepic;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Login extends Application {


    private VBox root;
    private Image icon;
    private GridPane gp_forum;
    private TextField tf_username;
    private TextField tf_password;
    private Label lb_signup;
    private Button btn_valide;
    private Label lb_account;
    private HBox login_hbox;
    private Label champs_control;
    public ResultSet rs;
    Login l;


    @Override
    public void start(Stage stage) throws Exception {
        champs_control = new Label();
        champs_control.setFont(Font.font("MONOSPACED BOLD", 15));


        
        root = new VBox(60);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(40, 0, 0, 0));
        BackgroundImage backgroundImage = new BackgroundImage(new Image("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic2\\mvcepic\\src\\img\\background_login.JPG"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        root.setBackground(new Background(backgroundImage));

        //icon
        icon = new Image(new FileInputStream("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic\\src\\img\\epic_icon.JPG"));
        //Setting the image view
        ImageView iconView = new ImageView(icon);

        iconView.setFitHeight(90);
        iconView.setFitWidth(90);

        //forum
        gp_forum = new GridPane();
        lb_signup = new Label("Login :");
        lb_signup.setFont(Font.font("MONOSPACED BOLD", 20));
        lb_signup.setTextFill(Color.WHITE);

        tf_username = new TextField();
        tf_username.setPromptText("User Name");
        tf_username.setPrefWidth(250);
        tf_username.setPrefHeight(30);

        PasswordField tf_password = new PasswordField();
        tf_password.setPromptText("Password");
        tf_password.setPrefHeight(30);
        tf_password.setPrefWidth(250);

        Label lb_button = new Label("Log in");
        lb_button.setFont(Font.font("MONOSPACED BOLD", 15));

        btn_valide = new Button("", lb_button);
        btn_valide.setPrefWidth(100);
        btn_valide.setPrefHeight(30);
        btn_valide.setStyle("-fx-background-color:#c3c4c4, linear-gradient(#d6d6d6 50%, white 100%), radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%); -fx-background-radius: 30; -fx-background-insets: 0,1,1; -fx-text-fill: black; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
        lb_account = new Label("Create account ");
        lb_account.setFont(Font.font("MONOSPACED BOLD", 15));
        lb_account.setTextFill(Color.WHITE);

        login_hbox = new HBox(100);
        login_hbox.getChildren().addAll(lb_account, btn_valide);


        gp_forum.add(lb_signup, 0, 0);
        gp_forum.add(tf_username, 0, 1);
        gp_forum.add(tf_password, 0, 2);
        gp_forum.add(login_hbox, 0, 3);

        gp_forum.setAlignment(Pos.CENTER);
        gp_forum.setVgap(20);


        root.getChildren().addAll(iconView, gp_forum, champs_control);

        Scene scene = new Scene(root, 400, 500);
        stage.setResizable(false);
        stage.setTitle("Epic Games");
        stage.setScene(scene);

        stage.show();


        //Events
        btn_valide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = tf_username.getText();
                String password = tf_password.getText();
                if (username.equals("") | (password.equals(""))) {
                    champs_control.setText("Fill the Forum please");
                    champs_control.setTextFill(Color.RED);

                } else if (isValid(username, password)) {
                    User u = ConnectedUser(rs);
                    if (u.User_role.equals("client")) {
                        System.out.println(u.User_id);
                        stage.setScene(new Scene(new ClientSide(u,stage), 1320, 860));
                    } else if (u.User_role.equals("admin")) {
                        stage.setScene(new Scene(new AdminSide(stage), 1320, 860));
                    } else {
                        System.out.println("role invalide");
                    }


                } else {
                    champs_control.setText("Wrong infos");
                    champs_control.setTextFill(Color.RED);
                }

                /* User u=new User(12,"aa","bb","454","454","client");
                 */
            }


        });
        lb_account.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage s=new Stage();
                s.setScene(new Scene(new signup(s),400,500));
                s.show();
            }
        });

    }

    public boolean isValid(String username, String password) {
        UserDao ud=new UserDao();
        rs=ud.select(username,password);
        try {
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public User ConnectedUser(ResultSet rs){
        try {
            int id=rs.getInt(1);
            String name=rs.getString(2);
            String lastname=rs.getString(3);

            String usname=rs.getString(4);

            String pwd=rs.getString(5);
            String role=rs.getString(6);
            double balance=rs.getDouble(7);

            User u=new User(id,name,lastname,usname,pwd,role,balance);
            return u;



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public static void main(String[] args) {launch(args);}

}
