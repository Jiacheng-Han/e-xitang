package dao;

import entity.Book;
import entity.Company;
import recommend.BookUserCF;
import recommend.CompanyUserCF;
import recommend.WangkeUserCF;
import utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class HistoryDao {
    Connection connection = DruidUtils.getConnection();
    public int AddHistoryVideo(String username,String videoname){
        Integer UserId = 0 , VideoId = 0 , WatchedTimes = 0 ;
        int i = 0;
        boolean flag = true;
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
        String sql2 = "select id , WatchedTimes from videos where VideoName = ?";
        try {
            PreparedStatement ps2 = this.connection.prepareStatement(sql2);
            ps2.setString(1,videoname);
            ResultSet re2 = ps2.executeQuery();
            while(re2.next()){
                VideoId = re2.getInt(1);
                WatchedTimes = re2.getInt(2);
                break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql3 = "select id from history_video where user_id = ? and video_id = ?";
        try {
            PreparedStatement ps3 = this.connection.prepareStatement(sql3);
            ps3.setInt(1,UserId);
            ps3.setInt(2,VideoId);
            ResultSet re3 = ps3.executeQuery();
            while(re3.next()){
                flag = false;
                break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(flag){
            String sql = "insert into history_video (user_id,video_id) values (?,?)";
            try {
                PreparedStatement ps = this.connection.prepareStatement(sql);
                ps.setInt(1,UserId);
                ps.setInt(2,VideoId);
                i = ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        int j = 0;
        try {
            String sql = "UPDATE videos SET WatchedTimes = ? WHERE id = ?;";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1,WatchedTimes+1);
            ps.setInt(2,VideoId);
            j = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }
    public WangkeUserCF selectALLwangke(Integer userID) {
        Set<Integer> items = new HashSet<>();
        Map<Integer, Set<Integer>> itemUserCollection = new HashMap<>();
        Map<Integer, Integer> userItemLength = new HashMap<>();
        String sql3 = "select user_id,video_id from history_video ";
        WangkeUserCF wangkeUserCF ;
        try {
            PreparedStatement ps3 = this.connection.prepareStatement(sql3);
            ResultSet re3 = ps3.executeQuery();
            while (re3.next()) {
                Integer userid = re3.getInt(1);
                if (items.contains(re3.getInt(2))) {//如果已经包含对应的物品--用户映射，直接添加对应的用户
                    itemUserCollection.get(re3.getInt(2)).add(userid);
                } else {//否则创建对应物品--用户集合映射
                    items.add(re3.getInt(2));
                    itemUserCollection.put(re3.getInt(2), new HashSet<Integer>());//创建物品--用户倒排关系
                    itemUserCollection.get(re3.getInt(2)).add(userid);
                }
                if (userItemLength.containsKey(userid)) {//如果已经包含对应的物品--用户映射，直接添加对应的用户
                    userItemLength.put(userid, userItemLength.get(userid) + 1);
                } else {//否则创建对应物品--用户集合映射
                    userItemLength.put(userid, 1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        wangkeUserCF = new WangkeUserCF(userItemLength, itemUserCollection, items,userID);
        return wangkeUserCF;
    }
    public Integer AddHistoryPosition(String username, String jobname, String companyname) {
        Integer UserId = 0 , JobId = 0 , readtimes = 0 ;
        int i = 0;
        boolean flag = true;
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
        String sql2 = "select id , Readtimes from companies where job = ? and CompanyName = ?";
        try {
            PreparedStatement ps2 = this.connection.prepareStatement(sql2);
            ps2.setString(1,jobname);
            ps2.setString(2,companyname);
            ResultSet re2 = ps2.executeQuery();
            while(re2.next()){
                JobId = re2.getInt(1);
                readtimes = re2.getInt(2);
                break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql3 = "select id from history_position where user_id = ? and job_id = ?";
        try {
            PreparedStatement ps3 = this.connection.prepareStatement(sql3);
            ps3.setInt(1,UserId);
            ps3.setInt(2,JobId);
            ResultSet re3 = ps3.executeQuery();
            while(re3.next()){
                flag = false;
                break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(flag){
            String sql = "insert into history_position (user_id,job_id) values (?,?)";
            try {
                PreparedStatement ps = this.connection.prepareStatement(sql);
                ps.setInt(1,UserId);
                ps.setInt(2,JobId);
                i = ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            String sql = "UPDATE companies SET ReadTimes = ? WHERE id = ?;";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1,readtimes+1);
            ps.setInt(2, JobId);
            Integer j = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }
    public CompanyUserCF selectALLposition(Integer userID) {
        Set<Integer> items = new HashSet<>();
        Map<Integer, Set<Integer>> itemUserCollection = new HashMap<>();
        Map<Integer, Integer> userItemLength = new HashMap<>();
        String sql3 = "select user_id,job_id from history_position ";
        CompanyUserCF companyUserCF;
        try {
            PreparedStatement ps3 = this.connection.prepareStatement(sql3);
            ResultSet re3 = ps3.executeQuery();
            while (re3.next()) {
                Integer userid = re3.getInt(1);
                if (items.contains(re3.getInt(2))) {//如果已经包含对应的物品--用户映射，直接添加对应的用户
                    itemUserCollection.get(re3.getInt(2)).add(userid);
                } else {//否则创建对应物品--用户集合映射
                    items.add(re3.getInt(2));
                    itemUserCollection.put(re3.getInt(2), new HashSet<Integer>());//创建物品--用户倒排关系
                    itemUserCollection.get(re3.getInt(2)).add(userid);
                }
                if (userItemLength.containsKey(userid)) {//如果已经包含对应的物品--用户映射，直接添加对应的用户
                    userItemLength.put(userid, userItemLength.get(userid) + 1);
                } else {//否则创建对应物品--用户集合映射
                    userItemLength.put(userid, 1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        companyUserCF = new CompanyUserCF(userItemLength, itemUserCollection, items , userID);
        return companyUserCF;
    }

    public Integer AddHistoryBook(String username, Integer bookid) {
        Integer UserId = 0 , readtimes = 0;
        int i = 0;
        boolean flag = true;
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
        } String sql3 = "select id from history_book where user_id = ? and book_id = ?";
        try {
            PreparedStatement ps3 = this.connection.prepareStatement(sql3);
            ps3.setInt(1,UserId);
            ps3.setInt(2,bookid);
            ResultSet re3 = ps3.executeQuery();
            while(re3.next()){
                flag = false;
                break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(flag){
            String sql = "insert into history_book (user_id,book_id) values (?,?)";
            try {
                PreparedStatement ps = this.connection.prepareStatement(sql);
                ps.setInt(1,UserId);
                ps.setInt(2,bookid);
                i = ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            String sql2 = "select readtimes from books where id = ?";
            PreparedStatement ps2 = this.connection.prepareStatement(sql2);
            ps2.setInt(1,bookid);
            ResultSet re2 = ps2.executeQuery();
            while(re2.next()){
                readtimes = re2.getInt(1);
                break;
            }
            String sql4 = "UPDATE books SET readtimes = ? WHERE id = ?";
            PreparedStatement ps4 = this.connection.prepareStatement(sql4);
            ps4.setInt(1,readtimes+1);
            ps4.setInt(2,bookid);
            Integer j = ps4.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    public BookUserCF selectALLBook(Integer userId) {
        Set<Integer> items = new HashSet<>();
        Map<Integer, Set<Integer>> itemUserCollection = new HashMap<>();
        Map<Integer, Integer> userItemLength = new HashMap<>();
        String sql3 = "select user_id,book_id from history_book ";
        BookUserCF bookUserCF;
        try {
            PreparedStatement ps3 = this.connection.prepareStatement(sql3);
            ResultSet re3 = ps3.executeQuery();
            while (re3.next()) {
                Integer userid = re3.getInt(1);
                if (items.contains(re3.getInt(2))) {//如果已经包含对应的物品--用户映射，直接添加对应的用户
                    itemUserCollection.get(re3.getInt(2)).add(userid);
                } else {//否则创建对应物品--用户集合映射
                    items.add(re3.getInt(2));
                    itemUserCollection.put(re3.getInt(2), new HashSet<Integer>());//创建物品--用户倒排关系
                    itemUserCollection.get(re3.getInt(2)).add(userid);
                }
                if (userItemLength.containsKey(userid)) {//如果已经包含对应的物品--用户映射，直接添加对应的用户
                    userItemLength.put(userid, userItemLength.get(userid) + 1);
                } else {//否则创建对应物品--用户集合映射
                    userItemLength.put(userid, 1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        bookUserCF = new BookUserCF(userItemLength, itemUserCollection, items , userId);
        return bookUserCF;
    }
}
