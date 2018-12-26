package com.ligoo.framework.bean;


import com.ligoo.framework.util.CastUtil;
import com.ligoo.framework.util.CollectionUtil;
import com.ligoo.framework.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Administrator
 * @Date: 2018/12/14 15:38:45
 * @Description:请求参数包装类
 */
public class Param {
    private List<FormParam> formParamList;

    public Param(List<FormParam> formParamList) {
        this.formParamList = formParamList;
    }


    /**
     * description: 获取请求参数映射
     * author: Administrator
     * date: 2018/12/24 15:02
     *
     * @param: 
     * @return: 
     */
    public Map<String, Object> getFieldMap(){
        Map<String, Object> fieldMap = new HashMap<>();
        if(CollectionUtil.isNotEmpty(formParamList)){
            for (FormParam formParam: formParamList){
                String fieldName = formParam.getFieldName();
                Object fieldValue = formParam.getFieldValue();
                if(fieldMap.containsKey(fieldName)){
                    fieldValue = fieldMap.get(fieldName) + StringUtil.SEPARATOR + fieldValue;
                }
                fieldMap.put(fieldName, fieldValue);
            }
        }
        return fieldMap;
    }

    /**
     * description: 根据参数名获取String型参数值
     * author: Administrator
     * date: 2018/12/14 15:40
     *
     * @param:
     * @return:
     */
    public String getString(String name){
        return CastUtil.castString(getFieldMap().get(name));
    }
    /**
     * description: 根据参数名获取Long型参数值
     * author: Administrator
     * date: 2018/12/14 15:40
     *
     * @param:
     * @return:
     */
    public long getLong(String name){
        return CastUtil.castLong(getFieldMap().get(name));
    }
    /**
     * description: 根据参数名获取Int型参数值
     * author: Administrator
     * date: 2018/12/14 15:40
     *
     * @param:
     * @return:
     */
    public int getInt(String name){
        return CastUtil.castInt(getFieldMap().get(name));
    }
    /**
     * description: 根据参数名获取Double型参数值
     * author: Administrator
     * date: 2018/12/14 15:40
     *
     * @param:
     * @return:
     */
    public double getDouble(String name){
        return CastUtil.castDouble(getFieldMap().get(name));
    }
    /**
     * description: 根据参数名获取Boolean型参数值
     * author: Administrator
     * date: 2018/12/14 15:40
     *
     * @param:
     * @return:
     */
    public boolean getBoolean(String name){
        return CastUtil.castBoolean(getFieldMap().get(name));
    }


    /**
     * description: 判断参数集合是否为空
     * author: Administrator
     * date: 2018/12/24 15:05
     *
     * @param:
     * @return:
     */
    public boolean isEmpty(){
        return CollectionUtil.isEmpty(formParamList);
    }
}
