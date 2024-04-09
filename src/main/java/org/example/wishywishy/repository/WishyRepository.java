package org.example.wishywishy.repository;

import org.example.wishywishy.model.Wish;
import org.example.wishywishy.model.Wishlist;

import org.example.wishywishy.repository.util.ConnectionManager;
import org.example.wishywishy.model.Wish;
import org.example.wishywishy.repository.util.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        try (PreparedStatement updateWishStmt = con.prepareStatement(SQLUPDATE)) {
            updateWishStmt.setString(1, updatedWish.getWishName());
            updateWishStmt.setURL(2, updatedWish.getUrl());
            updateWishStmt.setDouble(3, updatedWish.getWishPrice());
            updateWishStmt.setInt(4, updatedWish.getWishID());
            rows = updateWishStmt.executeUpdate();
            System.out.println("Rows: " + rows);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        if (rows == 1) {
            return updatedWish;
        } else return null;
    }

    public void deleteWish(int wishId) throws SQLException {
        String sqlDeleteWish = "DELETE FROM wish WHERE wishID = ?;";

        Connection connection = ConnectionManager.getConnection(url, user, password);


        try (PreparedStatement pstmtWish = connection.prepareStatement(sqlDeleteWish)) {
            pstmtWish.setInt(1, wishId);
            pstmtWish.executeUpdate();
        }
    }

    public void addWishList(Wishlist wishlist, String username) {
        String SQL = "INSERT INTO WISHLIST(USERNAME,WISHLISTNAME) values(?,?)";
        Connection con = ConnectionManager.getConnection(url, user, password);
        try {
            PreparedStatement preparedStatement = con.prepareStatement(SQL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, wishlist.getWishlistName());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public Wish findWish(int wishID) {
        Wish wish = null;
        String sql = "SELECT * FROM wish" +
                " WHERE wishId LIKE ?;";
        Connection connection = ConnectionManager.getConnection(url, user, password);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, wishID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int WISHID = rs.getInt("wishId");
                String WISHNAME = rs.getString("wishName");
                double WISHPRICE = rs.getDouble("wishPrice");
                URL URL = rs.getURL("url");
                wish = new Wish(WISHNAME, WISHPRICE, URL, WISHID);

            }
            return wish;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
