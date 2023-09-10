package com.example.mvcepic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_connection {
    public static Connection connect(){
        String nom_driver = "com.mysql.jdbc.Driver";

        String url="jdbc:mysql://127.0.0.1/Epic_games";
        String user="root";
        String password="";
        Connection con=null;
        try {
            Class.forName(nom_driver); // telechargement driver
        }catch(ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        try{
            con = DriverManager.getConnection(url,user,password);
            System.out.println("connected");
        }catch (SQLException e){
            System.out.println("echec connection");
        }

        return con;

    }
}
