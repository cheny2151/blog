package com.oc.utils;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 读取系统设置
 */
public class SystemUtils {

    private final static Logger LOGGER = Logger.getLogger(SystemUtils.class);

    private static HashMap<String, Object> systemValue;

    static {
        systemValue = new HashMap<>();
        try {
            JsonNode jsonNode = JsonUtils.readTree(new ClassPathResource("/imageType.json").getFile());
            Iterator<String> stringIterator = jsonNode.fieldNames();
            JsonNode node;
            String nodeName;
            Object nodeValue;
            while (stringIterator.hasNext()) {
                nodeName = stringIterator.next();
                node = jsonNode.get(nodeName);
                nodeValue = readValue(node);
                systemValue.put(nodeName, nodeValue);
            }
        } catch (IOException e) {
            LOGGER.error("文件类型工具类初始化失败", e);
        }
    }

    private static Object readValue(JsonNode node) {
        Object value;
        if (node.isArray()) {
            value = asArray(node);
        } else if (node.isInt()) {
            value = node.asInt();
        } else if (node.isBoolean()) {
            value = node.asBoolean();
        } else if (node.isDouble()) {
            value = node.asDouble();
        } else {
            value = node.asText();
        }
        return value;
    }

    private static Object[] asArray(JsonNode jsonNode) {
        Iterator<JsonNode> iterator = jsonNode.iterator();
        Object[] objects = new Object[jsonNode.size()];
        int i = 0;
        while (iterator.hasNext()) {
            objects[0] = iterator.next();
            i++;
        }
        return objects;
    }

    public static String getValue(String name) {
        return systemValue.get(name).toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getValue(String name, Class<T> tClass) {
        return (T) systemValue.get(name);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getValue(String name, Class<T> tClass, T defaultValue) {
        T value = (T) systemValue.get(name);
        return value == null ? defaultValue : value;
    }

}
