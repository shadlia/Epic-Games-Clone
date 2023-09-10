package com.example.mvcepic;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PurchasesDao {
    Connection con = null;
    Statement st = null;

    public PurchasesDao() {
        con = DB_connection.connect();
        if (con != null) {
            try {
                st = con.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //SHOW ALL GAMES
    public int insert(int Game_id, int User_id) {
        String req = "INSERT INTO `purchases`(`User_id`, `Game_id`) VALUES (?,?)";
        PreparedStatement ps = null; //ps 5ater paramteres


        try {
            ps = con.prepareStatement(req);

            ps.setInt(1, User_id);
            ps.setInt(2, Game_id);


            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }

    }

    boolean GameOwner(int User_id, int Game_id) {
        boolean owned;

        try {
            String sql = "SELECT * FROM purchases WHERE User_id = ? AND Game_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, User_id);
            ps.setInt(2, Game_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                owned = true;
            } else {
                owned = false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return owned;
    }

    public int gameownedid(int userid) { //n9asem il show all
        int Game_id = 0;
        try {
            String sql = "SELECT * FROM users WHERE User_username = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();
            Game_id = rs.getInt("Game_id");
            return Game_id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public ResultSet selectowned(int userid){

        try {
            String req="select Game_id,date from purchases where User_id = ? order by date";
            PreparedStatement ps= con.prepareStatement(req);

            ps.setInt(1,userid);
            return ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
