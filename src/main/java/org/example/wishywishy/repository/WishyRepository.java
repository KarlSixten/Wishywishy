package org.example.wishywishy.repository;

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

public void deleteWish (int wishId) throws SQLException {
String sqlDeleteWish = "DELETE FROM wish WHERE wishID = ?;";

    Connection connection = ConnectionManager.getConnection(url, user, password);

try (PreparedStatement pstmtWish = connection.prepareStatement(sqlDeleteWish)){
pstmtWish.setInt(1,wishId);
pstmtWish.executeUpdate();
}
}


}
