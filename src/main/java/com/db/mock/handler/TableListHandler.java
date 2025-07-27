package com.db.mock.handler;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.db.mock.comon.DbUtil;
import com.db.mock.comon.HttpResult;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import static com.db.mock.comon.DbUtil.properties;

/**
 * @author: caozm
 * @create: 2022-06-17 23:20
 * @Version 1.0
 * @description: 查询数据库表
 **/
public class TableListHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) {
        Connection connection = null;
        try {
            if (properties == null) {
                HttpResult.failure(httpExchange, "请先设置DB参数！");
            }
            String query = httpExchange.getRequestURI().getQuery();
            Map<String, String> querMap = HttpUtil.decodeParamMap(query, Charset.defaultCharset());
            if ("".equals(querMap.get("searchTableName"))) {
                HttpResult.failure(httpExchange, "输入查询参数！");
            }
            connection = DbUtil.getCon();
            DatabaseMetaData metaData = connection.getMetaData();

            //检索数据库中的列
            ResultSet set = metaData.getTables(properties.getDataBaseName(), null,
                    "%" + querMap.get("searchTableName") + "%", null);
            Map<String, String> tables = new HashMap<>(16);
            //打印列名称和大小
            int i = 1;
            while (set.next()) {
                tables.put(String.valueOf(i++), set.getString("Table_NAME"));
            }
            if (tables.size() > 10) {
                HttpResult.failure(httpExchange, "查询结果超过十条，请重新输入查询条件！");
            } else {
                HttpResult.success(httpExchange, JSONUtil.toJsonStr(tables));
            }
        } catch (Exception e) {
            e.printStackTrace();
            HttpResult.failure(httpExchange, e.getMessage());
        } finally {
            DbUtil.close(connection);
        }

    }
}