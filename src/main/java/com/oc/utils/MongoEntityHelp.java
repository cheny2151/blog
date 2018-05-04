package com.oc.utils;

import com.oc.entity.mongo.MongoBaseEntity;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;
import java.util.List;

/**
 * mongo实体工具类
 */
public class MongoEntityHelp {

    private MongoEntityHelp() {
    }

    private final static String[] DEFAULT_IGNORE_PROPERTIES = new String[]{"serialVersionUID"};

    /**
     * 获取更新mongo实体的update对象
     *
     * @param bean             更新实体
     * @param ignoreProperties 忽略的字段
     */
    public static <T extends MongoBaseEntity> Update update(T bean, String... ignoreProperties) {
        Update update = new Update();
        boolean hasIgnore = ignoreProperties != null && ignoreProperties.length > 0;
        Class clazz = bean.getClass();
        List<Field> fields = BeanUtils.getAllFields(clazz, Object.class);
        for (Field field : fields) {
            String name = field.getName();
            if (defaultIgnore(field, clazz) || (hasIgnore && ArrayUtils.contains(ignoreProperties, name))) {
                continue;
            }
            update.set(name, BeanUtils.readValue(bean, name));
        }
        return update;
    }

    private static boolean defaultIgnore(Field field, Class<?> clazz) {
        return ArrayUtils.contains(DEFAULT_IGNORE_PROPERTIES, field.getName())
                || field.getAnnotation(Transient.class) != null
                || BeanUtils.getReadMethod(clazz, field.getName()).getDeclaredAnnotation(Transient.class) != null;
    }


}