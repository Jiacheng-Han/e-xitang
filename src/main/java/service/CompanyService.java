package service;

import dao.CompanyDao;
import entity.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyService {
    public List<Company> companies(Integer id){
        List <Company> companies;
        CompanyDao companyDao = new CompanyDao();
        companies = companyDao.selectALL(id);
        return companies;
    }
    public Company company(Integer id){
        Company company;
        CompanyDao companyDao = new CompanyDao();
        company = companyDao.select(id);
        return company;
    }
    public String type(Integer id){
        String str = null;
        CompanyDao companyDao = new CompanyDao();
        str = companyDao.type(id);
        return str;
    }
    public ArrayList<Company> Recommend(String username){
        CompanyDao companyDao = new CompanyDao();
        ArrayList<Company> companies = companyDao.select(username);
        return companies;
    }
}
