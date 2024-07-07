package dao;

import entity.Video;
import recommend.WangkeUserCF;
import utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VideoDao {
    Connection connection = DruidUtils.getConnection();
    public ArrayList<Integer> selectTOP(Integer i){
        Integer flag = 0;
        String sql = "select id from videos order by WatchedTimes desc";
        ArrayList<Integer> Videos = new ArrayList<Integer>();
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet re = ps.executeQuery();
            while (re.next()) {
                //每读出一条就加入到链表里
                Videos.add(re.getInt(1));
                flag++;
                if(flag==i){
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Videos;
    }
    public List<Video> selectAll(Integer i){
        String sql = "select id,VideoName from videos where id > ? and id < ? order by WatchedTimes desc";
        ArrayList<Video> Videos = new ArrayList<Video>();
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1,i);
            ps.setInt(2,i+1000);
            ResultSet re = ps.executeQuery();
            while (re.next()) {
                //每读出一条就加入到链表里
                Video video = new Video();
                video.setId(re.getInt(1));
                video.setVideoName(re.getString(2));;
                Videos.add(video);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Videos;
    }

    public List<Video> select(String username, int i) {
        Integer UserId = 0,flag=0;
        String sql;
        ArrayList<Video> Videos = new ArrayList<Video>();
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
        WangkeUserCF wangkeUserCF = historyDao.selectALLwangke(UserId);
        ArrayList<Integer> videos = wangkeUserCF.UserCF();
//        System.out.println(UserId);
//        for(int j = 0 ;j< videos.size();j++){
//            System.out.println(videos.get(j));
//        }
//        System.out.println(i);
        if(i==0){
            for (int j = 0; j < 3 ; j ++) {
                sql = "select VideoName from videos where id =?";
                try {
                    PreparedStatement ps = this.connection.prepareStatement(sql);
                    ps.setInt(1,videos.get(j));
                    ResultSet re = ps.executeQuery();
                    while (re.next()) {
                        //每读出一条就加入到链表里
                        Video video = new Video();
                        video.setId(videos.get(j));
                        video.setVideoName(re.getString(1));;
                        Videos.add(video);
                        break;
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if(i==1){
            for (int j = 0 ; j < 9 ; j ++ ) {
                sql = "select VideoName from videos where id =?";
                try {
                    PreparedStatement ps = this.connection.prepareStatement(sql);
                    ps.setInt(1,videos.get(j));
                    ResultSet re = ps.executeQuery();
                    while (re.next()) {
                        //每读出一条就加入到链表里
                        Video video = new Video();
                        video.setId(videos.get(j));
                        video.setVideoName(re.getString(1));
//                        System.out.println(video.getId());
                        Videos.add(video);
                        break;
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
//        for(int j = 0 ;j<Videos.size();j++){
//            System.out.println(Videos.get(j));
//        }
        return Videos;
    }

    public Video select(Integer id) {
        Video video = new Video();
        String sql1 = "select id,VideoName from videos where id = ?";
        try {
            PreparedStatement ps1 = this.connection.prepareStatement(sql1);
            ps1.setInt(1, id);
            ResultSet re = ps1.executeQuery();
            while (re.next()) {
                video.setId(re.getInt(1));
                video.setVideoName(re.getString(2));
                break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return video;
    }
}