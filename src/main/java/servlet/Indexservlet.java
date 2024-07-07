package servlet;

import com.alibaba.fastjson.JSON;
import entity.User;
import utils.R;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({"/usermessage"})
public class Indexservlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        //判断任务登录状态
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null){
            String username = user.getUsername();
            writer.print(JSON.toJSON(R.ok(username)));
        }
        else{
            writer.print(JSON.toJSON(R.error("未登录")));
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
