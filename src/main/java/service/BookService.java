package service;

import dao.BookDao;
import dao.CompanyDao;
import dao.VideoDao;
import entity.Book;
import entity.Company;
import entity.Video;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    //获取当前用户文章
    public List<Book> selectAll(){
        List<Book> books;
        BookDao  bookDao = new BookDao();
        books = bookDao.select();
        return books;
    }

    public Book select(Integer id) {
        Book book = new Book();
        BookDao bookDao = new BookDao();
        book = bookDao.selectBook(id);
        return book;
    }

    public List<Book> selectAll(Integer id) {
        List<Book> books;
        BookDao bookDao = new BookDao();
        books = bookDao.selectAll(id);
        return books;
    }

    public List<Book> selectTop() {
        List<Book> books;
        BookDao bookDao = new BookDao();
        books = bookDao.selectTop();
        return books;
    }

    public ArrayList<Book> Recommend(String username) {
        BookDao bookDao = new BookDao();
        ArrayList<Book> books = bookDao.select(username);
        return books;
    }
}
