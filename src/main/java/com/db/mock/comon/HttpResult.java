package com.db.mock.comon;

import cn.hutool.http.HttpStatus;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author: caozm
 * @create: 2022-06-18 16:05
 * @Version 1.0
 * @description:
 **/
public class HttpResult {

    public static void success(HttpExchange httpExchange, byte[] message) {
        try {
            httpExchange.getResponseHeaders().add("Content-Type", "application/json");
            httpExchange.sendResponseHeaders(HttpStatus.HTTP_OK, 0);
            httpExchange.getResponseBody().write(message);
            httpExchange.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void success(HttpExchange httpExchange, String message) {
        success(httpExchange, message.getBytes(StandardCharsets.UTF_8));
    }

    public static void success(HttpExchange httpExchange) {
        success(httpExchange, "");
    }

    public static void failure(HttpExchange httpExchange, byte[] message) {
        try {
            httpExchange.getResponseHeaders().add("Content-Type", "application/json");
            httpExchange.sendResponseHeaders(HttpStatus.HTTP_INTERNAL_ERROR, 0);
            httpExchange.getResponseBody().write(message);
            httpExchange.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void failure(HttpExchange httpExchange, String message) {
        failure(httpExchange, message.getBytes(StandardCharsets.UTF_8));
    }

    public static void failure(HttpExchange httpExchange) {
        failure(httpExchange, "");
    }

}