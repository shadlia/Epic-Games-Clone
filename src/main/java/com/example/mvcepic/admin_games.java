package com.example.mvcepic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

public class admin_games extends BorderPane {
     Label comment;
    Stage s;
    String filePath;
    TableView tb;
    Button btn_games;
    public ObservableList<Game> data= FXCollections.observableArrayList();
    admin_games(Stage s,Button btn_games) {
        this.btn_games=btn_games;
        this.s=s;
        HBox form = new HBox(15);
        form.setPadding(new Insets(10, 10, 10, 10));

        Label lb_name = new Label("Game-name");
        lb_name.setFont(Font.font("MONOSPACED BOLD", 15));
        lb_name.setTextFill(Color.BLACK);

        TextField tf_name = new TextField();

        Label lb_des = new Label("Games_des");
        lb_des.setFont(Font.font("MONOSPACED BOLD", 15));
        lb_des.setTextFill(Color.BLACK);

        TextArea ta = new TextArea();
        ta.setPrefWidth(200);
        ta.setPrefHeight(50);
        ta.setWrapText(true);


        Label lb_price = new Label("Game-price");
        lb_price.setFont(Font.font("MONOSPACED BOLD", 15));
        lb_price.setTextFill(Color.BLACK);

        TextField tf_price = new TextField();

        Label lb_img = new Label("Game-img");
        lb_img.setFont(Font.font("MONOSPACED BOLD", 15));
        lb_img.setTextFill(Color.BLACK);

        TextField tf_img = new TextField();

        Button btn_add = new Button("Add game");
        btn_add.setFont(Font.font("MONOSPACED BOLD",15));
        btn_add.setBackground(new Background(new BackgroundFill(Color.SNOW,new CornerRadii(8),null)));
        btn_add.setTextFill(Color.BLACK);



        Image upload_icon = null;
        try {
            upload_icon = new Image(new FileInputStream("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic2\\mvcepic\\src\\img\\admin\\upoad_img.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //Setting the image view
        ImageView searchView = new ImageView(upload_icon);
        searchView.setFitWidth(20);
        searchView.setFitHeight(20);


        Button uploadButton = new Button("",searchView);

        uploadButton.setFont(Font.font("MONOSPACED BOLD",15));
        uploadButton.setBackground(new Background(new BackgroundFill(Color.SNOW,new CornerRadii(8),null)));
        uploadButton.setTextFill(Color.BLACK);

        //uploadButton.setGraphic(new FontAwesomeIcon(FontAwesomeIcon.UPLOAD));





            form.getChildren().addAll(lb_name, tf_name, lb_des, ta, lb_price, tf_price, lb_img, uploadButton, btn_add);


            //table view
        tb=new TableView();
        inittable();






        //footer

        HBox hb_fotter=new HBox(20);
        Label lb_title=new Label("Comment :");
        lb_title.setFont(Font.font("MONOSPACED BOLD", 15));
        lb_title.setTextFill(Color.BLACK);

        comment=new Label("");
        comment.setFont(Font.font("MONOSPACED BOLD", 15));
        comment.setTextFill(Color.RED);

        hb_fotter.getChildren().addAll(lb_title,comment);
        hb_fotter.setPadding(new Insets(20,3,20,3));

        this.setTop(form);
        this.setCenter(tb);
        this.setBottom(hb_fotter);

            //events
        //image upload
        uploadButton.setOnAction(e -> chooseImageFile(s));

        btn_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GamesDao gd=new GamesDao();
                String img=filePath;
                String game_name=tf_name.getText();
                String Game_desc=ta.getText();
                Double Game_price=Double.parseDouble(tf_price.getText());

                Game g=new Game(game_name,Game_desc,Game_price,img);
                int a=gd.insert(g);
                if(a>0){
                    data.add(g);
                    tb.refresh();
                    btn_games.fire();


                }


            }
        });
        }
    private void chooseImageFile(Stage primaryStage) {

        // Create a FileChooser to select the image file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        // If a file was selected, retrieve the file path or URL
        if (selectedFile != null) {
            filePath = selectedFile.getAbsolutePath();

            try {
                URL fileUrl = selectedFile.toURI().toURL();
            } catch (MalformedURLException e) {
            }
        }

    }
    public void inittable(){
        GamesDao sd=new GamesDao();
        ResultSet rs= sd.select("select * from Games");
        // COLUMNS : ResultSetMetaData : rs.getmedata
        try {
            ResultSetMetaData rsmd= rs.getMetaData();
            int nb_col=rsmd.getColumnCount();
            for(int i=0;i<nb_col;i++){
                TableColumn tc=new TableColumn<>(rsmd.getColumnName(i+1));
                tc.setCellValueFactory(new PropertyValueFactory<Game,Object>(rsmd.getColumnName(i+1)));
                tc.setMaxWidth(300);

                tb.getColumns().add(tc);
            }
            TableColumn<Game, Void> deleteColumn = new TableColumn<>("");



            deleteColumn.setCellFactory(param -> new TableCell<>() {
                Button deleteButton = new Button("Delete");


                @Override
                public void updateItem(Void item, boolean empty) {
                    deleteButton.setFont(Font.font("MONOSPACED BOLD",15));
                    deleteButton.setBackground(new Background(new BackgroundFill(Color.BEIGE,new CornerRadii(8),null)));
                    deleteButton.setTextFill(Color.RED);

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(deleteButton);
                    }
                    deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            Game g = getTableView().getItems().get(getIndex());
                            System.out.println(g.getGame_id()+"to delete");
                            GamesDao ud=new GamesDao();
                            int a= ud.deletebyid(g.getGame_id());
                            if(a>0){
                                data.remove(g);
                                System.out.println("game deleted");
                                tb.refresh();
                            }
                            else{
                                System.out.println("game doesnt exist");
                            }
                        }
                    });
                }

            });

            tb.getColumns().addAll(deleteColumn);

            //Modifie

            TableColumn<Game, Void> editcolum = new TableColumn<>("");



            editcolum.setCellFactory(param -> new TableCell<>() {
                Button edit_button = new Button("Edit");


                @Override
                public void updateItem(Void item, boolean empty) {
                    edit_button.setFont(Font.font("MONOSPACED BOLD",15));
                    edit_button.setBackground(new Background(new BackgroundFill(Color.BEIGE,new CornerRadii(8),null)));
                    edit_button.setTextFill(Color.GREEN);

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(edit_button);
                    }
                    edit_button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            Game g = getTableView().getItems().get(getIndex());
                            System.out.println(g.getGame_id());
                            Popup p=new Popup();
                            p.getContent().add(new editgame(p,g,tb,data,s));
                            p.show(s);

                        }
                    });



                }

            });

            tb.getColumns().addAll(editcolum);
            //Data FROM THE OBSERVABLE LIST
            tb.setItems(data);
            while(rs.next()){
                int game_id=rs.getInt(rsmd.getColumnName(1));
                String game_name=rs.getString(rsmd.getColumnName(2));
                String Game_desc=rs.getString(rsmd.getColumnName(3));
                Double Game_price=rs.getDouble(rsmd.getColumnName(4));
                String Game_image=rs.getString(rsmd.getColumnName(5));


                data.add(new Game(game_id,game_name,Game_desc,Game_price,Game_image));

            }}
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}


