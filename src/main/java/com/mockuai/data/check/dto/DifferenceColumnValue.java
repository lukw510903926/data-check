package com.mockuai.data.check.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-04 13:32
 */
@Data
public class DifferenceColumnValue {

    private Object originalValue;

    private Object newDataValue;

    private String column;

    public static DifferenceColumnValue build(Object originalValue, Object newDataValue, String column) {

        DifferenceColumnValue differenceColumnValue = new DifferenceColumnValue();
        differenceColumnValue.setColumn(column);
        differenceColumnValue.setOriginalValue(originalValue);
        differenceColumnValue.setNewDataValue(newDataValue);
        return differenceColumnValue;
    }

    @Override
    public String toString() {

        return JSON.toJSONString(this);
    }
}
