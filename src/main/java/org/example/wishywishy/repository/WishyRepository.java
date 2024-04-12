package org.example.wishywishy.repository;

import org.example.wishywishy.model.User;
import org.example.wishywishy.repository.util.ConnectionManager;
import org.example.wishywishy.model.Wish;
import org.example.wishywishy.model.Wishlist;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WishyRepository {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;


    public User createUser(User newUser) {
        if (checkUniqueUsername(newUser)) {
            String sql = "INSERT INTO users(username, password) VALUES (?, ?);";
            Connection connection = ConnectionManager.getConnection(url, user, password);

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, newUser.getUsername());
                pstmt.setString(2, newUser.getPassword());
                pstmt.executeUpdate();
                return newUser;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }

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
            if (updatedWish.getUrl() != null) {
                String urlString = updatedWish.getUrl().toString();
                if (!urlString.startsWith("http://") && !urlString.startsWith("https://")) {
                    urlString = "http://" + urlString;
                }
                updateWishStmt.setString(2, urlString);
            } else {
                updateWishStmt.setNull(2, Types.VARCHAR);
            }

            updateWishStmt.setDouble(3, updatedWish.getWishPrice());
            updateWishStmt.setInt(4, updatedWish.getWishID());
            rows = updateWishStmt.executeUpdate();
            System.out.println("Rows: " + rows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    public void deleteWishList(int wishlistID) {
        deleteWishesInWishlist(wishlistID);
        String SQL = "DELETE FROM WISHLIST WHERE WISHLISTID = ?";
        Connection connection = ConnectionManager.getConnection(url, user, password);

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, wishlistID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteWishesInWishlist(int wishlistID) {
        String SQL = "DELETE FROM WISH WHERE WISHLISTID = ?";
        Connection connection = ConnectionManager.getConnection(url, user, password);

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, wishlistID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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


    public void addWish(Wish wish, int wishListID) {
        String SQL = "INSERT INTO WISH(WISHNAME,WISHPRICE,URL,WISHLISTID) values(?,?,?,?)";
        Connection con = ConnectionManager.getConnection(url, user, password);
        try {
            PreparedStatement preparedStatement = con.prepareStatement(SQL);
            preparedStatement.setString(1, wish.getWishName());
            preparedStatement.setDouble(2, wish.getWishPrice());
            preparedStatement.setString(3, addProtocolToURL(wish.getUrl()));
            preparedStatement.setInt(4, wishListID);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    private String addProtocolToURL(String url) {
        if (url == null || url.trim().isEmpty()) {
            return url;
        }

        if (!url.matches("^\\w+://.*")) {
            url = "http://" + url;
        }

        return url;
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
                boolean ISRESERVED = rs.getBoolean("isReserved");
                String URLString = rs.getString("url");
                addProtocolToURL(URLString);

                wish = new Wish(WISHNAME, WISHPRICE, URLString, WISHID, ISRESERVED);


            }
            return wish;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Wish> findAllWishesInWishlist(int wishlistID) {
        List<Wish> wishList = new ArrayList<>();
        String SQL = "SELECT * FROM WISH WHERE WISHLISTID = ?";
        Connection con = ConnectionManager.getConnection(url, user, password);
        try (PreparedStatement psmt = con.prepareStatement(SQL)) {
            psmt.setInt(1, wishlistID);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                int WISHID = rs.getInt("wishId");
                String WISHNAME = rs.getString("wishName");
                double WISHPRICE = rs.getDouble("wishPrice");
                boolean ISRESERVED = rs.getBoolean("isReserved");
                String urlString = rs.getString("URL");
                addProtocolToURL(urlString);
                wishList.add(new Wish(WISHNAME, WISHPRICE, urlString, WISHID, ISRESERVED));
            }
            return wishList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Wishlist> getAllWishlistsFromUser(String userName) {
        List<Wishlist> wishlists = new ArrayList<>();
        String SQL = "SELECT * FROM WISHLIST WHERE USERNAME = ?";
        Connection con = ConnectionManager.getConnection(url, user, password);
        try (PreparedStatement psmt = con.prepareStatement(SQL)) {
            psmt.setString(1, userName);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                int WISHLISTID = rs.getInt("wishlistid");
                String WISHLISTNAME = rs.getString("wishlistName");
                wishlists.add(new Wishlist(WISHLISTID, userName, WISHLISTNAME));
            }
            return wishlists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean checkUniqueUsername(User userToCheck) {
        boolean nameIsUnique = false;

        String sql = "SELECT COUNT(*) FROM users WHERE username like (?);";
        Connection connection = ConnectionManager.getConnection(url, user, password);

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, userToCheck.getUsername());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("COUNT(*)");
                nameIsUnique = (count == 0);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nameIsUnique;
    }

    public User checkIfLoginValid(User userToCheck) {
        String sql = "SELECT * FROM users WHERE username LIKE (?) AND password LIKE (?);";
        Connection connection = ConnectionManager.getConnection(url, user, password);

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, userToCheck.getUsername());
            pstmt.setString(2, userToCheck.getPassword());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return userToCheck;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        String sql = "SELECT * FROM users;";
        Connection connection = ConnectionManager.getConnection(url, user, password);

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             while (rs.next()) {
                allUsers.add(new User(rs.getString("username"), rs.getString("password")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allUsers;
    }

    public void toggleReserve(boolean isReserved, int wishId) {
        String sql = "UPDATE wish SET isReserved = (?) WHERE wishId = (?);";
        Connection connection = ConnectionManager.getConnection(url, user, password);

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setBoolean(1, isReserved);
            pstmt.setInt(2, wishId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}