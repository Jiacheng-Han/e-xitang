package servlet;

import com.alibaba.fastjson.JSON;
import entity.Video;
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

@WebServlet({"/wangktypeemessage"})
public class WangkeTypeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("id");
        Integer id = Integer.parseInt(str);
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        VideoService videoService = new VideoService();
        List<Video> videos = videoService.selectAll(id);
        writer.print(JSON.toJSON(R.ok(videos)));
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

