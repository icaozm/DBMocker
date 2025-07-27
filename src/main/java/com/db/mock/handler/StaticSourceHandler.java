package com.db.mock.handler;

import cn.hutool.core.io.IoUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: caozm
 * @create: 2022-06-17 22:23
 * @Version 1.0
 * @description: 处理所有静态资源请求
 **/
public class StaticSourceHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String filePath = httpExchange.getRequestURI().toString();
        if (File.separator.equals(filePath)) {
            filePath= File.separator + "index.html";
        }
        // 获取请求头
        InputStream in = this.getClass().getResourceAsStream(filePath);
        byte[] bytes = IoUtil.readBytes(in);
        httpExchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        httpExchange.sendResponseHeaders(200, bytes.length);
        httpExchange.getResponseBody().write(bytes);
        httpExchange.close();
    }
}