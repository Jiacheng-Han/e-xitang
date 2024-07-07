package entity;

public class Book {
    private Integer id;
    private String bookName;
    private String bookAuthor;
    private String bookUrl;
    private String bookDescription;
    private String bookCoverUrl;
    private String bookDownloadUrl;

    public Book() {
    }

    public Book(Integer id, String bookName, String bookAuthor, String bookUrl, String bookDescription, String bookCoverUrl, String bookDownloadUrl) {
        this.id = id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookUrl = bookUrl;
        this.bookDescription = bookDescription;
        this.bookCoverUrl = bookCoverUrl;
        this.bookDownloadUrl = bookDownloadUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookCoverUrl() {
        return bookCoverUrl;
    }

    public void setBookCoverUrl(String bookCoverUrl) {
        this.bookCoverUrl = bookCoverUrl;
    }

    public String getBookDownloadUrl() {
        return bookDownloadUrl;
    }

    public void setBookDownloadUrl(String bookDownloadUrl) {
        this.bookDownloadUrl = bookDownloadUrl;
    }
}
