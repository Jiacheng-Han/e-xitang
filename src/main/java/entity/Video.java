package entity;

public class Video {
    private Integer Id;
    private String VideoName;
    private String reletivePosition;
    private Integer userId;
    private String url;

    public Video() {
    }

    public Video(Integer id, String videoName, String reletivePosition, Integer userId, String url) {
        Id = id;
        VideoName = videoName;
        this.reletivePosition = reletivePosition;
        this.userId = userId;
        this.url = url;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getVideoName() {
        return VideoName;
    }

    public void setVideoName(String videoName) {
        VideoName = videoName;
    }

    public String getReletivePosition() {
        return reletivePosition;
    }

    public void setReletivePosition(String reletivePosition) {
        this.reletivePosition = reletivePosition;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
