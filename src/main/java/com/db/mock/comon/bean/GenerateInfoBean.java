package com.db.mock.comon.bean;

import java.util.ArrayList;
import java.util.List;

public class GenerateInfoBean {

    private Integer rowCount;
    private String tableName;
    private List<FieldBeanInfo> fields;

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<FieldBeanInfo> getFields() {
        if (fields == null) {
            fields = new ArrayList<>();
        }
        return fields;
    }

    public void setFields(List<FieldBeanInfo> fields) {
        this.fields = fields;
    }
}
