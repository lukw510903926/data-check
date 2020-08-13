package com.mockuai.data.check.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-03 16:53
 */
@Data
public class ColumnValue implements Serializable {
    private static final long serialVersionUID = 2573409139708694015L;

    /**
     * 列名
     */
    private String column;

    /**
     * 值
     */
    private String value;

    public static ColumnValue build(String column, String value) {

        ColumnValue columnValues = new ColumnValue();
        columnValues.setColumn(column);
        columnValues.setValue(value);
        return columnValues;
    }
}
