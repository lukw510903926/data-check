package com.mockuai.data.check.dto;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;
import java.util.Optional;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-03 16:24
 */
@Data
public class DataStoreMapping {

    /**
     * 源数据名称
     */
    private String sourceStore;

    /**
     * 要比对的数据名称
     */
    private String targetStore;

    /**
     * 属性映射
     */
    private Map<String, String> propertyMapping;

    /**
     * 添加属性映射
     *
     * @param sourceProperty
     * @param targetProperty
     */
    public DataStoreMapping addPropertyMapping(String sourceProperty, String targetProperty) {

        propertyMapping = Optional.ofNullable(propertyMapping).orElse(Maps.newHashMap());
        propertyMapping.put(sourceProperty, targetProperty);
        propertyMapping.put(targetProperty, sourceProperty);
        return this;
    }
}
