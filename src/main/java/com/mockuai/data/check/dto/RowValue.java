package com.mockuai.data.check.dto;

import com.google.common.collect.Maps;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-03 16:54
 */
@Data
public class RowValue implements Serializable {

    private static final long serialVersionUID = -3577118524411380361L;

    private String tableName;

    private Map<String, String> columnValueMap;

    /**
     * 事件的时间戳
     */
    private Long occourTime;

    public static RowValue build(List<ColumnValue> columnValues, String tableName) {

        RowValue rowValue = new RowValue();
        rowValue.setTableName(tableName);
        Map<String, String> params = Maps.newHashMapWithExpectedSize(columnValues.size());
        columnValues.forEach(columnValue -> params.put(columnValue.getColumn().toLowerCase(), columnValue.getValue()));
        rowValue.setColumnValueMap(params);
        return rowValue;
    }

    public String getColumnValue(String column) {
        return Optional.ofNullable(columnValueMap).map(map -> map.get(column)).orElse(null);
    }

}
