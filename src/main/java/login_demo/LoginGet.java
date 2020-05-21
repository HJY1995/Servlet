package login_demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/loginGet")
public class LoginGet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String passwd = request.getParameter("passwd");
        if (DBSearch.check(name, passwd)) {
            System.out.println("success");
            out.println("success");
        } else {
            System.out.println("filed");
            out.println("filed");
        }
    }
}
