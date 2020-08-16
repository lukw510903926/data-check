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


    private String tableName;

    /**
     * 一行数据的唯一键
     */
    private Map<String, String> rowKeyMap;

    private Map<String, String> columnValueMap;

    /**
     * 事件的时间戳
     */
    private Long occourTime;

    public static RowValue build(List<ColumnValue> columnValues, String database, String tableName) {

        RowValue rowValue = new RowValue();
        rowValue.setTableName(tableName);
        rowValue.setDatabase(database);
        Map<String, String> params = Maps.newHashMapWithExpectedSize(columnValues.size());
        columnValues.forEach(columnValue -> params.put(columnValue.getColumn().toLowerCase(), columnValue.getValue()));
        rowValue.setColumnValueMap(params);
        TableInfo tableInfo = TableInfo.getTableInfo(tableName);
        List<String> uniqueColumn = tableInfo.getUniqueColumn();
        Map<String, String> rowKeyMap = new TreeMap<>();
        for (String column : uniqueColumn) {
            rowKeyMap.put(column, params.get(column));
        }
        rowValue.setRowKeyMap(rowKeyMap);
        return rowValue;
    }

    public String getColumnValue(String column) {
        return Optional.ofNullable(columnValueMap).map(map -> map.get(column)).orElse(null);
    }

    @Override
    public String toString() {

        return JSON.toJSONString(this);
    }
}
