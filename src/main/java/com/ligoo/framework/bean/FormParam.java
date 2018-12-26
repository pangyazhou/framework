package com.ligoo.framework.bean;

/**
 * @Author: Administrator
 * @Date: 2018/12/24 14:57:30
 * @Description: 表单参数对象
 */
public class FormParam {
    private String fieldName;
    private Object fieldValue;

    public FormParam(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
