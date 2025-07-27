package com.db.mock.comon.bean;

/**
 * @author: caozm
 * @create: 2022-06-18 10:32
 * @Version 1.0
 * @description: 表字段信息
 **/
public class FieldBeanInfo {
    private String fieldName;
    private String ruleType;
    private String ruleExpr;
    private String defaultValue;


    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleExpr() {
        return ruleExpr;
    }

    public void setRuleExpr(String ruleExpr) {
        this.ruleExpr = ruleExpr;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}