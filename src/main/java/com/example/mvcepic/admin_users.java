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
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class admin_users extends BorderPane {
    Stage s;
    String filePath;
    TableView tb;
    Label comment;
    Button deleteButton;
    public ObservableList<User> data= FXCollections.observableArrayList();

    admin_users(Stage s) {
        this.s = s;
        HBox form = new HBox(15);
        form.setPadding(new Insets(10, 10, 10, 20));
        Label lb=new Label("liste of users : ");
        lb.setFont(Font.font("MONOSPACED BOLD", 20));
        lb.setTextFill(Color.BLACK);




        form.getChildren().addAll(lb);


        //table view
        tb = new TableView();
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
    }
    public void inittable(){
        UserDao sd=new UserDao();
        ResultSet rs= sd.select2("select * from users where User_role='client'");
        // COLUMNS : ResultSetMetaData : rs.getmedata
        try {
            ResultSetMetaData rsmd= rs.getMetaData();
            int nb_col=rsmd.getColumnCount();
            for(int i=0;i<nb_col;i++){
                TableColumn tc=new TableColumn<>(rsmd.getColumnName(i+1));
                tc.setCellValueFactory(new PropertyValueFactory<User,Object>(rsmd.getColumnName(i+1)));

                tb.getColumns().addAll(tc);

            }

            TableColumn<User, Void> deleteColumn = new TableColumn<>("");



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
                            User person = getTableView().getItems().get(getIndex());
                            System.out.println(person.getUser_id()+"to delete");
                            UserDao ud=new UserDao();
                           int a= ud.deletebyid(person.getUser_id());
                            if(a>0){
                                data.remove(person);
                                System.out.println("user deleted");
                                tb.refresh();
                            }
                            else{
                                System.out.println("user doesnt exist");
                            }

                        }
                    });
                }

            });

            tb.getColumns().addAll(deleteColumn);

            //Modifie

            TableColumn<User, Void> editcolum = new TableColumn<>("");



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
                            User person = getTableView().getItems().get(getIndex());
                            System.out.println(person.toString()+"to edit");
                        }
                    });
                }

            });

            tb.getColumns().addAll(editcolum);


            //Data FROM THE OBSERVABLE LIST
            tb.setItems(data);
            while(rs.next()){
                int user_id=rs.getInt(rsmd.getColumnName(1));
                String username=rs.getString(rsmd.getColumnName(2));
                String userlastname=rs.getString(rsmd.getColumnName(3));
                String user_username=rs.getString(rsmd.getColumnName(4));
                String password=rs.getString(rsmd.getColumnName(5));
                String role=rs.getString(rsmd.getColumnName(6));
                Double balance=rs.getDouble(rsmd.getColumnName(7));


                data.add(new User(user_id,username,userlastname,user_username,password,role,balance));

            }}
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}