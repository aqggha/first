package com.zhiyou100.util;

import com.google.gson.Gson;
import com.zhiyou100.model.House;
import com.zhiyou100.model.Renter;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanUtil {
    public static <T>  T parseFromRequest(HttpServletRequest request,Class<T> clazz) throws IllegalAccessException, InstantiationException {
        Field[] fields = clazz.getDeclaredFields();
        T instance = clazz.newInstance();
        for (Field field:
             fields) {
            field.setAccessible(true);
            if(request.getParameter(field.getName())==null){
                continue;
            }else{
                //如果请求参数不为空，则利用ConvertUtils.convert(String value,class type)方法，此方法的作用是将字符串 转换成指定类型
                Object convertedValue = ConvertUtils.convert(request.getParameter(field.getName()), field.getType());
                field.set(instance,convertedValue);
            }
            //field.set(instance,request.getParameter(field.getName()));
        }
        return instance;
    }

    public static<T> T parseFromRequestMap(HttpServletRequest request,Class<T> clazz) throws IllegalAccessException, InstantiationException {
        Map parameterMap = request.getParameterMap();
        HashMap<String, String> map = new HashMap<>();
        Set<String> keySet = parameterMap.keySet();
        for (String key :
                keySet) {
            String[] values = (String[]) parameterMap.get(key);
            map.put(key,values[0]);
        }
        Gson gson = new Gson();
        T instance = gson.fromJson(gson.toJson(map), clazz);
        return instance;
    }

    //给定一个对象 得到该对象所有字段的值 放入数组
    public static Object[] getFieldsValue(House house) throws IllegalAccessException {
        Field[] fields = house.getClass().getDeclaredFields();
        ArrayList list = new ArrayList();

        for (Field field:
             fields) {
            field.setAccessible(true);
            Object value = field.get(house);
            list.add(value);
        }
        return list.toArray();
    }
}
