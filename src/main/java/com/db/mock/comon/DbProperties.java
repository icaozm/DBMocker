package com.db.mock.comon;

/**
 * @author: caozm
 * @create: 2022-06-18 15:43
 * @Version 1.0
 * @description:
 **/
public class DbProperties {
    /**
     * 数据库类型
     */
    private String dbType;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 数据库服务ip
     */
    private String ip;
    /**
     * 数据库服务端口
     */
    private int port;
    /**
     * 数据库名
     */
    private String dataBaseName;

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }
}