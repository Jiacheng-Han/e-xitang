package dao;

import entity.BookHistory;
import utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookHistoryDao {
    private Connection connection;

    public BookHistoryDao() {
        connection = DruidUtils.getConnection();
    }



    public void insertBookClickRecord(BookHistory bookHistory) {
        try {
            String sql = "INSERT INTO bookhistory (userId, bookId) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, bookHistory.getUserId());
            ps.setInt(2, bookHistory.getBookId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
