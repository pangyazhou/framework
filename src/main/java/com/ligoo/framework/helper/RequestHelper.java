package com.ligoo.framework.helper;

import com.ligoo.framework.bean.FormParam;
import com.ligoo.framework.bean.Param;
import com.ligoo.framework.util.ArrayUtil;
import com.ligoo.framework.util.CodecUtil;
import com.ligoo.framework.util.StreamUtil;
import com.ligoo.framework.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @Author: Administrator
 * @Date: 2018/12/24 15:21:24
 * @Description: 请求助手类
 */
public class RequestHelper {
    /**
     * description: 创建请求对象
     * author: Administrator
     * date: 2018/12/24 15:22
     *
     * @param: 
     * @return: 
     */
    public static Param createParam(HttpServletRequest request) throws IOException {
        List<FormParam> formParamList = new ArrayList<>();
        formParamList.addAll(parseParameterNames(request));
        formParamList.addAll(parseInputStream(request));
        return new Param(formParamList);
    }
    
    /**
     * description: 解析请求参数
     * author: Administrator
     * date: 2018/12/24 15:23
     *
     * @param: 
     * @return: 
     */
    private static List<FormParam> parseParameterNames(HttpServletRequest request){
        List<FormParam> formParamList = new ArrayList<FormParam>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String fieldName = paramNames.nextElement();
            String[] fieldValues = request.getParameterValues(fieldName);
            if (ArrayUtil.isNotEmpty(fieldValues)) {
                Object fieldValue;
                if (fieldValues.length == 1) {
                    fieldValue = fieldValues[0];
                } else {
                    StringBuilder sb = new StringBuilder("");
                    for (int i = 0; i < fieldValues.length; i++) {
                        sb.append(fieldValues[i]);
                        if (i != fieldValues.length - 1) {
                            sb.append(StringUtil.SEPARATOR);
                        }
                    }
                    fieldValue = sb.toString();
                }
                formParamList.add(new FormParam(fieldName, fieldValue));
            }
        }
        return formParamList;
    }

    /**
     * description: 解析表单参数
     * author: Administrator
     * date: 2018/12/24 15:24
     *
     * @param:
     * @return:
     */
    public static List<FormParam> parseInputStream(HttpServletRequest request) throws IOException {
        List<FormParam> formParamList = new ArrayList<FormParam>();
        String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
        if (StringUtil.isNotEmpty(body)) {
            String[] kvs = StringUtil.splitString(body, "&");
            if (ArrayUtil.isNotEmpty(kvs)) {
                for (String kv : kvs) {
                    String[] array = StringUtil.splitString(kv, "=");
                    if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                        String fieldName = array[0];
                        String fieldValue = array[1];
                        formParamList.add(new FormParam(fieldName, fieldValue));
                    }
                }
            }
        }
        return formParamList;
    }
}
