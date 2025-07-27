package com.db.mock.comon.bean;

import java.util.List;

/**
 * @author: caozm
 * @create: 2022-06-18 13:17
 * @Version 1.0
 * @description: 表规则
 **/
public class TableBean {

    /**
     * 表名称
     */
    private String tableName;
    /**
     * 表描述
     */
    private String tableDesc;
    /**
     * 表字段
     */
    private List<FieldBean> fieldBeanList;
    /**
     * 主键列表
     */
    private List<String> keys;
    /**
     * 字段总数
     */
    private int fieldTotal;


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableDesc() {
        return tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }

    public List<FieldBean> getFieldBeanList() {
        return fieldBeanList;
    }

    public void setFieldBeanList(List<FieldBean> fieldBeanList) {
        this.fieldBeanList = fieldBeanList;
    }

    public int getFieldTotal() {
        return fieldTotal;
    }

    public void setFieldTotal(int fieldTotal) {
        this.fieldTotal = fieldTotal;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    @Override
    public String toString() {
        return "TableBean{" +
                "tableName='" + tableName + '\'' +
                ", tableDesc='" + tableDesc + '\'' +
                ", fieldBeanList=" + fieldBeanList +
                ", keys=" + keys +
                ", fieldTotal=" + fieldTotal +
                '}';
    }
}