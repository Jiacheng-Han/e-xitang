package dao;

import entity.Book;
import entity.Company;
import recommend.BookUserCF;
import recommend.CompanyUserCF;
import utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    Connection connection = DruidUtils.getConnection();

    public List<Book> select() {
        ArrayList<Book> books = new ArrayList<>();
        try {
            String sql = "SELECT id, BookName, bookAuthor, bookUrl, bookDescription, bookCoverUrl, bookDownloadUrl FROM books order by readtimes DESC";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Integer flag = 0;
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setBookName(rs.getString("BookName"));
                book.setBookAuthor(rs.getString("bookAuthor"));
                book.setBookUrl(rs.getString("bookUrl"));
                book.setBookDescription(rs.getString("bookDescription"));
                book.setBookCoverUrl(rs.getString("bookCoverUrl"));
                book.setBookDownloadUrl(rs.getString("bookDownloadUrl"));
                books.add(book);
                flag++;
                if(flag==6){
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public Book selectBook(Integer id) {
        try {
            String sql = "SELECT id, BookName, bookAuthor, bookUrl, bookDescription, bookCoverUrl, bookDownloadUrl FROM books WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setBookName(rs.getString("BookName"));
                book.setBookAuthor(rs.getString("bookAuthor"));
                book.setBookUrl(rs.getString("bookUrl"));
                book.setBookDescription(rs.getString("bookDescription"));
                book.setBookCoverUrl(rs.getString("bookCoverUrl"));
                book.setBookDownloadUrl(rs.getString("bookDownloadUrl"));
                return book;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Book> selectAll(Integer id) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            String sql = "SELECT id, BookName, bookCoverUrl FROM books where id > ? and id < ? order by readtimes ASC";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            ps.setInt(2,id+1000);
            ResultSet rs = ps.executeQuery();
            Integer flag = 0;
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setBookName(rs.getString("BookName"));
                book.setBookCoverUrl(rs.getString("bookCoverUrl"));
                books.add(book);
                flag++;
                if(flag==8){
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public List<Book> selectTop() {
        ArrayList<Book> books = new ArrayList<>();
        try {
            String sql = "SELECT id, BookName, bookAuthor FROM books order by readtimes DESC";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Integer flag = 0;
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setBookName(rs.getString("BookName"));
                book.setBookAuthor(rs.getString("bookAuthor"));
                books.add(book);
                flag++;
                if(flag==6){
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public ArrayList<Book> select(String username) {
        Integer UserId = 0;
        String sql;
        ArrayList<Book> books = new ArrayList<>();
        String sql1 = "select id from userinformation where username = ?";
        try {
            PreparedStatement ps1 = this.connection.prepareStatement(sql1);
            ps1.setString(1,username);
            ResultSet re1 = ps1.executeQuery();
            while(re1.next()){
                UserId = re1.getInt(1);
                break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        HistoryDao historyDao = new HistoryDao();
        BookUserCF bookUserCF = historyDao.selectALLBook(UserId);
        ArrayList<Integer> book = bookUserCF.UserCF();
        for (int j = 0 ; j < 9 ; j ++ ) {
            sql = "SELECT id, BookName, bookAuthor, bookUrl, bookDescription, bookCoverUrl, bookDownloadUrl FROM books WHERE id = ?";
            try {
                PreparedStatement ps = this.connection.prepareStatement(sql);
                ps.setInt(1,book.get(j));
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    //每读出一条就加入到链表里
                    Book BOOK = new Book();
                    BOOK.setId(rs.getInt("id"));
                    BOOK.setBookName(rs.getString("BookName"));
                    BOOK.setBookAuthor(rs.getString("bookAuthor"));
                    BOOK.setBookUrl(rs.getString("bookUrl"));
                    BOOK.setBookDescription(rs.getString("bookDescription"));
                    BOOK.setBookCoverUrl(rs.getString("bookCoverUrl"));
                    BOOK.setBookDownloadUrl(rs.getString("bookDownloadUrl"));
                    books.add(BOOK);
                    break;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return books;
    }
}
