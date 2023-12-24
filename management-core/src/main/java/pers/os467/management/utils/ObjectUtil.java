package pers.os467.management.utils;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

public class ObjectUtil {

    /**
     * 判断对象中属性值是否全为空
     *
     * @param object
     * @return
     */
    public static boolean checkObjFieldNull(Object object) {
        if (null == object) {
            return false;
        }

        try {
            for(Field field : object.getClass().getDeclaredFields()){
                field.setAccessible(true);
                if(StringUtils.isEmpty(field.get(object))){
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
