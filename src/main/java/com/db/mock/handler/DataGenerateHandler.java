package com.db.mock.handler;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONUtil;
import com.db.mock.comon.bean.FieldBeanInfo;
import com.db.mock.comon.bean.GenerateInfoBean;
import com.db.mock.handler.rule.RuleContext;
import com.db.mock.handler.rule.RuleTemplateEngine;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataGenerateHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String read = IoUtil.read(exchange.getRequestBody(), Charset.defaultCharset());
        GenerateInfoBean generateInfoBean = JSONUtil.toBean(read, GenerateInfoBean.class);

        String tableName = generateInfoBean.getTableName();
        int rowCount = generateInfoBean.getRowCount();
        List<FieldBeanInfo> fields = generateInfoBean.getFields();

        // 准备字段名、值模板
        List<String> columnNames = new ArrayList<>();
        for (FieldBeanInfo field : fields) {
            columnNames.add(field.getFieldName());
        }


        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3400/szhb?useSSL=false&serverTimezone=UTC", "root", "124523")) {
            conn.setAutoCommit(false);

            String sql = buildInsertSQL(tableName, columnNames);
            System.out.println(sql);
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (int i = 0; i < rowCount; i++) {
                    for (int j = 0; j < fields.size(); j++) {
                        String value = generateValue(fields.get(j));
                        ps.setString(j + 1, value);
                    }
                    ps.addBatch();
                }
                ps.executeBatch();
            }

            conn.commit();

            // 返回响应
            String response = "{\"code\":200,\"msg\":\"生成成功\"}";
            byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, bytes.length);
            exchange.getResponseBody().write(bytes);
            exchange.getResponseBody().close();

        } catch (Exception e) {
            e.printStackTrace();
            String response = "{\"code\":500,\"msg\":\"生成失败: " + e.getMessage() + "\"}";
            byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(500, bytes.length);
            exchange.getResponseBody().write(bytes);
            exchange.getResponseBody().close();
        }


    }

    private String buildInsertSQL(String tableName, List<String> columns) {
        String colPart = String.join(", ", columns);
        String placeholders = String.join(", ", Collections.nCopies(columns.size(), "?"));
        return "INSERT INTO " + tableName + " (" + colPart + ") VALUES (" + placeholders + ")";
    }

    private String generateValue(FieldBeanInfo field) {
        if (field.getDefaultValue() != null && !field.getDefaultValue().isEmpty()) {
            return field.getDefaultValue();
        }

        String type = field.getRuleType();
        String expr = field.getRuleExpr();

        if (type == null || expr == null) return "";

        switch (type) {
            case "name":
                String[] surnames = {"张", "王", "李", "赵", "刘", "陈", "杨", "黄", "周", "吴"};
                String[] names = {"伟", "芳", "娜", "敏", "静", "强", "磊", "军", "洋", "勇", "艳", "杰", "娟", "涛"};
                String surname = surnames[(int) (Math.random() * surnames.length)];
                String givenName = names[(int) (Math.random() * names.length)] + (Math.random() < 0.5 ? names[(int) (Math.random() * names.length)] : "");
                return surname + givenName;
            case "email":
                String user = String.valueOf((int) (Math.random() * 100000));
                String[] domains = {"163.com", "qq.com", "gmail.com"};
                String domain = domains[(int) (Math.random() * domains.length)];
                return user + "@" + domain;
            case "phone":
                return "1" + (int) (Math.random() * 10) + (100000000 + (int) (Math.random() * 899999999));
            case "enum":
                try {
                    String[] options = expr.split(",");
                    return options[(int) (Math.random() * options.length)].trim();
                } catch (Exception e) {
                    return "未知";
                }
            case "range":
                try {
                    String[] parts = expr.split("-");
                    double min = Double.parseDouble(parts[0].trim());
                    double max = Double.parseDouble(parts[1].trim());
                    double value = min + Math.random() * (max - min);
                    return String.format("%.4f", value);
                } catch (Exception e) {
                    return "0";
                }
            case "date":
                try {
                    String[] dateParts = expr.split("~");
                    java.sql.Date start = java.sql.Date.valueOf(dateParts[0].trim());
                    java.sql.Date end = java.sql.Date.valueOf(dateParts[1].trim());
                    long randomMillis = start.getTime() + (long) (Math.random() * (end.getTime() - start.getTime()));
                    return new java.sql.Date(randomMillis).toString();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return "1970-01-01";
                }
            case "regex":
                RuleTemplateEngine engine = new RuleTemplateEngine();
                RuleContext ctx = new RuleContext("order");
                return engine.generate(expr, ctx);
            default:
                return "未定义";
        }
    }
}
