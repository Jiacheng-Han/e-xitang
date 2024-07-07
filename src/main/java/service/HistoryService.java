package service;

import dao.HistoryDao;

public class HistoryService {
    public boolean WangkeWatched(String username,String videoname){
        HistoryDao historyDao = new HistoryDao();
        Integer i = historyDao.AddHistoryVideo(username,videoname);
        if(i!=0){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean PositionRead(String username, String jobname, String companyname){
        HistoryDao historyDao = new HistoryDao();
        Integer i = historyDao.AddHistoryPosition(username,jobname,companyname);
        if(i!=0){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean BookRead(String username,Integer bookid){
        HistoryDao historyDao = new HistoryDao();
        Integer i = historyDao.AddHistoryBook(username,bookid);
        if(i!=0){
            return true;
        }
        else{
            return false;
        }
    }
}
