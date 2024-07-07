package servlet;

import com.alibaba.fastjson.JSON;
import entity.User;
import service.UserService;
import utils.R;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({"/log"})
public class Logservlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        UserService service = new UserService();
        boolean flag = service.login(name,password);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if(flag){
            //登陆成功后保存用户信息
            HttpSession session = req.getSession();
            User user = new User(name,password);
            session.setAttribute("user",user);
            writer.print(JSON.toJSON(R.ok("登录成功！")));
        }
        else{
            writer.print(JSON.toJSON(R.error("密码错误！")));
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
