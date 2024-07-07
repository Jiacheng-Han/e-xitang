package entity;

import java.time.LocalDateTime;

public class BookHistory {
    private int id;
    private int userId;
    private int bookId;
    private LocalDateTime clickTime;

    public BookHistory() {
    }

    public BookHistory(int userId, int bookId, LocalDateTime clickTime) {
        this.userId = userId;
        this.bookId = bookId;
        this.clickTime = clickTime;
    }
    public BookHistory(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }
    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDateTime getClickTime() {
        return clickTime;
    }

    public void setClickTime(LocalDateTime clickTime) {
        this.clickTime = clickTime;
    }
}
