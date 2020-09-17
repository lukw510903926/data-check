package com.mockuai.data.check.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-04 13:32
 */
@Data
public class DifferencePropertyValue implements CheckResult, Serializable {

    private static final long serialVersionUID = -3939409235144343316L;
    /**
     * 原始值
     */
    private String sourceValue;

    /**
     * 新数据源的值
     */
    private String targetDataValue;

    private String targetProperty;

    public static DifferencePropertyValue build(String sourceValue, String targetValue, String targetProperty) {

        DifferencePropertyValue differencePropertyValue = new DifferencePropertyValue();
        differencePropertyValue.setTargetProperty(targetProperty);
        differencePropertyValue.setSourceValue(sourceValue);
        differencePropertyValue.setTargetDataValue(targetValue);
        return differencePropertyValue;
    }

    @Override
    public String toString() {

        return JSON.toJSONString(this);
    }
}
