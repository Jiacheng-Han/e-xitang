package dao;

import entity.User;
import utils.DruidUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    Connection connection = DruidUtils.getConnection();
    //以下是注册
    public int addUser(User user){
        int i = 0;
        try {
            String sql = "insert into userinformation (username,password) values (?,?)";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            i = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }
    public boolean select(String username){
        try {
            String sql = "select username from userinformation where username = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1,username);
            ResultSet re = ps.executeQuery();
            while(re.next()){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    //以下是登陆
    public boolean select(String username,String password){
        try {
            String sql = "select username,password from userinformation where username = ? and password = ? ";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet re = ps.executeQuery();
            while(re.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
