package com.db.mock.comon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbUtil {

    public static volatile DbProperties properties = null;

    public static synchronized Connection getCon() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            String dbDriver = null;
            if (properties.getDbType().equals(Constant.MYSQL)) {
                dbDriver = "com.mysql.cj.jdbc.Driver";
                //            } else if (dbType.equals(Constant.ORACLE)) {
                //                dbDriver ="oracle.jdbc.driver.OracleDriver";
                //            } else if (dbType.equals(Constant.SQLSERVER)) {
                //                dbDriver ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
            }
            //步骤1：加载驱动(mysql)
            Class.forName(dbDriver);

            //步骤2：得到连接
            String connectionURL = "jdbc:mysql://" + properties.getIp() + ":" + properties.getPort() + "/" + properties.getDataBaseName() +
                    "?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
            connection = DriverManager.getConnection(connectionURL, properties.getUserName(), properties.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return connection;
    }

    /**
     * 获取数据库链接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            String dbType = "MYSQL";
            String userName = "root";
            String password = "124523";
            String ip = "localhost";
            String port = "3400";
            String schame = "szhb";

            String dbDriver = null;
            if (dbType.equals(Constant.MYSQL)) {
                dbDriver = "com.mysql.cj.jdbc.Driver";
//            } else if (dbType.equals(Constant.ORACLE)) {
//                dbDriver ="oracle.jdbc.driver.OracleDriver";
//            } else if (dbType.equals(Constant.SQLSERVER)) {
//                dbDriver ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
            }
            //步骤1：加载驱动(mysql)
            Class.forName(dbDriver);

            //步骤2：得到连接
            String connectionURL = "jdbc:mysql://" + ip + ":" + port + "/" + schame +
                    "?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true" +
                    "&failOverReadOnly=false&serverTimezone=GMT&rewriteBatchedStatements=true&useInformationSchema=true";
            connection = DriverManager.getConnection(connectionURL, userName, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Connection conn, PreparedStatement ps) {
        try {
            conn.close();
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
