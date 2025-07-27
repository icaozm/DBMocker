package com.db.mock.handler;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONUtil;
import com.db.mock.comon.DbProperties;
import com.db.mock.comon.DbUtil;
import com.db.mock.comon.HttpResult;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author: caozm
 * @create: 2022-06-17 22:21
 * @Version 1.0
 * @description: 处理初始化数据库参数
 **/
public class DbInitHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) {
        try {
            String read = IoUtil.read(httpExchange.getRequestBody(), Charset.defaultCharset());
            DbUtil.properties = JSONUtil.toBean(read, DbProperties.class);

            String response = "{\"code\":200,\"msg\":\"ok\"}";
            byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

            httpExchange.getResponseHeaders().set("Content-Type", "application/json");
            httpExchange.sendResponseHeaders(200, bytes.length);
            httpExchange.getResponseBody().write(bytes);
            httpExchange.getResponseBody().close();
        } catch (Exception e) {
            e.printStackTrace();
            HttpResult.failure(httpExchange);
        }
    }

}
