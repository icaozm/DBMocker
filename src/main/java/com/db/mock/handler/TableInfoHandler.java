package com.db.mock.handler;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.db.mock.comon.DbUtil;
import com.db.mock.comon.HttpResult;
import com.db.mock.comon.MapperSqlType;
import com.db.mock.comon.bean.FieldBean;
import com.db.mock.comon.bean.TableBean;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.db.mock.comon.DbUtil.properties;

/**
 * @author: caozm
 * @create: 2022-06-18 23:34
 * @Version 1.0
 * @description: 查询表字段信息
 **/
public class TableInfoHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Connection conn = DbUtil.getConnection();
        try {
            String query = httpExchange.getRequestURI().getQuery();
            Map<String, String> queryMap = HttpUtil.decodeParamMap(query, Charset.defaultCharset());

            DatabaseMetaData dbmd = conn.getMetaData();
            TableBean tableBean = new TableBean();
            ResultSet set = dbmd.getTables(properties.getDataBaseName(), properties.getDataBaseName(), queryMap.get("tableName"), null);
            while (set.next()) {
                tableBean.setTableName(set.getString("TABLE_NAME"));
                tableBean.setTableDesc(set.getString("REMARKS"));
            }

            ResultSet primaryKeyResultSet = dbmd.getPrimaryKeys(properties.getDataBaseName(), properties.getDataBaseName(), queryMap.get("tableName"));
            List<String> keys = new ArrayList<>(8);
            while (primaryKeyResultSet.next()) {
                keys.add(primaryKeyResultSet.getString("COLUMN_NAME"));
            }
            tableBean.setKeys(keys);

            ResultSet rs = dbmd.getColumns(properties.getDataBaseName(), properties.getDataBaseName(), queryMap.get("tableName"), null);
            List<FieldBean> fieldBeanList = new ArrayList<>();
            while (rs.next()) {
                FieldBean fieldBean = new FieldBean();
                fieldBean.setFieldName(rs.getString("COLUMN_NAME"));
                fieldBean.setFieldType(MapperSqlType.map(rs.getInt("DATA_TYPE")));
                fieldBean.setFieldLen(rs.getInt("COLUMN_SIZE"));
                fieldBean.setDigits(rs.getInt("DECIMAL_DIGITS"));
                fieldBean.setFieldTypeName(rs.getString("TYPE_NAME"));
                String tail = "";
                if (fieldBean.getFieldLen() != 0) {
                    tail += "(" + fieldBean.getFieldLen();
                }
                if (fieldBean.getDigits() != 0) {
                    tail += "," + fieldBean.getDigits() + ")";
                } else {
                    tail += ")";
                }
                fieldBean.setFieldTypeNameDesc(fieldBean.getFieldTypeName() + tail);
                fieldBean.setNullable(rs.getInt("NULLABLE") == 0 ? false : true);
                System.out.println("nullable" + rs.getInt("NULLABLE"));
                fieldBean.setDesc(rs.getString("REMARKS"));
                fieldBean.setDefaultValue(rs.getString("COLUMN_DEF"));
                fieldBean.setIndex(rs.getInt("ORDINAL_POSITION"));
                fieldBeanList.add(fieldBean);
            }
            tableBean.setFieldBeanList(fieldBeanList);
            tableBean.setFieldTotal(fieldBeanList.size());
            System.out.println(JSONUtil.toJsonStr(tableBean));
            HttpResult.success(httpExchange, JSONUtil.toJsonStr(tableBean));
        } catch (Exception e) {

        } finally {
            DbUtil.close(conn);
        }


    }
}