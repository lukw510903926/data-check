package com.mockuai.data.check.dto;

import lombok.Data;

import java.util.List;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-16 15:28
 */
@Data
public class DataStoreInfo {

    /**
     * 表名
     */
    private String dataStore;

    /**
     * 唯一属性列表
     */
    private List<String> uniqueProperty;

    /**
     * 比对属性列表
     */
    private List<String> propertyList;
}
