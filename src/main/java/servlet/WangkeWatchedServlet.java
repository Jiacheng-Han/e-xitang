package servlet;

import com.alibaba.fastjson.JSON;
import entity.Video;
import service.HistoryService;
import service.VideoService;
import utils.R;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/WangkeWatchedMessage"})
public class WangkeWatchedServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        String username = req.getParameter("username");
        String videoname = req.getParameter("videoname");
        HistoryService historyService = new HistoryService();
        boolean flag = historyService.WangkeWatched(username,videoname);
        if(flag){
            writer.print(JSON.toJSON(R.ok("已经录入历史观看记录")));
        }
        else{
            writer.print(JSON.toJSON(R.error("未录入成功")));
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

