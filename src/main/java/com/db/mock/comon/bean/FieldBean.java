package com.db.mock.comon.bean;

/**
 * @author: caozm
 * @create: 2022-06-18 10:32
 * @Version 1.0
 * @description: 表字段信息
 **/
public class FieldBean {
    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 字段类型
     */
    private Class<?> fieldType;
    /**
     * 字段类型名
     */
    private String fieldTypeName;
    /**
     * 字段类型描述
     */
    private String fieldTypeNameDesc;
    /**
     * 字段长度
     */
    private int fieldLen;
    /**
     * 小数位
     */
    private int digits;
    /**
     * 是否允许空
     */
    private boolean isNullable;
    /**
     * 字段描述
     */
    private String desc;
    /**
     * 默认值
     */
    private Object defaultValue;
    /**
     * 字段排序
     */
    private int index;


    public String getFieldTypeNameDesc() {
        return fieldTypeNameDesc;
    }

    public void setFieldTypeNameDesc(String fieldTypeNameDesc) {
        this.fieldTypeNameDesc = fieldTypeNameDesc;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Class<?> getFieldType() {
        return fieldType;
    }

    public void setFieldType(Class<?> fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldTypeName() {
        return fieldTypeName;
    }

    public void setFieldTypeName(String fieldTypeName) {
        this.fieldTypeName = fieldTypeName;
    }

    public int getFieldLen() {
        return fieldLen;
    }

    public void setFieldLen(int fieldLen) {
        this.fieldLen = fieldLen;
    }

    public int getDigits() {
        return digits;
    }

    public void setDigits(int digits) {
        this.digits = digits;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "FieldBean{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldType=" + fieldType +
                ", fieldTypeName='" + fieldTypeName + '\'' +
                ", fieldLen=" + fieldLen +
                ", digits=" + digits +
                ", isNullable=" + isNullable +
                ", desc='" + desc + '\'' +
                ", defaultValue=" + defaultValue +
                ", index=" + index +
                '}';
    }
}