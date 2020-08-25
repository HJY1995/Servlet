package login_demo;

import org.testng.annotations.Test;

import java.sql.*;

public class DBSearch {
    final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final static String DB_URL = "jdbc:mysql://192.168.186.131:3306/test?useSSL=true";
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
            assert connection != null;
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //使用statement执行select
        ResultSet resultSet = null;
        try {
            assert statement != null;
            resultSet = statement.executeQuery("SELECT * from student");
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

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
  //      System.out.println(DBSearch.check("root", "123456"));

        String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
        String DB_URL="jdbc:mysql://192.168.186.131:3306/test?useSSL=true";
        String user="root";
        String passwd="123456";
        //注册JDBC驱动
        Class.forName(JDBC_DRIVER);
        //建立连接
        Connection connection=DriverManager.getConnection(DB_URL,user,passwd);
        //创建执行SQL语句的 statement
        Statement statement=connection.createStatement();

        //使用statement执行select
        ResultSet resultSet=statement.executeQuery("SELECT * from student");
        while (resultSet.next()){
            System.out.println(resultSet.getString("name"));
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

    @Test
    public void  test() throws ClassNotFoundException, SQLException {
        String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
        String DB_URL="jdbc:mysql://192.168.186.131:3306/test?useSSL=false";
        String user="root";
        String passwd="123456";
        //注册JDBC驱动
        Class.forName(JDBC_DRIVER);
        //建立连接
        Connection connection=DriverManager.getConnection(DB_URL,user,passwd);
        //创建执行SQL语句的 statement
        Statement statement=connection.createStatement();


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
