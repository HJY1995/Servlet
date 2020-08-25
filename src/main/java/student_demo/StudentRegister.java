package student_demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/regist")
public class StudentRegister extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //中文转码
        String name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
        int age=Integer.parseInt(request.getParameter("age"));
        String major = new String(request.getParameter("major").getBytes("ISO-8859-1"), "UTF-8");
        String hometown = new String(request.getParameter("hometown").getBytes("ISO-8859-1"), "UTF-8");

        int result=StudentDAO.insert(name,age,major,hometown);

        if(result!=0){
            out.println("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "</head>\n" +
                    "<body id=\"body\">\n" +
                    "<h2>注册成功</h2>\n" +
                    "</body>\n" +
                    "</html>");
        }else{
            out.println("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "</head>\n" +
                    "<body id=\"body\">\n" +
                    "<h2>注册失败</h2>\n" +
                    "</body>\n" +
                    "</html>");
        }

    }

}
