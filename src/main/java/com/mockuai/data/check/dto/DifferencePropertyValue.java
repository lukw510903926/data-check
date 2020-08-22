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
public class DifferencePropertyValue {

    private Object originalValue;

    private Object newDataValue;

    private String property;

    public static DifferencePropertyValue build(Object originalValue, Object newDataValue, String property) {

        DifferencePropertyValue differenceColumnValue = new DifferencePropertyValue();
        differenceColumnValue.setProperty(property);
        differenceColumnValue.setOriginalValue(originalValue);
        differenceColumnValue.setNewDataValue(newDataValue);
        return differenceColumnValue;
    }

    @Override
    public String toString() {

        return JSON.toJSONString(this);
    }
}
