package login_demo;

import java.sql.*;

public class DBSearch {
    final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final static String DB_URL = "jdbc:mysql://192.168.186.129:3306/test";
    final static String USER = "root";
    final static String PWD = "123456";

    public static Boolean check(String name, String passwd) {
        //注册JDBC驱动
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //建立连接
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //创建执行SQL语句的 statement
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //使用statement执行select
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * from account");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(resultSet);

        while (true) {
            try {
                if (!resultSet.next()) break;

                if (name.equals(resultSet.getString("name")) && passwd.equals(resultSet.getString("passwd"))) {
                    return true;
                }

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

        return false;
    }

    public static void main(String[] args) {
        System.out.println(DBSearch.check("root", "123456"));
    }
}
