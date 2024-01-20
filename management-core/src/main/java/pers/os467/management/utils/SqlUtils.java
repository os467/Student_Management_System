package pers.os467.management.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

public class SqlUtils {

    public static <E>List<Integer> getFieldList(List<E> array,String fieldName){
        List<Integer> fieldList = array.stream().map((item) -> {
            Integer id = null;
            try {
                Method method = item.getClass().getMethod(
                        "get" + fieldName.substring(0, 1).toUpperCase()
                                + fieldName.substring(1));
                 id = (Integer) method.invoke(item);
            }catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return id;
        }).collect(Collectors.toList());

        return fieldList;
    }

    public static String getIdListString(List<Integer> ids) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer id : ids) {
            stringBuilder.append(id);
            stringBuilder.append(",");
        }
        //删除最后一个逗号
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

}
