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
public class PropertyValue implements Serializable {
    private static final long serialVersionUID = 2573409139708694015L;

    /**
     * 列名
     */
    private String property;

    /**
     * 值
     */
    private String value;

    public static PropertyValue build(String property, String value) {

        PropertyValue propertyValue = new PropertyValue();
        propertyValue.setProperty(property);
        propertyValue.setValue(value);
        return propertyValue;
    }
}
