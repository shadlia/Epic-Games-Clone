package com.example.mvcepic;

import java.sql.*;

public class GamesDao {
    Connection con=null;
    Statement st=null;

    public GamesDao() {
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
    public ResultSet select(String req){ //n9asem il show all
        try {
            return st.executeQuery(req);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void showall(ResultSet rs){
        if(rs!=null){
            try{
                while(rs.next()) {
                    ; //CURSOR MIL 1
                    int Game_id = rs.getInt(1);
                    String Game_name = rs.getString(2);
                    String Game_desc = rs.getString(3);
                    double Game_price = rs.getDouble(4);
                    String Game_image=rs.getString(5);
                    Game g=new Game(Game_id,Game_name,Game_desc,Game_price,Game_image);
                    System.out.println(g.toString());
                }   } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }
public ResultSet selectbyid(int id_game) {
        ResultSet rs=null;
    try {
        String req = "select * from Games where Game_id=?";
        PreparedStatement ps = con.prepareStatement(req);
        ps.setInt(1, id_game);
        rs=ps.executeQuery();


    } catch (SQLException e) {

    }
    return rs;
}

    public int insert(Game g){
        String req="INSERT INTO `games` ( `Game_name`, `Game_desc`, `Game_price`, `Game_image`) VALUES ( ?, ?, ?, ?);";
        try {
            PreparedStatement ps=con.prepareStatement(req);

            ps.setString(1,g.getGame_name());
            ps.setString(2,g.getGame_desc());
            ps.setDouble(3,g.getGame_price());
            ps.setString(4,g.getGame_image());

            return  ps.executeUpdate();
        } catch (SQLException ex) {
            return 0;
        }
    }
    public int deletebyid(int id){
        String req="delete from games where Game_id = ?";
        try {
            PreparedStatement ps= con.prepareStatement(req);
            ps.setInt(1,id);
            int res=ps.executeUpdate();

            return res;

        } catch (SQLException e) {

            return 0;
        }
    }
    public int edit_game(Game g,String name,String des, String img, Double price){
        int res=0;
        String sql="UPDATE `games` SET `Game_name`=?,`Game_desc`=?,`Game_price`=?,`Game_image`=? WHERE Game_id=?";

        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,des);
            ps.setDouble(3,price);
            ps.setString(4,img);
            ps.setInt(5,g.getGame_id());
            res=ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return res;
    }
}

