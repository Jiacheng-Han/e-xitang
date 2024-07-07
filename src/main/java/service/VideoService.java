package service;

import dao.VideoDao;
import entity.Video;

import java.util.List;

public class VideoService {
    //获取当前用户文章
    public List<Video> selectAll(String username, int i){
        List<Video> videos;
        VideoDao  videoDao = new VideoDao();
        if(i==0){
            videos = videoDao.select(username,0);
        }
        else{
            videos = videoDao.select(username,1);
        }
        return videos;
    }
    public Video select(Integer id){
        Video video = new Video();
        VideoDao  videoDao = new VideoDao();
        video = videoDao.select(id);
        return video;
    }
    public List<Video> selectAll(Integer id){
        List<Video> videos;
        VideoDao  videoDao = new VideoDao();
        videos = videoDao.selectAll(id);
        return videos;
    }
}
