package dao;

import entity.Company;
import entity.Video;
import recommend.CompanyUserCF;
import recommend.WangkeUserCF;
import utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CompanyDao {
    Connection connection = DruidUtils.getConnection();
    public CompanyDao(){}
    public List<Company> selectALL(Integer id){
        ArrayList<Company> companies = new ArrayList<Company>();
        String sql1;
        sql1 = "select id,job,place,salary,requirement,logo,CompanyName from companies where id > ? and id < ?";
        PreparedStatement ps1 = null;
        try {
            ps1 = this.connection.prepareStatement(sql1);
            ps1.setInt(1,id*100);
            ps1.setInt(2,id*100+100);
            ResultSet re1 = ps1.executeQuery();
            while(re1.next()){
                //每读出一条就加入到链表里
                Company company = new Company();
                company.setId(re1.getInt(1));
                company.setJob(re1.getString(2));
                company.setPlace(re1.getString(3));
                company.setSalary(re1.getString(4));
                company.setRequirement(re1.getString(5));
                company.setLogo(re1.getString(6));
                company.setCompanyName(re1.getString(7));
                company.setTags(CompanyDao.tags(company.getId()));
                company.setBonus(CompanyDao.bouns(company.getId()));
                company.setContents(CompanyDao.contents(company.getId()));
                companies.add(company);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return companies;
    }

    public Company select(Integer id){
        Company company = new Company();
        String sql1 = "select id,job,place,salary,requirement,logo,CompanyName,manager,manager_position,manager_sex from companies where id = ?";
        try {
            PreparedStatement ps1 = this.connection.prepareStatement(sql1);
            ps1.setInt(1,id);
            ResultSet re1 = ps1.executeQuery();
            while(re1.next()){
                //每读出一条就加入到链表里
                company.setId(re1.getInt(1));
                company.setJob(re1.getString(2));
                company.setPlace(re1.getString(3));
                company.setSalary(re1.getString(4));
                company.setRequirement(re1.getString(5));
                company.setLogo(re1.getString(6));
                company.setCompanyName(re1.getString(7));
                company.setManager(re1.getString(8));
                company.setManager_position(re1.getString(9));
                company.setManager_sex(re1.getInt(10));
                company.setTags(CompanyDao.tags(company.getId()));
                company.setBonus(CompanyDao.bouns(company.getId()));
                company.setContents(CompanyDao.contents(company.getId()));
                company.setIntroduction(CompanyDao.introduction(company.getId()));
                company.setDetail(CompanyDao.detail(company.getId()));
                break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return company;
    }
    public String type(Integer id){
        String str = null;
        String sql1 = "select company_name from companylist where id = ?";
        try {
            PreparedStatement ps1 = this.connection.prepareStatement(sql1);
            ps1.setInt(1,id);
            ResultSet re1 = ps1.executeQuery();
            while(re1.next()){
                str = re1.getString(1);
                break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return str;
    }
    static public ArrayList<String> bouns(int id){
        Connection connection = DruidUtils.getConnection();
        String sql3 = "select bonus from company_bonus where company_id = ?";
        ArrayList<String> bonus = new ArrayList<String>();
        PreparedStatement ps3;
        {
            try {
                ps3 = connection.prepareStatement(sql3);
                ps3.setInt(1,id);
                ResultSet re3 = ps3.executeQuery();
                while (re3.next()){
                    String b = re3.getString(1);
                    bonus.add(b);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return bonus;
    }
    static public ArrayList<String> contents(int id){
        Connection connection = DruidUtils.getConnection();
        String sql4 = "select content from company_contents where company_id = ?";
        ArrayList<String> contents = new ArrayList<String>();
        PreparedStatement ps4;
        {
            try {
                ps4 = connection.prepareStatement(sql4);
                ps4.setInt(1,id);
                ResultSet re4 = ps4.executeQuery();
                while (re4.next()){
                    String b = re4.getString(1);
                    contents.add(b);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return contents;
    }
    static public ArrayList<String> tags(int id){
        Connection connection = DruidUtils.getConnection();
        String sql2 = "select tag from company_tags where company_id = ?";
        ArrayList<String> tags = new ArrayList<String>();
        PreparedStatement ps2;
        {
            try {
                ps2 = connection.prepareStatement(sql2);
                ps2.setInt(1,id);
                ResultSet re2 = ps2.executeQuery();
                while (re2.next()){
                    String tag = re2.getString(1);
                    tags.add(tag);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return tags;
    }
    static public ArrayList<String> introduction(int id){
        Connection connection = DruidUtils.getConnection();
        String sql2 = "select paragraph from company_introduction where company_id = ?";
        ArrayList<String> introduction = new ArrayList<String>();
        PreparedStatement ps2;
        {
            try {
                ps2 = connection.prepareStatement(sql2);
                ps2.setInt(1,id);
                ResultSet re2 = ps2.executeQuery();
                while (re2.next()){
                    String str = re2.getString(1);
                    introduction.add(str);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return introduction;
    }
    static public ArrayList<String> detail(int id){
        Connection connection = DruidUtils.getConnection();
        String sql2 = "select paragraph from position_detail where company_id = ?";
        ArrayList<String> detail = new ArrayList<String>();
        PreparedStatement ps2;
        {
            try {
                ps2 = connection.prepareStatement(sql2);
                ps2.setInt(1,id);
                ResultSet re2 = ps2.executeQuery();
                while (re2.next()){
                    String str = re2.getString(1);
                    detail.add(str);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return detail;
    }
    public ArrayList<Company> select(String username) {
        Integer UserId = 0;
        String sql;
        ArrayList<Company> companies = new ArrayList<Company>();
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
        CompanyUserCF companyUserCF = historyDao.selectALLposition(UserId);
        ArrayList<Integer> company = companyUserCF.UserCF();
            for (int j = 0 ; j < 9 ; j ++ ) {
                sql = "select id , job , salary , CompanyName , requirement, place from companies where id =?";
                try {
                    PreparedStatement ps = this.connection.prepareStatement(sql);
                    ps.setInt(1,company.get(j));
                    ResultSet re = ps.executeQuery();
                    while (re.next()) {
                        //每读出一条就加入到链表里
                        Company company1 = new Company();
                        company1.setId(re.getInt(1));
                        company1.setJob(re.getString(2));
                        company1.setSalary(re.getString(3));
                        company1.setCompanyName(re.getString(4));
                        company1.setRequirement(re.getString(5));
                        company1.setPlace(re.getString(6));
                        companies.add(company1);
                        break;
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        return companies;
    }

    public ArrayList<Integer> selectTOP() {
        Integer flag = 0;
        String sql = "select id from companies order by ReadTimes desc";
        ArrayList<Integer> companies = new ArrayList<Integer>();
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet re = ps.executeQuery();
            while (re.next()) {
                //每读出一条就加入到链表里
                companies.add(re.getInt(1));
                flag++;
                if(flag==10){
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return companies;
    }
//    public ArrayList<Integer> selectTOP(Integer i){
//        Integer flag = 0;
//        String sql = "select id from videos order by WatchedTimes desc";
//        ArrayList<Integer> Videos = new ArrayList<Integer>();
//        try {
//            PreparedStatement ps = this.connection.prepareStatement(sql);
//            ResultSet re = ps.executeQuery();
//            while (re.next()) {
//                //每读出一条就加入到链表里
//                Videos.add(re.getInt(1));
//                flag++;
//                if(flag==i){
//                    break;
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return Videos;
//    }
}

