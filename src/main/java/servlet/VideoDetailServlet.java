package servlet;

import com.alibaba.fastjson.JSON;
import entity.Company;
import entity.Video;
import service.CompanyService;
import service.VideoService;
import utils.R;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({"/VideoDetail_message"})
public class VideoDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("id");
        Integer id = Integer.parseInt(str);
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        VideoService videoService =new VideoService();
        Video video = videoService.select(id);
        writer.print(JSON.toJSON(R.ok(video)));
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
