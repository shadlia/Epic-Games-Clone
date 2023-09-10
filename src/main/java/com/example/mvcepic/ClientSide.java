package com.example.mvcepic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientSide extends BorderPane {

    private final Button btn_home;
    public Label lb_balance;
    public HBox hb_balance;
    public  Button btn_add_balance;
    public  Label lb_title;
    public VBox menu;

    public HBox icon_hbox;

    public Button btn_library;
    public Button btn_store;
    public HBox hbox_top;

    public Button btn_back;

    public BorderPane bp_centre;
    public Button btn_next;
    public HBox hbox_buttons;
    public Quick_game hb_game1;

    public VBox vb_quickgame;
    public HBox hbox_search;
    public TextField tf_search;
    public Button btn_search;
    public VBox vbox_profile;
    public Label lb_profile;
    public Button  btn_settings;
    public VBox vbox_chat;
    public GridPane gp_store;
    public User user;
    public GridPane gp_lib;
    public Popup p;
    Stage s;
public VBox hb_account;
    private Button btn_save;

    ClientSide(User user,Stage s){
        this.user=user;
        this.s=s;


        Background bg=new Background(new BackgroundFill(Color.BLACK,null,null));
        this.setBackground(bg);
       // root.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(20),new BorderWidths(4),new Insets(10))));
        //Menu

        menu=new VBox(20);
        menu.setAlignment(Pos.TOP_CENTER);
        menu.setPrefHeight(200);
        menu.setPrefWidth(220);
        menu.setBorder(new Border(new BorderStroke(Paint.valueOf("#696969"),BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5),new Insets(10))));



        //top icon

        //Creating an image
         Image icon = null;
         try {
             icon = new Image(new FileInputStream("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic\\src\\img\\epic_icon.JPG"));
         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         }

         //Setting the image view
        ImageView iconView = new ImageView(icon);
        iconView.setFitHeight(90);
        iconView.setFitWidth(90);

        icon_hbox=new HBox();
        icon_hbox.setAlignment(Pos.TOP_CENTER);
        icon_hbox.setPadding(new Insets(10,0,50,0));
        
        icon_hbox.getChildren().add(iconView);


        //store button
        //icon:
         Image store_icon = null;
         try {
             store_icon = new Image(new FileInputStream("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic\\src\\img\\shop_icon.png"));
         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         }
         //Setting the image view
        ImageView storeView = new ImageView(store_icon);
        storeView.setFitWidth(35);
        storeView.setFitHeight(35);


        //button
        btn_store = new Button("    Store", storeView);
        btn_store.setFont(Font.font("MONOSPACED BOLD",20));
        btn_store.setTextFill(Color.WHITE);
        btn_store.setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(8),null)));
        btn_store.setPrefWidth(180);
        btn_store.setPrefHeight(60);
        //library button
        //Prepare icon:
         Image library_icon = null;
         try {
             library_icon = new Image(new FileInputStream("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic\\src\\img\\library.png"));
         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         }
         //Setting the image view
        ImageView librayView = new ImageView(library_icon);
        librayView.setFitWidth(35);
        librayView.setFitHeight(35);
        //button
        btn_library = new Button("    Library", librayView);
        btn_library.setFont(Font.font("MONOSPACED BOLD",20));
        btn_library.setTextFill(Color.WHITE);
        btn_library.setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(8),null)));
        btn_library.setPrefWidth(180);
        btn_library.setPrefHeight(60);
        //History button
        //Prepare icon:


         Image settings_icon = null;
         try {
             settings_icon = new Image(new FileInputStream("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic2\\mvcepic\\src\\img\\account.png"));
         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         }
         //Setting the image view
        ImageView settingsView = new ImageView(settings_icon);
        settingsView.setFitWidth(35);
        settingsView.setFitHeight(35);
        //button


        btn_settings = new Button("    Settings", settingsView);
        btn_settings.setFont(Font.font("MONOSPACED BOLD",20));
        btn_settings.setTextFill(Color.WHITE);
        btn_settings.setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(8),null)));
        btn_settings.setPrefWidth(180);
        btn_settings.setPrefHeight(60);

        //Home
        //Prepare icon:


        Image home_icon = null;
        try {
            home_icon = new Image(new FileInputStream("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic2\\mvcepic\\src\\img\\home.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //Setting the image view
        ImageView homeView = new ImageView(home_icon);
        homeView.setFitWidth(30);
        homeView.setFitHeight(30);
        //button


        btn_home = new Button("    Home", homeView);
        btn_home.setFont(Font.font("MONOSPACED BOLD",20));
        btn_home.setTextFill(Color.WHITE);
        btn_home.setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(8),null)));
        btn_home.setPrefWidth(180);
        btn_home.setPrefHeight(60);
        
        //settings


        //QUICK LUNCH
        Label lb_lunch=new Label("Quick lunch");
        lb_lunch.setFont(Font.font("MONOSPACED BOLD",15));
        lb_lunch.setTextFill(Color.WHITE);
        lb_lunch.setPadding(new Insets(20));
        Pane pn_lunch=new Pane(lb_lunch);

        //GAMEs
        vb_quickgame=new VBox(20);
        vb_quickgame.setAlignment(Pos.CENTER);

        hb_game1=new Quick_game();

        vb_quickgame.getChildren().addAll(hb_game1);
        vb_quickgame.setPadding(new Insets(0,10,0,10));


        hb_balance=new HBox(20);

        lb_balance=new Label("Your balance : "+user.getUser_balance()+"$");

        lb_balance.setFont(Font.font("MONOSPACED ",15));
        lb_balance.setTextFill(Color.WHITE);



        hb_balance.setPadding(new Insets(0,20,0,20));
        hb_balance.getChildren().addAll(lb_balance);
        btn_add_balance=new Button("Recharge Balance");
        btn_add_balance.setFont(Font.font("MONOSPACED BOLD",15));
        btn_add_balance.setBackground(new Background(new BackgroundFill(Color.SNOW,new CornerRadii(8),null)));
        btn_add_balance.setTextFill(Color.BLACK);

        menu.getChildren().addAll(icon_hbox,btn_home,btn_store,btn_library, btn_settings,pn_lunch,vb_quickgame,hb_balance,btn_add_balance);
      //
        bp_centre=new BorderPane();
        //history buttons

        //back
         Image back_icon = null;
         try {
             back_icon = new Image(new FileInputStream("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic\\src\\img\\back_icon.png"));
         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         }
         //Setting the image view
        ImageView backView = new ImageView(back_icon);
        backView.setFitWidth(30);
        backView.setFitHeight(30);
        btn_back=new Button("",backView);
        btn_back.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));


        //next
         Image next_icon = null;
         try {
             next_icon = new Image(new FileInputStream("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic\\src\\img\\next_icon2.png"));
         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         }
         //Setting the image view
        ImageView nextView = new ImageView(next_icon);
        nextView.setFitWidth(30);
        nextView.setFitHeight(30);
        btn_next=new Button("",nextView);
        btn_next.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));

        hbox_buttons=new HBox(1);
       // hbox_buttons.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(20),new BorderWidths(4))));
        hbox_buttons.setPadding(new Insets(10,30,10,30));
        hbox_buttons.setAlignment(Pos.CENTER);

        hbox_buttons.getChildren().addAll(btn_back,btn_next);


        //search hbox
        hbox_search=new HBox(5);

         Image search_icon = null;
         try {
             search_icon = new Image(new FileInputStream("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic\\src\\img\\search_icon.png"));
         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         }
         //Setting the image view
        ImageView searchView = new ImageView(search_icon);
        searchView.setFitWidth(30);
        searchView.setFitHeight(30);

        btn_search=new Button("",searchView);
        btn_search.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));


        tf_search=new TextField();
        tf_search.setPromptText("Search");
        tf_search.setMinWidth(200);
        tf_search.setMinHeight(30);

        hbox_search.getChildren().addAll(tf_search,btn_search);
        hbox_search.setAlignment(Pos.CENTER);





        hbox_top=new HBox();
        hbox_top.setBorder(new Border(new BorderStroke(Paint.valueOf("#696969"),BorderStrokeStyle.SOLID,new CornerRadii(20),new BorderWidths(4),new Insets(10))));
        hbox_top.setPadding(new Insets(20,0,20,0));

        //right side

        vbox_profile=new VBox(5);


         Image profile_icon = null;
         try {
             profile_icon = new Image(new FileInputStream("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic\\src\\img\\profile_icon.png"));
         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         }
         //Setting the image view
        ImageView profileView = new ImageView(profile_icon);
        profileView.setFitWidth(30);
        profileView.setFitHeight(30);


        MenuItem menuItem1 = new MenuItem("Logout");
        UserDao ud=new UserDao();
        MenuButton menuButton = new MenuButton("Account");
        menuButton.getItems().addAll(menuItem1);

        menuButton.setFont(Font.font("MONOSPACED BOLD",15));
        menuButton.setBackground(new Background(new BackgroundFill(Color.SNOW,new CornerRadii(8),null)));
        menuButton.setTextFill(Color.BLACK);




        lb_profile=new Label(user.getUser_username().toString());
        lb_profile.setFont(Font.font("MONOSPACED BOLD",15));
        lb_profile.setTextFill(Color.WHITE);

        vbox_profile.getChildren().addAll(profileView,menuButton);
        vbox_profile.setAlignment(Pos.CENTER);
        vbox_profile.setPadding(new Insets(0,0,0,400));
        hbox_top.setAlignment(Pos.CENTER);



        lb_title=new Label("Welcome to Epic Games");
        lb_title.setFont(Font.font("MONOSPACED BOLD",30));
        lb_title.setPadding(new Insets(10,0,10,180));
        lb_title.setTextFill(Color.WHITE);


        //Store Games Display
        ScrollPane scroll = new ScrollPane();
        ///scroll.setStyle( "-fx-background-color: #FFFFFF");
        scroll.setStyle("-fx-background: rgb(0,0,0);\n -fx-background-color: rgb(0,0,0)");
        scroll.setFitToWidth(true);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);




        //Chat
        ScrollPane sp_chat=new ScrollPane();

        vbox_chat=new VBox();

        Label lb_test=new Label("HI USER WELCME BACK");
        vbox_chat.getChildren().addAll(lb_test);
        vbox_chat.setPadding(new Insets(0,80,1000,80));
        sp_chat.setContent(vbox_chat);
        sp_chat.setBorder(new Border(new BorderStroke(Paint.valueOf("#696969"), BorderStrokeStyle.SOLID,null,new BorderWidths(4))));
        sp_chat.setStyle("-fx-background: rgb(0,0,0);\n -fx-background-color: rgb(0,0,0)");
        sp_chat.setFitToWidth(true);
        //sp_chat.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp_chat.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setContent(new acceuil());






    


        hbox_top.getChildren().addAll(lb_title,vbox_profile);
        bp_centre.setTop(hbox_top);
        bp_centre.setCenter(scroll);
        bp_centre.setRight(sp_chat);

        this.setCenter(bp_centre);
        this.setLeft(menu);




        //buttons effects

        btn_store.addEventHandler(MouseEvent.MOUSE_ENTERED,new ButtonEffects(this));
        btn_store.addEventHandler(MouseEvent.MOUSE_EXITED,new ButtonEffects(this));
        btn_store.addEventHandler(MouseEvent.MOUSE_CLICKED,new ButtonEffects(this));

        btn_library.addEventHandler(MouseEvent.MOUSE_ENTERED,new ButtonEffects(this));
        btn_library.addEventHandler(MouseEvent.MOUSE_EXITED,new ButtonEffects(this));
        btn_library.addEventHandler(MouseEvent.MOUSE_CLICKED,new ButtonEffects(this));



        btn_settings.addEventHandler(MouseEvent.MOUSE_ENTERED,new ButtonEffects(this));
        btn_settings.addEventHandler(MouseEvent.MOUSE_EXITED,new ButtonEffects(this));
        btn_settings.addEventHandler(MouseEvent.MOUSE_CLICKED,new ButtonEffects(this));

        btn_next.addEventHandler(MouseEvent.MOUSE_ENTERED,new ButtonEffects(this));
        btn_next.addEventHandler(MouseEvent.MOUSE_EXITED,new ButtonEffects(this));
        btn_next.addEventHandler(MouseEvent.MOUSE_CLICKED,new ButtonEffects(this));

        btn_back.addEventHandler(MouseEvent.MOUSE_ENTERED,new ButtonEffects(this));
        btn_back.addEventHandler(MouseEvent.MOUSE_EXITED,new ButtonEffects(this));
        btn_back.addEventHandler(MouseEvent.MOUSE_CLICKED,new ButtonEffects(this));

        btn_home.addEventHandler(MouseEvent.MOUSE_ENTERED,new ButtonEffects(this));
        btn_home.addEventHandler(MouseEvent.MOUSE_EXITED,new ButtonEffects(this));
        btn_home.addEventHandler(MouseEvent.MOUSE_CLICKED,new ButtonEffects(this));




        hb_game1.addEventHandler(MouseEvent.MOUSE_ENTERED,new QGEffects(this));
        hb_game1.addEventHandler(MouseEvent.MOUSE_EXITED,new QGEffects(this));
        hb_game1.addEventHandler(MouseEvent.MOUSE_CLICKED,new QGEffects(this));




        

        btn_search.addEventHandler(MouseEvent.MOUSE_ENTERED,new ButtonEffects(this));
        btn_search.addEventHandler(MouseEvent.MOUSE_EXITED,new ButtonEffects(this));
        btn_search.addEventHandler(MouseEvent.MOUSE_CLICKED,new ButtonEffects(this));
        // Events
        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                s.hide();
                Login l=new Login();
                try {
                    l.start(s);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        //disconnect

        //store
        btn_store.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lb_title.setText("Our Store");
                gp_store =PrepareStore();
                gp_store.setHgap(25);
                gp_store.setVgap(20);
                gp_store.setPrefWidth(800);
                scroll.setContent(gp_store);
            }
        });


        System.out.println(user.toString());
        btn_library.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lb_title.setText("Your Games");
                gp_lib =PrepareLibrary();
                gp_lib.setHgap(25);
                gp_lib.setVgap(20);
                gp_lib.setPrefWidth(800);
                scroll.setContent(gp_lib);
            }
        });

        btn_add_balance.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                 p = new Popup();

                 Recharge_balance rb=new Recharge_balance(user,p,lb_balance);
                 p.getContent().addAll(rb);
                 p.show(s);


            }
        });
        btn_settings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lb_title.setText("Settings ");
                hb_account =prepareAccountedit();

                hb_account.setPrefWidth(800);
                scroll.setContent(hb_account);

            }
        });
        btn_home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lb_title.setText("Welcome to Epic Games ");
                scroll.setContent(new acceuil());

            }
        });








    }
    public GridPane PrepareStore(){
        GridPane gp=new GridPane();
        GamesDao gd=new GamesDao();
        int c=0;
        int r=0;

        String req="select * from games";
        ResultSet rs= gd.select(req);
        if(rs!=null){
            try{
                while(rs.next()) {
                    ; //CURSOR MIL 1
                    int Game_id = rs.getInt(1);
                    String Game_name = rs.getString(2);
                    String Game_desc = rs.getString(3);
                    double Game_price = rs.getDouble(4);
                    String Game_image=rs.getString(5);
                    //String Game_url="C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic2\\mvcepic\\src\\img\\games"+Game_image;

                    Game g=new Game(Game_id,Game_name,Game_desc,Game_price,Game_image);
                    GameCard gc1=new GameCard(g,user,this);
                  if(c==3){
                        c=0;
                        r++;
                    }
                    gp.add(gc1,c++,r);



                }   } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }


        return gp;

    }
    public GridPane PrepareLibrary(){

        GridPane gp=new GridPane();
        PurchasesDao pd=new PurchasesDao();
       ResultSet rs=pd.selectowned(user.User_id);
        int c=0;
        int r=0;
        if(rs!=null){
            try{
                while(rs.next()) {
                int game_id=rs.getInt(1);
                Date date=rs.getDate(2);

                System.out.println(game_id+" "+date);
                GamesDao gd=new GamesDao();

                ResultSet rs1=gd.selectbyid(game_id);
                 int Game_id =0 ;
                 String Game_name=null ;
                 String Game_desc=null ;
                 double Game_price =0;
                 String Game_image=null;
                while(rs1.next()){
                     Game_id = rs1.getInt(1);
                    Game_name = rs1.getString(2);
                   Game_desc = rs1.getString(3);
                    Game_price = rs1.getDouble(4);
                     Game_image=rs1.getString(5);

                }
                Purchases p=new Purchases(0,Game_id, user.User_id,date.toString(),Game_name,Game_desc,Game_image);
                GameCardLib gc=new GameCardLib(p);
                    System.out.println(p.toString());
                    if(c==3){
                        c=0;
                        r++;
                    }
                    gp.add(gc,c++,r);





                }   } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }


        return gp;







    }
    public VBox prepareAccountedit(){
        VBox root=new VBox();
/*****************name******************/
        VBox vb_name=new VBox(10);
        HBox hb_name=new HBox(10);
        Button btn_name=new Button("Change");

        Label nameLabel = new Label("Name:");
        nameLabel.setFont(Font.font("MONOSPACED ", 15));
        nameLabel.setTextFill(Color.WHITE);
        TextField nameTextField = new TextField();
        nameTextField.setPrefWidth(250);

        hb_name.getChildren().addAll(nameTextField,btn_name);
        vb_name.getChildren().addAll(nameLabel,hb_name);
   /*****************lastname******************/

        VBox vb_lastname=new VBox(10);
        HBox hb_lastname=new HBox(10);
        Button btn_lastname=new Button("Change");

        Label lastnameLabel = new Label("Lastname:");
        lastnameLabel.setFont(Font.font("MONOSPACED ", 15));
        lastnameLabel.setTextFill(Color.WHITE);
        TextField lastnameTextField = new TextField();
        lastnameTextField.setPrefWidth(250);

        hb_lastname.getChildren().addAll(lastnameTextField,btn_lastname);
        vb_lastname.getChildren().addAll(lastnameLabel,hb_lastname);




        /*****************username******************/
        VBox vb_username=new VBox(10);
        HBox hb_username=new HBox(10);
        Button btn_username=new Button("Change");

        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(Font.font("MONOSPACED ", 15));
        usernameLabel.setTextFill(Color.WHITE);
        TextField usernameTextField = new TextField();
        usernameTextField.setPrefWidth(250);

        hb_username.getChildren().addAll(usernameTextField,btn_username);
        vb_username.getChildren().addAll(usernameLabel,hb_username);



        /*****************paswword******************/
        VBox vb_password=new VBox(10);
        HBox hb_password=new HBox(10);
        Button btn_password=new Button("Change");

        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("MONOSPACED ", 15));
        passwordLabel.setTextFill(Color.WHITE);
        TextField passwordTextField = new TextField();
        passwordTextField.setPrefWidth(250);

        hb_password.getChildren().addAll(passwordTextField,btn_password);
        vb_password.getChildren().addAll(passwordLabel,hb_password);


        Label lb_info=new Label("Edit your personal informations");
        lb_info.setFont(Font.font("MONOSPACED ", 20));
        lb_info.setTextFill(Color.WHITE);



        GridPane gp=new GridPane();
        gp.add(lb_info,0,0);
        gp.add(vb_name,0,1);
        gp.add(vb_lastname,0,2);
        gp.add(vb_username,0,3);
        gp.add(vb_password,0,4);

        gp.setPadding(new Insets(100));
        gp.setHgap(50);
        gp.setVgap(30);


        gp.setPadding(new Insets(50,50,50,50));
        
         btn_save= new Button("Save Changes");
         btn_save.setFont(new Font("MONOSPACED",15));
        btn_save.setPrefWidth(150);
        btn_save.setPrefHeight(50);

        root.getChildren().addAll(gp);
        root.setAlignment(Pos.CENTER);
        //load infos
        UserDao us=new UserDao();

        lastnameTextField.setText(us.get_lastname(user));
        usernameTextField.setText(us.get_username(user));
        nameTextField.setText(us.get_name(user));



        p = new Popup();
        Label succ=new Label("Change Saved");
        succ.setFont(Font.font("MONOSPACED ", 20));
        succ.setTextFill(Color.GREEN);
        succ.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(20),null)));
        succ.setPadding(new Insets(20));
        p.getContent().addAll(succ);



        int popupDuration = 2; // in seconds

        // Create a Timeline to hide the popup after the duration


        btn_name.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                UserDao us=new UserDao();
                us.setname(nameTextField.getText(),user);
                p.show(s);
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(popupDuration), event -> p.hide()));
                timeline.play();

            }
        });
        btn_lastname.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                UserDao us=new UserDao();
                us.setlastname(lastnameTextField.getText(),user);
                p.show(s);
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(popupDuration), event -> p.hide()));
                timeline.play();
            }
        });
        btn_username.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                UserDao us=new UserDao();
                us.setusername(usernameTextField.getText(),user);
                p.show(s);
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(popupDuration), event -> p.hide()));
                timeline.play();
            }
        });
        btn_password.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                UserDao us=new UserDao();
                us.setpassword(passwordTextField.getText(),user);
                p.show(s);
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(popupDuration), event -> p.hide()));
                timeline.play();
            }
        });

        return root;
    }





}