package org.example.wishywishy.repository;

import org.example.wishywishy.model.Wish;
import org.example.wishywishy.model.Wishlist;
import org.example.wishywishy.repository.util.ConnectionManager;
import org.example.wishywishy.model.Wish;
import org.example.wishywishy.repository.util.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class WishyRepository {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;

    public Wish updateWish(Wish updatedWish) {
        int rows = 0;
        String SQLUPDATE = "UPDATE WISH SET " +
                "WISHNAME = ?, " +
                "URL = ?, " +
                "WISHPRICE = ? " +
                "WHERE WISHID = ?;";
        Connection con = ConnectionManager.getConnection(url, user, password);
        try (PreparedStatement updateWishStmt = con.prepareStatement(SQLUPDATE)){
            updateWishStmt.setString(1, updatedWish.getWishName());
            updateWishStmt.setURL(2, updatedWish.getUrl());
            updateWishStmt.setDouble(3, updatedWish.getWishPrice());
            updateWishStmt.setInt(4,updatedWish.getWishID());
            rows = updateWishStmt.executeUpdate();
            System.out.println("Rows: " + rows);

    public void addWishList(Wishlist wishlist,String username){
        String SQL= "INSERT INTO WISHLIST(USERNAME,WISHLISTNAME) values(?,?)";
        Connection con = ConnectionManager.getConnection(url,user,password);
        try { PreparedStatement preparedStatement = con.prepareStatement(SQL);
                preparedStatement.setString(1,username);
                preparedStatement.setString(2,wishlist.getWishlistName());
                preparedStatement.executeUpdate();
            }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

        } catch(SQLException e){
            throw new RuntimeException();
        }
        if (rows == 1) {
            return updatedWish;
        }
        else return null;
    }

}
