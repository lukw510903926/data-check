package com.mockuai.data.check.dto;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-03 16:54
 */
@Data
public class RowValue implements Serializable {

    private static final long serialVersionUID = -3577118524411380361L;

    /**
     * 数据库
     */
    private String database;


    private String dataStore;

    /**
     * 一行数据的唯一键
     */
    private Map<String, String> rowKeyMap;

    private Map<String, String> propertyValueMap;

    /**
     * 事件的时间戳
     */
    private Long occourTime;

    public static RowValue build(List<PropertyValue> propertyValues, String database, String dataStore) {

        RowValue rowValue = new RowValue();
        rowValue.setDataStore(dataStore);
        rowValue.setDatabase(database);
        Map<String, String> params = Maps.newHashMapWithExpectedSize(propertyValues.size());
        propertyValues.forEach(propertyValue -> params.put(propertyValue.getProperty().toLowerCase(), propertyValue.getValue()));
        rowValue.setPropertyValueMap(params);
        DataStoreInfo dataStoreInfo = DataStoreMappingUtils.getDataStoreInfo(dataStore);
        List<String> uniqueProperty = dataStoreInfo.getUniqueProperty();
        Map<String, String> rowKeyMap = new TreeMap<>();
        for (String property : uniqueProperty) {
            rowKeyMap.put(property, params.get(property));
        }
        rowValue.setRowKeyMap(rowKeyMap);
        return rowValue;
    }

    public String getPropertyValue(String property) {
        return Optional.ofNullable(propertyValueMap).map(map -> map.get(property)).orElse(null);
    }

    @Override
    public String toString() {

        return JSON.toJSONString(this);
    }
}
