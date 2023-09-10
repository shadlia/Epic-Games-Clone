package com.example.mvcepic;
import com.example.mvcepic.DB_connection;

import java.sql.*;

public class UserDao {
    Connection con=null;
    Statement st=null;
    Double balance;

    public UserDao() {
        con= DB_connection.connect();
        if(con!=null){
            try {
                st=con.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //SHOW ALL GAMES

    public ResultSet select2(String req){ //n9asem il show all
        try {
            return st.executeQuery(req);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet select(String User_username,String User_password	){ //n9asem il show all
        try {
            String sql = "SELECT * FROM users WHERE User_username = ? AND User_password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, User_username);
            ps.setString(2, User_password	);
            return ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean user_exit_by_id(int User_id){
        boolean exist;
        String sql = "SELECT * FROM users WHERE User_id = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,User_id);
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                exist=true;
            }
            else exist=false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exist;
    }
    public void add(String name,String lastname,String username,String password){
        String sql = "INSERT INTO `users`(`User_name`, `User_lastname`, `User_username`, `User_password`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,lastname);
            ps.setString(3,username);
            ps.setString(4,password);

            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void Recharge_balance(User u,double toadd){
       String sql=" UPDATE `users` SET `User_balance`= ? WHERE User_id=?";
       UserDao ud=new UserDao();
       double old_balance=ud.get_balance(u);
       double new_balance=old_balance+toadd;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDouble(1,new_balance);
            ps.setInt(2,u.getUser_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public Double get_balance(User u){
        balance=0.0;
        String req="SELECT User_balance FROM users WHERE User_id = ?";
        try {
            PreparedStatement ps=con.prepareStatement(req);
            ps.setInt(1,u.User_id);
           ResultSet rs= ps.executeQuery();
           while(rs.next()) {
               balance = rs.getDouble(1);
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return  balance;
    }

    public String get_username(User u){
        String username=null;
        String req="SELECT User_username FROM users WHERE User_id = ?";
        try {
            PreparedStatement ps=con.prepareStatement(req);
            ps.setInt(1,u.User_id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()) {
                username = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return  username;
    }
    public String get_name(User u){
        String name=null;
        String req="SELECT User_name FROM users WHERE User_id = ?";
        try {
            PreparedStatement ps=con.prepareStatement(req);
            ps.setInt(1,u.User_id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return  name;
    }
    public String get_lastname(User u){
        String lastname=null;
        String req="SELECT User_lastname FROM users WHERE User_id = ?";
        try {
            PreparedStatement ps=con.prepareStatement(req);
            ps.setInt(1,u.User_id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()) {
                lastname = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return  lastname;
    }
    public String get_password(User u){
        String pw=null;
        String req="SELECT User_password FROM users WHERE User_id = ?";
        try {
            PreparedStatement ps=con.prepareStatement(req);
            ps.setInt(1,u.User_id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()) {
                pw = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return  pw;
    }
    public void setname(String name,User u){

        String req="UPDATE `users` SET `User_name`= ? WHERE User_id=?";
        try {
            PreparedStatement ps=con.prepareStatement(req);
            ps.setString(1,name);
            ps.setInt(2,u.User_id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void setlastname(String lastname,User u){

        String req="UPDATE `users` SET `User_lastname`= ? WHERE User_id=?";
        try {
            PreparedStatement ps=con.prepareStatement(req);
            ps.setString(1,lastname);
            ps.setInt(2,u.User_id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void setusername(String username,User u){

        String req="UPDATE `users` SET `User_username`= ? WHERE User_id=?";
        try {
            PreparedStatement ps=con.prepareStatement(req);
            ps.setString(1,username);
            ps.setInt(2,u.User_id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void setpassword(String password,User u){

        String req="UPDATE `users` SET `User_name`= ? WHERE User_id=?";
        try {
            PreparedStatement ps=con.prepareStatement(req);
            ps.setString(1,password);
            ps.setInt(2,u.User_id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void updatebalance(User u,Double price){
        UserDao ud=new UserDao();
        String sql=" UPDATE `users` SET `User_balance`= ? WHERE User_id=?";
        double old_balance= ud.get_balance(u);
        double new_balance=old_balance-price;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDouble(1,new_balance);
            ps.setInt(2,u.getUser_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateuser(User u,String name,String username,String lastname,String password){
        String sql="UPDATE `users` SET `User_name`=?,`User_lastname`=?,`User_username`=?,`User_password`=? WHERE User_id=?";

        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(1,lastname);
            ps.setString(1,username);
            ps.setString(1,password);
            ps.setInt(1,u.getUser_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int deletebyid(int id){
        String req="delete from users where User_id = ?";
        try {
            PreparedStatement ps= con.prepareStatement(req);
            ps.setInt(1,id);
            int res=ps.executeUpdate();

            return res;

        } catch (SQLException e) {

            return 0;
        }
    }
    }


