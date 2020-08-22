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

        DifferencePropertyValue differencePropertyValue = new DifferencePropertyValue();
        differencePropertyValue.setProperty(property);
        differencePropertyValue.setOriginalValue(originalValue);
        differencePropertyValue.setNewDataValue(newDataValue);
        return differencePropertyValue;
    }

    @Override
    public String toString() {

        return JSON.toJSONString(this);
    }
}
