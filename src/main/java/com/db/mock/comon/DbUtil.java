package com.db.mock.comon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    public static volatile DbProperties properties = null;

    public static synchronized Connection getCon() throws SQLException, ClassNotFoundException {
        Connection connection;
        try {
            String dbDriver = null;
            if (Constant.MYSQL.equals(properties.getDbType())) {
                dbDriver = "com.mysql.cj.jdbc.Driver";
            } else if (Constant.ORACLE.equals(properties.getDbType())) {
                dbDriver = "oracle.jdbc.driver.OracleDriver";
            } else if (Constant.SQLSERVER.equals(properties.getDbType())) {
                dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
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
