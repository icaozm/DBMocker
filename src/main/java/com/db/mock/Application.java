package com.db.mock;

import com.db.mock.handler.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author: caozm
 * @create: 2022-06-17 22:43
 * @Version 1.0
 * @description: DBMock 服务调度入口
 **/
public class Application {
    public static void main(String[] args) throws IOException {
        // 创建 http 服务器, 绑定本地 80 端口
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(9527), 0);

        // 处理静态资源
        httpServer.createContext("/", new StaticSourceHandler());
        // 初始化数据库信息
        httpServer.createContext("/init", new DbInitHandler());
        // 查询数据库表
        httpServer.createContext("/tableList", new TableListHandler());
        // 查询数据库字段详情
        httpServer.createContext("/tableInfo", new TableInfoHandler());
        // 数据生成
        httpServer.createContext("/generate", new DataGenerateHandler());

        // 启动服务
        httpServer.start();
        System.out.println("server started");
    }
}
