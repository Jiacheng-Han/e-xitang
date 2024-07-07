package entity;

import java.util.ArrayList;

public class Company {
    private Integer id;
    private String job;

    private String place;
    private String salary;
    private String requirement;
    private String logo;
    private String CompanyName;
    private ArrayList<String> tags;
    private ArrayList<String> contents;
    private ArrayList<String> bonus;
    private ArrayList<String> introduction;
    private ArrayList<String> detail;
    private String manager;
    private String manager_position;
    private Integer manager_sex;

    public Integer getManager_sex() {
        return manager_sex;
    }

    public void setManager_sex(Integer manager_sex) {
        this.manager_sex = manager_sex;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManager_position() {
        return manager_position;
    }

    public void setManager_position(String manager_position) {
        this.manager_position = manager_position;
    }

    public Company(Integer id, String job, String place, String salary, String requirement, String logo, String companyName, ArrayList<String> tags, ArrayList<String> contents, ArrayList<String> bonus, ArrayList<String> introduction, ArrayList<String> detail, String manager, String manager_position, Integer manager_sex) {
        this.id = id;
        this.job = job;
        this.place = place;
        this.salary = salary;
        this.requirement = requirement;
        this.logo = logo;
        CompanyName = companyName;
        this.tags = tags;
        this.contents = contents;
        this.bonus = bonus;
        this.introduction = introduction;
        this.detail = detail;
        this.manager = manager;
        this.manager_position = manager_position;
        this.manager_sex = manager_sex;
    }

    public Company() {
    }
    public ArrayList<String> getIntroduction() {
        return introduction;
    }

    public void setIntroduction(ArrayList<String> introduction) {
        this.introduction = introduction;
    }

    public ArrayList<String> getDetail() {
        return detail;
    }

    public void setDetail(ArrayList<String> detail) {
        this.detail = detail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public void addContent(String content){
        this.contents.add(content);
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<String> getContents() {
        return contents;
    }

    public void addtag(String tag){
        this.tags.add(tag);
    }

    public void setContents(ArrayList<String> contents) {
        this.contents = contents;
    }

    public ArrayList<String> getBonus() {
        return bonus;
    }

    public void setBonus(ArrayList<String> bonus) {
        this.bonus = bonus;
    }

    public void addBonus(String bonus){ this.bonus.add(bonus); };

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", job='" + job + '\'' +
                ", place='" + place + '\'' +
                ", salary='" + salary + '\'' +
                ", requirement='" + requirement + '\'' +
                ", logo='" + logo + '\'' +
                ", CompanyName='" + CompanyName + '\'' +
                ", tags=" + tags +
                ", contents=" + contents +
                ", bonus=" + bonus +
                ", introduction=" + introduction +
                ", detail=" + detail +
                ", manager='" + manager + '\'' +
                ", manager_position='" + manager_position + '\'' +
                ", manager_sex=" + manager_sex +
                '}';
    }
}
