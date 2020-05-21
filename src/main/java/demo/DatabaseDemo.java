package demo;

import login_demo.DBSearch;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/dbdemo")
public class DatabaseDemo extends HttpServlet {
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://192.168.186.129:3306/test";
    final String USER="root";
    final String PWD="123456";
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();

        out.println(DBSearch.check("root","123456"));

        //注册JDBC驱动
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //建立连接
        Connection connection= null;
        try {
            connection = DriverManager.getConnection(DB_URL,USER,PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //创建执行SQL语句的 statement
        Statement statement= null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //使用statement执行select
        ResultSet resultSet= null;
        try {
            resultSet = statement.executeQuery("SELECT id,name,url,point from websites");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(resultSet);

        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            try {
//                if (!resultSet.next())
//                    break;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
            try {
                out.println("id="+resultSet.getString("id"));
                out.println("name="+resultSet.getString("name"));
                out.println("url="+resultSet.getString("url"));
                out.println("point="+resultSet.getString("point"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //数据库连接（Connection）非常耗资源，尽量晚创建，尽量早的释放
        //都要加try catch 以防前面关闭出错，后面的就不执行了
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
